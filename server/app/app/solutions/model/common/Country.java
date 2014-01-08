package app.solutions.model.common;

import java.util.List;


import app.solutions.model.BaseObject;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;


@Entity
public class Country {

	
	private String countryCode;
	
	private String countryName;
	
	private String continent;
	
	private String currencyCode;
	
	@Embedded
	private List<CountryState> states;
	
}
