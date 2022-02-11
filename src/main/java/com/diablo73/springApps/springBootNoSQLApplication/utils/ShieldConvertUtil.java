package com.diablo73.springApps.springBootNoSQLApplication.utils;

public class ShieldConvertUtil {

	private static final String MASK		=	"*";
	private static final String ALPHASAND	=	"@";
	private static final String FULL_STOP	=	".";

	public static String shieldPassword(String password) {
		return MASK.repeat(password.length());
	}

	public static String shieldPhone(String phone) {
		StringBuilder maskedPhone = new StringBuilder(phone);
		for (int i = 2 ; i < 7 ; i++) {
			maskedPhone.setCharAt(i, MASK.charAt(0));
		}
		return String.valueOf(maskedPhone);
	}

	public static String shieldEmail(String email) {
		StringBuilder maskedId = new StringBuilder(email.substring(0, email.indexOf(ALPHASAND)));
		StringBuilder maskedDomain = new StringBuilder(email.substring(email.indexOf(ALPHASAND) + 1));
		if (maskedId.length() < 6) {
			maskedId.replace(0, maskedId.length(), MASK.repeat(maskedId.length()));
		} else {
			maskedId.replace(3, maskedId.length(), MASK.repeat(maskedId.length() - 3));
		}
		maskedDomain.replace(0, maskedDomain.indexOf(FULL_STOP), MASK.repeat(maskedDomain.indexOf(FULL_STOP)));

		return String.valueOf(maskedId.append(ALPHASAND).append(maskedDomain));
	}

}
