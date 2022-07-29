package com.spotify.oauth2.api;

public enum StatusCode {
	
	CODE_200(200,""),
	COCE_201(201,"");
	
	public int code; 
	public  String message;
	
	
	StatusCode(int code,String message){
		this.code=code;
		this.message=message;
	}
	public int getCode(){
		return code;
	}

	public String getMessage(){
		return message;
	}
}
