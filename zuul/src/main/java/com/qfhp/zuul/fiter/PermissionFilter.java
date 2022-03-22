package com.qfhp.zuul.fiter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Hu
 * @date 2022-03-21 10:29
 */
public class PermissionFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "per";
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
    public Object run() throws ZuulException {
//        ServletRequest request = (ServletRequest) RequestContextHolder.currentRequestAttributes();
        RequestContext cxt = RequestContext.getCurrentContext();
        HttpServletRequest request = cxt.getRequest();
        String token = request.getParameter("token");
        if ("true".equals(token)){
            return null;
        } else {
            cxt.setResponseBody("非法请求");
            cxt.addZuulRequestHeader("content-type","text/html;charset=utf-8");
            cxt.setSendZuulResponse(false);
        }
        return null;
    }
}
