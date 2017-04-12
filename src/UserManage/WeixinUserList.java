package UserManage;

import java.util.List;

public class WeixinUserList {
	private int total;
	private int count;
	private List<String> openIdList;
	private String nextopenid;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public List<String> getOpenIdList() {
		return openIdList;
	}

	public void setOpenIdList(List<String> openIdList) {
		this.openIdList = openIdList;
	}
	
	public String getNextopenid() {
		return nextopenid;
	}

	public void setNextopenid(String nextopenid) {
		this.nextopenid = nextopenid;
	}
}
