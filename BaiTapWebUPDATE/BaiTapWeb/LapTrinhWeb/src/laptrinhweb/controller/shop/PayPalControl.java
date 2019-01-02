package laptrinhweb.controller.shop;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import laptrinhweb.model.CartItem;
import laptrinhweb.model.Member;
import laptrinhweb.model.Order;
import laptrinhweb.service.GmailService;
import laptrinhweb.service.OrderService;
import laptrinhweb.service.impl.GmailServiceImp;
import laptrinhweb.service.impl.OrderServiceImpl;

@WebServlet(urlPatterns = "/member/thanh-toan-paypal")
public class PayPalControl extends HttpServlet {
	private OrderService orderService = new OrderServiceImpl();
	private GmailService gmailService = new GmailServiceImp();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String transID = req.getParameter("tx");
		if (transID != null && transID != "") {
			HttpSession session = req.getSession();
			Object obj = session.getAttribute("carts");
			if (obj != null) {
				// nguoi dung hien tai
				Member member = (Member) session.getAttribute("member");
				// ep kieu doi tuong sang List
				List<CartItem> carts = (List<CartItem>) obj;

				Order order = new Order();
				order.setMember(member);
				;
				order.setListCartItem(carts);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				order.setDate(format.format(new Date()));
				orderService.add(order);
				session.removeAttribute("carts");
				session.removeAttribute("soLuong");
				int tong = 0;
				for (CartItem item : carts) {
					tong += item.getQuantity() * item.getProduct().getChiTietSP().getGia();
				}
				req.setAttribute("tong", tong);
				req.setAttribute("order", order);
				gmailService.sendMail(member.getGmail(), "HomeShop"	, "Hello, " + member.getHoTen() + " : \n Mã đơn hàng: "+ order.getIdOrder()+"\n Tổng  hóa đơn là: "+ tong);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/view/shop/hoadon.jsp");
				dispatcher.forward(req, resp);
			} else {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/view/shop/thanhToanTB.jsp");
				dispatcher.forward(req, resp);
			}

		}
	}
}
