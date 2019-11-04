package impl.tew.business;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Agente;
import com.tew.model.Cliente;
import com.tew.model.User;

import impl.tew.business.classes.AgentesBuscar;
import impl.tew.business.classes.ClientesBuscar;

public class SimpleLoginService implements LoginService{

	public User verify(String login, String password) {
		return validLogin(login, password);
	}

	private User validLogin(String login, String password) {
		Cliente c = null;
		Agente g = null;

		try {
			c = new ClientesBuscar().find(login);

			if(c.getLogin().equals(login) && c.getPassword().equals(password))
				return new User(login, false);

		} catch (EntityNotFoundException e) {
		}
		try {
			g = new AgentesBuscar().find(login);
			if(g.getLogin().equals(login) && g.getPassword().equals(password))	
				return new User(login, true);
		} catch (EntityNotFoundException e) {
		}
		return null;
	}
}
