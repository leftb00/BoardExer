package com.leftb.board.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.leftb.board.dao.BoardDAO;
import com.leftb.board.dto.BoardDTO;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		BoardDAO boardDao = new BoardDAO();
		ArrayList<BoardDTO> dto_list = boardDao.list();

		model.addAttribute("list", dto_list);
		model.addAttribute("total", dto_list.size());
	}
}
