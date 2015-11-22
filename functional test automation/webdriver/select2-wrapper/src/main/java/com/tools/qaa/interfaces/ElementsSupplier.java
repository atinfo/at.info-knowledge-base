package com.tools.qaa.interfaces;

/**
 * Author: Sergey Korol.
 */

import com.tools.qaa.annotations.HTML;
import org.openqa.selenium.WebDriver;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Stream;

import static com.codepoetics.protonpack.StreamUtils.*;
import static org.springframework.util.ReflectionUtils.*;
import static org.apache.commons.lang3.ArrayUtils.*;
import static org.apache.commons.lang3.ClassUtils.*;

public interface ElementsSupplier {

	default <T> void initElements(final T targetPage) {
		final Stream<Class> pageChain = Stream.iterate(targetPage.getClass(), Class::getSuperclass);
		takeWhile(pageChain, pageObject -> !pageObject.equals(Object.class))
				.flatMap(pageObject -> Stream.of(pageObject.getDeclaredFields()))
				.filter(field -> field.isAnnotationPresent(HTML.class))
				.forEach(field -> initElement(targetPage, field, field.getAnnotation(HTML.class)));
	}

	default <T> void initElement(final T page, final Field field, final Annotation annotation) {
		getSupportedDrivers()
				.map(driver -> createInstance(field.getType(), add(getValues(annotation), 0, driver)))
				.filter(Optional::isPresent)
				.findAny()
				.ifPresent(value -> {
					makeAccessible(field);
					setField(field, page, value.get());
				});
	}

	@SuppressWarnings("unchecked")
	default Optional<?> createInstance(final Class<?> classToInit, final Object... args) {
		try {
			return Optional.of(classToInit.getConstructor(Stream.of(args)
					.map(arg -> convertType(arg.getClass(), WebDriver.class))
					.toArray(Class<?>[]::new))
					.newInstance(args));
		} catch (Exception ignored) {
			return Optional.empty();
		}
	}

	default Object[] getValues(final Annotation annotation) {
		try {
			return Stream.of(annotation.annotationType().getDeclaredMethods())
					.sorted(methodsComparator())
					.map(method -> invokeMethod(method, annotation))
					.toArray();
		} catch (Exception ignored) {
			return new Object[0];
		}
	}

	default Comparator<Method> methodsComparator() {
		return (m1, m2) -> m1.getName().compareTo(m2.getName());
	}

	default Class<?> convertType(final Class<?> fieldType, final Class<?>... types) {
		if (isPrimitiveWrapper(fieldType)) {
			return wrapperToPrimitive(fieldType);
		}

		return Stream.of(types)
				.filter(type -> type.isAssignableFrom(fieldType) && !fieldType.equals(Object.class))
				.findAny()
				.orElse(fieldType);
	}

	Stream<?> getSupportedDrivers();
}
