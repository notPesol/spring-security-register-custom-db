package com.pesol.spring.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {

	String message() default "The first and second fields must match";
	
	String first();
	String second();
	
	@interface List {
		
		FieldMatch[] values();
	}
}
