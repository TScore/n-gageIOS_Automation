package bean;

public class OneToOneAudioRecordingData 
{
	private String FriendName;
	private String noOfAudioRecording;
	private String TypeOFAudioRecording;
	public String getFriendName() {
		return FriendName;
	}
	public void setFriendName(String friendName) {
		FriendName = friendName;
	}
	public String getNoOfAudioRecording() {
		return noOfAudioRecording;
	}
	public void setNoOfAudioRecording(String noOfAudioRecording) {
		this.noOfAudioRecording = noOfAudioRecording;
	}
	public String getTypeOFAudioRecording() {
		return TypeOFAudioRecording;
	}
	public void setTypeOFAudioRecording(String typeOFAudioRecording) {
		TypeOFAudioRecording = typeOFAudioRecording;
	}
	
}
