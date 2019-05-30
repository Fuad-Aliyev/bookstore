package com.bookstore.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Close {
	
	private Close() {}
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void close(Connection conn, PreparedStatement ps) {
		try {
			if (conn != null) {
				conn.close();
			}
			if(ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
