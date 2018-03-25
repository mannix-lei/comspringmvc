package com.controller.manageController.userController;

import com.pojo.User;
import com.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by Tellyes_worker on 2017/12/4/0004.
 */
@Controller
public class MyController {

    private UserServiceImpl userService;
    @Autowired
    public void setUserService(UserServiceImpl userServiceInf){
        this.userService = userServiceInf;
    }
    /**
     * 使用json响应内容
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping(value = "/Main",method = RequestMethod.GET)
    public @ResponseBody List<User> showUser(){
        List<User> list = userService.findAllUser();
        return list;
    }
}
