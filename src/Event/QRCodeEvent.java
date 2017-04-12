package Event;

/*
 * 	扫描带参数的二维码
 */
public class QRCodeEvent extends BaseEvent {
	private String EventKey;
	private String Ticket;
	
	public String getEventKey() {
		return EventKey;
	}
	
	public void setEventKey(String eventkey ) {
		EventKey = eventkey;
	}
	
	public String getTicket() {
		return Ticket;
	}
	
	public void setTicket(String ticket ) {
		Ticket = ticket;
	}
	
}
