package com.spotify.oauth2.api;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestResource {

	public static Response post(String path,String token,Object requestPlayList) {
		return RestAssured.given(SpecBuilder.getRequestSpecification())
				.header("Authorization", "Bearer " + token).body(requestPlayList).when()
				.post(path).then()
				.spec(SpecBuilder.getResponsetSpecification()).extract().response();
	}

	public static Response get(String path,String token) {
		return RestAssured.given(SpecBuilder.getRequestSpecification())
				.header("Authorization", "Bearer " + token).when().get(path).then()
				.spec(SpecBuilder.getResponsetSpecification()).extract().response();
	}

	public static Response update(String path,String token, Object requestPlayList) {
		return RestAssured.given(SpecBuilder.getRequestSpecification())
				.header("Authorization", "Bearer " + token).body(requestPlayList).when()
				.put(path).then().spec(SpecBuilder.getResponsetSpecification()).assertThat()
				.statusCode(200).extract().response();
	}
	
	public static Response postAccount(HashMap<String, String> formParms) {
		return RestAssured.given(SpecBuilder.getAccountRequestSpecification())
				.formParams(formParms).when().post(RouteEndPoints.API+"/token").then().spec(SpecBuilder.getResponsetSpecification())
				.extract().response();
	}
}
