package com.pesol.spring.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>{
	
	private String firstNameName;
	private String secondFieldName;
	
	private String message;
	

	@Override
	public void initialize(FieldMatch constraintAnnotation) {
		firstNameName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		boolean valid = true;
		
		try {
			Object firstValue = new BeanWrapperImpl(value).getPropertyValue(firstNameName);
			Object secondValue = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);
			
			valid = firstValue == null && secondValue == null ||
					firstValue != null && firstValue.equals(secondValue);
			
		} catch (Exception e) {
			// Ignore
		}
		
		if (!valid) {
			context.buildConstraintViolationWithTemplate(message)
					.addPropertyNode(firstNameName)
					.addConstraintViolation()
					.disableDefaultConstraintViolation();
		}
		
		
		return valid;
	}

}
