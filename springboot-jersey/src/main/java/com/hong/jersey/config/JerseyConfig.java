package com.hong.jersey.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

/**
 * Created by hong on 2017/5/31.
 */
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(RequestContextFilter.class);
        packages("com.hong.jersey");
    }
}
