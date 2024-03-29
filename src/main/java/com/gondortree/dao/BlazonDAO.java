package com.gondortree.dao;

import com.gondortree.model.Blazon;
import java.util.List;

/**
 *
 * @author itmoura
 */
public interface BlazonDAO {
    public List<Blazon> list();
    public boolean register(Blazon blazon);
    public boolean edit(Blazon blazon);
    public boolean delete(Blazon blazon);
}
