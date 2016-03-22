package bean;

public class OneToOneVideoRecordingData {
	private String FriendName;
	private String noOfExistingVideo;
	private String TypeOfExistingVideo;
	public String getFriendName() {
		return FriendName;
	}
	public void setFriendName(String friendName) {
		FriendName = friendName;
	}
	public String getNoOfExistingVideo() {
		return noOfExistingVideo;
	}
	public void setNoOfExistingVideo(String noOfExistingVideo) {
		this.noOfExistingVideo = noOfExistingVideo;
	}
	public String getTypeOfExistingVideo() {
		return TypeOfExistingVideo;
	}
	public void setTypeOfExistingVideo(String typeOfExistingVideo) {
		TypeOfExistingVideo = typeOfExistingVideo;
	}
	
}
