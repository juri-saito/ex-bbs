package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * articlesテーブルを操作するリポジトリ.
 * @author juri.saito
 *
 */
@Repository
public class ArticleRepository {
	
	@Autowired
	private  NamedParameterJdbcTemplate template;
	
	/**
	 * Articleオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));;
		article.setContent(rs.getString("content"));
		return article;
	};
	
	/**
	 * 記事一覧情報を投稿順で取得する.
	 * @return 記事一覧
	 */
	public List<Article> findAll(){
		String sql = "SELECT id, name, content FROM articles ORDER BY id DESC;";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		return articleList;
	}

	/**
	 * 記事を登録する.
	 * @param article 記事情報
	 */
	public void insert(Article article) {
		String sql = "INSERT INTO articles (name, content) VALUES (:name, :content);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		template.update(sql, param);
	}
	
	/**
	 * 記事を削除する.
	 * @param articleId　記事ID
	 */
	public void delete(Integer articleId) {
		String sql = "DELETE FROM articles WHERE id=:articleId;";
		SqlParameterSource param = new MapSqlParameterSource("articleId", articleId);
		template.update(sql, param);
	}
}
