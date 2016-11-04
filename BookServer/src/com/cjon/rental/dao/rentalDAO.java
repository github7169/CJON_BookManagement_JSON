package com.cjon.rental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cjon.DBTemplate;

public class rentalDAO {

	public boolean selectStatus(String isbn) {

		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			String sql = "select rstatus from rental where bisbn = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			
			// 빌린 상태가 아예 없을 경우 대여 가능
			if(rs.next()){		
				
			}else {
				result = true;
			}
		
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		}

		return result;
		
	}

	public boolean insert(String email, String isbn) {

		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			String sql = "insert into rental (rstatus, memail, bisbn) values ('rent',?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, isbn);
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

	public String selectStatusMe(String email) {

		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		
		try {
			String sql = "select rental.bisbn, btitle, bauthor from rental, book where rental.bisbn = book.bisbn and rental.memail = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			JSONArray arr = new JSONArray();
			while(rs.next()){
				JSONObject obj = new JSONObject();
				obj.put("isbn", rs.getString("bisbn"));
				obj.put("title", rs.getString("btitle"));
				obj.put("author", rs.getString("bauthor"));
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

	public boolean delete(String isbn) {
		
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			String sql = "delete from rental where bisbn = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
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

	public String select(String keyword) {

		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		
		try {
			String sql = "select bimgbase64, btitle, bauthor, rstatus, rental.memail from book left outer join rental on book.bisbn = rental.bisbn and btitle like '%브랜드%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			
			JSONArray arr = new JSONArray();
			while(rs.next()){
				JSONObject obj = new JSONObject();
				obj.put("img", rs.getString("bimgbase64"));
				obj.put("title", rs.getString("btitle"));
				obj.put("author", rs.getString("bauthor"));
				obj.put("email", rs.getString("rental.memail"));
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
