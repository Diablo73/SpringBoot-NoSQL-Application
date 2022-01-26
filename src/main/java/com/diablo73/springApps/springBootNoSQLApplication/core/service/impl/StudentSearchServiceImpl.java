package com.diablo73.springApps.springBootNoSQLApplication.core.service.impl;

import com.diablo73.springApps.springBootNoSQLApplication.constants.APIConstantEnum;
import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ParametersEnum;
import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ResultInfoEnum;
import com.diablo73.springApps.springBootNoSQLApplication.core.innerService.StudentSearchInnerService;
import com.diablo73.springApps.springBootNoSQLApplication.core.service.StudentSearchService;
import com.diablo73.springApps.springBootNoSQLApplication.structures.request.Request;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.Response;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.ResponseBody;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.ResultInfo;
import com.diablo73.springApps.springBootNoSQLApplication.template.APIProcessTemplate;
import com.diablo73.springApps.springBootNoSQLApplication.template.APIProcessTemplateImpl;
import com.diablo73.springApps.springBootNoSQLApplication.utils.MapperUtil;
import com.diablo73.springApps.springBootNoSQLApplication.utils.ParamValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class StudentSearchServiceImpl implements StudentSearchService {

	@Autowired
	private StudentSearchInnerService studentSearchInnerService;


	@Override
	public Response searchBySearchParams(Request request) {
		final Response response = new Response(new ResponseBody());

		return APIProcessTemplateImpl.execute(request.getHead().getFunction(),
				new APIProcessTemplate<Map<String, Object>, Map<String, String>, Response>() {
					@Override
					public void validate() {
						ParamValidatorUtil.checkFunction(APIConstantEnum.STUDENT_RECORD_BY_SEARCH_PARAMS, request.getHead().getFunction());
						ParamValidatorUtil.checkSearchParams(request.getBody().getSearchParams());
					}

					@Override
					public Map<String, Object> convertRequest() {
						Map<String, Object> searchParams = request.getBody().getSearchParams();
						Map<String, Object> parameters = new HashMap<>();
						for (String key : searchParams.keySet()) {
							if (Objects.nonNull(ParametersEnum.getEnumByName(key))) {
								if (searchParams.get(key) instanceof String) {
									parameters.put(
											ParametersEnum.getEnumByName(key).getQueryName(),
											ParametersEnum.getStringifyQuery((String) searchParams.get(key)));
								} else if (searchParams.get(key) instanceof List && ((List<?>) searchParams.get(key)).get(0) instanceof String){
									List<String> paramStringList = (List<String>) searchParams.get(key);
									for (int i = 0 ; i < paramStringList.size() ; i++) {
										paramStringList.set(i, ParametersEnum.getStringifyQuery(paramStringList.get(i)));
									}
									parameters.put(ParametersEnum.getEnumByName(key).getQueryName(), paramStringList);
								} else {
									parameters.put(ParametersEnum.getEnumByName(key).getQueryName(), searchParams.get(key));
								}
							}
						}
						return parameters;
					}

					@Override
					public Map<String, String> invoke(Map<String, Object> parameters) {
						return studentSearchInnerService.searchQuery(parameters);
					}

					@Override
					public Response convertResponse(Map<String, String> searchDataMap) {
						Map<String, Object> documentList = new HashMap<>();
						List<Map<String, String>> studentsSearchDataList = MapperUtil.convert2List(searchDataMap.get(ParametersEnum.STUDENTS_TABLE.getName()));
						List<Map<String, String>> marksSearchDataList = MapperUtil.convert2List(searchDataMap.get(ParametersEnum.MARKS_TABLE.getName()));

						for (Map<String, String> studentData :studentsSearchDataList) {
							String rollNo = String.valueOf(studentData.get(ParametersEnum.ROLL_NO.getField()));
							for (Map<String, String> markData :marksSearchDataList) {
								if (rollNo.equals(String.valueOf(markData.get(ParametersEnum.ROLL_NO.getField())))) {
									studentData.putAll(markData);
									documentList.put(rollNo, studentData);
								}
							}
						}

						response.getBody().setDocumentList(documentList);
						response.getBody().setDocumentCount(documentList.keySet().size());
						return response;
					}

					@Override
					public Response composeResultInfo() {
						if (response.getBody().getDocumentCount() > 0) {
							response.getBody().setResultInfo(new ResultInfo(ResultInfoEnum.SUCCESS));
						} else {
							response.getBody().setResultInfo(new ResultInfo(ResultInfoEnum.DOCUMENT_NOT_FOUND));
						}
						return response;
					}

					@Override
					public Response composeFailResultInfo(ResultInfoEnum resultInfoEnum) {
						return null;
					}
				});
	}
}
