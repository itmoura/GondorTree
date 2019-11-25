/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gondortree.service;

import com.gondortree.dao.MemberDAO;
import com.gondortree.model.Member;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author itmoura
 */
@Service
@Transactional
public class MemberServiceImpl extends GenericServiceImpl implements MemberService {
    
    @Autowired
    private MemberDAO dao;

    @Override
    public List<Member> list() {
        return dao.list();
    }

    @Override
    public boolean register(Member member) {
        return dao.register(member);
    }

    @Override
    public boolean edit(Member member) {
        return dao.edit(member);
    }
    
    @Override
    public Member login(Member member) {
        return dao.login(member);
    }

    @Override
    public Member findByID(Long id) {
        return dao.findByID(id);
    }
}
