package com.sirdc.modules.security;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.sirdc.modules.sys.util.UserUtils;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-18
 * <p>Version: 1.0
 */
public class LoginSessionControlFilter extends AccessControlFilter {

	private String forwardUrl;
	
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        Object principal = subject.getPrincipal();
        
        String loginId = request.getParameter("loginId");
		String userId = UserUtils.getUserId();
		if(principal == null || !userId.equals(loginId)) {
			saveRequest(request);
            WebUtils.issueRedirect(request, response, forwardUrl);
            return false;
		}
        return true;
    }

	/**
	 * @author: weihuang.peng
	 * @return the forwardUrl
	 */
	public String getForwardUrl() {
		return forwardUrl;
	}

	/**
	 * @author: weihuang.peng
	 * @param forwardUrl the forwardUrl to set
	 */
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
}