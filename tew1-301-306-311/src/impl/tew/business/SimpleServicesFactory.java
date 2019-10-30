package impl.tew.business;

import com.tew.business.AlumnosService;
<<<<<<< HEAD
import com.tew.business.PisosService;
=======
import com.tew.business.CitasService;
>>>>>>> refs/remotes/origin/Generales
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

	@Override
<<<<<<< HEAD
	public AlumnosService createAlumnosService() {
		// TODO Auto-generated method stub
		return new SimpleAlumnosService();
	}

=======
	public CitasService createCitasService() {
		// TODO Auto-generated method stub
		return null;
	}
>>>>>>> refs/remotes/origin/Generales
}
