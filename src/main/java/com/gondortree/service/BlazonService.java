package com.gondortree.service;

import com.gondortree.model.Blazon;
import java.util.List;

/**
 *
 * @author Ítalo Moura
 */
public interface BlazonService extends GenericService{
    public List<Blazon> list();
    public boolean register(Blazon blazon);
    public boolean edit(Blazon blazon);
    public boolean delete(Blazon blazon);
}
