package com.diablo73.springApps.springBootNoSQLApplication.core.innerService;

import java.util.Map;

public interface StudentSearchInnerService {

	Map<String, String> searchQuery(Map<String, Object> parameters);

}
