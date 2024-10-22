package com.dms.standarddataserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class StandardDataServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StandardDataServerApplication.class, args);
    }

}
