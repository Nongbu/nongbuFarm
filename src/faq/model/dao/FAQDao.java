package faq.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import faq.model.vo.FAQ;

public class FAQDao {
	public FAQDao() {}

	public ArrayList<FAQ> selectList(Connection conn) {
		ArrayList<FAQ> list = new ArrayList<FAQ>();
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select * from faq order by faq_num desc";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				FAQ faq = new FAQ();

				faq.setFaqNum(rset.getInt("faq_num"));
				faq.setFaqCategory(rset.getString("faq_category"));
				faq.setFaqTitle(rset.getString("faq_title"));
				faq.setFaqContent(rset.getString("faq_content"));

				list.add(faq);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

	public ArrayList<FAQ> selectSearchTitle(Connection conn, String faqTitle) {
		ArrayList<FAQ> list = new ArrayList<FAQ>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from faq "
				+ "where faq_title like ? "
				+ "order by faq_num desc";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + faqTitle + "%");
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				FAQ faq = new FAQ();

				faq.setFaqNum(rset.getInt("faq_num"));
				faq.setFaqCategory(rset.getString("faq_category"));
				faq.setFaqTitle(rset.getString("faq_title"));
				faq.setFaqContent(rset.getString("faq_content"));

				list.add(faq);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public FAQ selectFaq(Connection conn, int faqNum) {
		FAQ faq = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from faq where faq_num = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, faqNum);
			
			rset = pstmt.executeQuery();

			if (rset.next()) {
				faq = new FAQ();

				faq.setFaqNum(faqNum);
				faq.setFaqCategory(rset.getString("faq_category"));
				faq.setFaqTitle(rset.getString("faq_title"));
				faq.setFaqContent(rset.getString("faq_content"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return faq;
	}

	public int insertFaq(Connection conn, FAQ faq) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into faq values ("
				+ "(select max(faq_num) + 1 from faq), "
				+ "?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, faq.getFaqTitle());
			pstmt.setString(2, faq.getFaqCategory());
			pstmt.setString(3, faq.getFaqContent());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}	
		
		return result;
	}

	public int updateFaq(Connection conn, FAQ faq) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update faq set faq_title = ?, faq_content = ?, faq_category = ? "
				+ "where faq_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, faq.getFaqTitle());
			pstmt.setString(2, faq.getFaqContent());
			pstmt.setString(3, faq.getFaqCategory());
			pstmt.setInt(4, faq.getFaqNum());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}		
		
		return result;
	}

	public int deleteFaq(Connection conn, int faqNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from faq where faq_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, faqNum);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

}
