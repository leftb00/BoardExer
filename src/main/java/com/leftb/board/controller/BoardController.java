package com.leftb.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leftb.board.command.BCommand;
import com.leftb.board.command.BDeleteCommand;
import com.leftb.board.command.BListCommand;
import com.leftb.board.command.BModifyCommand;
import com.leftb.board.command.BModifyFormCommand;
import com.leftb.board.command.BViewCommand;
import com.leftb.board.command.BWriteCommand;

@Controller
public class BoardController {

	BCommand command = null;

	@RequestMapping(value = {"/", "/list"})
	public String index(Model model) {
		command = new BListCommand();
		command.execute(model);

		return "list";
	}

	@RequestMapping(value = "/write_form")
	public String write_form() {
		return "write_form";
	}

	@RequestMapping(value = "/write")
	public String write(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.execute(model);

		return "redirect:list";
	}

	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new BViewCommand();
		command.execute(model);

		return "view";
	}

	@RequestMapping(value = "/modify_form")
	public String modify_form(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new BModifyFormCommand();
		command.execute(model);

		return "modify_form";
	}

	@RequestMapping(value = "/modify")
	public String modify(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);

		return "redirect:list";
	}

	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);

		return "redirect:list";
	}
}
