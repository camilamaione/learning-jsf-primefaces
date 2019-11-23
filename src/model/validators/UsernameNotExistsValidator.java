package model.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import model.dao.JpaUserDAO;
import model.dao.UserDAO;
import model.exceptions.UserNotFoundException;

public class UsernameNotExistsValidator implements ConstraintValidator<UsernameNotExists, String> {

	private UserDAO userDao = new JpaUserDAO();
	
	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {	
		try {
			userDao.findByUsername(arg0);
		} catch (UserNotFoundException e) {
			return true;
		} 
		return false;
	}
}
