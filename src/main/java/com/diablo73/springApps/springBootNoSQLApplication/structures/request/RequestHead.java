package com.diablo73.springApps.springBootNoSQLApplication.structures.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestHead {

    String function;
    Date requestTime;
}
