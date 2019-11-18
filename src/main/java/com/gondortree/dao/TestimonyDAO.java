package com.gondortree.dao;

import com.gondortree.model.Testimony;
import java.util.List;

/**
 *
 * @author itmoura
 */
public interface TestimonyDAO {
    public List<Testimony> list();
    public boolean saveOrUpdate(Testimony test);
    public boolean delete(Testimony test);
}
