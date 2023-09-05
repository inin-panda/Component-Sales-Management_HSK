package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;


public class KhachHangDAO {
	
	
	public List<KhachHang> getDanhSachKhachHang() {
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		String sql = "SELECT * FROM KhachHang";
		
		PreparedStatement prepareStatement = null;

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			prepareStatement = con.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				
				String maKhachHang = rs.getString(1);
				String tenKhachHang = rs.getString(2);
				String diaChi = rs.getString(3);
				String SDT = rs.getString(4);
				String email = rs.getString(5);
				Boolean gioitinh = rs.getBoolean(6);

				KhachHang KhachHang = new KhachHang(maKhachHang,tenKhachHang,diaChi,SDT,email,gioitinh);

				listKhachHang.add(KhachHang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return listKhachHang;
	}
	public KhachHang getKhachHangTheoMa(String maKhachHang) {
		KhachHang KhachHang = new KhachHang();
		String sql = "SELECT * FROM KhachHang" + " where maKh = ?";

		
		PreparedStatement prepareStatement = null;

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			prepareStatement = con.prepareStatement(sql);

			
			prepareStatement.setString(1, maKhachHang);
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				KhachHang.setMaKH("KH" + rs.getString(1));
				KhachHang.setTenKH(rs.getString(2));
				KhachHang.setDiaChi(rs.getString(3));
				KhachHang.setSdt(rs.getString(4));
				KhachHang.setEmail(rs.getString(5));
				KhachHang.setGioiTinh(rs.getBoolean(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return KhachHang;
	}
	public boolean addKhachHang(KhachHang KhachHang) {
		String sql = "INSERT INTO [dbo].[KhachHang] ([TenKh], [diaChi] ,[sdt],[email],[gioiTinh])" + " VALUES(?,?,?,?,?)";
		int rs = 0;

		
		PreparedStatement prepareStatement = null;

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, KhachHang.getTenKH());
			prepareStatement.setString(2, KhachHang.getDiaChi());
			prepareStatement.setString(3, KhachHang.getSdt());
			prepareStatement.setString(4, KhachHang.getEmail());
			prepareStatement.setBoolean(5, KhachHang.getGioiTinh());
			

			rs = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return rs > 0;
	}
	public boolean checkExist(String sdt) {
		boolean check = false;
		String sql = "select * from KhachHang where sdt = ?";

		
		PreparedStatement prepareStatement = null;

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, sdt);
			ResultSet rs = prepareStatement.executeQuery();
			check = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return check;
	}

	public boolean updateKhachHang(KhachHang KhachHang) {
		String sql = "UPDATE KhachHang SET tenKh=?,diaChi=?,sdt=?,email=?,gioiTinh=?" + " where maKh = ?";
		int rs = 0;

				PreparedStatement prepareStatement = null;

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			prepareStatement = con.prepareStatement(sql);
			
			prepareStatement.setString(1, KhachHang.getTenKH());
			prepareStatement.setString(2, KhachHang.getDiaChi());
			prepareStatement.setString(3, KhachHang.getSdt());
			prepareStatement.setString(4, KhachHang.getEmail());
			prepareStatement.setBoolean(5, KhachHang.getGioiTinh());
			
			prepareStatement.setString(6, KhachHang.getMaKH());
			

			rs = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return rs > 0;
	}
	
	public boolean deleteKhachHang(String maKhachHang) {
		String sql = "DELETE FROM KhachHang" + " where maKh= ?";
		int rs = 0;
		
		PreparedStatement prepareStatement = null;

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, maKhachHang);

			rs = prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return rs > 0;
	}
}
