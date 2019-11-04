package com.tew.persistence;

import java.util.List;

import com.tew.model.Agente;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface AgenteDao {	
	
	void save(Agente a) throws AlreadyPersistedException;
	Agente findByLogin(String login);
	
}
