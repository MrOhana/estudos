package com.schoolofnet.helpdesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.schoolofnet.helpdesk.model.User;
import com.schoolofnet.helpdesk.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPE;
	
	public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder bCryptPE) {
		this.repository = repository;
		this.bCryptPE = bCryptPE;
	}
	
	@Override
	public List<User> findAll() {
		return this.repository.findAll();
	}

	@Override
	public User create(User user) {
		user.setPassword(this.bCryptPE.encode(user.getPassword()));
		return this.repository.save(user);
	}

	@Override
	public Boolean delete(Long id) {
		User user = findById(id);
		
		if(user != null) {
			this.repository.delete(user);
			return true;
		}
		
		return false;
	}

	@Override
	public User show(Long id) {
		return findById(id);
	}

	@Override
	public Boolean update(Long id, User user) {
		User userExists = findById(id);
		
		if (userExists != null) {
			userExists.setId(user.getId());
			userExists.setName(user.getName());
			userExists.setLastName(user.getLastName());
			userExists.setEmail(user.getEmail());
			userExists.setPassword(this.bCryptPE.encode(user.getPassword()));
			userExists.setActive(user.getActive());
			
			this.repository.save(userExists);
			
			return true;
		}
		
		return false;
	}
	
	private User findById(Long id) {
		return this.repository.findOne(id);
	}
}
