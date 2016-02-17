package com.contact.dao;

import java.util.List;

import com.contact.beans.User;
import com.contact.core.Dao;

public interface LinkDao extends Dao<User>{
	List<User> testlink();

	boolean Add(User user);

	boolean Delete(Long id);

	boolean Modify(User user);

	List<User> testlink(String name);
	
	
	
	
}
