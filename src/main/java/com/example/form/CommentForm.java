package com.example.form;

/**
 * コメント投稿時に使用するフォーム
 * @author juri.saito
 *
 */
public class CommentForm {
	
		/** コメントID */
		private String id;
		
		/** コメント者名 */
		private String name;
		
		/** コメント */
		private String content;
		
		/** article_id */
		private String articleId;
		

		@Override
		public String toString() {
			return "CommentForm [id=" + id + ", name=" + name + ", content=" + content + ", articleId=" + articleId
					+ "]";
		}

		public Integer getIntId() {
			return Integer.parseInt(id);
		}
		
		public Integer getIntArticleId() {
			return Integer.parseInt(articleId);
		}
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getArticleId() {
			return articleId;
		}

		public void setArticleId(String articleId) {
			this.articleId = articleId;
		}
		


}
