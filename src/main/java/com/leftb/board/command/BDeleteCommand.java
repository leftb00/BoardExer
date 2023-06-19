package com.leftb.board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.leftb.board.dao.BoardDAO;

public class BDeleteCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");

		BoardDAO boardDao = new BoardDAO();
		boardDao.delete(request.getParameter("bid"));
	}
}
