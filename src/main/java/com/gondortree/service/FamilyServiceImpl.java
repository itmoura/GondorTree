package com.gondortree.service;

import com.gondortree.dao.FamilyDAO;
import com.gondortree.model.Family;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ítalo Moura
 */

@Service
@Transactional
public class FamilyServiceImpl implements FamilyService {
    
    @Autowired
    private FamilyDAO familyDAO;

    @Override
    public List<Family> list() {
        return familyDAO.list();
    }

    @Override
    public boolean register(Family family) {
        return familyDAO.register(family);
    }

    @Override
    public boolean edit(Family family) {
        return familyDAO.edit(family);
    }
    
}
