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

import br.com.fiap.entity.Usuario;

@WebFilter("/webapp/protected/admin/*")
public class AdminFilter implements Filter {

    public AdminFilter() {
        // Construtor padrão
    }

	public void destroy() {
		// Destory
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        HttpSession session = request.getSession(false);
        
        String redirecionamento = request.getContextPath() + "/index.xhtml";
        
        if (session == null || session.getAttribute("usuario") == null) {
        	response.sendRedirect(redirecionamento);
        } else {
        	Usuario usuario = (Usuario) session.getAttribute("usuario");
        	
        	if(!usuario.getAdmin()) {
        		response.sendRedirect(redirecionamento);
        	} else {
        		chain.doFilter(request, response);
        	}
        }
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// init
	}

}
