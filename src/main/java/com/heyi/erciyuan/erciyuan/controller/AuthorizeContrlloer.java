package com.heyi.erciyuan.erciyuan.controller;

import com.heyi.erciyuan.erciyuan.entity.User;
import com.heyi.erciyuan.erciyuan.eto.AccessTokenDTO;
import com.heyi.erciyuan.erciyuan.eto.GithubUser;
import com.heyi.erciyuan.erciyuan.mapper.UserMapper;
import com.heyi.erciyuan.erciyuan.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeContrlloer {
    @Autowired
    GithubProvider provider;
    @Autowired
    AccessTokenDTO accessTokenDTO;
    @Autowired
    UserMapper userMapper;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String token = provider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = provider.getGithubUser(token);
//        System.out.println(githubUser.getName());
        if(githubUser != null){
            //登录成功，写入session记录状态
            System.out.println("succeed");
//            request.getSession().setAttribute("user",githubUser);
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtCreate(user.getGmtCreate());
            System.out.println(userMapper);
            userMapper.insertSelective(user);
            response.addCookie(new Cookie("token",user.getToken()));
            return "redirect:/";
        }else{
            System.out.println("wrong");
            return "redirect:/";
        }
    }
}
