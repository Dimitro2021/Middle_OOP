package com.webparser.web.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UrlDto {
    private long id;
    private String name_url;
    private String name, twitter, facebook;
    private String logo, icon, employees, address;
}
