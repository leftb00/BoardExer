package com.leftb.board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.leftb.board.dao.BoardDAO;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");

		String bid = request.getParameter("bid");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");

		BoardDAO boardDao = new BoardDAO();
		boardDao.modify(bid, btitle, bcontent);
	}

}
