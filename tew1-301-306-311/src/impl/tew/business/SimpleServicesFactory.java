package impl.tew.business;

import com.tew.business.CitasService;
import com.tew.business.PisosService;
import com.tew.business.ServicesFactory;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public LoginService createLoginService() {
		return new SimpleLoginService();
	}

	@Override
	public CitasService createCitasService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SignupService createSignupService() {
		return new SimpleSignupService();
	}

	@Override
	public PisosService createPisosService() {
		// TODO Auto-generated method stub
		return new SimplePisosService();
	}
}
