package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.RouteEndPoints;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.PlayList;
import com.spotify.oauth2.utils.ConfigLoader;

import io.restassured.response.Response;

public class PlayListApi {

	public static Response post(PlayList requestPlayList) {

		return RestResource.post(RouteEndPoints.USERS+"/"+ConfigLoader.getInstance().getUserId()+RouteEndPoints.PLAYLISTS, TokenManager.getToken(), requestPlayList);

	}

	public static Response get(String playListId) {

		return RestResource.get(RouteEndPoints.PLAYLISTS+"/" + playListId, TokenManager.getToken());

	}

	public static Response update(String playListId, PlayList requestPlayList) {

		return RestResource.update(RouteEndPoints.PLAYLISTS+"/" + playListId, TokenManager.getToken(), requestPlayList);

	}

	public static Response post(PlayList requestPlayList, String token) {

		return RestResource.post(RouteEndPoints.USERS+"/"+ConfigLoader.getInstance().getUserId()+RouteEndPoints.PLAYLISTS, token, requestPlayList);

	}

}
