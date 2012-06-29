package app.solutions.model.common;

import java.util.List;

import com.google.code.morphia.annotations.Embedded;

import app.solutions.model.BaseObject;

@Embedded
public class CountryState {
	
	private String stateCode;
	
	private String stateName;
	
	private String capitalCity;
	
	@Embedded
	private List<District> districts;

}
