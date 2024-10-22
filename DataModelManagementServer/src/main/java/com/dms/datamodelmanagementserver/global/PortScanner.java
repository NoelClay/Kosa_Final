package com.dms.datamodelmanagementserver.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PortScanner {
    private final Environment environment;

    @Autowired
    public PortScanner(Environment environment) {
        this.environment = environment;
    }

    public String getCurrentServerPort() {
        return environment.getProperty("local.server.port");
    }
}
