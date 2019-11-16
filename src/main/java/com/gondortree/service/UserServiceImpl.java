package com.gondortree.service;

import com.gondortree.dao.UserDAO;
import com.gondortree.model.User;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author itmoura
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDAO userDAO;

    @Override
    public int create(User user) {
        return userDAO.create(user);
    }
    
}
