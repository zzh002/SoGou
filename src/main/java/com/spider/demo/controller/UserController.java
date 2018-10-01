package com.spider.demo.controller;

import com.spider.demo.dataobject.User;
import com.spider.demo.form.RegisterForm;
import com.spider.demo.form.UpdateForm;
import com.spider.demo.form.UserForm;
import com.spider.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/main")
    public ModelAndView main(){
        return new ModelAndView("login/main");
    }

    @RequestMapping("/RegisterMain")
    public ModelAndView RegisterMain(){
        return new ModelAndView("login/register");
    }

    @RequestMapping("/UpdateMain")
    public ModelAndView UpdateMain(){
        return new ModelAndView("login/update");
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid UserForm form,
                              BindingResult bindingResult,
                              Map<String, Object> map,
                              HttpSession session){
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/crawler/user/main");
            return new ModelAndView("login/error", map);
        }
        User user = userService.findOne(form.getUsername());
        if (user == null||!user.getPassword().equals(form.getPassword()))
        {
            map.put("msg", "密码错误！");
            map.put("url", "/crawler/user/main");
            return new ModelAndView("login/error", map);
        }
        map.put("msg", "登录成功！");
        map.put("url", "/crawler/Main/list");
        user.setPassword("");
        session.setAttribute("user",user);
        return new ModelAndView("login/success", map);
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid RegisterForm form,
                              BindingResult bindingResult,
                              Map<String, Object> map){
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/crawler/user/main");
            return new ModelAndView("login/error", map);
        }
        User user = userService.findOne(form.getUsername());
        if (user != null||!form.getPassword2().equals(form.getPassword1()))
        {
            map.put("msg", "注册失败！");
            map.put("url", "/crawler/user/main");
            return new ModelAndView("login/error", map);
        }
        user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword1());
        userService.save(user);
        map.put("msg", "注册成功！");
        map.put("url", "/crawler/user/main");
        return new ModelAndView("login/success", map);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid UpdateForm form,
                                 BindingResult bindingResult,
                                 Map<String, Object> map,
                               HttpSession session){
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/crawler/Main/list");
            return new ModelAndView("login/error", map);
        }
        User result = (User) session.getAttribute("user");
        User user = userService.findOne(result.getUsername());
        if (user == null||!form.getPasswordOld().equals(user.getPassword())||!form.getPasswordNew1().equals(form.getPasswordNew2()))
        {
            map.put("msg", "修改密码失败！");
            map.put("url", "/crawler/Main/list");
            return new ModelAndView("login/error", map);
        }
        userService.update(user.getUsername(),form.getPasswordOld(),form.getPasswordNew1());
        map.put("msg", "修改密码成功！");
        map.put("url", "/crawler/Main/list");
        return new ModelAndView("login/success", map);
    }

    @RequestMapping("/logout")
    public ModelAndView logout(Map<String, Object> map,
                               HttpSession session){

        session.removeAttribute("user");
        map.put("msg", "登出成功！");
        map.put("url", "/crawler/Main/list");
        return new ModelAndView("login/success");
    }
}
