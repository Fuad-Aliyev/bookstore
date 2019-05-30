package com.bookstore.service;

import java.util.List;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UsersService implements GenericService<Users>{
	
	private UserDAO userDao = new UserDAO();
	
	@Override
	public Users create(Users t) {
		return userDao.create(t);
	}

	@Override
	public int update(Users t) {
		return userDao.update(t);
	}

	@Override
	public Users get(int id) {
		return userDao.get(id);
	}

	@Override
	public int delete(int id) {
		return userDao.delete(id);
	}

	@Override
	public List<Users> listAll() {
		return userDao.listAll();
	}

	@Override
	public long count() {
		return userDao.count();
	}
	
	@Override 
	public int findByEmail(String email) {
		return userDao.findByEmail(email);
	}

}
