package com.temula.person;

import java.net.URI;

import com.temula.ResourceURI;

public class PersonURI implements ResourceURI {

	@Override
	public URI getURI() {
		return URI.create("http://www.google.com");
	}

}
