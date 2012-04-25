package com.temula.location;

import java.util.List;

public interface SpaceParser {
	 /** GET THE LIST OF SPACES **/
	public static final String EXPRESSION = "//div[@itemtype='http://schema.org/Place/Space']";
	public static final String SUBEXPRESSION="descendant::span[@itemprop]";
	public static final String SUBEXPRESSION2="descendant::span[@itemprop='ELEMNAMEGOESHERE']";
	
	public List<Space> parseXHTML(String xhtml)throws Exception;
}
