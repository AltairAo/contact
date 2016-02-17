package com.contact.service;

import java.util.List;

import com.contact.beans.User;
import com.contact.core.Service;

public interface LinkService extends Service<User>{
	List<User> testlink();

	boolean Add(User user);

	boolean Delete(Long id);

	boolean Modify(User user);

	List<User> testlink(String name);
}
