package com.tew.business;

import impl.tew.business.LoginService;
import impl.tew.business.SignupService;

public interface ServicesFactory {

	LoginService createLoginService();

	CitasService createCitasService();

	PisosService createPisosService();

	SignupService CreateSignupService();

}
