package com.spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class FakerUtil {
	
	public static String genrateName() {
		Faker faker= new Faker();
		return "Playlist "+faker.regexify("[a-zA-Z0-9,_-]{20}");
	}
	
	public static String genrateDescription() {
		Faker faker= new Faker();
		return "Description "+faker.regexify("[a-zA-Z0-9@#/,_-]{20}");
	}

}
