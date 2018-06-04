package com.hong.security.model;

import java.io.Serializable;

/**
 * @ClassName: JwtAuthenticationRequest
 * @Description: (用户登录请求对象.)
 * @author hong
 * @date 2017/6/16
 * @version v1.1
 */
public class JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String username;
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
