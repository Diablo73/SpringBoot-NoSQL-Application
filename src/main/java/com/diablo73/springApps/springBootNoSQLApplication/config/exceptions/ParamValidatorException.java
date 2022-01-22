package com.diablo73.springApps.springBootNoSQLApplication.config.exceptions;

import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ResultInfoEnum;
import lombok.Data;

@Data
public class ParamValidatorException extends RuntimeException {

	ResultInfoEnum resultInfoEnum;


	public ParamValidatorException(ResultInfoEnum resultInfoEnum) {
		super(resultInfoEnum.getResultMessage());
		this.resultInfoEnum = resultInfoEnum;
	}
}
