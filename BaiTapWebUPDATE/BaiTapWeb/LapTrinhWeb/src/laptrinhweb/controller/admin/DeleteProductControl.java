package laptrinhweb.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import laptrinhweb.model.Product;
import laptrinhweb.service.ProductService;
import laptrinhweb.service.impl.ProductServiceImpl;
@WebServlet(urlPatterns="/admin/product/delete")
public class DeleteProductControl extends HttpServlet{
	private ProductService prductServices = new ProductServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Product product = prductServices.getById(Integer.parseInt(req.getParameter("id")));
		prductServices.delete(Integer.parseInt(req.getParameter("id")));
		if(product.getDanhMuc().getId()==1){
			resp.sendRedirect(req.getContextPath()+"/admin/product/smartphone");
		}else if(product.getDanhMuc().getId()==2){
			resp.sendRedirect(req.getContextPath() + "/admin/product/tablet");
		}else if(product.getDanhMuc().getId()==3){
			resp.sendRedirect(req.getContextPath() + "/admin/product/laptop");
		}else if(product.getDanhMuc().getId()==4){
			resp.sendRedirect(req.getContextPath() + "/admin/product/phukien");
		}
	}
}
