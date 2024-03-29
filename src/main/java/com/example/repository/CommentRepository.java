package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;


/**
 * commentsテーブルを操作するリポジトリ.
 * @author juri.saito
 *
 */
@Repository
public class CommentRepository {
	
	@Autowired
	public NamedParameterJdbcTemplate template;

	/**
	 * Commentオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));;
		return comment;
	};
	
	/**
	 * 記事id毎にコメント一覧情報をコメント投稿順で取得する.
	 * @return コメント一覧
	 */
	public List<Comment> findByArticleId(int articleId){
		String sql = "SELECT id, name, content, article_id FROM comments WHERE article_id=:articleId ORDER BY id DESC;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
		
		return commentList;
	}
	

	/**
	 * コメントを登録する.
	 * @param comment コメント情報
	 */
	public void insert(Comment comment) {
		System.out.println(comment);
		String sql = "INSERT INTO comments (article_id, name, content) VALUES (:articleId, :name, :content);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		template.update(sql, param);
	}
	
	/**
	 * コメントを削除する.
	 * @param articleId　記事ID
	 */
	public void delete(Integer articleId) {
		String sql = "DELETE FROM comments WHERE article_id=:articleId;";
		SqlParameterSource param = new MapSqlParameterSource("articleId", articleId);
		template.update(sql, param);
	}
}
