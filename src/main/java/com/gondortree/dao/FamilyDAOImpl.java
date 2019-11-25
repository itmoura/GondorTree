package com.gondortree.dao;

import com.gondortree.model.Family;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ítalo Moura
 */

@Repository
@Transactional
public class FamilyDAOImpl implements FamilyDAO {
    
    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public List<Family> list() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Family").list();
    }

    @Override
    public boolean register(Family family) {
        Session session = sessionFactory.getCurrentSession();
        session.save(family); // SALVANDO DADOS
        return true;
    }

    @Override
    public boolean edit(Family family) {
        Session session = sessionFactory.getCurrentSession();
        session.update(family); // ATUALIZANDO DADOS
        return true;
    }
    
}
