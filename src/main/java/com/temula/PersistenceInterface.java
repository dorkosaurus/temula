package com.temula;

import java.util.List;
import com.temula.exception.InvalidInputException;
import com.temula.exception.NullInputException;
import com.temula.exception.NullListElementException;
import com.temula.exception.RecordExistsException;

public interface PersistenceInterface<T> {
	public void insert(T t) throws NullInputException,InvalidInputException,RecordExistsException;
	public void insert(List<T> list) throws NullInputException,InvalidInputException,RecordExistsException;
	public void update(T t) throws NullInputException,InvalidInputException;
	public void update(List<T> list) throws NullInputException,InvalidInputException,NullListElementException;
	public List<T> get(List<QueryParameterInterface<?>> queryList);
}
