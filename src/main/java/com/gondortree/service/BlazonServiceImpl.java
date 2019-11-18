/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gondortree.service;

import com.gondortree.dao.BlazonDAO;
import com.gondortree.model.Blazon;
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
public class BlazonServiceImpl implements BlazonService{
    
    @Autowired
    private BlazonDAO blazonDAO;
    
    @Override
    public boolean saveOrUpdate(Blazon blazon) {
        return blazonDAO.saveOrUpdate(blazon);
    }

    @Override
    public List<Blazon> list() {
        return blazonDAO.list();
    }

    @Override
    public boolean delete(Blazon blazon) {
        return blazonDAO.delete(blazon);
    }
    
}
