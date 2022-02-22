package com.diablo73.springApps.springBootNoSQLApplication.core.innerService.impl;

import com.diablo73.springApps.springBootNoSQLApplication.core.innerService.EmailInnerService;
import com.diablo73.springApps.springBootNoSQLApplication.structures.models.email.EmailStructure;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmailInnerServiceImpl extends CommonInnerServiceImpl implements EmailInnerService {

	private final String EMAIL_API_KEY 		= System.getenv("EMAIL_API_KEY");
	private final String EMAIL_SEND_KEY 	= "https://api.elasticemail.com/v2/email/send?";
	private final String EMAIL_STATUS_KEY 	= "https://api.elasticemail.com/v2/email/status?";

	@Override
	public String sendEmail(EmailStructure emailStructure) {

		StringBuilder params = new StringBuilder();
		params.append("apikey=").append(EMAIL_API_KEY);
		params.append("&from=").append(emailStructure.getFrom());
		params.append("&fromName=").append(
				Objects.nonNull(emailStructure.getFromName()) && !emailStructure.getFromName().isEmpty()
				? emailStructure.getFromName()
				: emailStructure.getFrom().split("@")[0]);
		params.append("&subject=").append(emailStructure.getSubject());
		if (Objects.nonNull(emailStructure.getBodyHtml())) {
			params.append("&bodyHtml=").append(emailStructure.getBodyHtml());
		}
		if (Objects.nonNull(emailStructure.getBodyText())) {
			params.append("&bodyText=").append(emailStructure.getBodyText());
		}
		params.append("&to=").append(emailStructure.getTo());
		params.append("&timeOffSetMinutes=").append(emailStructure.getTimeOffSetMinutes());
		params.append("&isTransactional=").append(emailStructure.isTransactional());

		return coreRestTemplate.execute(EMAIL_SEND_KEY.concat(String.valueOf(params)), HttpMethod.GET, null, null);
	}

	@Override
	public String checkMailStatus(String messageID) {

		StringBuilder params = new StringBuilder();
		params.append("apikey=").append(EMAIL_API_KEY);
		params.append("&messageID=").append(messageID);

		return coreRestTemplate.execute(EMAIL_STATUS_KEY.concat(String.valueOf(params)), HttpMethod.GET, null, null);
	}

}
