package com.gondortree.service;

import com.gondortree.dao.BlazonDAO;
import com.gondortree.model.Blazon;
import java.util.List;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author itmoura
 */
@Service
@Transactional
public class BlazonServiceImpl extends GenericServiceImpl implements BlazonService {
    
    @Autowired
    private BlazonDAO dao;

    @Override
    public List<Blazon> list() {
        return dao.list();
    }

    @Override
    public boolean register(Blazon blazon) {
        return dao.register(blazon);
    }

    @Override
    public boolean edit(Blazon blazon) {
        return dao.edit(blazon);
    }
    
    @Override
    public boolean delete(Blazon blazon) {
        return dao.delete(blazon);
    }
}
