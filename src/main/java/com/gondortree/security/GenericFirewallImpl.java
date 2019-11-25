package com.gondortree.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ítalo Moura
 */


public class GenericFirewallImpl implements GenericFirewall {

	private List<String> freeActions;
	
	public GenericFirewallImpl(){
		this.freeActions = new ArrayList<String>();
	}
	
	@Override
	public boolean verifyPermission(String module, String action) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addFreeAction(String mdl, String act) {
		this.freeActions.add(mdl+"@"+act);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	protected String getModuleReference(HttpServletRequest request){
            String uri = request.getRequestURI();
            int uint = uri.lastIndexOf("?");
            uri = (uint == -1)?uri:uri.substring(0,uint);
            int ubar = uri.lastIndexOf("/");
            String mri = uri.substring(ubar+1);
            int pr = mri.indexOf(".htm");
            String module = mri.substring(0,pr);	
            return module;
	}

	@Override
	public boolean isFreeAction(String htm, String act) {
		return this.freeActions.contains(htm+"@"+act);
	}

}
