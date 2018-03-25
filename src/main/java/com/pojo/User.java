package com.pojo;

import org.apache.ibatis.type.Alias;

/**
 * Created by Tellyes_worker on 2017/12/4/0004.
 */
@Alias("User")
public class User {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String id;
    private String name;
    private String password;
}
