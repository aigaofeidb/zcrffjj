package ReMessage;

public class VoiceMessage extends BaseMessage {

	/*
	 * ������Ϣ
	 */
	
	private Voice Voice;
	public Voice getVoice() {
		return Voice;
	}
	
	public void setVoice(Voice voice) {
		Voice = voice;
	}
	
	public class Voice {
		private String MediaId;
		
		public String getMediaId(){
			return MediaId;
		}
		
		public void setMediaId(String mediaId){
			MediaId = mediaId;
		}
	}
	
}
