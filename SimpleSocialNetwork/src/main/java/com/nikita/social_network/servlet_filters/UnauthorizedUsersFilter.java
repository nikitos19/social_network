package com.nikita.social_network.servlet_filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UnauthorizedUsersFilter implements Filter {

    private final String entryPageControllerName = "EntryPageController";
    private final String registrationPageControllerName = "RegistrationPageController";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String currentUrl = httpReq.getRequestURL().toString();
        if (!currentUrl.contains(entryPageControllerName) && !currentUrl.contains(registrationPageControllerName)) {
            HttpSession currentSession = httpReq.getSession(false);
            if (currentSession == null || currentSession.getAttribute("user") == null) {
                ((HttpServletResponse) response).sendRedirect("/services/EntryPageController");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
