package com.gondortree.dao;

import com.gondortree.model.Testimony;
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
public class TestimonyDAOImpl implements TestimonyDAO {
    
    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public List<Testimony> list() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Testimony").list(); // LISTANDO DADOS
    }

    @Override
    public boolean saveOrUpdate(Testimony test) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(test); // SALVANDO DADOS
        return true;
    }

    @Override
    public boolean delete(Testimony test) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.delete(test); // DELETANDO DADOS
        } catch (Exception e) {
                return false;
        }
        return true;
    }
    
}
