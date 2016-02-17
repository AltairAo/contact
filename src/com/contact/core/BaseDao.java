package com.contact.core;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.annotation.Resource;

public class BaseDao<E> implements Dao<E> {
	private SessionFactory sessionFactory;

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Resource(name = "sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
