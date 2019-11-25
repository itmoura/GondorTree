package com.gondortree.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gondortree.model.Member;

/**
 *
 * @author Ítalo Moura
 */
public class OSSFirewall extends GenericFirewallImpl {

    public OSSFirewall() {
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.addFreeAction("member", "login");
        this.addFreeAction("member", "register");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String htm = this.getModuleReference(request);
        String act = request.getParameter("action");
        if (!this.isFreeAction(htm, act)) {
            HttpSession session = request.getSession();
            Member u = (Member) session.getAttribute("user");
            if (u == null) {
                response.sendRedirect("member.htm?action=login");
                return;
            }
        }
        chain.doFilter(request, response);
    }

}
