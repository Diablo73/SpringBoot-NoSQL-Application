package com.diablo73.springApps.springBootNoSQLApplication.structures.request;

import com.diablo73.springApps.springBootNoSQLApplication.structures.models.email.EmailStructure;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestBody {

	String documentId;
	String tableName;
	Map<String, Object> searchParams;
	EmailStructure emailStructure;

}
