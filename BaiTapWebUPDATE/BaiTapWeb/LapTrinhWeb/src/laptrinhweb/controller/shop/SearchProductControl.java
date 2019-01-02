package laptrinhweb.controller.shop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import laptrinhweb.dao.impl.ProductDAOImpl;
@WebServlet( urlPatterns ="/tim-kiem")
public class SearchProductControl extends HttpServlet {
	private ProductDAOImpl productDAO = new ProductDAOImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("search").trim();
		if(input.length()!=0){
			Gson gson = new Gson();
			req.setAttribute("list", gson.toJson(productDAO.getByName(input)));
			req.getRequestDispatcher("/view/shop/searchProduct.jsp").forward(req, resp);
		}else{
			req.setAttribute("list", "");
			req.getRequestDispatcher("/view/shop/searchProduct.jsp").forward(req, resp);
		}
	}
}
