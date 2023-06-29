
package com.devsuperior.demo.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationErros extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	public void addErrors(String fieldName, String errorMessage ) {
		errors.add(new FieldMessage(fieldName, errorMessage));
	}

}
