package ReMessage;

import java.util.List;

public class NewsMessage extends BaseMessage {
	private int ArticleCount;
	private List Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List getArticles() {
		return Articles;
	}

	public void setArticles(List articles) {
		Articles = articles;
	}
}
