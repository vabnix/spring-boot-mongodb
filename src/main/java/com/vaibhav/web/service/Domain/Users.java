package com.vaibhav.web.service.Domain;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private List<UserDo> userList;

    public List<UserDo> getUserList() {
        if(userList==null){
            userList = new ArrayList<>();
        }
        return userList;
    }

    public void add(UserDo userDo){
        getUserList().add(userDo);
    }

    public void setUserList(List<UserDo> userList) {
        this.userList = userList;
    }
}
