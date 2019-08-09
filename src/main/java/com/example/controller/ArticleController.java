package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * 掲示板関連機能の処理の制御を行うコントローラ.
 * @author juri.saito
 *
 */
@Controller
@RequestMapping("/bbs")
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	/**
	 * コメント登録時に使用するフォームオブジェクトをModelオブジェクト（リクエストスコープ）に格納する.
	 * @return　コメント登録時に使用するフォームオブジェクト
	 */
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		CommentForm commentForm = new CommentForm();
		return commentForm;
	}
	

	/**
	 * 掲示板画面を表示する.
	 * @param model　リクエストスコープ
	 * @return　掲示板画面
	 */
	@RequestMapping("/")
	public String index(Model model) {
		
		//全記事を取得してリクエストスコープに格納
		List<Article> articleList = articleRepository.findAll(); 
		
		//記事一つ一つの全コメントを取得
		for(Article article : articleList) {
			int articleId = article.getId();
			List<Comment> commentList = commentRepository.findByArticleId(articleId); 
			article.setCommentList(commentList);
		}
		
		model.addAttribute("articleList", articleList);
		
		return "bbs";
	}
	
	/**
	 * 記事を投稿する.
	 * @param form 記事投稿時に使用するフォームオブジェクト
	 * @return　掲示板画面
	 */
	@RequestMapping("/insert")
	public String insertArticle(ArticleForm form) {
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		
		articleRepository.insert(article);
		return "redirect:/bbs/";
	}
	
	/**
	 * コメントを投稿する.
	 * @param form　コメント投稿時に使用するフォームオブジェクト
	 * @return　掲示板画面
	 */
	@RequestMapping("/insert-comment")
	public String  insertComment(CommentForm form, Model model) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setArticleId(form.getIntArticleId());
		System.out.println(comment);
		commentRepository.insert(comment);
		return "redirect:/bbs/";
	}
	
	/**
	 * 記事とコメントを削除する.
	 * @param articleId 記事ID
	 * @return　掲示板画面
	 */
	@RequestMapping("/delete-article")
	public String deleteArticle(int articleId) {
		commentRepository.delete(articleId);
		articleRepository.delete(articleId);
		return "redirect:/bbs/";
	}
}
