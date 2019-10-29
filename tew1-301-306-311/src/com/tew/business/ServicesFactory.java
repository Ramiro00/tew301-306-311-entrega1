package com.tew.business;

import impl.tew.business.LoginService;

public interface ServicesFactory {
	

	LoginService createLoginService();

	PisosService createPisosService();

}
