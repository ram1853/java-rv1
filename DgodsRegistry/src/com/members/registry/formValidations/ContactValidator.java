package com.members.registry.formValidations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mysql.cj.util.StringUtils;

public class ContactValidator implements ConstraintValidator<ValidateContact, String> {

	@Override
	public boolean isValid(String input, ConstraintValidatorContext context) {

		boolean result = true;

		try {

			if(!StringUtils.isNullOrEmpty(input)) {
				long contact = Long.parseLong(input);
				if(contact <= 0) {
					throw new RuntimeException();
				}
			}
		}catch(RuntimeException e) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Please provide valid contact").addConstraintViolation();
			result = false;
		}

		return result;
	}
}
