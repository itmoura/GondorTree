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
    public boolean saveOrUpdate(User user) {
        return userDAO.saveOrUpdate(user);
    }

    @Override
    public List<User> list() {
        return userDAO.list();
    }

    @Override
    public long login(User user) {
        return userDAO.login(user);
    }
    
}
