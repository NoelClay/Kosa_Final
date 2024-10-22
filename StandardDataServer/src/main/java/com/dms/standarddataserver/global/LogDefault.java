package com.dms.standarddataserver.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Slf4j
public class LogDefault {
    private final PortScanner portScanner;

    public LogDefault(PortScanner portScanner) {
        this.portScanner = portScanner;
    }

    public void logCurrentMethod() {
        String callingMethodName = getCallingMethodNameWithClass();
        callingMethodName = capitalizeFirstLetter(callingMethodName);
        String currentPort = portScanner.getCurrentServerPort();
        log.info("Method ::: {}" + "() [" + currentPort + "]", callingMethodName);
    }

    private String getCallingMethodNameWithClass() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length >= 4) {
            StackTraceElement caller = stackTrace[3];
            String className = caller.getClassName();
            String methodName = caller.getMethodName();
            return className + "." + methodName;
        }
        return "Unknown";
    }



    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
