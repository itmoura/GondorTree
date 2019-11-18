package com.gondortree.service;

import com.gondortree.model.Testimony;
import java.util.List;

/**
 *
 * @author itmoura
 */
public interface TestimonyService {
    public List<Testimony> list();
    public boolean saveOrUpdate(Testimony test);
    public boolean delete(Testimony test);
}
