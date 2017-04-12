package QRCode;

public class WeixinQRCode {
	
	private String ticket;
	private int expire_seconds;
	
	public String getTicket(){
		return ticket;
	}
	
	public void setTicket(String ticket){
		this.ticket = ticket;
	}

	public int getExpireSeconds(){
		return expire_seconds;
	}
	
	public void setExpireSeconds(int expire_seconds){
		this.expire_seconds = expire_seconds;
	}
	
}
