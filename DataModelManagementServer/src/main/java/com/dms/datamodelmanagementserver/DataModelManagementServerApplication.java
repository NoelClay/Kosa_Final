package com.dms.datamodelmanagementserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class DataModelManagementServerApplication extends SpringBootServletInitializer{
	
	

    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DataModelManagementServerApplication.class);
	}

	public static void main(String[] args) {
        SpringApplication.run(DataModelManagementServerApplication.class, args);
    }


}

