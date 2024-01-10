package com.application.User.Services;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;

public class DNIValidator implements Validator<String> {
	@Override
	public ValidationResult apply(String value, ValueContext context) {

		String num = "";

		char[] letraDni = {
				'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C',
				'K', 'E'
		};

		int numero;

		if (value.trim().length() == 9) {

			for (int i = 0; i < 8; i++) {
				if (!Character.isDigit(value.charAt(i))) {
					return ValidationResult.error("El dni es incorrecto, contiene más de una letra.");
				}
				num += value.charAt(i);
			}

			numero = Integer.parseInt(num);
			numero %= 23;

			if ((Character.toUpperCase(value.charAt(8))) == letraDni[numero]) {
				return ValidationResult.ok();
			} else {
				return ValidationResult.error("El dni es incorrecto, la letra no coincide.");
			}

		} else {

			return ValidationResult.error("El dni es incorrecto.");

		}

	}
}