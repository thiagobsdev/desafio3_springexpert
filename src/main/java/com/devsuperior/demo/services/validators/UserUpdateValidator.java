package com.devsuperior.demo.services.validators;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.devsuperior.demo.controllers.exceptions.FieldMessage;
import com.devsuperior.demo.dto.UserUpdateDTO;
import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	HttpServletRequest request;
	
	@Override
	public void initialize(UserUpdateValid ann) {
	}

	
	@Override
	public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long idUser = Long.parseLong(uriVars.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Optional<User> user  = userRepository.findByUserName(dto.getUserName());
		
		if (!user.isEmpty() && idUser != user.get().getId() ) {
			list.add(new FieldMessage("email", "o email já está cadastrado!"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

	
}