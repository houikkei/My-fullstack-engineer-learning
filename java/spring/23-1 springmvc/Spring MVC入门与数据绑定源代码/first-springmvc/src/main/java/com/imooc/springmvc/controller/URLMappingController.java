package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.Date;

@Controller
@RequestMapping("/um")
public class URLMappingController {
    @GetMapping("/g")
    //作用在方法上,不再区分get/post请求
    //@RequestMapping(value="/g",method = RequestMethod.GET)
    @ResponseBody
    public String getMapping(@RequestParam("manager_name") String managerName , Date createTime){
        System.out.println("managerName:"+managerName);
        return "This is get method";
    }
    @PostMapping("/p")
    @ResponseBody
    public String postMapping(String username,Long password){
        //User u = new User()
        //u.setUsername(username)
        //request.getParameter()
        System.out.println(username+":"+password);
        return "This is post method";
    }

    @PostMapping("/p1")
    @ResponseBody
    public String postMapping1(User user , String username ,@DateTimeFormat(pattern = "yyyy-MM-dd") Date createTime){
        System.out.println(user.getUsername() + ":" + user.getPassword());
        return "<h1>这是Post响应</h1>";
    }

    @GetMapping("/view")
    public ModelAndView showView(Integer userId){
//        ModelAndView mav = new ModelAndView("redirect:/view.jsp");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/um/view.jsp");
        User user = new User();
        if(userId == 1){
            user.setUsername("lily");
        }else if(userId == 2){
            user.setUsername("smith");
        }else if(userId == 3){
            user.setUsername("lina");
        }
        mav.addObject("u" , user);
        return mav;
    }
    //String与ModelMap
    //Controller方法返回String的情况
    //1. 方法被@ResponseBody描述，SpringMVC直接响应String字符串本身
    //2. 方法不存在@ResponseBody，则SpringMVC处理String指代的视图（页面）
    @GetMapping("/xxxx")
//    @ResponseBody
    public String showView1(Integer userId , ModelMap modelMap){
        String view = "/um/view.jsp";
        User user = new User();
        if(userId == 1){
            user.setUsername("lily");
        }else if(userId == 2){
            user.setUsername("smith");
        }else if(userId == 3){
            user.setUsername("lina");
        }
        modelMap.addAttribute("u", user);
        return view;
    }
}
