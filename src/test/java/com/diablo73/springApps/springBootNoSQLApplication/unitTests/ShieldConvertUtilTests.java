package com.diablo73.springApps.springBootNoSQLApplication.unitTests;

import com.diablo73.springApps.springBootNoSQLApplication.utils.ShieldConvertUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShieldConvertUtilTests {

	private static final String MASK					=	"*";
	private static final String ALPHASAND				=	"@";
	private static final String FULL_STOP				=	".";
	private static final int MINIMUM_PASSWORD_LENGTH	=	8;
	private static final int MAXIMUM_PASSWORD_LENGTH	=	20;
	private static final int PHONE_LENGTH				=	10;
	private static final int PHONE_MASKING_LENGTH		=	5;
	private static final int EMAIL_MASKING_LENGTH		=	4;
	private static final int EMAIL_MINIMUM_BOUND		=	6;
	private static final int EMAIL_MAXIMUM_BOUND		=	20;

	@Test
	public void shieldPasswordTestCase() {
		String actual = ShieldConvertUtil.shieldPassword(
				RandomStringUtils.randomAlphanumeric(MINIMUM_PASSWORD_LENGTH, MAXIMUM_PASSWORD_LENGTH));
		Assert.assertTrue(actual.contains(MASK.repeat(MINIMUM_PASSWORD_LENGTH)));
	}

	@Test
	public void shieldPhoneTestCase() {
		String actual = ShieldConvertUtil.shieldPhone(
				RandomStringUtils.randomNumeric(PHONE_LENGTH));
		Assert.assertTrue(actual.contains(MASK.repeat(PHONE_MASKING_LENGTH)));
	}

	@Test
	public void shieldEmail5TestCase() {
		String actual = ShieldConvertUtil.shieldEmail(
				RandomStringUtils.randomAlphanumeric(1, EMAIL_MINIMUM_BOUND)
						.concat(ALPHASAND)
						.concat(RandomStringUtils.randomAlphabetic(2, EMAIL_MINIMUM_BOUND))
						.concat(FULL_STOP)
						.concat(RandomStringUtils.randomAlphabetic(2, EMAIL_MINIMUM_BOUND)));
		Assert.assertTrue(actual.startsWith(MASK));
	}

	@Test
	public void shieldEmail6TestCase() {
		String actual = ShieldConvertUtil.shieldEmail(
				RandomStringUtils.randomAlphanumeric(EMAIL_MINIMUM_BOUND, EMAIL_MAXIMUM_BOUND)
						.concat(ALPHASAND)
						.concat(RandomStringUtils.randomAlphabetic(2, EMAIL_MINIMUM_BOUND))
						.concat(FULL_STOP)
						.concat(RandomStringUtils.randomAlphabetic(2, EMAIL_MINIMUM_BOUND)));
		Assert.assertTrue(actual.contains(MASK.repeat(EMAIL_MASKING_LENGTH)));
		Assert.assertTrue(actual.contains(ALPHASAND));
		Assert.assertTrue(actual.contains(FULL_STOP));
	}

}
