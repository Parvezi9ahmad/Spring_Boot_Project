package com.springboot.blog.security;

public class JWTAuthResponse {
    private String accessToken;
    private String tokenType="bearer";

    public JWTAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setAccessToken(String accessToken) {


        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
