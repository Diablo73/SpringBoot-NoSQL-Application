package com.diablo73.springApps.springBootNoSQLApplication.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperUtil {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static Object convertMap2Object(Map<String, Object> map, Class<?> clazz) {

		return objectMapper.convertValue(map, clazz);
	}

	public static Map<String, Object> convertObject2Map(Object o) {

		return objectMapper.convertValue(o, Map.class);
	}

	public static Map<String, Object> convertJsonString2Map(String s) {

		try {
			return objectMapper.readValue(s, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new HashMap<>();
	}

	public static List<Map<String, String>> convertJsonString2List(String s) {

		try {
			return objectMapper.readValue(s, List.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public static String convertObject2JsonString(Object o) {

		try {
			return objectMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "{}";
	}

}
