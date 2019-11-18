package com.gondortree.controller;

import com.gondortree.model.Blazon;
import com.gondortree.model.Testimony;
import com.gondortree.service.TestimonyService;
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
@RequestMapping("testimony")
public class TestimonyController {
    
    @Autowired
    private TestimonyService testService;
    
    @RequestMapping(value="/")
    public String showIndex(){
        return "testimony";
    }
    
    @RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getSaved(Testimony test){
        Map<String,Object> map = new HashMap<String,Object>();

        if(testService.saveOrUpdate(test)) {
            map.put("status", "200");
            map.put("message","Dado registrado com sucesso!");
        } else {
            map.put("status", "302");
            map.put("message","Falha");
        }

        return map;
    }
    
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getAll(Testimony test){
        Map<String,Object> map = new HashMap<String,Object>();

        List<Testimony> list = testService.list();

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
    public @ResponseBody Map<String,Object> delete(Testimony test){
        Map<String,Object> map = new HashMap<String,Object>();

        if(testService.delete(test)) {
                map.put("status", "200");
                map.put("message","Item deletado com sucesso!");
        }

        return map;
    }
}
