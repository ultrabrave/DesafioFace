package com.desafiolatam.desafioface.data;

import com.desafiolatam.desafioface.models.UserInfo;

/**
 * Created by QA on 22-11-2016.
 */

public class UserInfoData {

    public boolean isLoged()
    {
        long count = UserInfo.count(UserInfo.class);
        return (count > 0);
    }

    public String authToken()
    {
        return UserInfo.listAll(UserInfo.class).get(0).getAuth_token();
    }
}
