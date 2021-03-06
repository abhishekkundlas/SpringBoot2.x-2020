package in.sprl.security;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.sprl.handler.ExceptionJsonInfo;
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
	
	private static final long serialVersionUID = 3157286018966595461L;

	/*
	 * @Override public void commence(HttpServletRequest request,
	 * HttpServletResponse response, AuthenticationException authException) throws
	 * IOException { response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
	 * "Unauthorized"); }
	 */
	
	@Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		ExceptionJsonInfo exceptionJsonInfo = new ExceptionJsonInfo("Unauthorised Access", 
					HttpStatus.UNAUTHORIZED.value(), 
					HttpStatus.UNAUTHORIZED.name());
		OutputStream out = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, exceptionJsonInfo);
        out.flush();
    }
}