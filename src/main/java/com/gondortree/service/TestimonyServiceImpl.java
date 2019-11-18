package com.gondortree.service;

import com.gondortree.dao.TestimonyDAO;
import com.gondortree.model.Testimony;
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
public class TestimonyServiceImpl implements TestimonyService {
    
    @Autowired
    private TestimonyDAO testDAO;

    @Override
    public List<Testimony> list() {
        return testDAO.list();
    }

    @Override
    public boolean saveOrUpdate(Testimony test) {
        return testDAO.saveOrUpdate(test);
    }

    @Override
    public boolean delete(Testimony test) {
        return testDAO.delete(test);
    }
}
