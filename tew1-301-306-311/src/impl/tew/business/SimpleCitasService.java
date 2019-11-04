package impl.tew.business;

import java.util.List;

import com.tew.business.CitasService;
import com.tew.model.Cita;

import impl.tew.business.classes.CitasListado;

public class SimpleCitasService implements CitasService {

	@Override
	public List<Cita> getCitas() throws Exception {
		return new CitasListado().getCitas();
	}

}
