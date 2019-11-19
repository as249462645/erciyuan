package com.heyi.erciyuan.erciyuan.controller;

import com.heyi.erciyuan.erciyuan.entity.User;
import com.heyi.erciyuan.erciyuan.entity.example.UserExample;
import com.heyi.erciyuan.erciyuan.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserExample userExample;

    @GetMapping("/")
    public String index(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                UserExample.Criteria criteria = userExample.createCriteria();
                criteria.andTokenEqualTo(token);
                List<User> list = userMapper.selectByExample(userExample);
                if(list != null && list.size() == 1)
                    request.getSession().setAttribute("user",list.get(0));
                break;
            }
        }

        return "index";
    }
}
