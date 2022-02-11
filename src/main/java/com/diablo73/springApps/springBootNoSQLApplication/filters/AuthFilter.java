package com.diablo73.springApps.springBootNoSQLApplication.filters;

import com.diablo73.springApps.springBootNoSQLApplication.constants.APIConstantEnum;
import com.diablo73.springApps.springBootNoSQLApplication.constants.APIPathConstants;
import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ResultInfoEnum;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.Response;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.ResponseBody;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.ResponseHead;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.ResultInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cronitor.client.CronitorClient;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component("authFilter")
public class AuthFilter implements Filter {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	private final CronitorClient cronitorClient = new CronitorClient(System.getenv("CRONITOR_API_KEY"));

	private final List<String> authNotRequiredAPIs =
			List.of(APIPathConstants.BLANK,
					APIPathConstants.DEFAULT_MESSAGE);


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

		if (authCheckPass(httpServletRequest) && pathAndMethodAvailable()) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			cronitorClient.fail(System.getenv("CRONITOR_MONITOR_KEY"),"fail", Map.ofEntries(Map.entry("error_count", 1)));
			servletResponse.getWriter().write(objectMapper.writeValueAsString(createAuthFailedResponse(httpServletRequest.getServletPath())));
			return;
		}
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}

	private boolean authCheckPass(HttpServletRequest httpServletRequest) {

		if (!authNotRequiredAPIs.contains(httpServletRequest.getServletPath())) {
			String auth64Encoded = httpServletRequest.getHeader("authorization");
			if (ObjectUtils.allNotNull(auth64Encoded)) {
				String auth64Decoded = new String(Base64.getDecoder().decode(auth64Encoded.substring(6)));
				return Arrays.asList(System.getenv("AUTH").split("\\,")).contains(auth64Decoded);
			} else {
				return false;
			}
		}
		return true;
	}


	private boolean pathAndMethodAvailable() {
//		TODO
		return true;
	}

	private Response createAuthFailedResponse(String servletPath) {

		Response response = new Response();
		ResponseHead responseHead = new ResponseHead();
		ResponseBody responseBody = new ResponseBody();

		responseHead.setResponseTime(String.valueOf(new Date()));
		responseHead.setFunction(
				APIConstantEnum.BLANK_GET.getApiPath().equals(servletPath)
				? APIConstantEnum.DEFAULT_MESSAGE_GET.getApiName()
				: servletPath);
		responseBody.setResultInfo(new ResultInfo(ResultInfoEnum.AUTH_CHECK_FAILED));

		response.setHead(responseHead);
		response.setBody(responseBody);

		return response;
	}
}
