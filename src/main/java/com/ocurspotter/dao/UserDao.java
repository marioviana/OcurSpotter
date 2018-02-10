package com.ocurspotter.dao;

import com.ocurspotter.model.User;

public interface UserDao {

	User findByUserName(String username);

}