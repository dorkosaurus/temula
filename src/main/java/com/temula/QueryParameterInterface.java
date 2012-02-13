package com.temula;

public interface QueryParameterInterface<T> {
	public String getKey();
	public T getValue();
	public String getKeyValueRelationship();
	public String getExpressionRelationship();
}
