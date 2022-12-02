package com.webparser.web.services.impl;

import com.webparser.web.dto.UrlDto;
import com.webparser.web.model.Url;
import com.webparser.web.repository.UrlRepository;
import com.webparser.web.services.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlRepository urlRepository;

    @Override
    public List<UrlDto> findAllUrl() {
        List<Url> urls = urlRepository.findAll();
        return urls.stream().map(this::mapToUrlDto).collect(Collectors.toList());
    }

    @Override
    public Url isIn(String name_url) {
        List<Url> urls = urlRepository.findAll();
        for (int i = 0; i < urls.size(); i++) {
            if (name_url == urls.get(i).getName_url()){
                return urls.get(i);
            }
        }
        return null;
    }

    @Override
    public Url saveUrl(Url url) {
        return urlRepository.save(url);
    }

    private UrlDto mapToUrlDto(Url url){
        UrlDto urlDto = UrlDto.builder()
                .id(url.getId())
                .name_url(url.getName_url())
                .name(url.getName())
                .twitter(url.getTwitter())
                .facebook(url.getFacebook())
                .logo(url.getLogo())
                .icon(url.getIcon())
                .employees(url.getEmployees())
                .address(url.getAddress())
                .build();
        return urlDto;
    }
}
