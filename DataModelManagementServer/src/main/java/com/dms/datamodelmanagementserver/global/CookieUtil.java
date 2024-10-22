package com.dms.datamodelmanagementserver.global;

import java.util.Arrays;
import java.util.Optional;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {
	
	private final static String COOKIE_NAME_MEMBER_KEY = "memberKey";
	private final static String COOKIE_NAME_SUBJAREANAME_KEY = "subjAreaNameKey";
	
	public static void setCookie(HttpServletResponse response, String memberKey) {
		Cookie cookie = new Cookie(COOKIE_NAME_MEMBER_KEY, memberKey);
	    cookie.setMaxAge(60*60*24);
	    cookie.setPath("/");
	    response.addCookie(cookie);	    	    
	}
	
	public static String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String result = "";
        if(Optional.ofNullable(cookies).isEmpty()) {
        	result= null;
        }else {
            Optional<Cookie> targetCookie = Arrays.stream(cookies).filter(cookie -> COOKIE_NAME_MEMBER_KEY.equals(cookie.getName())).findFirst();
            if(targetCookie.isPresent()) {
            	result = targetCookie.get().getValue();
            }	
        }
        return result;
	}
	
	public static void setCookieBySubjAreaName(HttpServletResponse response, String subjAreaNameKey) {
		Cookie cookie = new Cookie(COOKIE_NAME_SUBJAREANAME_KEY, subjAreaNameKey);
	    cookie.setMaxAge(60*60*24);
	    cookie.setPath("/");
	    response.addCookie(cookie);
	}
	
	public static String getCookieBySubjAreaName(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String result = "";
        if(Optional.ofNullable(cookies).isEmpty()) {
        	result= null;
        } else {
        	Optional<Cookie> targetCookie = Arrays.stream(cookies).filter(cookie -> COOKIE_NAME_SUBJAREANAME_KEY.equals(cookie.getName())).findFirst();
            if(targetCookie.isPresent()) {
            	result = targetCookie.get().getValue();
            }
        }
        return result;
	}
}
