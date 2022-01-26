package com.diablo73.springApps.springBootNoSQLApplication.core.innerService.impl;

import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ParametersEnum;
import com.diablo73.springApps.springBootNoSQLApplication.core.innerService.StudentSearchInnerService;
import com.diablo73.springApps.springBootNoSQLApplication.core.repository.search.CoreRestTemplate;
import com.diablo73.springApps.springBootNoSQLApplication.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentSearchInnerServiceImpl implements StudentSearchInnerService {

	@Autowired
	private CoreRestTemplate coreRestTemplate;

	private static final String studentsURL = System.getenv("studentsURL");
	private static final String studentsDBName = System.getenv("studentsDBName");
	private static final String marksURL = System.getenv("marksURL");
	private static final String marksDBName = System.getenv("marksDBName");
	private static final String PATH_SEPARATOR = "/";
	private static final String QUERY_SEPARATOR = "?q=";


	@Override
	public Map<String, String> searchQuery(Map<String, Object> parameters) {

		String parametersString = convert2String(parameters.toString());

		String studentsTableResultString = getStudentsTableResultString(parametersString);
		List<Map<String, String>> studentsSearchDataList = MapperUtil.convert2List(studentsTableResultString);

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
		url.append(studentsURL).append(studentsDBName).append(QUERY_SEPARATOR).append(parametersString);

		return coreRestTemplate.execute(url.toString(), HttpMethod.GET, null);
	}

	private String getMarksTableResultString(String parametersString) {

		StringBuilder url = new StringBuilder();
		url.append(marksURL).append(marksDBName).append(QUERY_SEPARATOR).append(parametersString);

		return coreRestTemplate.execute(url.toString(), HttpMethod.GET, null);
	}
}
