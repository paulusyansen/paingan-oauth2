package org.paingan.oauth2.service;

import java.util.List;

import org.paingan.oauth2.model.User;

public interface UserService {
	
    User save(User user);
    
    List<User> findAll();
    
    void delete(long id);
}
