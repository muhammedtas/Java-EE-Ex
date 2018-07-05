package com.filmdirectory.filters;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.transaction.UserTransaction;

/**
 * Servlet Filter implementation class LazyFilter
 */
@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST, 
		DispatcherType.FORWARD, 
		DispatcherType.INCLUDE, 
		DispatcherType.ERROR
}
			, urlPatterns = { "/rest/*" })
public class LazyFilter implements Filter {

	@Resource
	 private UserTransaction utx;
	
    /**
     * Default constructor. 
     */
    public LazyFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	  try {
	   utx.begin();
	   chain.doFilter(request, response);
	   utx.commit();
	  } catch (Exception e) {
	   e.printStackTrace();
	  }

	 }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
