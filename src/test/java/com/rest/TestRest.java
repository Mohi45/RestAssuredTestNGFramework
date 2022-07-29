package com.rest;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class TestRest {

	@BeforeClass
	public void beforeClass() {
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setBaseUri("https://49bc7f79-bbe8-44d5-aa80-34a03fa54225.mock.pstmn.io")
				.setContentType(ContentType.JSON);
		requestSpecBuilder.addHeader("x-mock-match-request-body", "true").setContentType("application/json;charset=utf-8");
		requestSpecBuilder.log(LogDetail.ALL);

		RestAssured.requestSpecification = requestSpecBuilder.build();

		// responseSpecification =
		// RestAssured.expect().statusCode(200).contentType(ContentType.JSON).log().all();

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(201)
				.expectContentType(ContentType.JSON).log(LogDetail.ALL);
		RestAssured.responseSpecification = responseSpecBuilder.build();
	}

	// @Test
	public void validateGetStatus() {
		get("/get");

	}

	// @Test
	public void validateGetResponse() {
		Response res = given().baseUri("https://49bc7f79-bbe8-44d5-aa80-34a03fa54225.mock.pstmn.io")
				.header("x-mock-response-code", "200").log().all().when().get("/get").then().log().all().assertThat()
				.statusCode(200).extract().response();
		System.out.println("Message= " + res.path("msg"));
		String msg = res.path("msg").toString();
		Assert.assertEquals(msg, "successful");
	}

	@Test
	public void createApi() {
		String body = "{\n" + "    \"name\": \"Mohit\",\n" + "    \"id\": 123\n" + "}";
		Response resp = RestAssured.with().body(body).post("/post");
		System.out.println("response= " + resp.getStatusCode());
		String msg = resp.path("msg").toString();
		Assert.assertEquals(msg, "Created successfully ");
	}

	// @Test
	public void createApiWithMap() {

		HashMap<String, String> body = new HashMap<>();
		body.put("name", "Mohit");
		body.put("id", "123");
		Response resp = RestAssured.with().body(body).post("/post");
		System.out.println("response= " + resp.getStatusCode());
		String msg = resp.path("msg").toString();
		Assert.assertEquals(msg, "Created successfully ");
	}

	@Test
	public void createApiWithArrayList() {
		HashMap<String, String> body2 = new HashMap<>();
		body2.put("name", "Mohit");
		body2.put("id", "1234");

		HashMap<String, String> body1 = new HashMap<>();
		body1.put("name", "Mohit");
		body1.put("id", "123");

		List<Map> body = new ArrayList<Map>();
		body.add(body1);
		body.add(body2);
		Response resp = RestAssured.with().body(body).post("/postArray");
		System.out.println("response= " + resp.getStatusCode());
		String msg = resp.path("msg").toString();
		Assert.assertEquals(msg, "Created successfully ");
	}

}
