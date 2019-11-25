package com.gondortree.dao;

import com.gondortree.model.Blazon;
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
public class BlazonDAOImpl implements BlazonDAO {
    
    @Autowired(required = true)
    private SessionFactory sessionFactory;
    
    @Override
    public List<Blazon> list() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Blazon").list(); // LISTANDO DADOS
    }

    @Override
    public boolean register(Blazon blazon) {
        Session session = sessionFactory.getCurrentSession();
        session.save(blazon);
        return true;
    }

    @Override
    public boolean edit(Blazon blazon) {
        Session session = sessionFactory.getCurrentSession();
        session.update(blazon); // ATUALIZANDO DADOS
        return true;
    }
    
    @Override
    public boolean delete(Blazon blazon) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.delete(blazon); // DELETANDO DADOS
        } catch (Exception e) {
                return false;
        }
        return true;
    }
}
