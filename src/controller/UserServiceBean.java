package controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;

import model.dao.UserDAO;
import model.entities.User;
import model.exceptions.UserNotFoundException;
import utils.PasswordEncrypter;

@Named
@SessionScoped
public class UserServiceBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject private UserDAO userDAO;
	private User user;
	
	public UserServiceBean() {	
		user = new User();
	}

	public void addUser() {
		user.setPassword(PasswordEncrypter.convertToMd5(user.getPassword()));
		userDAO.insert(user);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "User '" + user.getUsername() + "' sucessfully registered in the system.", "");
		PrimeFaces.current().dialog().showMessageDynamic(msg);
	}
	
	public void editUser() {	
		userDAO.update(user);		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "User '" + user.getUsername() + "' sucessfully edited.", "");	
		PrimeFaces.current().dialog().showMessageDynamic(msg);		
	}
	
	public void deleteUser(User user) {
		try {
			userDAO.delete(user.getId());
			PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "User '" + user.getUsername() + "' removed sucessfully from system.", ""));
		} catch (UserNotFoundException e) {
			PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_ERROR, "User '" + user.getUsername() + "' not found.", ""));
		}
	}
	
	public void resetUser(CloseEvent event) {
		user = new User();
		System.out.println("Ayyyy");
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
	
	public List<User> getUsers() {
		return userDAO.findAll();
	}
}
