package com.dev.mpolacek.ebayclone.models;

import org.antlr.v4.runtime.misc.NotNull;

public class AuthenticationRequest {

    @NotNull
    private String login;

    @NotNull
    private String password;

    public AuthenticationRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AuthenticationRequest() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
