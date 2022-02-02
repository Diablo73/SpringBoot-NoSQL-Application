package com.diablo73.springApps.springBootNoSQLApplication.unitTests;

import com.diablo73.springApps.springBootNoSQLApplication.config.exceptions.ParamValidatorException;
import com.diablo73.springApps.springBootNoSQLApplication.constants.APIConstantEnum;
import com.diablo73.springApps.springBootNoSQLApplication.constants.ParamConstants;
import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ParametersEnum;
import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ResultInfoEnum;
import com.diablo73.springApps.springBootNoSQLApplication.utils.ParamValidatorUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Random;

public class ParamValidatorUtilTests {

	@Test
	public void checkFunctionPassTestCase() {
		APIConstantEnum apiConstantEnum = APIConstantEnum.values()[new Random().nextInt(APIConstantEnum.values().length)];
		ParamValidatorUtil.checkFunction(apiConstantEnum, apiConstantEnum.getApiName());
	}

	@Test
	public void checkFunctionFailTestCase() {
		try {
			APIConstantEnum apiConstantEnum = APIConstantEnum.values()[new Random().nextInt(APIConstantEnum.values().length)];
			ParamValidatorUtil.checkFunction(apiConstantEnum, apiConstantEnum.getApiPath());
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), ParamValidatorException.class);
			Assert.assertEquals(e.getMessage(), ResultInfoEnum.WRONG_FUNCTION.getResultMessage());
			Assert.assertEquals(((ParamValidatorException) e).getResultInfoEnum(), ResultInfoEnum.WRONG_FUNCTION);
		}
	}

	@Test
	public void checkDocumentIdPassTestCase() {
		ParamValidatorUtil.checkDocumentId(RandomStringUtils.randomAlphanumeric(ParamConstants.DOCUMENT_ID_LENGTH));
	}

	@Test
	public void checkDocumentIdFailTestCase() {
		try {
			ParamValidatorUtil.checkDocumentId(RandomStringUtils.randomAlphanumeric(0, ParamConstants.DOCUMENT_ID_LENGTH));
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), ParamValidatorException.class);
			Assert.assertEquals(e.getMessage(), ResultInfoEnum.WRONG_DOCUMENT_ID.getResultMessage());
			Assert.assertEquals(((ParamValidatorException) e).getResultInfoEnum(), ResultInfoEnum.WRONG_DOCUMENT_ID);
		}
	}

	@Test
	public void checkSearchParamsPassTestCase() {
		ParamValidatorUtil.checkSearchParams(new HashMap<>(){{
			put(ParametersEnum.SECTION.getName(), "A");
		}});
	}

	@Test
	public void checkSearchParamsEmptyTestCase() {
		try {
			ParamValidatorUtil.checkSearchParams(new HashMap<>());
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), ParamValidatorException.class);
			Assert.assertEquals(e.getMessage(), ResultInfoEnum.SEARCH_PARAM_EMPTY.getResultMessage());
			Assert.assertEquals(((ParamValidatorException) e).getResultInfoEnum(), ResultInfoEnum.SEARCH_PARAM_EMPTY);
		}
	}

	@Test
	public void checkSearchParamsNoneMatchTestCase() {
		try {
			ParamValidatorUtil.checkSearchParams(new HashMap<>(){{
				put(RandomStringUtils.randomAlphabetic(6), "A");
			}});
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), ParamValidatorException.class);
			Assert.assertEquals(e.getMessage(), ResultInfoEnum.WRONG_SEARCH_PARAM.getResultMessage());
			Assert.assertEquals(((ParamValidatorException) e).getResultInfoEnum(), ResultInfoEnum.WRONG_SEARCH_PARAM);
		}
	}

}
