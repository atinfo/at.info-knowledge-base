package com.tools.qaa.annotations;

/**
 * Author: Sergey Korol.
 */

import com.tools.qaa.elements.HTMLElement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface HTML {
	HTMLElement.SearchBy searchBy();

	String value();
}