package com.myapp.clientservice.controller.impl;

import com.myapp.bookstore.entity.Author;
import com.myapp.clientservice.controller.ClientController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Old implementation via RestTemplate to send request to bookstore-app.
 * Feign implementation in {@link ClientSpringCloudController}.
 *
 * @author Ivan_Semenov
 */
@Component
@Slf4j
@Deprecated
public class ClientRestTemplateController implements ClientController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BOOKSTORE_APP_URL = "http://localhost:8090/authors/top?from=1990-01-01&to=2020-01-01";

    @GetMapping("/get-authors-v1")
    @Override
    public List<Author> getTopAuthorsFromBookstoreApp() {
        log.debug("getTopAuthorsFromBookstoreApp() - start:");
        List<Author> authors = restTemplate.getForObject(BOOKSTORE_APP_URL, List.class);
        log.info("Response received: {}", authors);
        log.debug("getTopAuthorsFromBookstoreApp() - end:");
        return authors;
    }

    @LoadBalanced //in case we have several instances of application for balancing.
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
