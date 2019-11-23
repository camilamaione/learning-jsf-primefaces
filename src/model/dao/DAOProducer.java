package model.dao;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class DAOProducer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	public UserDAO createrUserDAO() {
		return new JpaUserDAO();
	}
}
