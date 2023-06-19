package com.leftb.board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.leftb.board.dao.BoardDAO;
import com.leftb.board.dto.BoardDTO;

public class BModifyFormCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");

		BoardDAO boardDao = new BoardDAO();
		String bid = request.getParameter("bid");

		BoardDTO dto = boardDao.view(bid, false);
		model.addAttribute("dto", dto);
	}

}
