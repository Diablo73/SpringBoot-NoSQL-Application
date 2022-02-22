package com.diablo73.springApps.springBootNoSQLApplication.structures.models.email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailStatus {

	boolean success;
	EmailStatusData data;
}
