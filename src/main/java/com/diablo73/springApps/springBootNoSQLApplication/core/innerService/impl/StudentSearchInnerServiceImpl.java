package com.diablo73.springApps.springBootNoSQLApplication.core.innerService.impl;

import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ParametersEnum;
import com.diablo73.springApps.springBootNoSQLApplication.core.innerService.StudentSearchInnerService;
import com.diablo73.springApps.springBootNoSQLApplication.utils.MapperUtil;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentSearchInnerServiceImpl extends CommonInnerServiceImpl implements StudentSearchInnerService {


	@Override
	public Map<String, String> searchQuery(Map<String, Object> parameters) {

		String parametersString = convert2String(parameters.toString());

		String studentsTableResultString = getStudentsTableResultString(parametersString);
		List<Map<String, String>> studentsSearchDataList = MapperUtil.convertJsonString2List(studentsTableResultString);

		List<Integer> rollNoList = new ArrayList<>();
		for (Map<String, String> studentData :studentsSearchDataList) {
			Integer rollNo = Integer.valueOf(String.valueOf(studentData.get(ParametersEnum.ROLL_NO.getField())));
			rollNoList.add(rollNo);
		}
		parameters.clear();
		parameters.put(ParametersEnum.ROLL_NO.getQueryName(), Map.ofEntries(Map.entry("\"$in\"", rollNoList)).toString());
		parametersString = convert2String(parameters.toString());

		String marksTableResultString = getMarksTableResultString(parametersString);

		Map<String, String> studentDataMap = new HashMap<>();
		studentDataMap.put(ParametersEnum.STUDENTS_TABLE.getName(), studentsTableResultString);
		studentDataMap.put(ParametersEnum.MARKS_TABLE.getName(), marksTableResultString);

		return studentDataMap;
	}

	private String convert2String(String parameters) {
		StringBuilder parametersString = new StringBuilder();

		for (int index = 0; index < parameters.length(); index++) {
			String c = String.valueOf(parameters.charAt(index));
			if ("=".equals(c)) {
				parametersString.append(":");
			} else {
				parametersString.append(c);
			}
		}

		return parametersString.toString();
	}

	private String getStudentsTableResultString(String parametersString) {

		StringBuilder url = new StringBuilder();
		url.append(STUDENTS_URL).append(STUDENTS_DB_NAME).append(QUERY_SEPARATOR).append(parametersString);

		return coreRestTemplate.execute(url.toString(), HttpMethod.GET, null, null);
	}

	private String getMarksTableResultString(String parametersString) {

		StringBuilder url = new StringBuilder();
		url.append(MARKS_URL).append(MARKS_DB_NAME).append(QUERY_SEPARATOR).append(parametersString);

		return coreRestTemplate.execute(url.toString(), HttpMethod.GET, null, null);
	}
}
