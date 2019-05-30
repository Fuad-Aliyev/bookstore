package com.bookstore.database;

import java.io.InputStream;
import java.util.Properties;

public class DBConfiguration {
	private Properties prop = null;
	private InputStream is = null;
	
	private String username = "";
	private String password = "";
	private String url = "";
	
	public DBConfiguration() {
		try {
			prop = new Properties();
			is = this.getClass().getClassLoader().getResourceAsStream("resources/db_config.properties");
			prop.load(is);
			
			setUsername(prop.getProperty("username"));
			setPassword(prop.getProperty("password"));
			setUrl(prop.getProperty("url"));
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public String getPassowrd() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "DBConfiguration Class";
	}
}
