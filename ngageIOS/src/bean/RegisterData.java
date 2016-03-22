package bean;
/*@Authore:Tapana
 *@Description:useing encapsulation,we make some method which 
 *is used to  extract  data from excel sheet. 
 * */
public class RegisterData 
{
	private String userName;
	private String countryName;
	private String mobileNumber;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	

}
