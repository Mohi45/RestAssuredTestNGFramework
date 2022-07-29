package com.spotify.oauth2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlayListApi;
import com.spotify.oauth2.pojo.PlayList;
import com.spotify.oauth2.utils.FakerUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.restassured.response.Response;

public class PlaylistTests extends BaseTest {

	String playListId = "";
	
	@Link("https://example.org")
	@Link(name = "allure", type = "mylink")
	@Issue("123345")
	@Description("This request is to create an new play List")
	@Test(description = "Create a Play List")
	public void createAPlayList() {

		PlayList requestPlayList = new PlayList();
		requestPlayList.setName(FakerUtil.genrateName());
		requestPlayList.setDescription(FakerUtil.genrateDescription());
		requestPlayList.set_public(false);
		Response response = PlayListApi.post(requestPlayList);
		PlayList playListResponse = response.as(PlayList.class);
		playListId = playListResponse.getId();

		System.out.println("playListId=" + playListId);

		Assert.assertEquals(requestPlayList.getName(), playListResponse.getName());
		Assert.assertEquals(requestPlayList.getDescription(), playListResponse.getDescription());
		Assert.assertEquals(requestPlayList.get_public(), playListResponse.get_public());

	}

	@Test
	public void featchPlayList() {
		Response response = PlayListApi.get(playListId);
		PlayList playListResponse = response.as(PlayList.class);

	}

	@Test
	public void updatePlayList() {
		PlayList requestPlayList = new PlayList();
		requestPlayList.setName("New Playlist");
		requestPlayList.setDescription("New playlist description");
		requestPlayList.set_public(false);

		Response response = PlayListApi.update(playListId, requestPlayList);
		Assert.assertEquals(response.getStatusCode(),StatusCode.CODE_200.code);
	}

}
