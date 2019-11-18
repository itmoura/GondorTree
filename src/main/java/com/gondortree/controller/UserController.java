package com.gondortree.controller;

import com.gondortree.model.Member;
import com.gondortree.model.User;
import com.gondortree.service.MemberService;
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
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private UserService userService; 
    
    @Autowired
    private MemberService memberService; 
    
    @RequestMapping(value="/")
    public String showIndex(){
        return "login";
    }
    
    @RequestMapping(value="/register")
    public String showRegister(){
        return "register";
    }
    
    @RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getSaved(User user){
        Map<String,Object> map = new HashMap<String,Object>();

        if(userService.saveOrUpdate(user)) {
            Long id = userService.login(user);
            map.put("status", "200");
            map.put("message","Dado registrado com sucesso!");
            map.put("data", id);
        } else {
            map.put("status", "302");
            map.put("message","Email já cadastrado!");
        }

        return map;
    }
    
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> login(User user){
        Map<String,Object> map = new HashMap<String,Object>();

        Long id = userService.login(user);

        if(id != 0) {
            map.put("status", "200");
            map.put("message","Loggado com sucesso!");
            map.put("data",id);
        } else {
            map.put("status", "404");
            map.put("message","E-mail ou senha incorretos!");
        }

        return map;
    }
    
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getAll(User user){
        Map<String,Object> map = new HashMap<String,Object>();

        List<User> list = userService.list();

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
}
