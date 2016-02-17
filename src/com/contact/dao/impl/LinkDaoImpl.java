package com.contact.dao.impl;

import java.util.List;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import com.contact.beans.User;
import com.contact.core.BaseDao;
import com.contact.dao.LinkDao;

@Repository
public class LinkDaoImpl extends BaseDao<User> implements LinkDao {

	@Override
	public List<User> testlink() {
		SQLQuery query = getSession().createSQLQuery("select * from person");
		query.addEntity(User.class);
		return query.list();
	}

	@Override
	public List<User> testlink(String name) {
		SQLQuery query = getSession().createSQLQuery(
				"select * from person where name = ?");
		query.setParameter(0, name);
		query.addEntity(User.class);
		return query.list();

	}

	@Override
	public boolean Add(User user) {
		getSession().save(user);
		return true;

	}

	@Override
	public boolean Delete(Long id) {
		SQLQuery query = getSession().createSQLQuery(
				"delete from person where id = ?");
		query.setParameter(0, id);
		query.addEntity(User.class);
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean Modify(User user) {
		SQLQuery query = getSession()
				.createSQLQuery(
						"update person set name = ?, phoneno=?, location=? where id = ?");
		query.setParameter(0, user.getName());
		query.setParameter(1, user.getPhoneno());
		query.setParameter(2, user.getLocation());
		query.setParameter(3, user.getId());
		query.addEntity(User.class);
		query.executeUpdate();
		return true;

	}

}
