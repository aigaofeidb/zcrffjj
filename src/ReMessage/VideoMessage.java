package ReMessage;

public class VideoMessage extends BaseMessage {

	/*
	 * ”Ô“Ùœ˚œ¢
	 */

	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

	public class Video {
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

		public void setThumbMediaId(String thumbMediaId) {
			ThumbMediaId = thumbMediaId;
		}
	}

}
