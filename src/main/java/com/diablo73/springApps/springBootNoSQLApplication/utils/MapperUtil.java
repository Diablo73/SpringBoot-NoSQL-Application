package com.diablo73.springApps.springBootNoSQLApplication.utils;

import com.diablo73.springApps.springBootNoSQLApplication.structures.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperUtil {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static Object convert2RequestObject(Map<String, Object> requestMap, Class<?> clazz) {

		return objectMapper.convertValue(requestMap, clazz);
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

	public static List<Map<String, String>> convert2List(String s) {

		try {
			return objectMapper.readValue(s, List.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

}
