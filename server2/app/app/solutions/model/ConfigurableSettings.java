package app.solutions.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import app.solutions.validation.annotations.Unique;

public class ConfigurableSettings extends BaseObject{

	@NotEmpty
	@Length(min= 1, max= 15 )
	@Unique
	private String externalId;
	
	
	
	
}
