package Message;

public class VideoMessage extends Basemsg {
	private String MediaId;
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId){
		ThumbMediaId = thumbMediaId;
	}
}
