package ksc.poc.spring.unique.request.id.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ksc.poc.spring.unique.request.id.helpers.RequestHelper;
@Component
public class ValidatingUniqueRequestIdInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private RequestHelper requestHelper;
	
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)throws Exception {
		if(requestHelper == null 
				|| requestHelper.getCorelationId() == null 
				|| StringUtils.isEmpty(requestHelper.getCorelationId())) {
			throw new Exception("Unique request id is missing.");
		}
		return super.preHandle(request, response, handler);
	}

}
