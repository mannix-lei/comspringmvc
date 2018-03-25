package com.dao;

import com.controller.SqlSession.MyBatisSqlSession;
import com.mapper.IUserMapper;
import com.out.UserOut;
import com.pojo.User;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by Tellyes_worker on 2017/12/4/0004.
 */
@Repository
public class UserDaoInf {

    /**
     * 新增用户
     * @param user
     */
    public boolean addUser(User user){

        MyBatisSqlSession ms = new MyBatisSqlSession(IUserMapper.class);
        if(findUserByName(user.getName()) == null){
            ms.add(user);
            ms.commit();
            ms.close();
            return true;
        }else {
            return false;
        }
    }

    @Test
    public void fdf(){
        User u = new User();
        u.setId(UUID.randomUUID().toString());
        u.setName("002");
        u.setPassword("002");
        addUser(u);
    }

    /**
     * 删除用户
     * @param user
     */
    public void delUserById(User user){
        MyBatisSqlSession ms = new MyBatisSqlSession(IUserMapper.class);
        ms.del(user);
        ms.commit();
        ms.close();
        System.out.println("删除成功");
    }

    /**
     * 更新用户
     * @param user
     */
    public void updateUserById(User user){
        MyBatisSqlSession ms = new MyBatisSqlSession(IUserMapper.class);
        ms.update(user);
        ms.commit();
        ms.close();
    }

    /**
     * 获取用户列表
     * @return
     */
    public List<User> findAllUser(){
       MyBatisSqlSession ms = new MyBatisSqlSession(IUserMapper.class);
        Map<String,String> map = new HashMap<String, String>();
       List<User> list = ms.findAll(map);
       ms.close();
       return list;
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    public User findUserById(String id){
        MyBatisSqlSession ms = new MyBatisSqlSession(IUserMapper.class);
        Map<String,String>map = new HashMap<String, String>();
        map.put("id",id);
        User user = ms.findFirst(map);
        System.out.println(user.getName());
        return user;
    }

    public String findUserByName(String name){
        String pwd = "";
        MyBatisSqlSession ms = new MyBatisSqlSession(IUserMapper.class);
        Map<String,String> map = new HashMap<String, String>();
        map.put("name",name);
        if(ms.findFirst(map)==null){
            pwd = null;
        }else {
            User user1 = ms.findFirst(map);
            pwd = user1.getPassword();
        }
        return pwd;
    }

    @Test
    public void fasdf(){
        String name = "002";
        findUserByName(name);
    }
}
