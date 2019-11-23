package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.dao.UserDAO;
import model.entities.User;
import model.exceptions.UserNotFoundException;
import utils.PasswordEncrypter;

@Named
@RequestScoped
public class Authenticator implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<User> users;
	private String username;
	private String password;
	@Inject private UserDAO userDAO;
	
	public Authenticator() {		
	}
	
	@PostConstruct
	public void init() {
		users = userDAO.findAll();
	}
	
	public String login() {			
		try {
			User u = userDAO.findByUsername(username);
			String encryptedPassword = PasswordEncrypter.convertToMd5(password);
			if (encryptedPassword.equals(u.getPassword())) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", username);
				return "/restricted/index.jsf?faces-redirect=true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong password!", ""));
				return "";
			}
		} catch (UserNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username not found!", ""));
			return "";	
		}		
	}
	
	public void logout() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String path = ec.getRequestContextPath();
		username = null;
		password = null;
		ec.invalidateSession();
		try {
			ec.redirect(path + "/login.jsf");
		} catch (IOException e) {			
			e.printStackTrace();
		}	
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getWelcomeMessage() {
		String usernameLogged = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
		if (usernameLogged != null) {
			try {			
				User logged = userDAO.findByUsername(usernameLogged);
				return logged.getFirstname();
			} catch (UserNotFoundException e) {
				// Theorically unreachable
			}
		}
		return "";
	}
	
	public boolean getThereIsLoggedUser() {
		String usernameLogged = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
		if (usernameLogged != null)
			return true;
		return false;
	}
	
}
