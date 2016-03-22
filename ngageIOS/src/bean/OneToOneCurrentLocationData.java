package bean;

public class OneToOneCurrentLocationData 
{

	private String FriendName;
	private String noOfCurrentLocation;
	private String TypeOfCurrentLOcation;
	public String getFriendName() {
		return FriendName;
	}
	public void setFriendName(String friendName) {
		FriendName = friendName;
	}
	public String getNoOfCurrentLocation() {
		return noOfCurrentLocation;
	}
	public void setNoOfCurrentLocation(String noOfCurrentLocation) {
		this.noOfCurrentLocation = noOfCurrentLocation;
	}
	public String getTypeOfCurrentLOcation() {
		return TypeOfCurrentLOcation;
	}
	public void setTypeOfCurrentLOcation(String typeOfCurrentLOcation) {
		TypeOfCurrentLOcation = typeOfCurrentLOcation;
	}
	
}
