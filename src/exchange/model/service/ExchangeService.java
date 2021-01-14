package exchange.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import exchange.model.dao.ExchangeDao;
import exchange.model.vo.Exchange;


public class ExchangeService {
	//DI
		private ExchangeDao edao = new ExchangeDao();
		
		public ExchangeService() {}
		
		//db 테이블 board 에 저장된 총 목록(행)갯수 조회용
		public int getListCount() {
			Connection conn = getConnection();
			int listCount = edao.getListCount(conn);
			close(conn);
			return listCount;
		}
		
		//전체 목록 조회
		public ArrayList<Exchange> selectList(int currentPage, int limit){
			Connection conn = getConnection();
			ArrayList<Exchange> list = edao.selectList(conn, currentPage, limit);
			close(conn);
			return list;
		}

		public void addReadCount(int exNo) {
			Connection conn = getConnection();
			int result = edao.addReadCount(conn, exNo);
			if(result > 0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
		}

		public Exchange selectExchange(int exNo) {
			Connection conn = getConnection();
			Exchange exchange = edao.selectExchange(conn, exNo);
			close(conn);
			return exchange;
		}

		public int insertExchange(Exchange exchange) {
			Connection conn = getConnection();
			int result = edao.insertOriginExchange(conn, exchange);
			if(result > 0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
			return result;
		}

		public void updateReplySeq(Exchange reply) {
			Connection conn = getConnection();
			int result = edao.updateReplySeq(conn, reply);
			if(result > 0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
		}

		public int insertReply(Exchange reply) {
			Connection conn = getConnection();
			int result = edao.insertReply(conn, reply);
			if(result > 0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
			return result;
		}

		public int deleteExchange(int exNo, int exReplyLev) {
			Connection conn = getConnection();
			int result = edao.deleteExchange(conn, exNo, exReplyLev);
			if(result > 0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
			return result;
		}

		public int updateReply(Exchange reply) {
			Connection conn = getConnection();
			int result = edao.updateReply(conn, reply);
			if(result > 0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
			return result;
		}

		public int updateOrigin(Exchange exchange) {
			Connection conn = getConnection();
			int result = edao.updateOrgin(conn, exchange);
			if(result > 0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
			return result;
		}
		
		public ArrayList<Exchange> selectSearchTitle(String exTitle) {
			Connection conn = getConnection();
			ArrayList<Exchange> list = edao.selectSearchTitle(conn, exTitle);
			close(conn);
			return list;
		}

		public ArrayList<Exchange> selectSearchWriter(String exWriter) {
			Connection conn = getConnection();
			ArrayList<Exchange> list = edao.selectSearchWriter(conn, exWriter);
			close(conn); 
			return list;
		}

		public ArrayList<Exchange> selectSearchDate(Date beginDate, Date endDate) {
			Connection conn = getConnection();
			ArrayList<Exchange> list = edao.selectSearchDate(conn, beginDate, endDate);
			close(conn);
			return list;
		}
}
