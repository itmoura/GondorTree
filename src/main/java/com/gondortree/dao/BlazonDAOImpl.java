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
    
    public boolean saveOrUpdate(Blazon blazon) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(blazon); // SALVANDO DADOS
        return true;
    }

    @Override
    public List<Blazon> list() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Blazon").list(); // BUSCANDO DADOS DE BLAZON
    }

    @Override
    public boolean delete(Blazon blazon) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.delete(blazon);  // DELETANDO DADOS
        } catch (Exception e) {
                return false;
        }
        return true;
    }
    
    
}
