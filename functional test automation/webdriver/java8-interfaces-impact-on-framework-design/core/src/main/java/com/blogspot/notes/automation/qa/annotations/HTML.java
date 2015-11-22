package com.blogspot.notes.automation.qa.annotations;

import com.blogspot.notes.automation.qa.elements.HTMLElement.SearchBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface HTML {
    SearchBy searchBy();

    String value();
}
