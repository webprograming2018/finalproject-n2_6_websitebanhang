package laptrinhweb.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import laptrinhweb.dao.impl.OrderDAOImpl;
@WebServlet(urlPatterns="/admin/thong-ke/san-pham")
public class ThongKeSanPhamBanRaControl extends HttpServlet{
	private OrderDAOImpl orderDAO = new OrderDAOImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("SmBanRa", orderDAO.soLuongSpBanRa(1));
		req.setAttribute("TlBanRa", orderDAO.soLuongSpBanRa(2));
		req.setAttribute("LtBanRa", orderDAO.soLuongSpBanRa(3));
		req.setAttribute("PkBanRa", orderDAO.soLuongSpBanRa(4));
		req.getRequestDispatcher("/view/admin/thongKeSanPhamBanRa.jsp").forward(req, resp);
	} 
	
}
