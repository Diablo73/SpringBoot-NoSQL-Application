package com.diablo73.springApps.springBootNoSQLApplication.structures.models.email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailStructure {

	String from;
	String fromName;
	String to;
	String subject;
	String bodyHtml;
	String bodyText;
	int timeOffSetMinutes;
	boolean isTransactional;

	String messageId;
}
