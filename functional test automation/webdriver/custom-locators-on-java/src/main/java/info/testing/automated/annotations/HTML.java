package info.testing.automated.annotations;

import info.testing.automated.enums.SearchBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Serhii Kuts
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface HTML {
    SearchBy searchBy();

    String value();
}
