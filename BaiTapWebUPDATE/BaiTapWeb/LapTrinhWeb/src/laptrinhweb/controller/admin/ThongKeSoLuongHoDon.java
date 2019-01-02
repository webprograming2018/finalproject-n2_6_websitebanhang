package laptrinhweb.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import laptrinhweb.dao.impl.OrderDAOImpl;
@WebServlet(urlPatterns="/admin/thong-ke/so-luong-hoa-don")
public class ThongKeSoLuongHoDon extends HttpServlet{
	private OrderDAOImpl orderDAO = new OrderDAOImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("t1", orderDAO.soLuongOrderTrongThangTrenNam(1,2018));
		req.setAttribute("t2", orderDAO.soLuongOrderTrongThangTrenNam(2,2018));
		req.setAttribute("t3", orderDAO.soLuongOrderTrongThangTrenNam(3,2018));
		req.setAttribute("t4", orderDAO.soLuongOrderTrongThangTrenNam(4,2018));
		req.setAttribute("t5", orderDAO.soLuongOrderTrongThangTrenNam(5,2018));
		req.setAttribute("t6", orderDAO.soLuongOrderTrongThangTrenNam(6,2018));
		req.setAttribute("t7", orderDAO.soLuongOrderTrongThangTrenNam(7,2018));
		req.setAttribute("t8", orderDAO.soLuongOrderTrongThangTrenNam(8,2018));
		req.setAttribute("t9", orderDAO.soLuongOrderTrongThangTrenNam(9,2018));
		req.setAttribute("t10", orderDAO.soLuongOrderTrongThangTrenNam(10,2018));
		req.setAttribute("t11", orderDAO.soLuongOrderTrongThangTrenNam(11,2018));
		req.setAttribute("t12", orderDAO.soLuongOrderTrongThangTrenNam(12,2018));
		
		req.getRequestDispatcher("/view/admin/thongKeHoaDon.jsp").forward(req, resp);
	}
}
