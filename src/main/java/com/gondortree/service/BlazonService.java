/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gondortree.service;

import com.gondortree.model.Blazon;
import java.util.List;

/**
 *
 * @author itmoura
 */
public interface BlazonService {
    public List<Blazon> list();
    public boolean saveOrUpdate(Blazon blazon);
    public boolean delete(Blazon blazon);
}
