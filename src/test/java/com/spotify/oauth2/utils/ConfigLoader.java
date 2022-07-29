package com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {

	private final Properties properties;
	private static ConfigLoader configLoader;

	private ConfigLoader() {
		properties = PropertiyUtilts.propertyLoader("src/test/java/resources/config.properties");
	}

	public static ConfigLoader getInstance() {
		if (configLoader == null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}

	public String getClientId() {
		String prop = properties.getProperty("client_id");
		if (prop != null) {
			return prop;
		} else {
			throw new RuntimeException("Properties client_id not found in the file ..");
		}
	}
	public String getClientSecret() {
		String prop = properties.getProperty("client_secret");
		if (prop != null) {
			return prop;
		} else {
			throw new RuntimeException("Properties client_secret not found in the file ..");
		}
	}
	public String getGrantType() {
		String prop = properties.getProperty("grant_type");
		if (prop != null) {
			return prop;
		} else {
			throw new RuntimeException("Properties grant_type not found in the file ..");
		}
	}
	public String getRefreshToken() {
		String prop = properties.getProperty("refresh_token");
		if (prop != null) {
			return prop;
		} else {
			throw new RuntimeException("Properties refresh_token not found in the file ..");
		}
	}
	public String getRedirectUri() {
		String prop = properties.getProperty("redirect_uri");
		if (prop != null) {
			return prop;
		} else {
			throw new RuntimeException("Properties redirect_uri not found in the file ..");
		}
	}
	public String getUserId() {
		String prop = properties.getProperty("user_id");
		if (prop != null) {
			return prop;
		} else {
			throw new RuntimeException("Properties user_id not found in the file ..");
		}
	}

}
