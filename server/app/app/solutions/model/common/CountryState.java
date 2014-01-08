package app.solutions.model.common;

import java.util.List;



import app.solutions.model.BaseObject;
import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class CountryState {
	
	private String stateCode;
	
	private String stateName;
	
	private String capitalCity;
	
	@Embedded
	private List<District> districts;

}
