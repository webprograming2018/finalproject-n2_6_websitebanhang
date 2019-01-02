package laptrinhweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import laptrinhweb.dao.CartItemDAO;
import laptrinhweb.model.CartItem;
import laptrinhweb.model.Order;
import laptrinhweb.model.Product;

public class CartItemDAOImpl extends RootDAO implements CartItemDAO {
	private PreparedStatement ppst;
	private ResultSet rs;
	private ProductDAOImpl productDAO = new ProductDAOImpl();

	@Override
	public void add(CartItem cartItem) {
		String sql = "INSERT INTO cartitem(`idP`,`idO`,`quantity`) VALUES(?,?,?)";
		Connection conn = getJDBCConnection();
		try {
			ppst = conn.prepareStatement(sql);
			ppst.setInt(1, cartItem.getProduct().getIdP());
			ppst.setInt(2, cartItem.getOrder().getIdOrder());
			ppst.setInt(3, cartItem.getQuantity());
			ppst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void edit(CartItem cartItem) {
		String sql = "UPDATE cartitem SET idP = ?, idO =?, quantity = ? WHERE idC=?;";
		Connection conn = getJDBCConnection();
		try {
			ppst = conn.prepareStatement(sql);
			ppst.setInt(1, cartItem.getProduct().getIdP());
			ppst.setInt(2, cartItem.getOrder().getIdOrder());
			ppst.setInt(3, cartItem.getQuantity());
			ppst.setInt(4, cartItem.getIdC());
			ppst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int idCartItem) {
		String sql = "DELETE FROM cartitem WHERE idC = ?;";
		Connection conn = getJDBCConnection();
		try {
			ppst = conn.prepareStatement(sql);
			ppst.setInt(1, idCartItem);
			ppst.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteByIdOrder(int idOrder) {
		String sql = "DELETE FROM cartitem WHERE idO = ?;";
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
	public CartItem getById(int idCartItem) {
		String sql = "SELECT * FROM cartitem WHERE idC = ?;";
		CartItem cartItem = new CartItem();
		Connection conn = getJDBCConnection();
		try {
			ppst = conn.prepareStatement(sql);
			ppst.setInt(1, idCartItem);
			rs = ppst.executeQuery();
			while (rs.next()) {
				cartItem.setIdC(rs.getInt("idC"));
				Product product = productDAO.getById(rs.getInt("idP"));
				cartItem.setProduct(product);
				Order order = new Order();
				order.setIdOrder(rs.getInt("idO"));
				cartItem.setOrder(order);
				;
				cartItem.setQuantity(rs.getInt("quantity"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartItem;
	}

	@Override
	public List<CartItem> getByIdOrder(int idOrder) {
		List<CartItem> list = new ArrayList<>();
		Connection conn = getJDBCConnection();
		String sql = "SELECT * FROM cartitem WHERE idO = ?;";
		try {
			ppst = conn.prepareStatement(sql);
			ppst.setInt(1, idOrder);
			rs = ppst.executeQuery();
			while (rs.next()) {
				CartItem cartItem = new CartItem();
				cartItem.setIdC(rs.getInt("idC"));
				Product product = productDAO.getById(rs.getInt("idP"));
				cartItem.setProduct(product);
				Order order = new Order();
				order.setIdOrder(rs.getInt("idO"));
				cartItem.setOrder(order);
				;
				cartItem.setQuantity(rs.getInt("quantity"));
				list.add(cartItem);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<CartItem> getAll() {
		List<CartItem> list = new ArrayList<>();
		Connection conn = getJDBCConnection();
		String sql = "SELECT * FROM cartitem;";
		try {
			ppst = conn.prepareStatement(sql);
			rs = ppst.executeQuery();
			while (rs.next()) {
				CartItem cartItem = new CartItem();
				cartItem.setIdC(rs.getInt("idC"));
				Product product = productDAO.getById(rs.getInt("idP"));
				cartItem.setProduct(product);
				Order order = new Order();
				order.setIdOrder(rs.getInt("idO"));
				cartItem.setOrder(order);
				;
				cartItem.setQuantity(rs.getInt("quantity"));
				list.add(cartItem);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
