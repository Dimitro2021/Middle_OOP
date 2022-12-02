package com.webparser.web.services;

import com.webparser.web.dto.UrlDto;
import com.webparser.web.model.Url;

import java.util.List;

public interface UrlService {
    List<UrlDto> findAllUrl();

    Url isIn(String name_url);

    Url saveUrl(Url url);
}
