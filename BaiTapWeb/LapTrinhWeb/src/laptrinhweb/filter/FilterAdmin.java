package laptrinhweb.filter;

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

import laptrinhweb.model.Member;
@WebFilter(urlPatterns="/admin/*")
public class FilterAdmin implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("member");
		if (obj == null) {
			resp.sendRedirect(req.getContextPath() + "/dang-nhap");
		} else {
			Member member = (Member) obj;
			if (member.getRole() == 1) {
				chain.doFilter(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/dang-nhap");
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
