package com.contact.core;
import com.contact.core.Dao;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseService<E> implements Service<E> {
	protected Dao<E> dao;
}
