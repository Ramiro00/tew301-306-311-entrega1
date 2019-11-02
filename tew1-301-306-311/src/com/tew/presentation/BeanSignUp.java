package com.tew.presentation;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.infrastructure.Factories;
import com.tew.model.Cliente;
import com.tew.model.User;

import impl.tew.business.LoginService;
import impl.tew.business.SignupService;

@ManagedBean(name = "signup")
@SessionScoped
public class BeanSignUp extends Cliente implements Serializable {
	private static final long serialVersionUID = 8948269713447154938L;

	public BeanSignUp() {
		iniciaCliente(null);
	}

	public void iniciaCliente(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		setNombre(bundle.getString("signup_valorDefectoNombre"));
		setApellidos(bundle.getString("signup_valorDefectoApellidos"));
		setEmail(bundle.getString("signup_valorDefectoCorreo"));
		setPassword(bundle.getString("signup_valorDefectoPassword"));
	}

	public String registrarse() {
		SignupService signup = Factories.services.CreateSignupService();
		User user = signup.registrarse(new Cliente(getNombre(), getApellidos(), getEmail(), getPassword()));
		if (user != null) {
			putUserInSession(user);
			return "success";
		} else {
			FacesContext jsfCtx = FacesContext.getCurrentInstance();
			ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
					bundle.getString("signup_form_result_error"), null);

			FacesContext.getCurrentInstance().addMessage(null, msg);

			return "signup";
		}
	}

	private void putUserInSession(User user) {
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", user);
	}

}