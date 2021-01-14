package board.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import board.model.vo.Board;

public class BoardDao {
	public BoardDao() {}

	public ArrayList<Board> selectList(Connection conn, int currentPage, int limit) {
		ArrayList<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * " + 
				"FROM (SELECT ROWNUM RNUM, BOARD_NUM, BOARD_CATEGORY, BOARD_TITLE, BOARD_WRITER,  " + 
				"                        BOARD_CONTENT, BOARD_ORIGINAL_FILENAME, " + 
				"                        BOARD_RENAME_FILENAME, BOARD_REF, BOARD_REPLY_REF,  " + 
				"                        BOARD_REPLY_LEV, BOARD_REPLY_SEQ, BOARD_READCOUNT,  " + 
				"                        BOARD_DATE " + 
				"            FROM (SELECT * FROM BOARD " + 
				"                      ORDER BY BOARD_REF DESC, BOARD_REPLY_REF DESC,  " + 
				"                                     BOARD_REPLY_LEV ASC, BOARD_REPLY_SEQ ASC)) " + 
				"WHERE  RNUM >= ? AND RNUM <= ?";
		
		int startRow = (currentPage - 1) * limit + 1;  //3 page 이면 시작행은 21
		int endRow = startRow + limit - 1;   //3 page 이면 끝행은 30
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				
				board.setBoardNum(rset.getInt("board_num"));
				board.setBoardCategory(rset.getString("board_category"));
				board.setBoardWriter(rset.getString("board_writer"));
				board.setBoardTitle(rset.getString("board_title"));
				board.setBoardContent(rset.getString("board_content"));
				board.setBoardOriginalFileName(rset.getString("board_original_filename"));
				board.setBoardRenameFileName(rset.getString("board_rename_filename"));
				board.setBoardRef(rset.getInt("board_ref"));
				board.setBoardReplyLev(rset.getInt("board_reply_lev"));
				board.setBoardReplyRef(rset.getInt("board_reply_ref"));
				board.setBoardReplySeq(rset.getInt("board_reply_seq"));
				board.setBoardReadCount(rset.getInt("board_readcount"));
				board.setBoardDate(rset.getDate("board_date"));
								
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from board";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public int addReadCount(Connection conn, int boardNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update board "
				+ "set board_readcount = board_readcount + 1 "
				+ "where board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public Board selectBoard(Connection conn, int boardNum) {
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from board where board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				board = new Board();
				
				board.setBoardNum(boardNum);
				board.setBoardCategory(rset.getString("board_category"));
				board.setBoardWriter(rset.getString("board_writer"));
				board.setBoardTitle(rset.getString("board_title"));
				board.setBoardContent(rset.getString("board_content"));
				board.setBoardOriginalFileName(rset.getString("board_original_filename"));
				board.setBoardRenameFileName(rset.getString("board_rename_filename"));
				board.setBoardRef(rset.getInt("board_ref"));
				board.setBoardReplyLev(rset.getInt("board_reply_lev"));
				board.setBoardReplyRef(rset.getInt("board_reply_ref"));
				board.setBoardReplySeq(rset.getInt("board_reply_seq"));
				board.setBoardReadCount(rset.getInt("board_readcount"));
				board.setBoardDate(rset.getDate("board_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return board;
	}

	public int insertOriginBoard(Connection conn, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into board values ("
				+ "(select max(board_num) + 1 from board), ?, ?, ?, ?, ?, ?, "
				+ "(select max(board_num) + 1 from board), null, "
				+ "default, default, default, default)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getBoardCategory());
			pstmt.setString(2, board.getBoardWriter());
			pstmt.setString(3, board.getBoardTitle());
			pstmt.setString(4, board.getBoardContent());
			pstmt.setString(5, board.getBoardOriginalFileName());
			pstmt.setString(6, board.getBoardRenameFileName());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReplySeq(Connection conn, Board reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update board set "
				+ "board_reply_seq = board_reply_seq + 1 ";
		
		if(reply.getBoardReplyLev() == 2) {
			query += "where board_ref = ? and board_reply_lev = ?";
		}
		if(reply.getBoardReplyLev() == 3) {
			query += "where board_ref = ? and board_reply_lev = ? "
					+ "and board_reply_ref = ?";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reply.getBoardRef());
			pstmt.setInt(2, reply.getBoardReplyLev());
			if(reply.getBoardReplyLev() == 3) {
				pstmt.setInt(3, reply.getBoardReplyRef());
			}
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertReply(Connection conn, Board reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into board values ("
				+ "(select max(board_num) + 1 from board), "
				+ "?, ?, ?, ?, null, null, ?, ";
		
		if(reply.getBoardReplyLev() == 2) {  //원글의 댓글일 때, board_reply_ref 는 자기번호를 기록함
			query += "(select max(board_num) + 1 from board), "
					+ "2, ?, default, default)";
		}
		if(reply.getBoardReplyLev() == 3) {  //댓글의 댓글일 때
			query += "?, 3, ?, default, default)";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getBoardCategory());
			pstmt.setString(2, reply.getBoardWriter());
			pstmt.setString(3, reply.getBoardTitle());
			pstmt.setString(4, reply.getBoardContent());
			pstmt.setInt(5, reply.getBoardRef());
			
			if(reply.getBoardReplyLev() == 2) { 
				pstmt.setInt(6, reply.getBoardReplySeq());
			}
			if(reply.getBoardReplyLev() == 3) { 
				pstmt.setInt(6, reply.getBoardReplyRef());
				pstmt.setInt(7, reply.getBoardReplySeq());
			}
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}		
		
		return result;
	}

	public int deleteBoard(Connection conn, int boardNum, int boardReplyLev) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from board ";
		
		if(boardReplyLev == 1) //원글이면
			query += "where board_ref = ?";
		if(boardReplyLev == 2) //댓글이면
			query += "where board_reply_ref = ?";
		if(boardReplyLev == 3) //대댓글이면
			query += "where board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReply(Connection conn, Board reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update board set board_title = ?, board_content = ?, board_category = ? "
				+ "where board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);			
			pstmt.setString(1, reply.getBoardTitle());
			pstmt.setString(2, reply.getBoardContent());
			pstmt.setString(3, reply.getBoardCategory());
			pstmt.setInt(4, reply.getBoardNum());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateOrgin(Connection conn, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update board set board_title = ?, "
				+ "board_content = ?, board_original_filename = ?, "
				+ "board_rename_filename = ? "
				+ "where board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);			
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setString(3, board.getBoardOriginalFileName());
			pstmt.setString(4, board.getBoardRenameFileName());
			pstmt.setInt(5, board.getBoardNum());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Board> selectTop3(Connection conn) {
		ArrayList<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * " + 
				"from (select rownum rnum, board_num, board_title, board_readcount " + 
				"        from (select * from board " + 
				"                where board_reply_lev = 1 " + 
				"                order by board_readcount desc)) " + 
				"where rnum >= 1 and rnum <= 3";		
		
		try {
			pstmt = conn.prepareStatement(query);
						
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				
				board.setBoardNum(rset.getInt("board_num"));				
				board.setBoardTitle(rset.getString("board_title"));				
				board.setBoardReadCount(rset.getInt("board_readcount"));				
								
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	
}








