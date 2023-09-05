package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;

import entity.NhanVien;

public class HoaDon_DAO {
	public ArrayList<HoaDon> getAllHoaDon() {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "select *from HoaDon join KhachHang on HoaDon.maKh=KhachHang.maKh join NhanVien on HoaDon.maNV=NhanVien.maNV";
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
				
			while (rs.next()) {
				String maHD = rs.getString("maHD");
				NhanVien NV = new NhanVien(rs.getString("maNv"),rs.getString("tenNv"),rs.getString("sDT"));
				KhachHang KH = new KhachHang(rs.getString("maKh"),rs.getString("tenKH"));
				String diaChiGH = rs.getString("diaChiGH");
				LocalDate ngayLapHD= rs.getDate("ngayLapHoaDon") != null ? LocalDate.parse(rs.getDate("ngayLapHoaDon").toString()) : null;
				Boolean trangThai = rs.getBoolean("trangThaiThanhToan");
				HoaDon hd = new HoaDon(maHD,NV, KH, diaChiGH, ngayLapHD, trangThai);
				dsHoaDon.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsHoaDon;
	}
	public ArrayList<HoaDon> getHoaDonTheoKhachHang(String tenKH) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "select *from HoaDon join KhachHang on HoaDon.maKh=KhachHang.maKh join NhanVien on HoaDon.maNV=NhanVien.maNV where tenKH LIKE N'%"+ tenKH +"%'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String maHD = rs.getString("maHD");
				NhanVien NV = new NhanVien(rs.getString("maNv"),rs.getString("tenNv"),rs.getString("sDT"));
				KhachHang KH = new KhachHang(rs.getString("maKh"),rs.getString("tenKH"));
				String diaChiGH = rs.getString("diaChiGH");
				LocalDate ngayLapHD= rs.getDate("ngayLapHoaDon") != null ? LocalDate.parse(rs.getDate("ngayLapHoaDon").toString()) : null;
				Boolean trangThai = rs.getBoolean("trangThaiThanhToan");
				HoaDon hd = new HoaDon(maHD,NV, KH, diaChiGH, ngayLapHD, trangThai);
				dsHoaDon.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsHoaDon;
	}
	public ArrayList<HoaDon> getHoaDonTheoNhanVien(String tenNV) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "select *from HoaDon join KhachHang on HoaDon.maKh=KhachHang.maKh join NhanVien on HoaDon.maNV=NhanVien.maNV where tenNV LIKE N'%"+ tenNV +"%'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String maHD = rs.getString("maHD");
				NhanVien NV = new NhanVien(rs.getString("maNv"),rs.getString("tenNv"),rs.getString("sDT"));
				KhachHang KH = new KhachHang(rs.getString("maKh"),rs.getString("tenKH"));
				String diaChiGH = rs.getString("diaChiGH");
				LocalDate ngayLapHD= rs.getDate("ngayLapHoaDon") != null ? LocalDate.parse(rs.getDate("ngayLapHoaDon").toString()) : null;
				Boolean trangThai = rs.getBoolean("trangThaiThanhToan");
				HoaDon hd = new HoaDon(maHD,NV, KH, diaChiGH, ngayLapHD, trangThai);
				dsHoaDon.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsHoaDon;
	}
	
	
//	select * from HoaDon where  diaChiGH LIKE  N'%Nguyễn%'
	public ArrayList<HoaDon> getHoaDonTheoDiaChiGH(String diaChi) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT * from HoaDon join KhachHang on HoaDon.maKh=KhachHang.maKh join NhanVien on HoaDon.maNV=NhanVien.maNV where diaChiGH LIKE N'%"+ diaChi +"%'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String maHD = rs.getString("maHD");
				NhanVien NV = new NhanVien(rs.getString("maNv"),rs.getString("tenNv"),rs.getString("sDT"));
				KhachHang KH = new KhachHang(rs.getString("maKh"),rs.getString("tenKH"));
				String diaChiGH = rs.getString("diaChiGH");
				LocalDate ngayLapHD= rs.getDate("ngayLapHoaDon") != null ? LocalDate.parse(rs.getDate("ngayLapHoaDon").toString()) : null;
				Boolean trangThai = rs.getBoolean("trangThaiThanhToan");
				HoaDon hd = new HoaDon(maHD,NV, KH, diaChiGH, ngayLapHD, trangThai);
				dsHoaDon.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsHoaDon;
	}
	
	public ArrayList<HoaDon> getHoaDonTheoMa(String maHoaDon) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "select *from HoaDon join KhachHang on HoaDon.maKh=KhachHang.maKh join NhanVien on HoaDon.maNV=NhanVien.maNV where maHD LIKE N'%"+ maHoaDon +"%'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String maHD = rs.getString("maHD");
				NhanVien NV = new NhanVien(rs.getString("maNv"),rs.getString("tenNv"),rs.getString("sDT"));
				KhachHang KH = new KhachHang(rs.getString("maKh"),rs.getString("tenKH"));
				String diaChiGH = rs.getString("diaChiGH");
				LocalDate ngayLapHD= rs.getDate("ngayLapHoaDon") != null ? LocalDate.parse(rs.getDate("ngayLapHoaDon").toString()) : null;
				Boolean trangThai = rs.getBoolean("trangThaiThanhToan");
				HoaDon hd = new HoaDon(maHD,NV, KH, diaChiGH, ngayLapHD, trangThai);
				dsHoaDon.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsHoaDon;
	}
	
	public HoaDon getOneHoaDonTheoMa(String maHoaDon) {
		HoaDon hd = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();

			String sql = "select *from HoaDon where maHD = '"+ maHoaDon +"'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String maHD = rs.getString("maHD");
				NhanVien NV = new NhanVien(rs.getString("maNv"),rs.getString("tenNv"),rs.getString("sDT"));
				KhachHang KH = new KhachHang(rs.getString("maKh"),rs.getString("tenKH"));
				String diaChiGH = rs.getString("diaChiGH");
				LocalDate ngayLapHD= rs.getDate("ngayLapHoaDon") != null ? LocalDate.parse(rs.getDate("ngayLapHoaDon").toString()) : null;
				Boolean trangThai = rs.getBoolean("trangThaiThanhToan");
				hd = new HoaDon(maHD,NV, KH, diaChiGH, ngayLapHD, trangThai);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return hd;
	}
	
	public boolean addHoaDon(HoaDon hd) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "INSERT INTO [dbo].[HoaDon]([maKh],[maNv],[diaChiGH],[ngayLapHoaDon],[trangThaiThanhToan]) "
									+ "VALUES(?,?,?,?,?)";
			ps = con.prepareStatement(sql);

			ps.setString(1, hd.getKH().getMaKH());
			ps.setString(2,hd.getNV().getMaNV());
			ps.setString(3, hd.getDiaChiGH());
			ps.setDate(4, hd.getNgayLapHD()!= null ? Date.valueOf(hd.getNgayLapHD()) : null);
			ps.setBoolean(5, hd.isTrangThaiThanhToan());
			
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

	public boolean deleteHoaDon(String maHD) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "delete from HoaDon where maHD= ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, maHD);
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
	public boolean updateHoaDon(HoaDon hd) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "update HoaDon "
					+ "set maKh= ?, maNv= ?, diaChiGH= ?, ngayLapHoaDon =?, trangThaiThanhToan =? "
					+ "where maHD= ? ";
			ps = con.prepareStatement(sql);

			ps.setString(1, hd.getKH().getMaKH());
			ps.setString(2,hd.getNV().getMaNV());
			ps.setString(3, hd.getDiaChiGH());
			ps.setDate(4, hd.getNgayLapHD()!= null ? Date.valueOf(hd.getNgayLapHD()) : null);
			ps.setBoolean(5, hd.isTrangThaiThanhToan());
			ps.setString(6, hd.getMaHD());
			
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
	public boolean updateThanhToan(String maHD) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "update HoaDon "
					+ "set trangThaiThanhToan = 0 "
					+ "where maHD= ? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, maHD);		
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
}
