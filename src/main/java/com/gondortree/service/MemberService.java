/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gondortree.service;

import com.gondortree.model.Member;
import java.util.List;

/**
 *
 * @author itmoura
 */
public interface MemberService {
    public List<Member> list();
    public boolean saveOrUpdate(Member member);
    public boolean delete(Member member);
}
