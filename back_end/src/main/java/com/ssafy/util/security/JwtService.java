package com.ssafy.util.security;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String getToken(String key, Object value);

    Claims getClaims(String token);

    boolean isValid(String token);

    String getMemberMail(String token);

	String getMemberId(String token);
}