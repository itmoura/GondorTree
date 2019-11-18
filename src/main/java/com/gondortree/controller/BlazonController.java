package com.gondortree.controller;

import com.gondortree.model.Blazon;
import com.gondortree.service.BlazonService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author itmoura
 */
@Controller
@RequestMapping("blazon")
public class BlazonController {
    
    @Autowired
    private BlazonService blazonService;
    
    @RequestMapping(value="/")
    public String showIndex(){
        return "blazon";
    }
    
    @RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getSaved(Blazon blazon){
        Map<String,Object> map = new HashMap<String,Object>();

        if(blazonService.saveOrUpdate(blazon)) {
            map.put("status", "200");
            map.put("message","Dado registrado com sucesso!");
        } else {
            map.put("status", "302");
            map.put("message","Falha");
        }

        return map;
    }
    
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getAll(Blazon blazon){
        Map<String,Object> map = new HashMap<String,Object>();

        List<Blazon> list = blazonService.list();

        if(list != null) {
            map.put("status", "200");
            map.put("message","Dados encontrado");
            map.put("data",list);
        } else {
            map.put("status", "404");
            map.put("message","Dados não encontrado");
        }

        return map;
    }
    
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(Blazon blazon){
        Map<String,Object> map = new HashMap<String,Object>();

        if(blazonService.delete(blazon)) {
                map.put("status", "200");
                map.put("message","Item deletado com sucesso!");
        }

        return map;
    }
    
}
