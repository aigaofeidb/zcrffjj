package UserManage;

import java.util.List;

public class WeixinUserInfo {
	// �û���ʶ
	private String openId;
	// ��ע״̬��1�ǹ�ע��0��δ��ע����δ��עʱ��ȡ����������Ϣ
	private int subscribe;
	// ��עʱ��
	private String subscribeTime;
	// �û��ǳ�
	private String nickname;
	// �Ա�1���С�2��Ů��0��δ֪��
	private int sex;
	// ����
	private String country;
	// ʡ��
	private String province;
	// ����
	private String city;
	// �û�����
	private String language;
	// ͷ������
	private String headImgUrl;
	// ���ں���Ӫ�߶Է�˿�ı�ע
	private String remark;
	// �û����ڵķ���ID
	private String groupid;
	// �û������ϵı�ǩID�б�
	private String tagidlist;
	

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public String getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getTagidlist() {
		return tagidlist;
	}

	public void setTagidlist(String tagidlist) {
		this.tagidlist = tagidlist;
	}

}
