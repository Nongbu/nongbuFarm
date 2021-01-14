package veggie.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import faq.model.vo.FAQ;
import veggie.model.dao.ProductDao;
import veggie.model.vo.Product;



public class ProductService {
	private ProductDao pdao = new ProductDao();

	public ProductService() {}

	//리스트 출력
	public ArrayList<Product> selectList() {
		Connection conn = getConnection();
		ArrayList<Product> list = pdao.selectList(conn);
		close(conn);
		return list;
	}

	//상품명으로 검색
	public ArrayList<Product> selectSearchTitle(String proName) {
		Connection conn = getConnection();
		ArrayList<Product> list = pdao.selectSearchTitle(conn, proName);
		close(conn);
		return list;
	}
	
	
	//상세보기
	public Product selectPro(int proNum) {
		Connection conn = getConnection();
		Product paq = pdao.selectPro(conn, proNum);
		close(conn);
		return paq;
	}

	//새로운 글 작성
	public int insertPro(Product product) {
		Connection conn = getConnection();
		int result = pdao.insertPro(conn, product);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	//글 수정
	public int updatePro(Product product) {
		Connection conn = getConnection();
		int result = pdao.updatePro(conn, product);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	//글 삭제
	public int deletePro(int proNum) {
		Connection conn = getConnection();
		int result = pdao.deletePro(conn, proNum);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
}
