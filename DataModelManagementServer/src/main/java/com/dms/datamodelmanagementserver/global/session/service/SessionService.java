package com.dms.datamodelmanagementserver.global.session.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public interface SessionService {

	void setSession(String selectedStandardArea, HttpSession session, HttpServletResponse response);

	String getSession(HttpServletRequest request);
}
