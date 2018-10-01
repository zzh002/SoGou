package com.spider.demo.service;

import com.spider.demo.dataobject.User;

public interface UserService {

    //注册用户
    User save(User user);

    //修改密码
    User update(String username,String passwordOld,String passwordNew);

    //登陆验证
    Boolean checkLogin(String username,String password);

    User findOne(String username);

}
