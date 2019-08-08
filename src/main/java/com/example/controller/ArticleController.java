package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
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
	 * 掲示板画面を表示する.
	 * @return 掲示板画面
	 */
	@RequestMapping("/")
	public String index() {
		return "bbs";
	}

	/**
	 * 記事一覧を出力する.
	 * @param model　リクエストスコープ
	 * @return　掲示板画面
	 */
	@RequestMapping("/showArticle")
	public String showArticle(Model model) {
		List<Article> articleList = articleRepository.findAll();
		model.addAttribute("articleList", articleList);
		return "bbs";
	}
}
