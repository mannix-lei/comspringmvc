package com.controller.manageController.userController;

import com.out.UserOut;
import com.pojo.User;
import com.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Tellyes_worker on 2017/12/29/0029.
 */
@Controller
public class RegistController {
//    private static final long serialVersionUID = 1L;
    private UserServiceImpl userService;
    @Autowired
    public void setUserService(UserServiceImpl userServiceInf){
        this.userService = userServiceInf;
    }

    private UserOut userOut;
    public RegistController(){
        userOut = new UserOut();
    }
    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public @ResponseBody UserOut register(@RequestParam(value = "name",required = false) String name, @RequestParam(value = "password",required = false) String password){
        String n = name;
        String p = password;
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(n);
        user.setPassword(p);
        if(userService.addUser(user) == true){
            userOut.setId("0");
        }else {
            userOut.setId("1");
        }
        return userOut;
    }
}
