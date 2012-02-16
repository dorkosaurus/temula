package com.temula;

import java.net.URI;
import java.util.List;

public interface  RepresentationalStateBinder<T> {

	public RepresentationalState marshall(List<T> data, URI resource);
	public List<T> unmarshall(RepresentationalState state, URI resource);

}
