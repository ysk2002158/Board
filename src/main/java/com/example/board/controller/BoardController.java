package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.Optional;

import com.example.board.repository.Post;
import com.example.board.repository.PostFactory;
import com.example.board.repository.PostRepository;

/**
 * 掲示板のフロントコントローラー.
 */
@Controller
public class BoardController {

	@Autowired
	private PostRepository repository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("form", PostFactory.newPost());
		model = this.setList(model);
		model.addAttribute("path", "create");
		return "layout";
	}

	private Model setList(Model model) {
		Iterable<Post> list = repository.findAll();
		model.addAttribute("list", list);
		return model;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute("form") @Validated(GroupOrder.class) Post form, BindingResult result,
			Model model) {
		if (!result.hasErrors()) {
			repository.saveAndFlush(PostFactory.createPost(form));
			model.addAttribute("form", PostFactory.newPost());
		}
		model = this.setList(model);
		model.addAttribute("path", "create");
		return "layout";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ModelAttribute("form") @Validated Post form, BindingResult result, Model model) {

		if (!result.hasErrors()) {
			Optional<Post> post = repository.findById(form.getId());
			repository.saveAndFlush(PostFactory.updatePost(post.get(), form));
		}
		Optional<Post> post = repository.findById(form.getId());
		model.addAttribute("form", post);
		model = setList(model);
		model.addAttribute("path", "update");
		return "layout";
	}

	/**
	 * 更新する
	 *
	 * @param form  フォーム
	 * @param model モデル
	 * @return テンプレート
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("form") Post form, Model model) {
		Optional<Post> post = repository.findById(form.getId());
		repository.saveAndFlush(PostFactory.updatePost(post.get(), form));
		model.addAttribute("form", PostFactory.newPost());
		model = setList(model);
		model.addAttribute("path", "create");
		return "layout";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@ModelAttribute("form") Post form, Model model) {
		Optional<Post> post = repository.findById(form.getId());
		repository.saveAndFlush(PostFactory.deletePost(post.get()));
		model.addAttribute("form", PostFactory.newPost());
		model = setList(model);
		model.addAttribute("path", "create");
		return "layout";
	}
}