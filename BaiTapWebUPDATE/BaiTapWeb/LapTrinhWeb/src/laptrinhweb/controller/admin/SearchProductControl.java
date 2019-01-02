package laptrinhweb.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import laptrinhweb.dao.impl.ProductDAOImpl;
@WebServlet(urlPatterns = "/admin/product/tim-kiem")
public class SearchProductControl extends HttpServlet{
	private ProductDAOImpl productDAO = new ProductDAOImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idDM = Integer.parseInt(req.getParameter("type"));
		String input = req.getParameter("search").trim();
		req.setAttribute("list", productDAO.getByNameAndDM(input, idDM));
		if(idDM == 1){
			req.getRequestDispatcher("/view/admin/listSmartphone.jsp").forward(req, resp);
		}else if(idDM == 2){
			req.getRequestDispatcher("/view/admin/listTablet.jsp").forward(req, resp);
		}else if(idDM == 3){
			req.getRequestDispatcher("/view/admin/listLaptop.jsp").forward(req, resp);
		}else if(idDM == 4){
			req.getRequestDispatcher("/view/admin/listPhuKien.jsp").forward(req, resp);
		}
	}
}
