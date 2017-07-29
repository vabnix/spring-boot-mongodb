package com.vaibhav.web.service.data;

import com.vaibhav.web.service.Domain.UserDo;
import com.vaibhav.web.service.configuration.Encryption;

public class UserTransformer {

    public static User transform(UserDo userDo) {
        User user = new User();
        if(userDo != null) {
            user.setId(userDo.getId());
            user.setUsername(userDo.getUsername());
            if(userDo.getPassword() != null){
                String password = Encryption.encrypt(userDo.getPassword());
                user.setPassword(password);
            }

        }
        return user;
    }
}
