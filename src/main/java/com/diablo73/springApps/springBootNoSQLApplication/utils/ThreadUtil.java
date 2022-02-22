package com.diablo73.springApps.springBootNoSQLApplication.utils;

import org.apache.commons.lang3.ThreadUtils;

import java.time.Duration;

public class ThreadUtil {

	public static void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public static void sleep(int ms) {
		try {
			ThreadUtils.sleep(Duration.ofMillis(ms));
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
