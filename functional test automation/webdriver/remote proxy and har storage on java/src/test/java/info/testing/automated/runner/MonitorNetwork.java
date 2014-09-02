package info.testing.automated.runner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Serhii Kuts
 * Date: 8/31/2014
 * Time: 5:08 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface MonitorNetwork {
    boolean captureHeaders() default true;

    boolean captureContent() default true;

    boolean captureBinaryContent() default false;

    boolean enabled() default true;
}
