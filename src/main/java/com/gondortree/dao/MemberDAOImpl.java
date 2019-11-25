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
    
    @Override
    public List<Member> list() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Member").list(); // LISTANDO DADOS
    }

    @Override
    public boolean register(Member member) {
        Session session = sessionFactory.getCurrentSession();
        List<Member> l = session.createQuery("from Member").list();
        for (Member ref : l) {
            // VERIFICAR EMAIL E SENHA ESTÃO CORRETOS
            if(ref.getEmail().equals(member.getEmail())){
                return false;
            }
        }
        session.save(member);
        return false;
    }

    @Override
    public boolean edit(Member member) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(member); // ATUALIZANDO DADOS
        return true;
    }
    
    @Override
    public Member login(Member member) {
        Session session = sessionFactory.getCurrentSession();
        List<Member> l = session.createQuery("from Member").list();
        for (Member ref : l) {
            // VERIFICAR EMAIL E SENHA ESTÃO CORRETOS
            if(ref.getEmail().equals(member.getEmail()) && ref.getPassword().equals(member.getPassword())){ 
                Member mdb = l.get(0);
                return mdb;
            }
        }
        return null;
    }

    @Override
    public Member findByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        List<Member> l = session.createQuery("from Member where id = "+id).list();
        for (Member ref : l) {
            if(ref.getId().longValue() == id){ 
                Member mdbS = l.get(0);
                return mdbS;
            }
        }
        return null;
    }
}
