package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.database.Close;
import com.bookstore.database.DBConnection;
import com.bookstore.entity.Users;

public class UserDAO implements GenericDAO<Users> {
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String query = "";
	
	public UserDAO() {}
	
	@Override
	public Users create(Users t) {
		try {
			query = "INSERT INTO users(email, password, full_name) VALUES(?,?,?)";
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, t.getEmail());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getFullName());
			
			ps.executeUpdate();
		} catch (Exception e) {
			Close.close(conn, ps);
		}
		
		return t;
	}
	
	@Override
	public int findByEmail(String email) {
		int flag = 0;
		try {
			query = "SELECT * FROM users WHERE email=?";
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			if (rs.first()) {
				flag = 1;
			}
		} catch (Exception e) {
			Close.close(conn, ps, rs);
		}
		
		return flag;
	}

	@Override
	public int update(Users t) {
		int flag = 0;
		
		try {
			query = "UPDATE users SET email=?, password=?, full_name=? WHERE user_id=?";
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, t.getEmail());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getPassword());
			ps.setInt(4, t.getUserId());
			
			flag = ps.executeUpdate();
		} catch (Exception e) {
			Close.close(conn, ps);
		}
		
		return flag;
	}

	@Override
	public Users get(int id) {
		Users user = new Users();
		query = "SELECT * FROM users WHERE user_id=?";
		
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if (rs.first()) {
				user.setUserId(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFullName(rs.getString("full_name"));
			}
		} catch (Exception e) {
			Close.close(conn, ps, rs);
		}
		
		return user;
	}

	@Override
	public int delete(int id) {
		int flag = 0;
		query = "DELETE FROM users WHERE user_id=?";
		
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			flag = ps.executeUpdate();
			
		} catch (Exception e) {
			Close.close(conn, ps);
		}
		
		return flag;
	}

	@Override
	public List<Users> listAll() {
		List<Users> users = new ArrayList<Users>();
		query = "SELECT * FROM users";
		
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Users user  = new Users();
				user.setUserId(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setFullName(rs.getString("full_name"));
				user.setPassword(rs.getString("password"));
				
				users.add(user);
			}
		} catch (Exception e) {
			Close.close(conn, ps, rs);
		}
		
		return users;
	}

	@Override
	public long count() {
		String query = "SELECT COUNT(*) FROM users";
		long numOfRows = 0;
		
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(query);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				numOfRows = (long) rs.getInt(1);
			}
		} catch (Exception e) {
			Close.close(conn, ps, rs);
		}
		
		return numOfRows;
	}

}
