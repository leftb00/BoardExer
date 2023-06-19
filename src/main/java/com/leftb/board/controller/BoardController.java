package com.leftb.board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leftb.board.dao.BoardDAO;
import com.leftb.board.dto.BoardDTO;

@Controller
public class BoardController {

	@RequestMapping(value = {"/", "/list"})
	public String index(Model model) {
		BoardDAO boardDao = new BoardDAO();
		ArrayList<BoardDTO> dto_list = boardDao.list();

		model.addAttribute("list", dto_list);
		model.addAttribute("total", dto_list.size());

		return "list";
	}

	@RequestMapping(value = "/write_form")
	public String write_form() {
		return "write_form";
	}

	@RequestMapping(value = "/write")
	public String write(HttpServletRequest request) {
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");

		BoardDAO boardDao = new BoardDAO();
		boardDao.write(bname, btitle, bcontent);

		return "redirect:list";
	}

	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request, Model model) {
		BoardDAO boardDao = new BoardDAO();
		String bid = request.getParameter("bid");

		boardDao.uphit(bid);
		BoardDTO dto = boardDao.view(bid);
		model.addAttribute("dto", dto);

		return "view";
	}

	@RequestMapping(value = "/modify_form")
	public String modify_form(HttpServletRequest request, Model model) {
		BoardDAO boardDao = new BoardDAO();
		String bid = request.getParameter("bid");

		BoardDTO dto = boardDao.view(bid);
		model.addAttribute("dto", dto);

		return "modify_form";
	}

	@RequestMapping(value = "/modify")
	public String modify(HttpServletRequest request) {
		String bid = request.getParameter("bid");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");

		BoardDAO boardDao = new BoardDAO();
		boardDao.modify(bid, btitle, bcontent);

		return "redirect:list";
	}

	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request) {
		BoardDAO boardDao = new BoardDAO();
		String bid = request.getParameter("bid");

		boardDao.delete(bid);

		return "redirect:list";
	}
}
