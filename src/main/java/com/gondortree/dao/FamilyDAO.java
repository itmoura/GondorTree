package com.gondortree.dao;

import com.gondortree.model.Family;
import java.util.List;

/**
 *
 * @author Ítalo Moura
 */
public interface FamilyDAO {
    public List<Family> list();
    public boolean register(Family family);
    public boolean edit(Family family);
}
