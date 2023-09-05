package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LinhKien;
import entity.LoaiLinhKien;
import entity.NhaCungCap;

public class LoaiLinhKien_DAO {
	public ArrayList<LoaiLinhKien> getAllLoaiLinhKien() {
		ArrayList<LoaiLinhKien> dsLoaiLinhKien = new ArrayList<LoaiLinhKien>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT * from LoaiLinhKien";
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
				
			while (rs.next()) {
				String maLoai = rs.getString("maLoai");
				String tenLoai= rs.getString("tenLoai");
				int soLuongLinhKien= rs.getInt("soLuongLinhKien");
				
				LoaiLinhKien llk = new LoaiLinhKien(maLoai, tenLoai, soLuongLinhKien);
				dsLoaiLinhKien.add(llk);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsLoaiLinhKien;
	}
	
	public LoaiLinhKien getLoaiLinhKienTheoMa(String maLLK) {
		LoaiLinhKien llk = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT * FROM LoaiLinhKien " + "WHERE maLoai = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maLLK);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maLoai = rs.getString("maLoai");
				String tenLoai= rs.getString("tenLoai");
				int soLuongLinhKien= rs.getInt("soLuongLinhKien");
				
				llk = new LoaiLinhKien(maLoai, tenLoai, soLuongLinhKien);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return llk;
	}
	public LoaiLinhKien getLoaiLinhKienTheoTen(String tenLLK) {
		LoaiLinhKien llk = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT * FROM LoaiLinhKien " + "WHERE tenLoai = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tenLLK);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maLoai = rs.getString("maLoai");
				String tenLoai= rs.getString("tenLoai");
				int soLuongLinhKien= rs.getInt("soLuongLinhKien");
				
				llk = new LoaiLinhKien(maLoai, tenLoai, soLuongLinhKien);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return llk;
	}
	
	public boolean addLoaiLinhKien(LoaiLinhKien llk) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "insert into LoaiLinhKien (tenLoai,soLuongLinhKien) values (?,?)";
			ps = con.prepareStatement(sql);

			ps.setString(1, llk.getTenLoai());
			ps.setInt(2,llk.getSoLuongLinhKien());
			
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
	
	public boolean deleteLoaiLinhKien(String maLLK) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "delete from LoaiLinhKien where maLoai= ? ";
			ps = con.prepareStatement(sql);

			ps.setString(1, maLLK);
		
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
	
	public boolean updateLoaiLinhKien(LoaiLinhKien llk) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "update LoaiLinhKien set tenLoai= ?, soLuongLinhKien= ? where maLoai= ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, llk.getTenLoai());
			ps.setInt(2,llk.getSoLuongLinhKien());
			ps.setString(3, llk.getMaLoai());
		
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
	
	public boolean checkExist(String tenllk) {
		boolean check = false;
		String sql = "select * from LoaiLinhKien where tenLoai = ?";
		PreparedStatement prepareStatement = null;

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, tenllk);
			ResultSet rs = prepareStatement.executeQuery();
			check = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return check;
	}
}
