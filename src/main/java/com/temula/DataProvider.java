package com.temula;

import java.util.List;

import javax.ws.rs.core.Response;

public interface DataProvider<T> {
	public List<T> get(T attributes);
	
	/* Should return CREATED status code if everything went well */
	public Response.Status put(T t);

	/* Should return CREATED status code if everything went well */
	public Response.Status post(List<T> list);
	
	/* Should return OK status code if everything went well */
	public Response.Status delete(T t);
}