package model.dao;

import java.util.List;

import model.entities.User;
import model.exceptions.UserNotFoundException;

public interface UserDAO {
	
	public void insert(User user);
	public void update(User user);
	public void delete(Integer id) throws UserNotFoundException;
	public List<User> findAll();	
	public User findById(Integer id) throws UserNotFoundException;
	public User findByUsername(String username) throws UserNotFoundException;

}
