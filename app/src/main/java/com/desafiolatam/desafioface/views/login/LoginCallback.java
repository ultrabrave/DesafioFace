package com.desafiolatam.desafioface.views.login;

/**
 * Created by QA on 22-11-2016.
 */

public interface LoginCallback {

    void success();
    void fail(String error);
    void invalidEmail(String error);
    void invalidPassword(String error);
}
