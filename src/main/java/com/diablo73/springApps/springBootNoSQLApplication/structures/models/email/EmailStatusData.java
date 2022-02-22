package com.diablo73.springApps.springBootNoSQLApplication.structures.models.email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailStatusData {

	String from;
	String to;
	Date date;
	int status;
	String statusname;
	Date statuschangedate;
	Date datesent;
	Date dateopened;
	Date dateclicked;
	String errormessage;
	String transactionid;
	String envelopefrom;

	String messageid;
}
