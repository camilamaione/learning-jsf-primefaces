package model.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import model.entities.User;
import model.exceptions.UserNotFoundException;

public class JpaUserDAO implements UserDAO, Serializable {

	private static final long serialVersionUID = 1L;
	private EntityManager em;
	private EntityTransaction et;
	
	public JpaUserDAO() {
		em = Persistence.createEntityManagerFactory("learning_jsf").createEntityManager();
		et = em.getTransaction();		
	}

	@Override
	public void insert(User user) {
		et.begin();	
		em.persist(user);
		et.commit();
	}

	@Override
	public void update(User user) {
		et.begin();		
		em.merge(user);
		et.commit();
	}

	@Override
	public void delete(Integer id) throws UserNotFoundException {
		User user = findById(id);		
		et.begin();		
		em.remove(user);
		int num = (int) em.createNativeQuery("SELECT max(id) FROM public.user").getSingleResult();
		em.createNativeQuery("ALTER SEQUENCE user_id_seq RESTART WITH " + (num + 1)).executeUpdate();
		et.commit();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		List<User> users = em.createQuery("Select u from User u").getResultList();
		return users;
	}

	@Override
	public User findById(Integer id) throws UserNotFoundException {
		User user = em.find(model.entities.User.class, id);		
		if (user == null) 
			throw new UserNotFoundException();
		return user;
		
	}

	@Override
	public User findByUsername(String usrname) throws UserNotFoundException {	
		try {
			User user = (User) em.createQuery("SELECT u FROM User u WHERE u.username = '" + usrname + "'").getSingleResult();
			return user;
		} catch (NoResultException e) { 
			throw new UserNotFoundException();
		}		
	}
}
