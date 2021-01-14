package sale.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import sale.model.vo.Sale;

public class SaleDao {

	public ArrayList<Sale> selectList(Connection conn) {
		ArrayList<Sale> list = new ArrayList<Sale>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM SELL " ;
		
		try {
			stmt = conn.prepareStatement(query);
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Sale sale = new Sale();
				
				sale.setProductNo(rset.getString("product_no"));
				sale.setSellerId(rset.getString("seller_id"));
				sale.setSellerProduct(rset.getString("seller_product"));
				sale.setUserAddress(rset.getString("user_address"));
				sale.setPrice(rset.getInt("price"));
				sale.setSellerOriginalFileName(rset.getString("seller_original_filename"));
				sale.setSellerRenameFileName(rset.getString("seller_rename_filename"));
				
				list.add(sale);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public int insertSale(Connection conn, Sale sale) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into sell valuse ( "
				+ "(select max(sell_num) + 1 from sell), "
				+ "?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, sale.getProductNo());
			pstmt.setString(2, sale.getSellerId());
			pstmt.setString(3, sale.getSellerProduct());
			
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			}finally {
				close(pstmt);
			}
		return result;
	}

}
