package com.example.domain;

import java.util.List;

/**
 * 記事情報を表すドメイン.
 * @author juri.saito
 *
 */
public class Article {

	/** ID */
	private Integer id;
	
	/** 投稿者名 */
	private String name;
	
	/** 記事 */
	private String content;
	
	/** コメントリスト */
	private List<Comment> commentList;
	
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", commentList=" + commentList + "]";
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

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	
	
}
