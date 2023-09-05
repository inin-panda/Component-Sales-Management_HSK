package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LinhKien;
import entity.LoaiLinhKien;
import entity.NhaCungCap;


public class LinhKien_DAO {
	public ArrayList<LinhKien> getAllLinhKien() {
		ArrayList<LinhKien> dsLinhKien = new ArrayList<LinhKien>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT * from LinhKien";
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
				
			while (rs.next()) {
				String maLk = rs.getString("maLk");
				String tenLk= rs.getString("tenLk");
				int soLuong= rs.getInt("soLuong");
				LocalDate ngaySx= rs.getDate("ngaySx") != null ? LocalDate.parse(rs.getDate("ngaySx").toString()) : null;
				double gia= rs.getDouble("gia");
				String chiTiet= rs.getString("chiTietLk");
				NhaCungCap maNhaCungCap= new NhaCungCap(rs.getString("maNhaCungCap"));
				LoaiLinhKien maLoai= new LoaiLinhKien(rs.getString("maLoai"));
				
				LinhKien lk = new LinhKien(maLk, tenLk, soLuong, ngaySx, gia, chiTiet,maNhaCungCap, maLoai);
				dsLinhKien.add(lk);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsLinhKien;
	}
	
	
	public LinhKien getLinhKienTheoMa(String maLK) {
		LinhKien lk = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT * FROM LinhKien " + "WHERE maLk = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maLK);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maLk = rs.getString("maLk");
				String tenLk= rs.getString("tenLk");
				int soLuong= rs.getInt("soLuong");
				LocalDate ngaySx= rs.getDate("ngaySx") != null ? LocalDate.parse(rs.getDate("ngaySx").toString()) : null;
				double gia= rs.getDouble("gia");
				String chiTiet= rs.getString("chiTietLk");

				NhaCungCap maNhaCungCap= new NhaCungCap(rs.getString("maNhaCungCap"));
				LoaiLinhKien maLoai= new LoaiLinhKien(rs.getString("maLoai"));
				
				lk = new LinhKien(maLk, tenLk, soLuong, ngaySx, gia, chiTiet, maNhaCungCap, maLoai);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return lk;
	}
	public ArrayList<LinhKien> getLinhKienTheoTen(String tenLK) {
		ArrayList<LinhKien> dsLinhKien = new ArrayList<LinhKien>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT * FROM LinhKien " + "WHERE tenLk LIKE N'%"+tenLK+"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
//			PreparedStatement ps = con.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maLk = rs.getString("maLk");
				String tenLk= rs.getString("tenLk");
				int soLuong= rs.getInt("soLuong");
				LocalDate ngaySx= rs.getDate("ngaySx") != null ? LocalDate.parse(rs.getDate("ngaySx").toString()) : null;
				double gia= rs.getDouble("gia");
				String chiTiet= rs.getString("chiTietLk");

				NhaCungCap maNhaCungCap= new NhaCungCap(rs.getString("maNhaCungCap"));
				LoaiLinhKien maLoai= new LoaiLinhKien(rs.getString("maLoai"));
				
				LinhKien lk = new LinhKien(maLk, tenLk, soLuong, ngaySx, gia, chiTiet, maNhaCungCap, maLoai);
				dsLinhKien.add(lk);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsLinhKien;
	}
	
	public LinhKien getOneLinhKienTheoTen(String tenLK) {
		LinhKien lk= null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT * FROM LinhKien " + "WHERE tenLk = '"+tenLK+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
//			PreparedStatement ps = con.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maLk = rs.getString("maLk");
				String tenLk= rs.getString("tenLk");
				int soLuong= rs.getInt("soLuong");
				LocalDate ngaySx= rs.getDate("ngaySx") != null ? LocalDate.parse(rs.getDate("ngaySx").toString()) : null;
				double gia= rs.getDouble("gia");
				String chiTiet= rs.getString("chiTietLk");

				NhaCungCap maNhaCungCap= new NhaCungCap(rs.getString("maNhaCungCap"));
				LoaiLinhKien maLoai= new LoaiLinhKien(rs.getString("maLoai"));
				
				lk = new LinhKien(maLk, tenLk, soLuong, ngaySx, gia, chiTiet, maNhaCungCap, maLoai);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return lk;
	}
	public ArrayList<LinhKien> getLinhKienTheoNhaCungCap(String maNCC) {
		ArrayList<LinhKien> dsLinhKien = new ArrayList<LinhKien>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT * FROM LinhKien " + "WHERE maNhaCungCap LIKE '%"+maNCC+"%'";
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maLk = rs.getString("maLk");
				String tenLk= rs.getString("tenLk");
				int soLuong= rs.getInt("soLuong");
				LocalDate ngaySx= rs.getDate("ngaySx") != null ? LocalDate.parse(rs.getDate("ngaySx").toString()) : null;
				double gia= rs.getDouble("gia");				
				String chiTiet= rs.getString("chiTietLk");

				NhaCungCap maNhaCungCap= new NhaCungCap(rs.getString("maNhaCungCap"));
				LoaiLinhKien maLoai= new LoaiLinhKien(rs.getString("maLoai"));
				
				LinhKien lk = new LinhKien(maLk, tenLk, soLuong, ngaySx, gia, chiTiet, maNhaCungCap, maLoai);
				dsLinhKien.add(lk);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsLinhKien;
	}
	public ArrayList<LinhKien> getLinhKienTheoLoaiLK(String maLLK) {
		ArrayList<LinhKien> dsLinhKien = new ArrayList<LinhKien>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT * FROM LinhKien " + "WHERE maLoai LIKE '%"+maLLK+"%'";
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maLk = rs.getString("maLk");
				String tenLk= rs.getString("tenLk");
				int soLuong= rs.getInt("soLuong");
				LocalDate ngaySx= rs.getDate("ngaySx") != null ? LocalDate.parse(rs.getDate("ngaySx").toString()) : null;
				double gia= rs.getDouble("gia");
				String chiTiet= rs.getString("chiTietLk");

				NhaCungCap maNhaCungCap= new NhaCungCap(rs.getString("maNhaCungCap"));
				LoaiLinhKien maLoai= new LoaiLinhKien(rs.getString("maLoai"));
				
				LinhKien lk = new LinhKien(maLk, tenLk, soLuong, ngaySx, gia, chiTiet, maNhaCungCap, maLoai);
				dsLinhKien.add(lk);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsLinhKien;
	}
	public ArrayList<LinhKien> getLinhKienTheoGia(float gia) {
		ArrayList<LinhKien> dsLinhKien = new ArrayList<LinhKien>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT * FROM LinhKien " + "WHERE gia = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setFloat(1,gia);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maLk = rs.getString("maLk");
				String tenLk= rs.getString("tenLk");
				int soLuong= rs.getInt("soLuong");
				LocalDate ngaySx= rs.getDate("ngaySx") != null ? LocalDate.parse(rs.getDate("ngaySx").toString()) : null;
				double Gia= rs.getDouble("gia");
				String chiTiet= rs.getString("chiTietLk");

				NhaCungCap maNhaCungCap= new NhaCungCap(rs.getString("maNhaCungCap"));
				LoaiLinhKien maLoai= new LoaiLinhKien(rs.getString("maLoai"));
				
				LinhKien lk = new LinhKien(maLk, tenLk, soLuong, ngaySx, Gia, chiTiet, maNhaCungCap, maLoai);
				dsLinhKien.add(lk);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsLinhKien;
	}
	
	public boolean addLinhKien(LinhKien lk) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "INSERT INTO [dbo].[LinhKien]\r\n"
					+ "           ([tenLk], [soLuong] ,[ngaySx] ,[gia], [chiTietLk] ,[maNhaCungCap] ,[maLoai])\r\n"
					+ "     VALUES\r\n"
					+ "           (?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);

			ps.setString(1, lk.getTenLk());
			ps.setInt(2,lk.getSoLuong());
			ps.setDate(3, lk.getNgaySx()!= null ? Date.valueOf(lk.getNgaySx()) : null);
			ps.setDouble(4, lk.getGia());
			ps.setString(5, lk.getChiTietLk());
			ps.setString(6, lk.getMaNhaCungCap().getMaNhaCungCap());
			ps.setString(7,lk.getMaLoai().getMaLoai());
			
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
	
	public boolean deleteLinhKien(String maLK) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "delete from LinhKien where maLk= ? ";
			ps = con.prepareStatement(sql);

			ps.setString(1, maLK);
		
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
	
	public boolean updateLinhKien(LinhKien lk) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "UPDATE [dbo].[LinhKien]\r\n"
					+ "   SET [tenLk] = ? ,[soLuong] = ? ,[ngaySx] = ?  ,[gia] = ?, [chiTietLk] = ? ,[maNhaCungCap] = ? ,[maLoai] = ?\r\n"
					+ " WHERE [maLk] = ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, lk.getTenLk());
			ps.setInt(2,lk.getSoLuong());
			ps.setDate(3, lk.getNgaySx()!= null ? Date.valueOf(lk.getNgaySx()) : null);
			ps.setDouble(4, lk.getGia());
			ps.setString(5, lk.getChiTietLk());
			ps.setString(6, lk.getMaNhaCungCap().getMaNhaCungCap());
			ps.setString(7,lk.getMaLoai().getMaLoai());
			ps.setString(8, lk.getMaLk());
		
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
	
	public boolean checkExist(String maLoai, String maNCC) {
		boolean check = false;
		String sql = "select * from LinhKien where maLoai = ? AND maNhaCungCap= ?";
		PreparedStatement prepareStatement = null;

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			prepareStatement = con.prepareStatement(sql);

			prepareStatement.setString(1, maLoai);
			prepareStatement.setString(2, maNCC);
			
			ResultSet rs = prepareStatement.executeQuery();
			check = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return check;
	}
}

