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
public class MemberServiceImpl implements MemberService {
    
    @Autowired
    private MemberDAO memberDAO;
    
    @Override
    public boolean saveOrUpdate(Member member) {
        return memberDAO.saveOrUpdate(member);
    }

    @Override
    public List<Member> list() {
        return memberDAO.list();
    }

    @Override
    public boolean delete(Member member) {
        return memberDAO.delete(member);
    }
}
