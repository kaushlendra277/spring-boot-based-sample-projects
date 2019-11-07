package ksc.poc.spring.unique.request.id.filters;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import ksc.poc.spring.unique.request.id.helpers.RequestHelper;

@Component
public class Slf4jMDCFilter extends OncePerRequestFilter {
	
	@Autowired
	private RequestHelper requestHelper;
	
	private String responseHeader;
    private String mdcTokenKey="xyzCorelationId";
    private String requestHeader =mdcTokenKey;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)throws ServletException, IOException {
        try {
            final String token;
            if (!StringUtils.isEmpty(requestHeader) && !StringUtils.isEmpty(request.getHeader(requestHeader))) {
                token = request.getHeader(requestHeader);
            } else {
                token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
            }
            MDC.put(mdcTokenKey, token);
            if (!StringUtils.isEmpty(responseHeader)) {
                response.addHeader(responseHeader, token);
            }
            requestHelper.setCorelationId(token);
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(mdcTokenKey);
        }
    }

}
