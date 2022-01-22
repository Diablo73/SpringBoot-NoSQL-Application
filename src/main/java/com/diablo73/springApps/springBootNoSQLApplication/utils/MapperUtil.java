package com.diablo73.springApps.springBootNoSQLApplication.utils;

import com.diablo73.springApps.springBootNoSQLApplication.structures.request.Request;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class MapperUtil {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static Request convert2RequestObject(Map<String, Object> requestMap) {

		return objectMapper.convertValue(requestMap, Request.class);
	}

	public static Map<String, Object> convert2ResponseMap(Response response) {

		return objectMapper.convertValue(response, Map.class);
	}

	public static Map<String, Object> convert2Map(String s) {

		try {
			return objectMapper.readValue(s, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new HashMap<>();
	}
}
