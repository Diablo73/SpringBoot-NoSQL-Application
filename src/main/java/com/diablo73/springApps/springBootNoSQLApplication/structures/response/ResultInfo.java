package com.diablo73.springApps.springBootNoSQLApplication.structures.response;

import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ResultInfoEnum;
import lombok.Data;

@Data
public class ResultInfo {

	private String resultCode;
	private String resultStatus;
	private String resultMessage;


	public ResultInfo(ResultInfoEnum resultInfoEnum) {
		this.resultCode = resultInfoEnum.getResultCode();
		this.resultStatus = resultInfoEnum.getResultStatus();
		this.resultMessage = resultInfoEnum.getResultMessage();
	}

}
