package impl.tew.business;


import com.tew.business.AlumnosService;
import com.tew.business.CitasService;
import com.tew.business.ServicesFactory;

public class SimpleServicesFactory implements ServicesFactory {


	@Override
	public LoginService createLoginService() {
		return new SimpleLoginService();
	}

	@Override
	public CitasService createCitasService() {
		return new SimpleCitasService();
	}


	@Override
	public SignupService createSignupService() {
		return new SimpleSignupService();
	}

	@Override
	public AlumnosService createAlumnosService() {
		return new SimpleAlumnosService();
	}
}
