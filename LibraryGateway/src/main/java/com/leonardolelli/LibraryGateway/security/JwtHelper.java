package com.leonardolelli.LibraryGateway.security;

import java.util.Base64;

import org.json.JSONObject;

public final class JwtHelper {

    public static String getUserFromJwtToken(String token) {
	JSONObject payload = new JSONObject(decode(token.split("\\.")[1]));
	String username = payload.getString("http://wso2.org/claims/subscriber");
	// System.out.println(String.format("Request made by: %s", username));
	return username;
    }

    public static String decode(String encodedString) {
	return new String(Base64.getUrlDecoder().decode(encodedString));
    }
}
