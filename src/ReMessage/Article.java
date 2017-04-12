package ReMessage;

public class Article {

	private String Title;
	private String Description;
	// 图片链接 效果较好： 大图 640 * 320 小图 80 * 80
	private String PicUrl;
	private String Url;

	public String getTitle() {
		// TODO Auto-generated method stub
		return Title;
	}

	public void setTitle(String title) {
		// TODO Auto-generated method stub
		Title = title;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null == Description ? "" : Description;
	}

	public void setDescription(String description) {
		// TODO Auto-generated method stub
		Description = description;
	}

	public String getPicUrl() {
		// TODO Auto-generated method stub
		return null == PicUrl ? "" : PicUrl;
	}

	public void setPicUrl(String picUrl) {
		// TODO Auto-generated method stub
		Title = picUrl;
	}

	public String getUrl() {
		// TODO Auto-generated method stub
		return null == Url ? "" : Url;
	}

	public void setUrl(String url) {
		// TODO Auto-generated method stub
		Url = url;
	}
}
