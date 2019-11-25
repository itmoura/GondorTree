package com.gondortree.service;

import com.gondortree.model.Family;
import java.util.List;

/**
 *
 * @author Ítalo Moura
 */
public interface FamilyService {
    public List<Family> list();
    public boolean register(Family family);
    public boolean edit(Family family);
}
