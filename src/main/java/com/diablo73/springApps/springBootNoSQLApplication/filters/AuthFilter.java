package com.diablo73.springApps.springBootNoSQLApplication.filters;

import com.diablo73.springApps.springBootNoSQLApplication.constants.APIConstants;
import com.diablo73.springApps.springBootNoSQLApplication.constants.enums.ResultInfoEnum;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.Response;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.ResponseBody;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.ResponseHead;
import com.diablo73.springApps.springBootNoSQLApplication.structures.response.ResultInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

//@Order(0)
@Component("authFilter")
public class AuthFilter implements Filter {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

		String auth64 = new String(Base64.getDecoder().decode(httpServletRequest.getHeader("authorization").substring(6)));
		if (Arrays.stream(System.getenv("auth").split("\\,")).noneMatch(auth64::equals) &&
				!StringUtils.EMPTY.equals(httpServletRequest.getContextPath())) {

			servletResponse.getWriter().write(objectMapper.writeValueAsString(createAuthFailedResponse(httpServletRequest.getContextPath())));
			return;
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}

	private Response createAuthFailedResponse(String contextPath) {

		Response response = new Response();
		ResponseHead responseHead = new ResponseHead();
		ResponseBody responseBody = new ResponseBody();

		responseHead.setResponseTime(new Date());
		responseHead.setFunction(contextPath.isEmpty() ? APIConstants.DEFAULT_MESSAGE : contextPath);
		responseBody.setResultInfo(new ResultInfo(ResultInfoEnum.AUTH_CHECK_FAILED));

		response.setHead(responseHead);
		response.setBody(responseBody);

		return response;
	}
}
