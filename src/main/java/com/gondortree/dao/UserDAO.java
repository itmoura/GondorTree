package com.gondortree.dao;

import com.gondortree.model.User;
import java.util.List;

/**
 *
 * @author itmoura
 */
public interface UserDAO {
    public List<User> list();
    public boolean saveOrUpdate(User user);
    public long login(User user);
}
