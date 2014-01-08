package app.solutions.model.common;

import java.util.List;



import app.solutions.model.BaseObject;
import org.mongodb.morphia.annotations.Embedded;


@Embedded
public class District {

	private String districtCode;
	
	private String districtName;
	
	private List<City> cities;
	
	
}
