package app.solutions.model;


import org.mongodb.morphia.annotations.Entity;

@Entity
public class AddressType  extends ObjectType{
	
	private boolean defaultAddress;

	public boolean isDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(boolean defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

}
