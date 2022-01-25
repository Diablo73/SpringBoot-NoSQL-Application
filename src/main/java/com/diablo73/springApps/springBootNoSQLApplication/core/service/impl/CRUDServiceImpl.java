package com.diablo73.springApps.springBootNoSQLApplication.core.service.impl;

import com.diablo73.springApps.springBootNoSQLApplication.constants.APIConstantEnum;
import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ParametersEnum;
import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ResultInfoEnum;
import com.diablo73.springApps.springBootNoSQLApplication.core.innerService.CRUDInnerService;
import com.diablo73.springApps.springBootNoSQLApplication.core.service.CRUDService;
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
import java.util.Map;

@Service
public class CRUDServiceImpl implements CRUDService {

	@Autowired
	private CRUDInnerService crudInnerService;


	@Override
	public Response get(Request request) {
		final Response response = new Response(new ResponseBody());

		return APIProcessTemplateImpl.execute(request.getHead().getFunction(),
				new APIProcessTemplate<Map<ParametersEnum, String>, String, Response>() {

					@Override
					public void validate() {
						ParamValidatorUtil.checkFunction(APIConstantEnum.GET_RECORD_BY_DOCUMENT_ID,request.getHead().getFunction());
						ParamValidatorUtil.checkDocumentId(request.getBody().getDocumentId());
					}

					@Override
					public Map<ParametersEnum, String> convertRequest() {
						Map<ParametersEnum, String> parameters = new HashMap<>();
						parameters.put(ParametersEnum.DOCUMENT_ID, request.getBody().getDocumentId());
						parameters.put(ParametersEnum.TABLE_NAME, request.getBody().getTableName());

						return parameters;
					}

					@Override
					public String invoke(Map<ParametersEnum, String> parameters) {
						return crudInnerService.get(parameters);
					}

					@Override
					public Response convertResponse(String result) {

						Map<String, Object> resultMap = new HashMap<>();
						if (result.contains(request.getBody().getDocumentId())) {
							resultMap.put(request.getBody().getDocumentId(), MapperUtil.convert2Map(result));
						}
						response.getBody().setDocumentList(resultMap);
						response.getBody().setDocumentCount(resultMap.keySet().size());
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
						response.getBody().setResultInfo(new ResultInfo(resultInfoEnum));
						return response;
					}
				});
	}

	@Override
	public Response post(Request request) {
		return null;
	}

	@Override
	public Response put(Request request) {
		return null;
	}

	@Override
	public Response delete(Request request) {
		return null;
	}
}
