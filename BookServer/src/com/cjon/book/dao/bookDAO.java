package com.cjon.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cjon.book.common.DBTemplate;

public class bookDAO {

	public String select(String keyword) {

		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		
		try {
			String sql = "select bisbn, bimgbase64, btitle, bauthor, bprice from book where btitle like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			
			JSONArray arr = new JSONArray();
			while(rs.next()){
				JSONObject obj = new JSONObject();
				obj.put("isbn", rs.getString("bisbn"));
				obj.put("img", rs.getString("bimgbase64"));
				obj.put("title", rs.getString("btitle"));
				obj.put("author", rs.getString("bauthor"));
				obj.put("price", rs.getString("bprice"));
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

	public boolean update(String isbn, String title, String author, String price) {

		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			String sql = "update book set btitle = ?, bauthor = ?, bprice = ? where bisbn = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, author);
			pstmt.setInt(3, Integer.parseInt(price));
			pstmt.setString(4, isbn);
			int count = pstmt.executeUpdate();	// update 개수
		
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

	public boolean insert(String isbn, String img, String title, String author, String price) {

		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			String sql = "insert into book (bisbn, bimgbase64, btitle, bauthor, bprice) values (?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			pstmt.setString(2, img);
			pstmt.setString(3, title);
			pstmt.setString(4, author);
			pstmt.setInt(5, Integer.parseInt(price));
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

	public boolean insert(String isbn) {
		
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			String sql = "delete from book where bisbn = ?";
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

	public String selectDetail(String isbn) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		
		try {
			String sql = "select bpage, btranslator, bsupplement, bpublisher from book where bisbn = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			
			JSONArray arr = new JSONArray();
			while(rs.next()){
				JSONObject obj = new JSONObject();
				obj.put("page", rs.getString("bpage"));
				obj.put("translator", rs.getString("btranslator"));
				obj.put("supplement", rs.getString("bsupplement"));
				obj.put("publisher", rs.getString("bpublisher"));
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
