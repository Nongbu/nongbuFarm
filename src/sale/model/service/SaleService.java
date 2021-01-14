package sale.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import faq.model.dao.FAQDao;

import static common.JDBCTemp.*;

import sale.model.dao.SaleDao;
import sale.model.vo.Sale;

public class SaleService {
	private SaleDao sdao = new SaleDao();
	
	
	public ArrayList<Sale> selectList() {
			Connection conn = getConnection();
			ArrayList<Sale> list = sdao.selectList(conn);
			close(conn);
			return list;
		}


	public int insertSale(Sale sale) {
		Connection conn = getConnection();
		int result = sdao.insertSale(conn, sale);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
}

