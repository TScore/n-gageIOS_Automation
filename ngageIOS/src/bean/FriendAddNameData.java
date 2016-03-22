package bean;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FriendAddNameData 
{
	private String FriendName;
	private String CountrySelection;
	private String PhoneNumber;
	private String ConfirmPhoneNumber;
	private String EmailId;
	public String getFriendName() {
		return FriendName;
	}
	public void setFriendName(String friendName) {
		FriendName = friendName;
	}
	public String getCountrySelection() {
		return CountrySelection;
	}
	public void setCountrySelection(String countrySelection) {
		CountrySelection = countrySelection;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getConfirmPhoneNumber() {
		return ConfirmPhoneNumber;
	}
	public void setConfirmPhoneNumber(String confirmPhoneNumber) {
		ConfirmPhoneNumber = confirmPhoneNumber;
	}
	public String getEmailId() {
		return EmailId;
	}
	public void setEmailId(String emailId) {
		EmailId = emailId;
	}
	
}
