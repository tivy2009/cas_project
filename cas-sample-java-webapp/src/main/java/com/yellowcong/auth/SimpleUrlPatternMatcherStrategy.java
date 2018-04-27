package com.yellowcong.auth;

import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;

/**
 * @author tivy
 *
 */
public class SimpleUrlPatternMatcherStrategy implements UrlPatternMatcherStrategy {

	/**
	 * validate the login is required when access the url
	 * true : not need to login
	 * false : need to login
	 */
    @Override
    public boolean matches(String url) {
        return url.contains("/loginOut/success") || url.contains("/logout.jsp");
    }

    /**
     * use pattern validate
     */
    @Override
    public void setPattern(String pattern) {

    }

}