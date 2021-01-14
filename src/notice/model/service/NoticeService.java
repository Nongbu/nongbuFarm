package notice.model.service;

import static common.JDBCTemp.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;

public class NoticeService {
	//DI(Dependency Injection, 의존성 주입)
	private NoticeDao ndao = new NoticeDao();
	
	public NoticeService() {}
	
	//공지사항 전체 조회용
	public ArrayList<Notice> selectList(){
		Connection conn = getConnection();
		ArrayList<Notice> list = ndao.selectList(conn);
		close(conn);
		return list;
	}
	
	//공지사항 상세보기 처리용
	public Notice selectNotice(int noticeNo) {
		Connection conn = getConnection();
		Notice notice = ndao.selectNotice(conn, noticeNo);
		close(conn);
		return notice;
	}

	public int insertNotice(Notice notice) {
		Connection conn = getConnection();
		int result = ndao.insertNotice(conn, notice);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateNotice(Notice notice) {
		Connection conn = getConnection();
		int result = ndao.updateNotice(conn, notice);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int deleteNotice(int noticeNo) {
		Connection conn = getConnection();
		int result = ndao.deleteNotice(conn, noticeNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public ArrayList<Notice> selectSearchTitle(String noticeTitle) {
		Connection conn = getConnection();
		ArrayList<Notice> list = ndao.selectSearchTitle(conn, noticeTitle);
		close(conn);
		return list;
	}

	public ArrayList<Notice> selectSearchWriter(String noticeWriter) {
		Connection conn = getConnection();
		ArrayList<Notice> list = ndao.selectSearchWriter(conn, noticeWriter);
		close(conn);
		return list;
	}

	public ArrayList<Notice> selectSearchDate(Date beginDate, Date endDate) {
		Connection conn = getConnection();
		ArrayList<Notice> list = ndao.selectSearchDate(conn, beginDate, endDate);
		close(conn);
		return list;
	}

	public ArrayList<Notice> selectNewTop3() {
		Connection conn = getConnection();
		ArrayList<Notice> list = ndao.selectNewTop3(conn);
		close(conn);
		return list;
	}
}
