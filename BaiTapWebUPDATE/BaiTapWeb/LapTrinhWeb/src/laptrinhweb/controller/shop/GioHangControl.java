package laptrinhweb.controller.shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import laptrinhweb.paypal.PayPalConfig;

@WebServlet(urlPatterns = "/gio-hang")
public class GioHangControl extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PayPalConfig payPalConfig = new PayPalConfig();
		payPalConfig.setAuthToken("ay91BoOdTnerRX5UArLox2f10ZiIVeX8x3o6dR9C818tHLaLwA46Gpx10VO");
		payPalConfig.setPortUrl("https://www.sandbox.paypal.com/cgi-bin/webscr");
		payPalConfig.setBusiness("nguoinhanhomeshop@gmail.com");
		payPalConfig.setReturnUrl("http://localhost:8080/LapTrinhWeb/member/thanh-toan-paypal");
		req.setAttribute("payPal", payPalConfig);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/shop/giohang.jsp");
		requestDispatcher.forward(req, resp);
	}
	
}
