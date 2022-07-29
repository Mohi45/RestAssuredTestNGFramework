package com.spotify.oauth2.api;

import java.time.Instant;
import java.util.HashMap;

import com.spotify.oauth2.utils.ConfigLoader;

import io.restassured.response.Response;

public class TokenManager {
	
	private static String access_token;
	private static Instant expiry_time;
	
	public synchronized static String getToken() {
		try {
			if(access_token== null || Instant.now().isAfter(expiry_time)) {
				System.out.println("Renewing the token ..... ");
				Response response =renewToken();
				access_token=response.path("access_token");
				int expiryDurationInSeconds=response.path("expires_in");
				expiry_time=Instant.now().plusSeconds(expiryDurationInSeconds-300);
				
			}else {
				System.out.println("Token good to use");
			}
			
		}catch(Exception e) {
			throw new RuntimeException("ABORT !! Failed to get token");
		}
		return access_token;
		
	}
	
	

	private static Response renewToken() {
		HashMap<String, String> formParms = new HashMap<>();
		formParms.put("client_id", ConfigLoader.getInstance().getClientId());
		formParms.put("client_secret", ConfigLoader.getInstance().getClientSecret());
		formParms.put("grant_type", ConfigLoader.getInstance().getGrantType());
		formParms.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());
		formParms.put("redirect_uri", ConfigLoader.getInstance().getRedirectUri());

		Response response=RestResource.postAccount(formParms);
		
		if(response.getStatusCode()!=200) {
			throw new RuntimeException("ABORT !!! Renew token is failed"); 
		}
		return response;
	}

}
