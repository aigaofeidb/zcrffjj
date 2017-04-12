package UserManage;

import java.util.List;

public class WeixinUserInfo {
	// 用户标识
	private String openId;
	// 关注状态（1是关注、0是未关注），未关注时获取不到其余信息
	private int subscribe;
	// 关注时间
	private String subscribeTime;
	// 用户昵称
	private String nickname;
	// 性别（1是男、2是女、0是未知）
	private int sex;
	// 国家
	private String country;
	// 省份
	private String province;
	// 城市
	private String city;
	// 用户语言
	private String language;
	// 头像链接
	private String headImgUrl;
	// 公众号运营者对粉丝的备注
	private String remark;
	// 用户所在的分组ID
	private String groupid;
	// 用户被打上的标签ID列表
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
