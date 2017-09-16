package br.com.fiap.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/webapp/protected/*")
public class ProtectedFilter implements Filter {

    public ProtectedFilter() {
        // Construtor padrão
    }

	public void destroy() {
		// Destory
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("usuario") == null) {
        	response.sendRedirect(request.getContextPath() + "/login.xhtml");
        } else {
        	chain.doFilter(request, response);
        }
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// init
	}

}
