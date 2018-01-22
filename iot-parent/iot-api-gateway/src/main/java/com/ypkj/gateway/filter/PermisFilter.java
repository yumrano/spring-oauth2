package com.ypkj.gateway.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PermisFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String uri = request.getRequestURI();
//		if ( "/sys/login".equals(uri)) {
//			ctx.setSendZuulResponse(false);
////			ctx.setResponseStatusCode(200);
////			ctx.addZuulResponseHeader("content-type", "text/html;charset=utf-8");
////			ctx.setResponseBody("登陆页面！");
//			try {
//				request.getRequestDispatcher("/login.html").forward(request, ctx.getResponse());
//			} catch (ServletException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		return null;
	}

}
