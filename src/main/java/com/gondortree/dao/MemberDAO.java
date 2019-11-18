package com.gondortree.dao;

import com.gondortree.model.Member;
import java.util.List;

/**
 *
 * @author itmoura
 */
public interface MemberDAO {
    public List<Member> list(); 
    public boolean saveOrUpdate(Member member);
    public boolean delete(Member member);
}
