package com.gondortree.service;

import com.gondortree.model.User;
import java.util.List;

/**
 *
 * @author itmoura
 */

public interface UserService {
    public boolean saveOrUpdate(User user);
    public List<User> list();
    public long login(User user);
}
