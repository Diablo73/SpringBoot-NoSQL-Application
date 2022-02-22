package com.diablo73.springApps.springBootNoSQLApplication.core.innerService;

import com.diablo73.springApps.springBootNoSQLApplication.structures.models.email.EmailStructure;

public interface EmailInnerService {

	String sendEmail(EmailStructure emailStructure);

	String checkMailStatus(String messageID);
}
