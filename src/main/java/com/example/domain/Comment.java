	package com.example.domain;

	public class Comment {

		/** コメントID */
		private Integer id;
		
		/** コメント者名 */
		private String name;
		
		/** コメント */
		private String content;
		
		/** article_id */
		private Integer articled;
		
		

		@Override
		public String toString() {
			return "commit [id=" + id + ", name=" + name + ", content=" + content + ", articled=" + articled + "]";
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
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

		public Integer getArticled() {
			return articled;
		}

		public void setArticled(Integer articled) {
			this.articled = articled;
		}
		
		
	}

