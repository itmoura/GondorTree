package com.gondortree.controller;

import com.gondortree.model.User;
import com.gondortree.service.UserService;
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
public class HomeController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/")
    public String showIndex(){
        return "index";
    }
    
    @RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST)
    public String create(){
        User p = new User();
        p.setEmail("Obama");
        p.setPassword("132");
        
        int id= userService.create(p);
        
        if(id!=0){
            return "success";
        }
        else        
        return "fail";
    }
}
