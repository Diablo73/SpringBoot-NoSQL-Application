package com.diablo73.springApps.springBootNoSQLApplication.monitors;

import com.diablo73.springApps.springBootNoSQLApplication.drms.ReplItDbDrm;
import kamon.Kamon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class KamonMonitor {

	private static final ReplItDbDrm replItDbDrm = new ReplItDbDrm();


	public static void kamonInit() {
		if (getKamonStatusOn()) {
			Kamon.init();
		}
	}

	public static void apiHitCountIncrement(String apiName) {
		if (replItDbDrm.getKamonStatusOn()) {
			Kamon.counter("API_HIT_COUNT").withTag(apiName, 1).increment();
		}
	}

	public static void apiSuccessCountIncrement(String apiName) {
		if (replItDbDrm.getKamonStatusOn()) {
			Kamon.counter("API_SUCCESS_COUNT").withTag(apiName, 1).increment();
		}
	}

	public static void apiFailureCountIncrement(String apiName) {
		if (replItDbDrm.getKamonStatusOn()) {
			Kamon.counter("API_FAILURE_COUNT").withTag(apiName, 1).increment();
		}
	}

	private static boolean getKamonStatusOn() {
		HttpURLConnection http;
		String kamonStatusOn = String.valueOf(false);
		try {
			String replDbURL = System.getenv("REPLIT_DB_URL").concat("/").concat("kamonStatusOn");
			URL url = new URL(replDbURL);
			http = (HttpURLConnection)url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
			String current;

			while((current = in.readLine()) != null) {
				kamonStatusOn = current;
			}
			http.disconnect();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Boolean.parseBoolean(kamonStatusOn);
	}

}
