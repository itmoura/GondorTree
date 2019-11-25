package com.gondortree.dao;

import com.gondortree.model.Member;
import java.util.List;

/**
 *
 * @author itmoura
 */
public interface MemberDAO {
    public List<Member> list(); 
    public boolean register(Member member);
    public boolean edit(Member member);
    
    public Member login(Member m);
    
    public Member findByID(Long id);
}
