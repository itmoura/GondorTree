package com.gondortree.dao;

import com.gondortree.model.User;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author itmoura
 */

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;
    
    public int create(User user) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(user);
        return Integer.valueOf(id.toString());
    }
    
}
