package com.gondortree.controller;

import com.gondortree.model.Member;
import com.gondortree.service.MemberService;
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
@RequestMapping("member")
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    @RequestMapping(value="/")
    public String showIndex(){
        return "member";
    }
    
    @RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getSaved(Member member){
        Map<String,Object> map = new HashMap<String,Object>();

        if(memberService.saveOrUpdate(member)) {
            map.put("status", "200");
            map.put("message","Dado registrado com sucesso!");
        } else {
            map.put("status", "302");
            map.put("message","Falha");
        }

        return map;
    }
    
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getAll(Member member){
        Map<String,Object> map = new HashMap<String,Object>();

        List<Member> list = memberService.list();

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
    public @ResponseBody Map<String,Object> delete(Member member){
        Map<String,Object> map = new HashMap<String,Object>();

        if(memberService.delete(member)) {
                map.put("status", "200");
                map.put("message","Item deletado com sucesso!");
        }

        return map;
    }
    
}
