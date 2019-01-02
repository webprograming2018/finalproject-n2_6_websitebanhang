package laptrinhweb.dao;

import java.util.List;

import laptrinhweb.model.Order;
import laptrinhweb.model.SoLuongAndNgay;

public interface OrderDAO {
	void add(Order order);

	void edit(Order order);

	void delete(int idOrder);

	Order getById(int idOrder);
	
	List<Order> getByIdMember(int idMember);

	List<Order> getAll();
	
	int soLuongOrderTrongThangTrenNam(int m, int y);
	
	int doanhThuTheoThang(int m, int y);
	
	int soLuongSpBanRa(int idDM);
	
	List<SoLuongAndNgay> soLuongSpTheoNgayTrongTungThang(int mm);
}
