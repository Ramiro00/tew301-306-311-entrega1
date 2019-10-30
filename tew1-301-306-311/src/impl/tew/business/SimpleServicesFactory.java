package impl.tew.business;

import com.tew.business.PisosService;
import com.tew.business.CitasService;
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

	public CitasService createCitasService() {
		// TODO Auto-generated method stub
		return null;
	}

}
