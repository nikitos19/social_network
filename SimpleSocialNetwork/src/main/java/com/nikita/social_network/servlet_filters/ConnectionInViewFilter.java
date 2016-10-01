package com.nikita.social_network.servlet_filters;

import com.nikita.social_network.ConnectionProvider;

import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionInViewFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } finally {
            try {
                ConnectionProvider.closeConnection();
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }
    }

    public void destroy() {

    }
}
