package com.desafiolatam.desafioface.data;

import com.desafiolatam.desafioface.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QA on 23-11-2016.
 */

public class UserData {
    public void handle(User[] users) {
        for (User user : users) {
            List<User> userList = User.find(User.class, "serverId = ?", String.valueOf(user.getId()));
            if (userList == null || userList.size() == 0)
            {
                user.create();

            }
        }
    }

    public List<User> all()
    {
        List<User> users = new ArrayList<>();
        List<User> userList = User.listAll(User.class);
        if (userList != null && userList.size() > 0 )
        {
            users.addAll(userList);
        }
        return users;
    }
}
