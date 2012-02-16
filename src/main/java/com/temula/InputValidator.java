package com.temula;

import java.util.List;

public interface InputValidator<T> {
	public boolean isValid(T t);
	public boolean isValid(List<T> list);
}
