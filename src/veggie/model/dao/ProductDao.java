package veggie.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import veggie.model.vo.Product;


public class ProductDao {

	//글목록
	public ArrayList<Product> selectList(Connection conn) {
		ArrayList<Product> list = new ArrayList<Product>();
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select * from product order by pr_no desc";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				Product pro = new Product();

				pro.setProNum(rset.getInt("pr_no"));
				pro.setProID(rset.getString("pr_id"));
				pro.setProCtgr(rset.getInt("pr_ctgr"));
				pro.setProName(rset.getString("pr_name"));
				pro.setProDate(rset.getDate("pr_date"));
				pro.setProOrgin(rset.getString("pr_origin"));
				pro.setProPrice(rset.getInt("pr_price"));

				list.add(pro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

	//상품명으로 검색
	public ArrayList<Product> selectSearchTitle(Connection conn, String proName) {
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from product "
				+ "where pr_name like ? "
				+ "order by pr_no desc";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + proName + "%");
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product pro = new Product();

				pro.setProNum(rset.getInt("pr_no"));
				pro.setProID(rset.getString("pr_id"));
				pro.setProCtgr(rset.getInt("pr_ctgr"));
				pro.setProName(rset.getString("pr_name"));
				pro.setProDate(rset.getDate("pr_date"));
				pro.setProOrgin(rset.getString("pr_origin"));
				pro.setProPrice(rset.getInt("pr_price"));

				list.add(pro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}
	
	//상세보기
	public Product selectPro(Connection conn, int proNum) {
		Product pro = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from product where pr_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, proNum);
			
			rset = pstmt.executeQuery();

			if (rset.next()) {
				pro = new Product();

				pro.setProNum(rset.getInt("pr_no"));
				pro.setProID(rset.getString("pr_id"));
				pro.setProCtgr(rset.getInt("pr_ctgr"));
				pro.setProName(rset.getString("pr_name"));
				pro.setProDate(rset.getDate("pr_date"));
				pro.setProOrgin(rset.getString("pr_origin"));
				pro.setProPrice(rset.getInt("pr_price"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return pro;
	}

	//글 작성
	public int insertPro(Connection conn, Product pro) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into product values ("
				+ "(select max(pr_no) + 1 from product), "
				+ "?, ?, ?, sysdate, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, pro.getProID());
			pstmt.setInt(2, pro.getProCtgr());
			pstmt.setString(3, pro.getProName());
			pstmt.setString(4, pro.getProOrgin());
			pstmt.setInt(5, pro.getProPrice());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}	
		
		return result;
	}

	//글 수정
	public int updatePro(Connection conn, Product pro) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update product set pr_ctgr = ?, pr_name = ?, pr_origin = ?, pr_price = ? "
				+ "where pr_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, pro.getProCtgr());
			pstmt.setString(2, pro.getProName());
			pstmt.setString(3, pro.getProOrgin());
			pstmt.setInt(4, pro.getProPrice());
			pstmt.setInt(5, pro.getProNum());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}		
		
		return result;
	}
	
	//글 삭제
	public int deletePro(Connection conn, int proNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from product where pr_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, proNum);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

}
