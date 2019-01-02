package laptrinhweb.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import laptrinhweb.dao.impl.OrderDAOImpl;
@WebServlet(urlPatterns="/admin/thong-ke/doanh-thu")
public class ThongKeDoanhThuControl extends HttpServlet{
	private OrderDAOImpl orderDAO = new OrderDAOImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("t1", orderDAO.doanhThuTheoThang(1,2018));
		req.setAttribute("t2", orderDAO.doanhThuTheoThang(2,2018));
		req.setAttribute("t3", orderDAO.doanhThuTheoThang(3,2018));
		req.setAttribute("t4", orderDAO.doanhThuTheoThang(4,2018));
		req.setAttribute("t5", orderDAO.doanhThuTheoThang(5,2018));
		req.setAttribute("t6", orderDAO.doanhThuTheoThang(6,2018));
		req.setAttribute("t7", orderDAO.doanhThuTheoThang(7,2018));
		req.setAttribute("t8", orderDAO.doanhThuTheoThang(8,2018));
		req.setAttribute("t9", orderDAO.doanhThuTheoThang(9,2018));
		req.setAttribute("t10", orderDAO.doanhThuTheoThang(10,2018));
		req.setAttribute("t11", orderDAO.doanhThuTheoThang(11,2018));
		req.setAttribute("t12", orderDAO.doanhThuTheoThang(12,2018));
		req.getRequestDispatcher("/view/admin/thongKeDoanhThu.jsp").forward(req, resp);
	}
}
