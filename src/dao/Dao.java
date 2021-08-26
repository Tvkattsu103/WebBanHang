package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.contextDB;
import entity.Account;
import entity.Category;
import entity.Product;

public class Dao {
	static Connection conn = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;

	public static List<Product> getAllProduct() {
		List<Product> list = new ArrayList<>();
		String query = "select * from product";
		try {
			conn = new contextDB().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return list;

	}

	public static List<Category> getAllCategory() {
		List<Category> list = new ArrayList<>();
		String query = "select * from Category";
		try {
			conn = new contextDB().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Category(rs.getInt(1), rs.getString(2)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return list;

	}

	public static Product getLastest() {
		String query = "select top 1 * from product \n" + "order by id desc";
		try {
			conn = new contextDB().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	public static List<Product> getProductByCategoryID(String cid) {
		List<Product> list = new ArrayList<>();
		String query = "select * from product \n" + "where cateID = ?";
		try {
			conn = new contextDB().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return list;

	}

	public static Product getProductByID(String id) {
		String query = "select * from product \n" + "where id = ?";
		try {
			conn = new contextDB().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	public static List<Product> SearchProduct(String txt) {
		List<Product> list = new ArrayList<>();
		String query = "select * from product \n" + "where [name] like ?";
		System.out.println(query);
		try {
			conn = new contextDB().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + txt + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return list;

	}

	public static Account login(String user, String pass) {
//		String query = "select * from Account\n"
//					+ "where [user] = ?\n"
//					+ "and pass = ?";
		String query = "select * from Account\n"
				+ "where [user] = '"+user+"'\n"
				+ "and pass = '"+pass+"'";
		try {
			conn = new contextDB().getConnection();
			Statement st = conn.createStatement();
			rs = st.executeQuery(query);
//			ps = conn.prepareStatement(query);
//			ps.setString(1, user);
//			ps.setString(2, pass);

//			rs = ps.executeQuery();
			while(rs.next()) {
				return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
		
	}
	public static Account checkIfExistUser(String user) {
		String query = "select * from Account\n"
				+ "where [user] = ?";
		try {
			conn = new contextDB().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			rs = ps.executeQuery();
			while(rs.next()) {
				return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public static Account signup(String user, String pass) {
		String query = "insert into Account\n"
					+ "values(?,?,0,0)";
		
		try {
			conn = new contextDB().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pass);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public static List<Product> listProductBySellID(int id) {
		List<Product> list = new ArrayList<>();
		String query = "select * from product \n" + "where sell_ID = ?";
		
		try {
			conn = new contextDB().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return list;

	}
	public static List<Product> getTop3() {
		List<Product> list = new ArrayList<>();
		String query = "select top 3 * from product";
		try {
			conn = new contextDB().getConnection();
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return list;

	}
	public static List<Product> getNext3Product(int amount) {
		List<Product> list = new ArrayList<>();
		String query = "select * from product order by id offset ? rows fetch next 3 rows only";
		try {
			conn = new contextDB().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, amount);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return list;

	}
	public static void deleteProduct(int id) {
		String query = "delete from product\n" + "where id = ?";
		try {
			conn = new contextDB().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void createProduct(String name, String image, Double price, String title, String description, String category, int sid) {
		String query = "insert into product\n" + "([name], image, price, title, description, cateID, sell_ID)\n" + "values(?,?,?,?,?,?,?)";
		try {
			conn = new contextDB().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, image);
			ps.setDouble(3, price);
			ps.setString(4, title);
			ps.setString(5, description);
			ps.setString(6, category);
			ps.setInt(7, sid);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void updateProduct(String name, String image, Double price, String title, String description, String category, int pid) {
		String query = "update product\n" + "set [name] = ?, image = ?, price = ?, title = ?, description = ?, cateID = ?\n" + "where id = ?";
		try {
			conn = new contextDB().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, image);
			ps.setDouble(3, price);
			ps.setString(4, title);
			ps.setString(5, description);
			ps.setString(6, category);
			ps.setInt(7, pid);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	public static void main(String[] args) {
		Dao dao = new Dao();
		Product p = dao.getLastest();
		String query = "select * from product \n" + "where name like '%?%'";
//		for(Category o: list) {
		System.out.println(query);
//		}
	}
}
