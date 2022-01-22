package com.diablo73.springApps.springBootNoSQLApplication.structures.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

	ResponseHead head;
	ResponseBody body;


	public Response(ResponseBody responseBody) {
		this.body = responseBody;
	}
}
