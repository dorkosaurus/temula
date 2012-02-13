package com.temula;

public class RowNumQueryParameter implements QueryParameterInterface<Integer> {

	Integer rowNum;
	
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getValue() {
		return rowNum;
	}

	@Override
	public String getKeyValueRelationship() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getExpressionRelationship() {
		// TODO Auto-generated method stub
		return null;
	}

}
