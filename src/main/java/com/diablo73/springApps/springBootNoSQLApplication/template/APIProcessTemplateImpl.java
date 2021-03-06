package com.diablo73.springApps.springBootNoSQLApplication.template;

import com.diablo73.springApps.springBootNoSQLApplication.config.exceptions.ParamValidatorException;
import com.diablo73.springApps.springBootNoSQLApplication.monitors.KamonMonitor;
import io.cronitor.client.CronitorClient;
import lombok.SneakyThrows;

import java.util.Map;

public class APIProcessTemplateImpl {

	private static final CronitorClient cronitorClient = new CronitorClient(System.getenv("CRONITOR_API_KEY"));

	@SneakyThrows
	public static <REQUEST, RESULT, RESPONSE> RESPONSE execute(String apiName, APIProcessTemplate<REQUEST, RESULT, RESPONSE> processCallback) {

		RESPONSE response = null;
		KamonMonitor.apiHitCountIncrement(apiName);

		try {
			processCallback.validate();

			REQUEST request = processCallback.convertRequest();

			RESULT result = processCallback.invoke(request);

			response = processCallback.convertResponse(result);

			response = processCallback.composeResultInfo();

			cronitorClient.tick(System.getenv("CRONITOR_MONITOR_KEY"),"tick", Map.ofEntries(Map.entry("count", 1)));
			KamonMonitor.apiSuccessCountIncrement(apiName);

		} catch (ParamValidatorException e) {
			cronitorClient.fail(System.getenv("CRONITOR_MONITOR_KEY"),"fail", Map.ofEntries(Map.entry("error_count", 1)));
			KamonMonitor.apiFailureCountIncrement(apiName);
			response = processCallback.composeFailResultInfo(e.getResultInfoEnum());

		} catch (Exception e) {
			cronitorClient.fail(System.getenv("CRONITOR_MONITOR_KEY"),"fail", Map.ofEntries(Map.entry("error_count", 1)));
			KamonMonitor.apiFailureCountIncrement(apiName);
			System.out.println(e.getMessage());

		} finally {

		}
		return response;
	}
}
