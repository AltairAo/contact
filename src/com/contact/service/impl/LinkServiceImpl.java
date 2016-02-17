package com.contact.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.contact.beans.User;
import com.contact.core.BaseService;
import com.contact.dao.LinkDao;
import com.contact.service.LinkService;

@Service
public class LinkServiceImpl extends BaseService<User> implements LinkService {

	private LinkDao linkDao;

	@Resource
	public void setLinkDao(LinkDao linkDao) {
		this.linkDao = linkDao;
		this.dao = linkDao;
	}

	@Override
	public List<User> testlink() {
		return (linkDao).testlink();
	}

	@Override
	public List<User> testlink(String name) {
		return (linkDao).testlink(name);
	}

	@Override
	public boolean Add(User user) {
		return (linkDao).Add(user);
	}

	@Override
	public boolean Delete(Long id) {
		return (linkDao).Delete(id);
	}

	@Override
	public boolean Modify(User user) {
		return (linkDao).Modify(user);
	}

}
