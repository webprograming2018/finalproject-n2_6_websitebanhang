package laptrinhweb.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import laptrinhweb.dao.OrderDAO;
import laptrinhweb.model.Member;
import laptrinhweb.model.Order;
import laptrinhweb.model.SoLuongAndNgay;

public class OrderDAOImpl extends RootDAO implements OrderDAO{
	private PreparedStatement ppst;
	private ResultSet rs;
	@Override
	public void add(Order order) {
		String sql = "INSERT INTO `order` (`idMember`, `dateMua`) VALUES(?,?);";
		Connection conn = getJDBCConnection();
		try {
			ppst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ppst.setInt(1, order.getMember().getId());
			ppst.setDate(2, new Date(format.parse(order.getDate()).getTime()));
			ppst.executeUpdate();
			rs = ppst.getGeneratedKeys();
			while(rs.next()){
				order.setIdOrder(rs.getInt(1));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
	}

	@Override
	public void edit(Order order) {
		String sql ="UPDATE `order` SET `idMember` = ?, `dateMua` =? WHERE `idOrder` = ?;";
		Connection conn = getJDBCConnection();
		try {
			ppst = conn.prepareStatement(sql);
			ppst.setInt(1, order.getMember().getId());
			java.util.Date date = format.parse(order.getDate());
			ppst.setDate(2, new Date(date.getTime()));
			ppst.setInt(3, order.getIdOrder());
			ppst.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int idOrder) {
		String sql = "DELETE FROM `order` WHERE idOrder = ?;";
		Connection conn = getJDBCConnection();
		try {
			ppst = conn.prepareStatement(sql);
			ppst.setInt(1, idOrder);
			ppst.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Order getById(int idOrder) {
		String sql = "SELECT * FROM `order` WHERE idOrder = ?;";
		Connection conn = getJDBCConnection();
		Order order = new Order();
		try {
			ppst = conn.prepareStatement(sql);
			ppst.setInt(1, idOrder);
			rs = ppst.executeQuery();
			while(rs.next()){
				order.setIdOrder(idOrder);
				Member member = new Member();
				member.setId(rs.getInt("idMember"));
				order.setMember(member);
				order.setDate(format.format(rs.getDate("dateMua")));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public List<Order> getByIdMember(int idMember) {
		List<Order> list = new ArrayList<>();
		String sql = "SELECT * FROM `order` WHERE idMember = ?;";
		Connection conn = getJDBCConnection();
		try {
			ppst = conn.prepareStatement(sql);
			ppst.setInt(1, idMember);
			rs = ppst.executeQuery();
			while(rs.next()){
				Order order = new Order();
				order.setIdOrder(rs.getInt("idOrder"));
				Member member = new Member();
				member.setId(rs.getInt("idMember"));
				order.setMember(member);
				order.setDate(format.format(rs.getDate("dateMua")));
				list.add(order);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Order> getAll() {
		List<Order> list = new ArrayList<>();
		Connection conn = getJDBCConnection();
		String sql = "SELECT * FROM `order`;";
		try {
			ppst = conn.prepareStatement(sql);
			rs = ppst.executeQuery();
			while(rs.next()){
				Order order = new Order();
				order.setIdOrder(rs.getInt("idOrder"));
				Member member = new Member();
				member.setId(rs.getInt("idMember"));
				order.setMember(member);
				order.setDate(format.format(rs.getDate("dateMua")));
				list.add(order);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	@Override
	public int soLuongOrderTrongThangTrenNam(int m , int y) {
		String sql = "SELECT count(*) AS count FROM laptrinhweb.`order` WHERE MONTH(`dateMua`) = ? AND YEAR(`dateMua`) = ? ;";
		int giaTri = 0;
		Connection conn = getJDBCConnection();
		try {
			ppst = conn.prepareStatement(sql);
			ppst.setInt(1, m);
			ppst.setInt(2, y);
			rs = ppst.executeQuery();
			while(rs.next()){
				giaTri = rs.getInt("count");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return giaTri;
	}

	@Override
	public int doanhThuTheoThang(int m, int y) {
		String sql = "SELECT laptrinhweb.`product`.gia AS GIA, laptrinhweb.`cartitem`.quantity AS SOLUONG FROM laptrinhweb.`product` JOIN laptrinhweb.`cartitem` JOIN laptrinhweb.`order` ON laptrinhweb.`product`.idP = laptrinhweb.`cartitem`.idP AND laptrinhweb.`order`.idOrder = laptrinhweb.`cartitem`.idO WHERE MONTH(`dateMua`) = ? AND YEAR(`dateMua`) = ?;";
		Connection conn = getJDBCConnection();
		int gia, soLuong, tong = 0;
		try {
			ppst = conn.prepareStatement(sql);
			ppst.setInt(1, m);
			ppst.setInt(2, y);
			rs = ppst.executeQuery();
			while(rs.next()){
				gia = rs.getInt("GIA");
				soLuong = rs.getInt("SOLUONG");
				tong+= gia*soLuong;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tong;
	}

	@Override
	public int soLuongSpBanRa(int idDM) {
		String sql = "SELECT SUM(laptrinhweb.`cartitem`.quantity) AS SOLUONG FROM laptrinhweb.`product` JOIN laptrinhweb.`cartitem` JOIN laptrinhweb.`order` ON laptrinhweb.`product`.idP = laptrinhweb.`cartitem`.idP AND laptrinhweb.`order`.idOrder = laptrinhweb.`cartitem`.idO WHERE laptrinhweb.`product`.idDanhMuc = ?;";
		Connection conn = getJDBCConnection();
		int tong = 0;
		try {
			ppst = conn.prepareStatement(sql);
			ppst.setInt(1, idDM);
			rs = ppst.executeQuery();
			while(rs.next()){
				tong = rs.getInt("SOLUONG");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tong;
	}

	@Override
	public List<SoLuongAndNgay> soLuongSpTheoNgayTrongTungThang(int mm) {
		List<SoLuongAndNgay> list = new ArrayList<>();
		int soLuongNgay;
		if( mm == 1 ||mm==3 ||mm==5||mm==7||mm==8||mm==10||mm==12){
			soLuongNgay = 31;
		}else if(mm == 4 ||mm==6 ||mm==9||mm==11){
			soLuongNgay = 30;
		}else{
			soLuongNgay=28;
		}
		String sql = "SELECT SUM(laptrinhweb.`cartitem`.quantity) AS SOLUONG, laptrinhweb.`order`.dateMua AS NGAY  FROM laptrinhweb.`cartitem` JOIN laptrinhweb.`order` ON laptrinhweb.`order`.idOrder = laptrinhweb.`cartitem`.idO WHERE laptrinhweb.`order`.dateMua = ?;";
		Connection conn = getJDBCConnection();
		try {
			for(int i = 1 ; i<= soLuongNgay; i++){
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, "2018-"+mm+"1");
				rs = ppst.executeQuery();
				while(rs.next()){
					SoLuongAndNgay s = new SoLuongAndNgay(rs.getInt("SOLUONG"), format.format(rs.getDate("NGAY")));
					list.add(s);
				}
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
