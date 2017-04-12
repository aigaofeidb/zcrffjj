package Message;

public class VoiceMessage extends Basemsg {
	private String MediaID;
	private String Format;
	private String Recognition;

	public String getMediaID() {
		return MediaID;
	}

	public void setMediaID(String mediaID) {
		MediaID = mediaID;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
}
