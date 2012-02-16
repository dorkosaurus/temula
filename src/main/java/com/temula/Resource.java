package com.temula;

import java.util.List;

import javax.ws.rs.core.Response;

public interface Resource<T> {
	public RepresentationalState get(T attributes);
	public Response.Status put(T t);
	public Response.Status post(List<T> list);
	public Response.Status delete(T t);
}
