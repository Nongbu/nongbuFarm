package free.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import free.model.vo.Free;

public class FreeDao {
	public FreeDao() {}

	public ArrayList<Free> selectList(Connection conn, int currentPage, int limit) {
		ArrayList<Free> list = new ArrayList<Free>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * " + 
				"FROM (SELECT ROWNUM RNUM, FREENO, FREETITLE, FREEWRITER,  " + 
				"                        FREECONTENT, ORIGINALFILENAME, " + 
				"                        RENAMEFILENAME, FREE_REF, FREE_REPLY_REF,  " + 
				"                        FREE_REPLY_LEV, FREE_REPLY_SEQ, FREE_READCOUNT,  " + 
				"                        FREEDATE " + 
				"            FROM (SELECT * FROM FREE " + 
				"                      ORDER BY FREE_REF DESC, FREE_REPLY_REF DESC,  " + 
				"                                     FREE_REPLY_LEV ASC, FREE_REPLY_SEQ ASC)) " + 
				"WHERE  RNUM >= ? AND RNUM <= ?";
		
		int startRow = (currentPage - 1) * limit + 1;  //3 page 이면 시작행은 21
		int endRow = startRow + limit - 1;   //3 page 이면 끝행은 30
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Free free = new Free();
				
				free.setFreeNo(rset.getInt("freeno"));
				free.setFreeWriter(rset.getString("freewriter"));
				free.setFreeTitle(rset.getString("freetitle"));
				free.setFreeContent(rset.getString("freecontent"));
				free.setOriginalFileName(rset.getString("originalfilename"));
				free.setRenameFileName(rset.getString("renamefilename"));
				free.setFreeRef(rset.getInt("free_ref"));
				free.setFreeReplyLev(rset.getInt("free_reply_lev"));
				free.setFreeReplyRef(rset.getInt("free_reply_ref"));
				free.setFreeReplySeq(rset.getInt("free_reply_seq"));
				free.setFreeReadCount(rset.getInt("free_readcount"));
				free.setFreeDate(rset.getDate("freedate"));
								
				list.add(free);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from free";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public int addReadCount(Connection conn, int freeNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update free "
				+ "set free_readcount = free_readcount + 1 "
				+ "where freeno = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public Free selectFree(Connection conn, int freeNo) {
		Free free = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from free where freeno = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				free = new Free();
				
				free.setFreeNo(freeNo);
				free.setFreeWriter(rset.getString("freewriter"));
				free.setFreeTitle(rset.getString("freetitle"));
				free.setFreeContent(rset.getString("freecontent"));
				free.setOriginalFileName(rset.getString("originalfilename"));
				free.setRenameFileName(rset.getString("renamefilename"));
				free.setFreeRef(rset.getInt("free_ref"));
				free.setFreeReplyLev(rset.getInt("free_reply_lev"));
				free.setFreeReplyRef(rset.getInt("free_reply_ref"));
				free.setFreeReplySeq(rset.getInt("free_reply_seq"));
				free.setFreeReadCount(rset.getInt("free_readcount"));
				free.setFreeDate(rset.getDate("freedate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return free;
	}

	public int insertOriginFree(Connection conn, Free free) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into free values ("
				+ "(select max(freeno) + 1 from free), ?, ?, ?, ?, ?, "
				+ "(select max(freeno) + 1 from free), null, "
				+ "default, default, default, default)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, free.getFreeWriter());
			pstmt.setString(2, free.getFreeTitle());
			pstmt.setString(3, free.getFreeContent());
			pstmt.setString(4, free.getOriginalFileName());
			pstmt.setString(5, free.getRenameFileName());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReplySeq(Connection conn, Free reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update free set "
				+ "free_reply_seq = free_reply_seq + 1 ";
		
		if(reply.getFreeReplyLev() == 2) {
			query += "where free_ref = ? and free_reply_lev = ?";
		}
		if(reply.getFreeReplyLev() == 3) {
			query += "where free_ref = ? and free_reply_lev = ? "
					+ "and free_reply_ref = ?";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reply.getFreeRef());
			pstmt.setInt(2, reply.getFreeReplyLev());
			if(reply.getFreeReplyLev() == 3) {
				pstmt.setInt(3, reply.getFreeReplyRef());
			}
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertReply(Connection conn, Free reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into free values ("
				+ "(select max(freeno) + 1 from free), "
				+ "?, ?, ?, null, null, ?, ";
		
		if(reply.getFreeReplyLev() == 2) {  //원글의 댓글일 때, board_reply_ref 는 자기번호를 기록함
			query += "(select max(freeno) + 1 from free), "
					+ "2, ?, default, default)";
		}
		if(reply.getFreeReplyLev() == 3) {  //댓글의 댓글일 때
			query += "?, 3, ?, default, default)";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getFreeWriter());
			pstmt.setString(2, reply.getFreeTitle());
			pstmt.setString(3, reply.getFreeContent());
			pstmt.setInt(4, reply.getFreeRef());
			
			if(reply.getFreeReplyLev() == 2) { 
				pstmt.setInt(5, reply.getFreeReplySeq());
			}
			if(reply.getFreeReplyLev() == 3) { 
				pstmt.setInt(5, reply.getFreeReplyRef());
				pstmt.setInt(6, reply.getFreeReplySeq());
			}
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}		
		
		return result;
	}

	public int deleteFree(Connection conn, int freeNo, int freeReplyLev) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from free ";
		
		if(freeReplyLev == 1) //원글이면
			query += "where free_ref = ?";
		if(freeReplyLev == 2) //댓글이면
			query += "where free_reply_ref = ?";
		if(freeReplyLev == 3) //대댓글이면
			query += "where freeno = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReply(Connection conn, Free reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update free set freetitle = ?, freecontent = ? "
				+ "where freeno = ?";
		
		try {
			pstmt = conn.prepareStatement(query);			
			pstmt.setString(1, reply.getFreeTitle());
			pstmt.setString(2, reply.getFreeContent());
			pstmt.setInt(3, reply.getFreeNo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateOrgin(Connection conn, Free free) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update free set freetitle = ?, "
				+ "freecontent = ?, originalfilename = ?, "
				+ "renamefilename = ? "
				+ "where freeno = ?";
		
		try {
			pstmt = conn.prepareStatement(query);			
			pstmt.setString(1, free.getFreeTitle());
			pstmt.setString(2, free.getFreeContent());
			pstmt.setString(3, free.getOriginalFileName());
			pstmt.setString(4, free.getRenameFileName());
			pstmt.setInt(5, free.getFreeNo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	 public ArrayList<Free> selectSearchTitle(Connection conn, String freeTitle) {
	      ArrayList<Free> list = new ArrayList<Free>();
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;

	      String query = "select * from free "
	            + "where freetitle like ? "
	            + "order by freeno desc";

	      try {
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, "%" + freeTitle + "%"); //?양옆에는 % 쓸 수 없어서 setString에 따로 쓴다. 이걸 포함한 것을 검색한다.

	         rset = pstmt.executeQuery();
	         
	         while (rset.next()) {
	            Free free = new Free();

	            free.setFreeNo(rset.getInt("freeno"));
	            free.setFreeTitle(rset.getString("freetitle"));
	            free.setFreeDate(rset.getDate("freedate"));
	            free.setFreeWriter(rset.getString("freewriter"));
	            free.setFreeContent(rset.getString("freecontent"));
	            free.setOriginalFileName(rset.getString("originalfilename"));
	            free.setRenameFileName(rset.getString("renamefilename"));

	            list.add(free);
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         close(rset);
	         close(pstmt);
	      }

	      return list;
	   }
	   
	   public ArrayList<Free> selectSearchWriter(Connection conn, String freeWriter) {
	      ArrayList<Free> list = new ArrayList<Free>();
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;

	      String query = "select * from free "
	      		+ " where freewriter like ? "
	      		+ " order by freeno desc";

	      try {
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, "%" + freeWriter + "%");

	         rset = pstmt.executeQuery();
	         
	         while (rset.next()) {
	            Free free = new Free();

	            free.setFreeNo(rset.getInt("freeno"));
	            free.setFreeTitle(rset.getString("freetitle"));
	            free.setFreeDate(rset.getDate("freedate"));
	            free.setFreeWriter(rset.getString("freewriter"));
	            free.setFreeContent(rset.getString("freecontent"));
	            free.setOriginalFileName(rset.getString("originalfilename"));
	            free.setRenameFileName(rset.getString("renamefilename"));

	            list.add(free);
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         close(rset);
	         close(pstmt);
	      }

	      return list;
	   }

	   public ArrayList<Free> selectSearchDate(Connection conn, Date beginDate, Date endDate) {
	      ArrayList<Free> list = new ArrayList<Free>();
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;

	      String query = "select * from free "
	      		+ "where freedate between ? and ? "
	      		+ "order by freeno desc";

	      try {
	         pstmt = conn.prepareStatement(query);
	         pstmt.setDate(1, beginDate);
	         pstmt.setDate(2, endDate);
	         
	         rset = pstmt.executeQuery(); //pstmt에 query문 담아줬으므로 executequery 괄호는 빈칸으로 실행시킨다.

	         while (rset.next()) {
	            Free free = new Free();

	            free.setFreeNo(rset.getInt("freeno"));
	            free.setFreeTitle(rset.getString("freetitle"));
	            free.setFreeDate(rset.getDate("freedate"));
	            free.setFreeWriter(rset.getString("freewriter"));
	            free.setFreeContent(rset.getString("freecontent"));
	            free.setOriginalFileName(rset.getString("originalfilename"));
	            free.setRenameFileName(rset.getString("renamefilename"));

	            list.add(free);
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

