package cn.connie.api.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

public class WebRequestWrapper extends HttpServletRequestWrapper{

	private Principal principal;
	
	public WebRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public Principal getUserPrincipal() {
		
		return this.principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

}
