package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 掲示板関連機能の処理の制御を行うコントローラ
 * @author juri.saito
 *
 */
@Controller
@RequestMapping("/bbs")
public class ArticleController {
	
	/**
	 * 掲示板画面を表示する
	 * @return 掲示板画面
	 */
	@RequestMapping("/")
	public String index() {
		return "bbs";
	}

}
