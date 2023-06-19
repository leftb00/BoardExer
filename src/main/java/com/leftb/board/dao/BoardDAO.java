package com.leftb.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.leftb.board.dto.BoardDTO;

public class BoardDAO {

	DataSource dataSource;

	public BoardDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void write(String bname, String btitle, String bcontent) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO mvc_board(bid, bname, btitle, bcontent) "
					   + "VALUES (mvc_board_seq.nextval,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);

			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<BoardDTO> list() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardDTO> board_list = new ArrayList<BoardDTO>();

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT bid,bname,btitle,bcontent,bdate,bhit "
					   + "FROM mvc_board ORDER BY bdate DESC";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bid = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");

				BoardDTO dto = new BoardDTO(bid, bname, btitle, bcontent, bdate, bhit);
				board_list.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		return board_list;
	}

	public BoardDTO view(String bid, boolean uphit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO dto = null;

		try {
			conn = dataSource.getConnection();
			String sql;

			if(uphit) {
				sql = "UPDATE mvc_board SET bhit=bhit+1 WHERE bid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bid);
				pstmt.executeUpdate();
				pstmt.close();
			}

			sql = "SELECT bid,bname,btitle,bcontent,bdate,bhit "
				+ "FROM mvc_board WHERE bid=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bid);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int bid2 = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");

				dto = new BoardDTO(bid2, bname, btitle, bcontent, bdate, bhit);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		return dto;
	}

	public void modify(String bid, String btitle, String bcontent) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_board SET btitle=?, bcontent=? " 
					   + "WHERE bid=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, btitle);
			pstmt.setString(2, bcontent);
			pstmt.setString(3, bid);

			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(String bid) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM mvc_board WHERE bid=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bid);

			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
/*
	public void uphit(String bid) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_board SET bhit=bhit+1 WHERE bid=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bid);

			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
*/
}
