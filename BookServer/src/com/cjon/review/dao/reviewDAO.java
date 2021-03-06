package com.cjon.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cjon.DBTemplate;

public class reviewDAO {

	public String select(String isbn) {

		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		
		try {
			String sql = "select  member.memail, mname, rgrade, rcontent, rdate, review.seq from member, review where bisbn = ? and member.memail = review.memail order by rgrade desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			
			JSONArray arr = new JSONArray();
			while(rs.next()){
				JSONObject obj = new JSONObject();
				obj.put("name", rs.getString("mname"));
				obj.put("email", rs.getString("member.memail"));
				obj.put("grade", rs.getString("rgrade"));
				obj.put("content", rs.getString("rcontent"));
				obj.put("date", rs.getString("rdate"));
				obj.put("no", rs.getString("review.seq"));
				arr.add(obj);
			}
			result = arr.toJSONString();
		
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		}

		return result;
	}

	public boolean delete(String seq) {
		
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			String sql = "delete from review where seq = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, seq);
			int count = pstmt.executeUpdate();	// delete 개수
		
			if( count == 1 ) {
				result = true;
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		}

		return result;
	}

	public boolean insert(String grade, String content, String email, String isbn) {

		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			String sql = "insert into review (rgrade, rcontent, rdate, memail, bisbn) values (?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, grade);
			pstmt.setString(2, content);
			pstmt.setString(3, email);
			pstmt.setString(4, isbn);
			int count = pstmt.executeUpdate();	// insert 개수
		
			if( count == 1 ) {
				result = true;
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		}

		return result;		
	}

	public String selectKeyword(String keyword) {

		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		
		try {
			String sql = "select btitle, member.mname, review.memail, rgrade, rcontent, rdate from review, book, member "
						+ "where rcontent like ? and review.bisbn = book.bisbn and review.memail = member.memail;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			
			JSONArray arr = new JSONArray();
			while(rs.next()){
				JSONObject obj = new JSONObject();
				obj.put("title", rs.getString("btitle"));
				obj.put("name", rs.getString("member.mname"));
				obj.put("email", rs.getString("review.memail"));
				obj.put("grade", rs.getString("rgrade"));
				obj.put("content", rs.getString("rcontent"));
				obj.put("date", rs.getString("rdate"));
				arr.add(obj);
			}
			result = arr.toJSONString();
		
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		}

		return result;
	}

}
