package com.diablo73.springApps.springBootNoSQLApplication.core.service.impl;

import com.diablo73.springApps.springBootNoSQLApplication.constants.APIConstantEnum;
import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ResultInfoEnum;
import com.diablo73.springApps.springBootNoSQLApplication.core.innerService.EmailInnerService;
import com.diablo73.springApps.springBootNoSQLApplication.core.service.EmailService;
import com.diablo73.springApps.springBootNoSQLApplication.structures.models.email.EmailStatus;
import com.diablo73.springApps.springBootNoSQLApplication.structures.models.email.EmailStatusData;
import com.diablo73.springApps.springBootNoSQLApplication.structures.models.email.EmailStructure;
import com.diablo73.springApps.springBootNoSQLApplication.structures.request.Request;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.Response;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.ResponseBody;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.ResultInfo;
import com.diablo73.springApps.springBootNoSQLApplication.template.APIProcessTemplate;
import com.diablo73.springApps.springBootNoSQLApplication.template.APIProcessTemplateImpl;
import com.diablo73.springApps.springBootNoSQLApplication.utils.MapperUtil;
import com.diablo73.springApps.springBootNoSQLApplication.utils.ParamValidatorUtil;
import com.diablo73.springApps.springBootNoSQLApplication.utils.ThreadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailInnerService emailInnerService;


	@Override
	public Response sendEmail(Request request) {
		final Response response = new Response(new ResponseBody());

		return APIProcessTemplateImpl.execute(request.getHead().getFunction(),
				new APIProcessTemplate<EmailStructure, EmailStatus, Response>() {

					@Override
					public void validate() {
						ParamValidatorUtil.checkFunction(APIConstantEnum.EMAIL_SEND, request.getHead().getFunction());
						ParamValidatorUtil.checkStringNotEmpty(request.getBody().getEmailStructure().getFrom(), "From");
						ParamValidatorUtil.checkStringNotEmpty(request.getBody().getEmailStructure().getTo(), "To");
						ParamValidatorUtil.checkStringNotEmpty(request.getBody().getEmailStructure().getSubject(), "Subject");
					}

					@Override
					public EmailStructure convertRequest() {
						return request.getBody().getEmailStructure();
					}

					@Override
					public EmailStatus invoke(EmailStructure emailStructure) {
						String emailResponse = emailInnerService.sendEmail(emailStructure);
						String messageId = ((EmailStatus) MapperUtil.convertJsonString2Object(emailResponse, EmailStatus.class))
								.getData().getMessageid();
						ThreadUtil.sleep(2500);
						EmailStatus emailStatus = (EmailStatus) MapperUtil.convertJsonString2Object(
								emailInnerService.checkMailStatus(messageId), EmailStatus.class);
						emailStatus.getData().setMessageid(messageId);
						return emailStatus;
					}

					@Override
					public Response convertResponse(EmailStatus emailStatus) {
						response.getBody().setEmailStatus(emailStatus);
						return response;
					}

					@Override
					public Response composeResultInfo() {
						EmailStatus emailStatus = response.getBody().getEmailStatus();
						EmailStatusData emailStatusData = emailStatus.getData();
						if (emailStatus.isSuccess() && emailStatusData.getErrormessage().isEmpty()) {
							response.getBody().setResultInfo(new ResultInfo(ResultInfoEnum.getResultInfoEnumWithMessage(
									ResultInfoEnum.EMAIL_SUCCESS,
											emailStatusData.getStatusname())));
						} else {
							response.getBody().setResultInfo(new ResultInfo(ResultInfoEnum.getResultInfoEnumWithMessage(
									ResultInfoEnum.EMAIL_FAILURE,
											emailStatusData.getErrormessage())));
						}
						return response;
					}

					@Override
					public Response composeFailResultInfo(ResultInfoEnum resultInfoEnum) {
						return null;
					}
				});
	}

	@Override
	public Response checkEmailStatus(Request request) {
		final Response response = new Response(new ResponseBody());

		return APIProcessTemplateImpl.execute(request.getHead().getFunction(),
				new APIProcessTemplate<String, EmailStatus, Response>() {

					@Override
					public void validate() {
						ParamValidatorUtil.checkFunction(APIConstantEnum.EMAIL_STATUS, request.getHead().getFunction());
						ParamValidatorUtil.checkStringNotEmpty(request.getBody().getEmailStructure().getMessageId(), "MessageId");
					}

					@Override
					public String convertRequest() {
						return request.getBody().getEmailStructure().getMessageId();
					}

					@Override
					public EmailStatus invoke(String messageId) {
						EmailStatus emailStatus = (EmailStatus) MapperUtil.convertJsonString2Object(
								emailInnerService.checkMailStatus(messageId), EmailStatus.class);
						emailStatus.getData().setMessageid(messageId);
						return emailStatus;
					}

					@Override
					public Response convertResponse(EmailStatus emailStatus) {
						response.getBody().setEmailStatus(emailStatus);
						return response;
					}

					@Override
					public Response composeResultInfo() {
						EmailStatus emailStatus = response.getBody().getEmailStatus();
						EmailStatusData emailStatusData = emailStatus.getData();
						if (emailStatus.isSuccess() && emailStatusData.getErrormessage().isEmpty()) {
							response.getBody().setResultInfo(new ResultInfo(ResultInfoEnum.getResultInfoEnumWithMessage(
									ResultInfoEnum.EMAIL_SUCCESS,
											emailStatusData.getStatusname())));
						} else {
							response.getBody().setResultInfo(new ResultInfo(ResultInfoEnum.getResultInfoEnumWithMessage(
									ResultInfoEnum.EMAIL_FAILURE,
											emailStatusData.getErrormessage())));
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
