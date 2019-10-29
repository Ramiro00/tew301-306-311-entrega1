package impl.tew.business;


import com.tew.business.AlumnosService;
import com.tew.business.PisosService;
import com.tew.business.ServicesFactory;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public PisosService createPisosService() {
		return new SimplePisosService();
	}

	@Override
	public LoginService createLoginService() {
	 return new SimpleLoginService();
	}

}
