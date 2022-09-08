package com.crm.genericUtilities;

import static io.restassured.response.Response.*;

import io.restassured.response.Response;

public class RestAssured_library {
	/**
	 * this method will get the JSON path
	 * @param response
	 * @param path
	 * @return
	 */
	public String getJSONPath(Response response,String path)
	{
		String jsonData = response.jsonPath().get(path);
		return jsonData;
	}

}
