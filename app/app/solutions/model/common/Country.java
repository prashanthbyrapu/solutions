package app.solutions.model.common;

import java.util.List;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;

import app.solutions.model.BaseObject;


@Entity
public class Country {

	
	private String countryCode;
	
	private String countryName;
	
	private String continent;
	
	private String currencyCode;
	
	@Embedded
	private List<CountryState> states;
	
}
