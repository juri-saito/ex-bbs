package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

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
	
	/**
	 * 記事登録時に使用するフォームオブジェクトをModelオブジェクト（リクエストスコープ）に格納される.
	 * @return　記事登録時に使用するフォームオブジェクト
	 */
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		ArticleForm articleForm = new ArticleForm();
		return articleForm;
	}
	
	
//	/**
//	 * 掲示板画面を表示する.
//	 * @return 掲示板画面
//	 */
//	@RequestMapping("/")
//	public String index() {
//		return "bbs";
//	}

	/**
	 * 掲示板画面を表示する.
	 * @param model　リクエストスコープ
	 * @return　掲示板画面
	 */
	@RequestMapping("/")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
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
}
