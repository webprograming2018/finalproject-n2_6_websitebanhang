package laptrinhweb.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import laptrinhweb.dao.impl.MemberDAOImpl;
@WebServlet(urlPatterns = "/admin/member/tim-kiem")
public class SearchMemberControl extends HttpServlet{
	private MemberDAOImpl memberDAO = new MemberDAOImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("timkiem").trim();
		if(input.length()==0){
			req.setAttribute("listMember", memberDAO.getAll());
			req.getRequestDispatcher("/view/admin/listMember.jsp").forward(req, resp);
		}else{
			req.setAttribute("listMember", memberDAO.getByName(input));
			req.getRequestDispatcher("/view/admin/listMember.jsp").forward(req, resp);
		}
	}
}
