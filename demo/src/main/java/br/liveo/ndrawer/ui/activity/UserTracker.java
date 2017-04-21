package br.liveo.ndrawer.ui.activity;

import java.io.Serializable;

public class UserTracker implements Serializable
{
	String trackerID,trackerIDPin,trackerMobileNum,trackerName;
	int trackerPic;
	
	public UserTracker(String trackerID, String trackerIDPin,
					   String trackerMobileNum, String trackerName, int trackerPic) {
		super();
		this.trackerID = trackerID;
		this.trackerIDPin = trackerIDPin;
		this.trackerMobileNum = trackerMobileNum;
		this.trackerName = trackerName;
		this.trackerPic = trackerPic;
	}

	public String getTrackerID() {
		return trackerID;
	}

	public void setTrackerID(String trackerID) {
		this.trackerID = trackerID;
	}

	public String getTrackerIDPin() {
		return trackerIDPin;
	}

	public void setTrackerIDPin(String trackerIDPin) {
		this.trackerIDPin = trackerIDPin;
	}

	public String getTrackerMobileNum() {
		return trackerMobileNum;
	}

	public void setTrackerMobileNum(String trackerMobileNum) {
		this.trackerMobileNum = trackerMobileNum;
	}

	public String getTrackerName() {
		return trackerName;
	}

	public void setTrackerName(String trackerName) {
		this.trackerName = trackerName;
	}

	public int getTrackerPic() {
		return trackerPic;
	}

	public void setTrackerPic(int trackerPic) {
		this.trackerPic = trackerPic;
	}
	

}
