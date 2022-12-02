package com.webparser.web.repository;

import com.webparser.web.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {

}
