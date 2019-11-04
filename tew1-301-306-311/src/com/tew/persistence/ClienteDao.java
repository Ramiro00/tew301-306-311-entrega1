package com.tew.persistence;

import java.sql.SQLIntegrityConstraintViolationException;

import com.tew.model.Cliente;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface ClienteDao {

	void save(Cliente c) throws AlreadyPersistedException, SQLIntegrityConstraintViolationException;
	Cliente findByLogin(String login);
}