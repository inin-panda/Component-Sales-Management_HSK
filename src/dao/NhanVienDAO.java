package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVienDAO {
	public ArrayList<NhanVien> getalltbNhanVien() {
		ArrayList<NhanVien> listNV = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance().connect();;
			Connection conn = ConnectDB.getConnection();
			 String sql = "Select * from NhanVien";
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql);
			 while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTen = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				String sdt = rs.getString(5);
				String diaChi =rs.getString(6);
				String chucVu = rs.getString(7);
				String email = rs.getString(8);
				NhanVien nv = new NhanVien(maNV,hoTen, gioiTinh, ngaySinh, sdt, diaChi, chucVu, email);
				listNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return listNV;
	}
//	
	public NhanVien thongTinNhanVienTheoMaNhanVien(String maNhanVien) throws SQLException {
		NhanVien nv = new NhanVien(maNhanVien);
		ConnectDB.getInstance().connect();
		Connection conn = ConnectDB.getConnection();
		String sql = "Select * from NhanVien where maNv = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, maNhanVien);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				nv.setMaNV(rs.getString(1));
				nv.setTenNV(rs.getString(2));
				nv.setGioiTinh(rs.getBoolean(3));
				nv.setNgaySinh(rs.getDate(4));
				nv.setSdt(rs.getString(5));
				nv.setDiaChi(rs.getString(6));
				nv.setEmail(rs.getString(7));
				nv.setChucVu(rs.getString(8));
				return nv;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean addNhanVien(NhanVien nv) throws SQLException {
		String sql  = "insert into NhanVien(tenNv, gioiTinh,namSinh, sdt, diaChi, chucVu, email) values(?, ?, ?, ?, ?, ?,?)";
		ConnectDB.getInstance().connect();
		Connection conn = ConnectDB.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nv.getTenNV());
			stmt.setBoolean(2, nv.isGioiTinh());
			stmt.setDate(3, nv.getNgaySinh());
			stmt.setString(4, nv.getSdt());
			stmt.setString(5, nv.getDiaChi());	
			stmt.setString(7, nv.getEmail());
			stmt.setString(6, nv.getChucVu());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean updateNhanVien(NhanVien nv) throws SQLException {
		String sql = "update NhanVien set tenNv = ?, gioiTinh = ?,namSinh = ?, sdt = ?, diaChi = ?, chucVu = ?, email = ? where maNv = ?";
		ConnectDB.getInstance().connect();
		Connection conn = ConnectDB.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nv.getTenNV());
			stmt.setBoolean(2, nv.isGioiTinh());
			stmt.setString(3, nv.getNgaySinh().toString());
			stmt.setString(4, nv.getSdt());
			stmt.setString(5, nv.getDiaChi());
			stmt.setString(7, nv.getEmail());
			stmt.setString(6, nv.getChucVu());
			stmt.setString(8, nv.getMaNV());
			 stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean deleteNhanVien(String maNhanVien) throws SQLException {
		String sql = "delete NhanVien where maNv = ?";
		ConnectDB.getInstance().connect();
		Connection conn = ConnectDB.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, maNhanVien);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}	
}
