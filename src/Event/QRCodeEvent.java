package Event;

/*
 * 	ɨ��������Ķ�ά��
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
