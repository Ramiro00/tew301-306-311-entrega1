package com.tew.business;

import impl.tew.business.LoginService;
import impl.tew.business.SignupService;

public interface ServicesFactory {
	
	AlumnosService createAlumnosService();

	LoginService createLoginService();
	
	CitasService createCitasService();
	
	SignupService createSignupService();

}
