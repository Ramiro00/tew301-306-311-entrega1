package impl.tew.business;


import com.tew.business.AlumnosService;
import com.tew.business.CitasService;
import com.tew.business.ServicesFactory;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public AlumnosService createAlumnosService() {
		return new SimpleAlumnosService();
	}

	@Override
	public LoginService createLoginService() {
	 return new SimpleLoginService();
	}

	@Override
	public CitasService createCitasService() {
		// TODO Auto-generated method stub
		return null;
	}
}
