package app.solutions.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import app.solutions.validation.annotations.Reference;
import org.mongodb.morphia.annotations.Embedded;


/**
 * Address Document
 * 
 * @author I038968
 * 
 */

@Embedded
public class Address extends BaseObject {

	/**
	 * Street Name
	 */
	@NotBlank( message = "Street Name in address is mandatory")
	@Length(min= 1, max= 30 )
	private String streetName;

	/**
	 * House Number
	 */
	
	@NotBlank( message = "House Number in Address is mandatory")
	@Length(min= 1, max= 30 )
	private String houseNumber;

	/**
	 * City Name
	 */
	
	private String cityName;

	/**
	 * Pin Code
	 */

	private String pinCode;
	
	/**
	 * State
	 */
	private String state;
	
	
	/**
	 * District Code
	 */
	private String district;
	
	
	/**
	 * Country Code
	 */
	private String country;



	//@Reference( collectionName = "AddressType" , fieldName = "externalId")
	//@NotBlank
	private String  addressType;

	

	/**
	 * @return the houseNumber
	 */
	public String getHouseNumber() {
		return houseNumber;
	}

	/**
	 * @param houseNumber
	 *            the houseNumber to set
	 */
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the pinCode
	 */
	public String getPinCode() {
		return pinCode;
	}

	/**
	 * @param pinCode
	 *            the pinCode to set
	 */
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}



	/**
	 * @return the addressType
	 */
	public String getAddressType() {
		return addressType;
	}

	/**
	 * @param addressType
	 *            the addressType to set
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	/**
	 * @param streetName
	 *            the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	

}
