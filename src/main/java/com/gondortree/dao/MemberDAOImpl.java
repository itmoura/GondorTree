package com.gondortree.dao;

import com.gondortree.model.Member;
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
public class MemberDAOImpl implements MemberDAO {
    
    @Autowired(required = true)
    private SessionFactory sessionFactory;
    
    public boolean saveOrUpdate(Member member) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(member); // SALVANDO DADOS
        return true;
    }

    @Override
    public List<Member> list() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Member").list(); // LISTANDO DADOS
    }

    @Override
    public boolean delete(Member member) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.delete(member); // EXLUINDO DADOS
        } catch (Exception e) {
                return false;
        }
        return true;
    }
}
