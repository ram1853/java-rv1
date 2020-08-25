package com.members.registry.formValidations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mysql.cj.util.StringUtils;

public class PincodeValidator implements ConstraintValidator<ValidatePincode, String> {

	@Override
	public boolean isValid(String input, ConstraintValidatorContext context) {

		boolean result = true;

		try {
			if(!StringUtils.isNullOrEmpty(input)) {
				int pinCode = Integer.parseInt(input);
				int length = (int) (Math.log10(pinCode) + 1);
				if(pinCode < 0 || length != 6) {
					throw new RuntimeException();
				}
			}
		}catch(RuntimeException e) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Please provide valid pincode").addConstraintViolation();
			result = false;
		}

		return result;
	}
}
