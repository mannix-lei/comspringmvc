package com.controller.manageController.userController;

import com.out.UserOut;
import com.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Tellyes_worker on 2017/12/29/0029.
 */
@Controller
public class LoginController{
//    private static final long serialVersionUID = 1L;

    private UserOut userOut;
    private UserServiceImpl userService;
    public LoginController(){
        userOut = new UserOut();
    }
    @Autowired
    public void setUserService(UserServiceImpl userServiceInf){
        this.userService = userServiceInf;
    }

    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody UserOut login(@RequestParam(value = "name",required = false) String name, @RequestParam(value = "password",required = false) String password){
        String msg = "";
        String pwd = password;

        if(userService.login(name)!= null && pwd.equals(userService.login(name))){
            msg = "welcome" + name;
            userOut.setId("0");
        }
        if(userService.login(name)==null){
            msg = "user is not exist";
            userOut.setId("1");
        }
        if(userService.login(name) != null && !pwd.equals(userService.login(name))){
            msg = "password is wrong";
            userOut.setId("2");
        }
        userOut.setMsg(msg);
        return userOut;
    }
//    public @ResponseBody  void login(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
//        req.setCharacterEncoding("utf-8");
//        resp.setContentType("text/html;charset = utf-8");
//        String name = req.getParameter("name");
//        String password = req.getParameter("password");
////        String svc = (String) req.getSession().getAttribute("sessionVerify");
//
//        String psw = userService.login(name);
//        if(psw == null){
//            req.setAttribute("msg","用户不存在！");
//            req.getRequestDispatcher("http://localhost:8080/#/").forward(req,resp);
//            return;
//        }
//        if(psw!=null && !psw.equals(password)){
//            req.setAttribute("msg","密码输入错误请重新输入！");
//            req.getRequestDispatcher("http://localhost:8080/#/").forward(req,resp);
//            return;
//        }
//        if(psw.equals(password)){
//            req.setAttribute("msg","用户："+name+",欢迎访问");
//            req.getRequestDispatcher("http://localhost:8080/#/").forward(req,resp);
//        }
//    }
}
