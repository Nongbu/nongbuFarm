package board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDao;
import board.model.vo.Board;
import static common.JDBCTemp.*;

public class BoardService {
	//DI
	private BoardDao bdao = new BoardDao();
	
	public BoardService() {}
	
	//db 테이블 board 에 저장된 총 목록(행)갯수 조회용
	public int getListCount() {
		Connection conn = getConnection();
		int listCount = bdao.getListCount(conn);
		close(conn);
		return listCount;
	}
	
	//전체 목록 조회
	public ArrayList<Board> selectList(int currentPage, int limit){
		Connection conn = getConnection();
		ArrayList<Board> list = bdao.selectList(conn, currentPage, limit);
		close(conn);
		return list;
	}

	public void addReadCount(int boardNum) {
		Connection conn = getConnection();
		int result = bdao.addReadCount(conn, boardNum);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
	}

	public Board selectBoard(int boardNum) {
		Connection conn = getConnection();
		Board board = bdao.selectBoard(conn, boardNum);
		close(conn);
		return board;
	}

	public int insertBoard(Board board) {
		Connection conn = getConnection();
		int result = bdao.insertOriginBoard(conn, board);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public void updateReplySeq(Board reply) {
		Connection conn = getConnection();
		int result = bdao.updateReplySeq(conn, reply);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
	}

	public int insertReply(Board reply) {
		Connection conn = getConnection();
		int result = bdao.insertReply(conn, reply);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteBoard(int boardNum, int boardReplyLev) {
		Connection conn = getConnection();
		int result = bdao.deleteBoard(conn, boardNum, boardReplyLev);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateReply(Board reply) {
		Connection conn = getConnection();
		int result = bdao.updateReply(conn, reply);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateOrigin(Board board) {
		Connection conn = getConnection();
		int result = bdao.updateOrgin(conn, board);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public ArrayList<Board> selectTop3() {
		Connection conn = getConnection();
		ArrayList<Board> list = bdao.selectTop3(conn);
		close(conn);
		return list;
	}

	
	
	
	
}
