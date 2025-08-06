package com.nagp.advancedevops.assignment.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "usersrecord")
@Getter
@Setter
public class UserRecordInfoDto {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;

}
