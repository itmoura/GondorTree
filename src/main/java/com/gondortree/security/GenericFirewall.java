package com.gondortree.security;

import javax.servlet.Filter;

/**
 *
 * @author Ítalo Moura
 */


public interface GenericFirewall extends Filter {

    public boolean verifyPermission(String module, String action);

    public void addFreeAction(String mdl, String act);

    public boolean isFreeAction(String mdl, String act);
	
}
