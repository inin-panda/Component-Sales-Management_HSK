package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiLinhKien;
import entity.NhaCungCap;

public class NhaCungCap_DAO {
	public ArrayList<NhaCungCap> getAllNhaCungCap() {
		ArrayList<NhaCungCap> dsNhaCungCap = new ArrayList<NhaCungCap>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT * from NhaCungCap";
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
				
			while (rs.next()) {
				String maNhaCungCap = rs.getString("maNhaCungCap");
				String tenNhaCungCap= rs.getString("tenNhaCungCap");
				String diaChi= rs.getString("diaChi");
				int soLuongSp= rs.getInt("soLuongSp");
				
				NhaCungCap ncc = new NhaCungCap(maNhaCungCap, tenNhaCungCap, diaChi, soLuongSp);
				dsNhaCungCap.add(ncc);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsNhaCungCap;
	}
	
	public NhaCungCap getNCCTheoMa(String maNCC) {
		NhaCungCap ncc = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT * FROM NhaCungCap WHERE maNhaCungCap = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maNCC);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maNhaCungCap = rs.getString("maNhaCungCap");
				String tenNhaCungCap= rs.getString("tenNhaCungCap");
				String diaChi= rs.getString("diaChi");
				int soLuongSp= rs.getInt("soLuongSp");
				
				ncc = new NhaCungCap(maNhaCungCap, tenNhaCungCap, diaChi, soLuongSp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return ncc;
	}
	public boolean checkExist(String tenNCC, String diaChi) {
		boolean check = false;
		String sql = "select * from NhaCungCap where tenNhaCungCap = ? AND diaChi= ?";
		PreparedStatement prepareStatement = null;

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, tenNCC);
			prepareStatement.setString(2, diaChi);
			
			ResultSet rs = prepareStatement.executeQuery();
			check = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return check;
	}
	
	
//	public ArrayList<NhaCungCap> getNCCTheoTen(String tenNCC) {
//		ArrayList<NhaCungCap> dsNhaCungCap = new ArrayList<NhaCungCap>();
//		try {
//			ConnectDB.getInstance().connect();
//			Connection con = ConnectDB.getConnection();
//
//			String sql = "SELECT * FROM NhaCungCap " + "WHERE tenNhaCungCap = ?";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, tenNCC);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				String maNhaCungCap = rs.getString("maNhaCungCap");
//				String tenNhaCungCap= rs.getString("tenNhaCungCap");
//				String diaChi= rs.getString("diaChi");
//				int soLuongSp= rs.getInt("soLuongSp");
//				
//				NhaCungCap ncc = new NhaCungCap(maNhaCungCap, tenNhaCungCap, diaChi, soLuongSp);
//				dsNhaCungCap.add(ncc);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}
//		return dsNhaCungCap;
//	}
//	
//	public ArrayList<NhaCungCap> getNCCTheoDiaChi(String diachi) {
//		ArrayList<NhaCungCap> dsNhaCungCap = new ArrayList<NhaCungCap>();
//		try {
//			ConnectDB.getInstance().connect();
//			Connection con = ConnectDB.getConnection();
//
////			String sql = "SELECT * from NhaCungCap" + "WHERE diaChi" + " LIKE CONCAT('%',?,'%')";
//			String sql = "SELECT * from NhaCungCap" + "WHERE diaChi = ?";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, diachi);
//
//			ResultSet rs = ps.executeQuery();
//				
//			while (rs.next()) {
//				String maNhaCungCap = rs.getString("maNhaCungCap");
//				String tenNhaCungCap= rs.getString("tenNhaCungCap");
//				String diaChi= rs.getString("diaChi");
//				int soLuongSp= rs.getInt("soLuongSp");
//				
//				NhaCungCap ncc = new NhaCungCap(maNhaCungCap, tenNhaCungCap, diaChi, soLuongSp);
//				dsNhaCungCap.add(ncc);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}
//		return dsNhaCungCap;
//	}
	
	public boolean addNCC(NhaCungCap ncc) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "INSERT INTO [dbo].[NhaCungCap] ( [tenNhaCungCap] , [diaChi] , [soLuongSp])\r\n"
					+ "     VALUES (? , ? , ?)";
			ps = con.prepareStatement(sql);

			ps.setString(1, ncc.getTenNhaCungCap());
			ps.setString(2,ncc.getDiaChi());
			ps.setInt(3, ncc.getSoLuongSp());
			
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		}
		return n > 0;
	}
	
	public boolean deleteNCC(String maNCC) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "delete from NhaCungCap where maNhaCungCap= ? ";
			ps = con.prepareStatement(sql);

			ps.setString(1, maNCC);
		
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		}
		return n > 0;
	}
	
	public boolean updateNCC(NhaCungCap ncc) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "update NhaCungCap set tenNhaCungCap= ?, diaChi= ?, soLuongSp= ? where maNhaCungCap= ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, ncc.getTenNhaCungCap());
			ps.setString(2, ncc.getDiaChi());
			ps.setInt(3,ncc.getSoLuongSp());
			ps.setString(4, ncc.getMaNhaCungCap());
		
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		}
		return n > 0;
	}
	
	
	public NhaCungCap getNCCTheoTen(String tenNCC) {
		NhaCungCap ncc = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT * FROM NhaCungCap " + "WHERE tenNhaCungCap = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tenNCC);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maNhaCungCap = rs.getString("maNhaCungCap");
				String tenNhaCungCap= rs.getString("tenNhaCungCap");
				String diaChi= rs.getString("diaChi");
				int soLuongSp= rs.getInt("soLuongSp");
				
				ncc = new NhaCungCap(maNhaCungCap, tenNhaCungCap, diaChi, soLuongSp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return ncc;
	}
	public NhaCungCap getNCCTheoDiaChi(String diachi) {
		NhaCungCap ncc = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
//			String sql = "SELECT * from NhaCungCap" + "WHERE diaChi" + " LIKE CONCAT('%',?,'%')";

			String sql = "SELECT * FROM NhaCungCap " + "WHERE diaChi = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, diachi);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maNhaCungCap = rs.getString("maNhaCungCap");
				String tenNhaCungCap= rs.getString("tenNhaCungCap");
				String diaChi= rs.getString("diaChi");
				int soLuongSp= rs.getInt("soLuongSp");
				
				ncc = new NhaCungCap(maNhaCungCap, tenNhaCungCap, diaChi, soLuongSp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return ncc;
	}
}
