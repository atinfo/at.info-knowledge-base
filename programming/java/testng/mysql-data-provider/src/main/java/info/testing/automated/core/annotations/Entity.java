package info.testing.automated.core.annotations;

import info.testing.automated.core.enums.Schema;

import java.lang.annotation.*;

/**
 * Author: Sergey Kuts
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Repeatable(Entities.class)
public @interface Entity {
    Class entity();

    Schema schema();

    int invocationCount() default 0;

    long[] ids() default {};
}
