package free.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import free.model.dao.FreeDao;
import free.model.vo.Free;

import static common.JDBCTemp.*;

public class FreeService {
	//DI
	private FreeDao fdao = new FreeDao();
	
	public FreeService() {}
	
	//db 테이블 board 에 저장된 총 목록(행)갯수 조회용
	public int getListCount() {
		Connection conn = getConnection();
		int listCount = fdao.getListCount(conn);
		close(conn);
		return listCount;
	}
	
	//전체 목록 조회
	public ArrayList<Free> selectList(int currentPage, int limit){
		Connection conn = getConnection();
		ArrayList<Free> list = fdao.selectList(conn, currentPage, limit);
		close(conn);
		return list;
	}

	public void addReadCount(int freeNo) {
		Connection conn = getConnection();
		int result = fdao.addReadCount(conn, freeNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
	}

	public Free selectFree(int freeNo) {
		Connection conn = getConnection();
		Free free = fdao.selectFree(conn, freeNo);
		close(conn);
		return free;
	}

	public int insertFree(Free free) {
		Connection conn = getConnection();
		int result = fdao.insertOriginFree(conn, free);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public void updateReplySeq(Free reply) {
		Connection conn = getConnection();
		int result = fdao.updateReplySeq(conn, reply);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
	}

	public int insertReply(Free reply) {
		Connection conn = getConnection();
		int result = fdao.insertReply(conn, reply);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteFree(int freeNo, int freeReplyLev) {
		Connection conn = getConnection();
		int result = fdao.deleteFree(conn, freeNo, freeReplyLev);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateReply(Free reply) {
		Connection conn = getConnection();
		int result = fdao.updateReply(conn, reply);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateOrigin(Free free) {
		Connection conn = getConnection();
		int result = fdao.updateOrgin(conn, free);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	public ArrayList<Free> selectSearchTitle(String freeTitle) {
		Connection conn = getConnection();
		ArrayList<Free> list = fdao.selectSearchTitle(conn, freeTitle);
		close(conn);
		return list;
	}

	public ArrayList<Free> selectSearchWriter(String freeWriter) {
		Connection conn = getConnection();
		ArrayList<Free> list = fdao.selectSearchWriter(conn, freeWriter);
		close(conn);
		return list;
	}

	public ArrayList<Free> selectSearchDate(Date beginDate, Date endDate) {
		Connection conn = getConnection();
		ArrayList<Free> list = fdao.selectSearchDate(conn, beginDate, endDate);
		close(conn);
		return list;
	}
	
	
}
