package ksc.poc.spring.unique.request.id.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ksc.poc.spring.unique.request.id.interceptors.dtos.MyRequestDetailsHolder;
@Component
public class ValidatingUniqueRequestIdInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)throws Exception {
		String corelatioId = MyRequestDetailsHolder.getCorelationId();
		if( corelatioId== null 
				|| StringUtils.isEmpty(corelatioId)) {
			throw new Exception("Unique request id is missing.");
		}
		return super.preHandle(request, response, handler);
	}

}
