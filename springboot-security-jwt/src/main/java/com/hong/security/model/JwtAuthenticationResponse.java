package com.hong.security.model;

import java.io.Serializable;

/**
 * @ClassName: JwtAuthenticationResponse
 * @Description: ( token 返回对象.)
 * @author hong
 * @date 2017/6/16
 * @version v1.1
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
