package faq.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import faq.model.dao.FAQDao;
import faq.model.vo.FAQ;

public class FAQService {
	private FAQDao fdao = new FAQDao();

	public FAQService() {}

	public ArrayList<FAQ> selectList() {
		Connection conn = getConnection();
		ArrayList<FAQ> list = fdao.selectList(conn);
		close(conn);
		return list;
	}

	public ArrayList<FAQ> selectSearchTitle(String faqTitle) {
		Connection conn = getConnection();
		ArrayList<FAQ> list = fdao.selectSearchTitle(conn, faqTitle);
		close(conn);
		return list;
	}

	public FAQ selectFaq(int faqNum) {
		Connection conn = getConnection();
		FAQ faq = fdao.selectFaq(conn, faqNum);
		close(conn);
		return faq;
	}

	public int insertFaq(FAQ faq) {
		Connection conn = getConnection();
		int result = fdao.insertFaq(conn, faq);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateFaq(FAQ faq) {
		Connection conn = getConnection();
		int result = fdao.updateFaq(conn, faq);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteFaq(int faqNum) {
		Connection conn = getConnection();
		int result = fdao.deleteFaq(conn, faqNum);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

}
