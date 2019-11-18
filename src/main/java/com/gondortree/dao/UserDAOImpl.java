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
    
    public boolean saveOrUpdate(User user) {
        Session session = sessionFactory.getCurrentSession();
        if(user.getId() == 0){
            List<User> l = session.createQuery("from User").list(); // LISTANDO DADOS
            for (User ref : l) {
                if(ref.getEmail().equals(user.getEmail())){ // VERIFICANDO SE JÁ EXISTE ESSE EMAIL
                   return false; 
                }
            }
        }
        session.saveOrUpdate(user); // SALVANDO DADOS
        return true;
    }

    @Override
    public List<User> list() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list(); // LISTANDO DADOS
    }

    @Override
    public long login(User user) {
        Session session = sessionFactory.getCurrentSession();
        List<User> l = session.createQuery("from User").list(); // LISTANDO DADOS
        for (User ref : l) {
            // VERIFICAR EMAIL E SENHA ESTÃO CORRETOS
            if(ref.getEmail().equals(user.getEmail()) && ref.getPassword().equals(user.getPassword())){ 
                Long id = (Long) ref.getId();
                return id;
            }
        }
        return 0;
    }
}
