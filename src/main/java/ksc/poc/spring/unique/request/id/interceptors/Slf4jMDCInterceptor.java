package ksc.poc.spring.unique.request.id.interceptors;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ksc.poc.spring.unique.request.id.interceptors.dtos.MyRequestDetailsHolder;

@Component
public class Slf4jMDCInterceptor extends HandlerInterceptorAdapter {
	
	private String mdcTokenKey="xyzCorelationId";
    private String requestHeader =mdcTokenKey;

	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)throws Exception {
		final String token;
        if (!StringUtils.isEmpty(requestHeader) && !StringUtils.isEmpty(request.getHeader(requestHeader))) {
            token = request.getHeader(requestHeader);
            // this is for testing error response in specific format
        	if(token.equalsIgnoreCase("error")) {
        		MyRequestDetailsHolder.setCorelationId(token);
        		throw new Exception("This is a test exception");
        	}
        } else {
        	token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        }
        MDC.put(mdcTokenKey, token);
        //requestHelper.setCorelationId(token);
        MyRequestDetailsHolder.setCorelationId(token);
        return super.preHandle(request, response, handler);
    }

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		MDC.remove(mdcTokenKey);
		// resetting corelationId
		MyRequestDetailsHolder.clearRequestInfo();
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
