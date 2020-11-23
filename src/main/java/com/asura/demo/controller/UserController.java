package com.asura.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asura.demo.entity.User;
import com.asura.demo.entity.ResultEntity;
import com.asura.demo.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/demo")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/regist")
    public String regist(@RequestBody User user){
        ResultEntity resultEntity=new ResultEntity();
        try {
            if(StringUtils.isNotBlank(user.getUserName())&&StringUtils.isNotBlank(user.getPass())) {
                if (userService.userIsExix(user.getUserName())) {
                    resultEntity.setStatus(-1);
                    resultEntity.setMsg("用户名已存在");
                } else {
                    if (userService.insertUser(user)) {
                        resultEntity.setStatus(0);
                        resultEntity.setMsg("注册成功");
                    }
                }
            }else {
                resultEntity.setStatus(-1);
                resultEntity.setMsg("注册失败");
            }
        }catch (Exception e){
            resultEntity.setStatus(-1);
            resultEntity.setMsg("注册失败");
        }
        return JSON.toJSONString(resultEntity);
    }

    @RequestMapping("/login")
    public String login(String userName,String pass){
        ResultEntity resultEntity=new ResultEntity();
        try{
            User user=userService.selectUserByUsername(userName);
            if(user!=null){
                if (pass.equals(user.getPass())){
                    resultEntity.setStatus(0);
                    resultEntity.setMsg("登录成功");
                }else {
                    resultEntity.setStatus(-1);
                    resultEntity.setMsg("密码错误");
                }
            }else {
                resultEntity.setStatus(-1);
                resultEntity.setMsg("用户不存在");
            }
        }catch (Exception e){
            resultEntity.setStatus(-1);
            resultEntity.setMsg("登录失败");
        }
        return JSON.toJSONString(resultEntity);
    }

    public static void main(String[] args) {
        User user=new User();
        user.setUserName("admin");
        user.setPass("123");
        System.out.println(JSON.toJSON(user));
    }
}
