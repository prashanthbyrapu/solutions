package app.solutions.model.common;

import java.util.List;

import com.google.code.morphia.annotations.Embedded;

import app.solutions.model.BaseObject;


@Embedded
public class District {

	private String districtCode;
	
	private String districtName;
	
	private List<City> cities;
	
	
}
