package ksc.poc.spring.unique.request.id.controllers.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ksc.poc.spring.unique.request.id.interceptors.Slf4jMDCInterceptor;
import ksc.poc.spring.unique.request.id.interceptors.ValidatingUniqueRequestIdInterceptor;

@Configuration
public class UniqueRequestIdConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Slf4jMDCInterceptor slf4jMDCInterceptor;
	
	@Autowired
	private ValidatingUniqueRequestIdInterceptor validatingUniqueRequestIdInterceptor; 

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// order of interceptors as per the order of adding interceptors to InterceptorRegistry
		registry.addInterceptor(slf4jMDCInterceptor);
		registry.addInterceptor(validatingUniqueRequestIdInterceptor);
	}
}
