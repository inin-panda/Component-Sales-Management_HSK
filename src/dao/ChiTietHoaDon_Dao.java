package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;

public class ChiTietHoaDon_Dao {
	public ArrayList<ChiTietHoaDon> getAllCTHoaDon(String MaHD) {
		ArrayList<ChiTietHoaDon> dsCTHoaDon = new ArrayList<ChiTietHoaDon>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			
			String sql = "select * from ChiTietHoaDon join LinhKien on ChiTietHoaDon.maLK = LinhKien.maLK where maHD = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, MaHD);
			ResultSet rs = ps.executeQuery();
				
			while (rs.next()) {
				LinhKien malk = new LinhKien(rs.getString("MaLk"),rs.getString("tenLK"),rs.getFloat("gia"));
				int sl = rs.getInt("soLuong");
				Float giamGia = rs.getFloat("mucGiamGia");
				ChiTietHoaDon ct = new ChiTietHoaDon(malk, sl, giamGia);
				dsCTHoaDon.add(ct);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsCTHoaDon;
	}
	
	
	public boolean addCTHoaDon(ChiTietHoaDon ct) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "INSERT INTO [dbo].[ChiTietHoaDon]([maHD],[maLK],[soLuong],[mucGiamGia]) "
									+ "VALUES(?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, ct.getMaHD().getMaHD());
			ps.setString(2,ct.getMaLk().getMaLk());
			ps.setInt(3, ct.getSoLuong());
			ps.setDouble(4, ct.getMucGiamGia());
			
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

	public boolean deleteCTHoaDon(ChiTietHoaDon ct) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "delete from ChiTietHoaDon where maHD= ? AND maLK =?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, ct.getMaHD().getMaHD());
			ps.setString(2, ct.getMaLk().getMaLk());
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
	public boolean updateCTHoaDon(ChiTietHoaDon ct) {
		PreparedStatement ps = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "update ChiTietHoaDon "
					+ "set soLuong= ?, mucGiamGia =? "
					+ "where maHD= ? and maLk= ? ";
			ps = con.prepareStatement(sql);

			ps.setInt(1, ct.getSoLuong());
			ps.setFloat(2,ct.getMucGiamGia());
			ps.setString(3, ct.getMaHD().getMaHD());
			ps.setString(4, ct.getMaLk().getMaLk());
			
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
	public long tinhTongTienTheoMaHD(String maHD) {
		long tongTien = 0;
	    try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
	        String sql = "SELECT hd.maHD, SUM(lk.gia * cthd.soLuong * (1 - cthd.mucGiamGia/100)) AS TongTien "
	                + "FROM HoaDon hd "
	                + "JOIN ChiTietHoaDon cthd ON hd.maHD = cthd.maHD "
	                + "JOIN LinhKien lk ON cthd.maLk = lk.maLk "
	                + "WHERE hd.maHD = ? "
	                + "GROUP BY hd.maHD";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, maHD);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            tongTien = rs.getLong("TongTien");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return tongTien;
	}
	public long tinhTongTienTheoMalK(ChiTietHoaDon ct) {
		long tongTien = 0;
	    try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
	        String sql = "SELECT cthd.maHD,cthd.maLk, SUM(lk.gia * cthd.soLuong * (1 - cthd.mucGiamGia/100)) AS TongTien FROM  ChiTietHoaDon cthd "
	        		+"JOIN LinhKien lk ON cthd.maLk = lk.maLk "
	        		+"where cthd.maHD = ? and cthd.maLk = ? "
	        		+"GROUP BY cthd.maHD,cthd.maLk";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, ct.getMaHD().getMaHD());
	        ps.setString(2, ct.getMaLk().getMaLk());
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            tongTien = rs.getLong("TongTien");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return tongTien;
	}
}