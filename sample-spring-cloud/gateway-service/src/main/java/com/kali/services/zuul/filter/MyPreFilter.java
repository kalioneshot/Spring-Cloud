package com.kali.services.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.Debug;
import com.netflix.zuul.context.RequestContext;

/**
 * Example of the Pre filter.
 * 
 * @author kali
 *
 */
public class MyPreFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(MyPreFilter.class);
	
	/**
	 * Pre Filter. pre : will contain the filters executed before the routing of a
	 * request (pre-routing rules). <br>
	 * post : will contain filters executed after routing a query (post-routing
	 * rules) <br>
	 * error : will contain the executed filters if an error occurs while processing
	 * a query. <br>
	 * routing : will contain the filters executed during the routing of the request
	 * 
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 10000;
	}

	@Override
	public boolean shouldFilter() {
		return Debug.debugRequest();
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        return null;
	}
}
