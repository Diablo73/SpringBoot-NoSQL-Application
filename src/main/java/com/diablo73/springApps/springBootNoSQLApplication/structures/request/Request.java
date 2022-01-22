package com.diablo73.springApps.springBootNoSQLApplication.structures.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {

    RequestHead head;
    RequestBody body;
}
