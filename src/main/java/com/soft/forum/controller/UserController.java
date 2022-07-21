package com.soft.forum.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft.forum.Utils.Res;
import com.soft.forum.Utils.resCodeEnum;
import com.soft.forum.entity.User;
import com.soft.forum.service.MailService;
import com.soft.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @PostMapping("/login")
    //public Res login(@RequestParam String email, @RequestParam String password) {
    public Res login(@RequestBody User user) {
        System.out.println(user);
        String email = user.getUserMail();
        String password = user.getUserPassword();
        System.out.println(email + " " + password);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_mail", email)
                .eq("user_state", 1)
                .eq("user_password", password);
        List<User> currentUser = userService.list(queryWrapper);
        //currentUser.forEach(System.out::println);
        if (currentUser.isEmpty()){
            return Res.error(resCodeEnum.LOGIN_ERROR);
        }else {
            Res r = new Res();
            return r.ok(resCodeEnum.SUCCESS).data(currentUser.get(0).getUserMail(), currentUser.get(0).getUserRole());
        }
    }

    @PostMapping("/signup")
    public Res signUp(@RequestBody User user) {
        String name = user.getUserName();
        String email = user.getUserMail();
        String password = user.getUserPassword();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> currentUser = userService.list(queryWrapper.eq("user_mail", email));
        if (currentUser.isEmpty() || currentUser.get(0).getUserState() > 1){
            return saveProcess(user);//还没有注册过或者已经注销，可以完成注册等待激活
        }
        else if (currentUser.get(0).getUserState() == 0){//已经有人注册该邮箱但未激活
            return Res.error(resCodeEnum.ACTIVATE_ERROR).data("name", currentUser.get(0).getUserName());
        }else if (currentUser.get(0).getUserState() == 1){//已经有人成功完成注册
            return Res.error(resCodeEnum.FAIL).data("name", currentUser.get(0).getUserName());
        }
        return Res.error(resCodeEnum.UNKNOW_REASON);
    }
    Res saveProcess(User user){
        boolean saveFlag = userService.save(user);
        if (saveFlag){
            String confirmCode = String.valueOf(user.getUserMail().hashCode());
            String activationUrl = "http://localhost/activation?confirmCode=" + confirmCode + "&email=" + user.getUserMail();
            return mailService.sendMail(activationUrl, user.getUserMail());
        }else {
            return Res.error(resCodeEnum.UNKNOW_REASON);//服务器数据库异常
        }
    }

    @GetMapping("/activation")
    public Res activation(@RequestParam String email,@RequestParam String confirmCode){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_mail", email).eq("user_state", 0);
        User currentUser = userService.getOne(userQueryWrapper);
        if (String.valueOf(email.hashCode()).equals(confirmCode)){
            currentUser.setUserState(1);
            if(userService.updateById(currentUser)) {
                return Res.right();
            }else {
                return Res.error();
            }
        }
        return Res.error();
    }

    @RequestMapping("hello")
    public String getBasePage(){
        return "hello";
    }
}
