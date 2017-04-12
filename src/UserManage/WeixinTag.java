package UserManage;

public class WeixinTag {
	//标签id
	private int id;
	//标签名称
	private String name;
	//标签内的用户数量
	private int count;
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public int getCount(){
		return count;
	}
	public void setCount(int count){
		this.count = count;
	}
}
