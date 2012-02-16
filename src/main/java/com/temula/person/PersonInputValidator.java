package com.temula.person;

import java.util.List;

import com.temula.InputValidator;

public class PersonInputValidator implements InputValidator<Person> {

	@Override
	public boolean isValid(Person t) {
		return true;
	}

	@Override
	public boolean isValid(List<Person> list) {
		return true;
	}

}
