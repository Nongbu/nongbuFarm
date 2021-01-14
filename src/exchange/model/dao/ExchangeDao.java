package exchange.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import exchange.model.vo.Exchange;


public class ExchangeDao {
	public ExchangeDao() {}

	public ArrayList<Exchange> selectList(Connection conn, int currentPage, int limit) {
		ArrayList<Exchange> list = new ArrayList<Exchange>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT * " + "FROM (SELECT ROWNUM RNUM, EXNO, EXTITLE, EXWRITER,  "
				+ "                        EXCONTENT, ORIGINALFILENAME, "
				+ "                        RENAMEFILENAME, EX_REF, EX_REPLY_REF,  "
				+ "                        EX_REPLY_LEV, EX_REPLY_SEQ, EX_READCOUNT,  "
				+ "                        EXDATE " + "            FROM (SELECT * FROM EXCHANGE "
				+ "                      ORDER BY EX_REF DESC, EX_REPLY_REF DESC,  "
				+ "                                     EX_REPLY_LEV ASC, EX_REPLY_SEQ ASC)) "
				+ "WHERE  RNUM >= ? AND RNUM <= ?";

		int startRow = (currentPage - 1) * limit + 1; // 3 page 이면 시작행은 21
		int endRow = startRow + limit - 1; // 3 page 이면 끝행은 30

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Exchange exchange = new Exchange();

				exchange.setExNo(rset.getInt("exno"));
				exchange.setExWriter(rset.getString("exwriter"));
				exchange.setExTitle(rset.getString("extitle"));
				exchange.setExContent(rset.getString("excontent"));
				exchange.setOriginalFileName(rset.getString("originalfilename"));
				exchange.setRenameFileName(rset.getString("renamefilename"));
				exchange.setExRef(rset.getInt("ex_ref"));
				exchange.setExReplyLev(rset.getInt("ex_reply_lev"));
				exchange.setExReplyRef(rset.getInt("ex_reply_ref"));
				exchange.setExReplySeq(rset.getInt("ex_reply_seq"));
				exchange.setExReadCount(rset.getInt("ex_readcount"));
				exchange.setExDate(rset.getDate("exdate"));

				list.add(exchange);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from exchange";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return listCount;
	}

	public int addReadCount(Connection conn, int exNo) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update exchange " + "set ex_readcount = ex_readcount + 1 " + "where exno = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, exNo);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Exchange selectExchange(Connection conn, int exNo) {
		Exchange exchange = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from exchange where exno = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, exNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				exchange = new Exchange();

				exchange.setExNo(exNo);
				exchange.setExWriter(rset.getString("exwriter"));
				exchange.setExTitle(rset.getString("extitle"));
				exchange.setExContent(rset.getString("excontent"));
				exchange.setOriginalFileName(rset.getString("originalfilename"));
				exchange.setRenameFileName(rset.getString("renamefilename"));
				exchange.setExRef(rset.getInt("ex_ref"));
				exchange.setExReplyLev(rset.getInt("ex_reply_lev"));
				exchange.setExReplyRef(rset.getInt("ex_reply_ref"));
				exchange.setExReplySeq(rset.getInt("ex_reply_seq"));
				exchange.setExReadCount(rset.getInt("ex_readcount"));
				exchange.setExDate(rset.getDate("exdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return exchange;
	}

	public int insertOriginExchange(Connection conn, Exchange exchange) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into exchange values (" + "(select max(exno) + 1 from exchange), ?, ?, ?, ?, ?, "
				+ "(select max(exno) + 1 from exchange), null, " + "default, default, default, default)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, exchange.getExWriter());
			pstmt.setString(2, exchange.getExTitle());
			pstmt.setString(3, exchange.getExContent());
			pstmt.setString(4, exchange.getOriginalFileName());
			pstmt.setString(5, exchange.getRenameFileName());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateReplySeq(Connection conn, Exchange reply) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update exchange set " + "ex_reply_seq = ex_reply_seq + 1 ";

		if (reply.getExReplyLev() == 2) {
			query += "where ex_ref = ? and ex_reply_lev = ?";
		}
		if (reply.getExReplyLev() == 3) {
			query += "where ex_ref = ? and ex_reply_lev = ? " + "and ex_reply_ref = ?";
		}

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reply.getExRef());
			pstmt.setInt(2, reply.getExReplyLev());
			if (reply.getExReplyLev() == 3) {
				pstmt.setInt(3, reply.getExReplyRef());
			}

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertReply(Connection conn, Exchange reply) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into exchange values (" + "(select max(exno) + 1 from exchange), "
				+ "?, ?, ?, null, null, ?, ";

		if (reply.getExReplyLev() == 2) { // 원글의 댓글일 때, board_reply_ref 는 자기번호를 기록함
			query += "(select max(exno) + 1 from exchange), " + "2, ?, default, default)";
		}
		if (reply.getExReplyLev() == 3) { // 댓글의 댓글일 때
			query += "?, 3, ?, default, default)";
		}

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getExWriter());
			pstmt.setString(2, reply.getExTitle());
			pstmt.setString(3, reply.getExContent());
			pstmt.setInt(4, reply.getExRef());

			if (reply.getExReplyLev() == 2) {
				pstmt.setInt(5, reply.getExReplySeq());
			}
			if (reply.getExReplyLev() == 3) {
				pstmt.setInt(5, reply.getExReplyRef());
				pstmt.setInt(6, reply.getExReplySeq());
			}

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteExchange(Connection conn, int exNo, int exReplyLev) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from exchange ";

		if (exReplyLev == 1) // 원글이면
			query += "where ex_ref = ?";
		if (exReplyLev == 2) // 댓글이면
			query += "where ex_reply_ref = ?";
		if (exReplyLev == 3) // 대댓글이면
			query += "where exno = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, exNo);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateReply(Connection conn, Exchange reply) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update exchange set extitle = ?, excontent = ? " + "where exno = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getExTitle());
			pstmt.setString(2, reply.getExContent());
			pstmt.setInt(3, reply.getExNo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateOrgin(Connection conn, Exchange exchange) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update exchange set extitle = ?, " + "excontent = ?, originalfilename = ?, "
				+ "renamefilename = ? " + "where exno = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, exchange.getExTitle());
			pstmt.setString(2, exchange.getExContent());
			pstmt.setString(3, exchange.getOriginalFileName());
			pstmt.setString(4, exchange.getRenameFileName());
			pstmt.setInt(5, exchange.getExNo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public ArrayList<Exchange> selectSearchTitle(Connection conn, String exTitle) {
		ArrayList<Exchange> list = new ArrayList<Exchange>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from exchange " + "where extitle like ? " + "order by exno desc";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + exTitle + "%"); // ?양옆에는 % 쓸 수 없어서 setString에 따로 쓴다. 이걸 포함한 것을 검색한다.

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Exchange exchange = new Exchange();

				exchange.setExNo(rset.getInt("exno"));
				exchange.setExTitle(rset.getString("extitle"));
				exchange.setExDate(rset.getDate("exdate"));
				exchange.setExWriter(rset.getString("exwriter"));
				exchange.setExContent(rset.getString("excontent"));
				exchange.setOriginalFileName(rset.getString("originalfilename"));
				exchange.setRenameFileName(rset.getString("renamefilename"));

				list.add(exchange);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public ArrayList<Exchange> selectSearchWriter(Connection conn, String exWriter) {
		ArrayList<Exchange> list = new ArrayList<Exchange>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from exchange " + " where exwriter like ? " + " order by exno desc";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + exWriter + "%");

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Exchange exchange = new Exchange();

				exchange.setExNo(rset.getInt("exno"));
				exchange.setExTitle(rset.getString("extitle"));
				exchange.setExDate(rset.getDate("exdate"));
				exchange.setExWriter(rset.getString("exwriter"));
				exchange.setExContent(rset.getString("excontent"));
				exchange.setOriginalFileName(rset.getString("originalfilename"));
				exchange.setRenameFileName(rset.getString("renamefilename"));

				list.add(exchange);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<Exchange> selectSearchDate(Connection conn, Date beginDate, Date endDate) {
		ArrayList<Exchange> list = new ArrayList<Exchange>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from exchange " + "where exdate between ? and ? " + "order by exno desc";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDate(1, beginDate);
			pstmt.setDate(2, endDate);

			rset = pstmt.executeQuery(); // pstmt에 query문 담아줬으므로 executequery 괄호는 빈칸으로 실행시킨다.

			while (rset.next()) {
				Exchange exchange = new Exchange();

				exchange.setExNo(rset.getInt("exno"));
				exchange.setExTitle(rset.getString("extitle"));
				exchange.setExDate(rset.getDate("exdate"));
				exchange.setExWriter(rset.getString("exwriter"));
				exchange.setExContent(rset.getString("excontent"));
				exchange.setOriginalFileName(rset.getString("originalfilename"));
				exchange.setRenameFileName(rset.getString("renamefilename"));

				list.add(exchange);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}
}
