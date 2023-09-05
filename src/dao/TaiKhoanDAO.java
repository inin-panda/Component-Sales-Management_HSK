package dao;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;


public class TaiKhoanDAO {
	private Connection con;
	private NhanVienDAO dao_NV;
	public TaiKhoanDAO(Connection con) {
		this.con = con;
		dao_NV = new NhanVienDAO();
	}
	
	public ResultSet dangNhap(String userName, String passWord) {
		PreparedStatement preparedStatement = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
	
			String sql = "SELECT * FROM TaiKhoan\r\n"
					+ "WHERE tenTk = ? AND matKhau = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, passWord);
			ResultSet rs = preparedStatement.executeQuery();   
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public TaiKhoan timKiemTaiKhoanBangTK(String tenTK) throws SQLException {
		TaiKhoan tk = null;
		String sql = "select * from TaiKhoan where tenTk ="+tk+"'";
		ConnectDB.getInstance().connect();;
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			tk = new TaiKhoan(
					rs.getString("tenTk"),
					rs.getString("matKhau"),
					rs.getString("chucVu"),
					dao_NV.thongTinNhanVienTheoMaNhanVien(rs.getString("maNv"))
					);
		}
		return tk;
	}
	
	public TaiKhoan gettaiKhoan(String ten) {
		TaiKhoan tk = null;
		try {
			String sql = "select * from TaiKhoan where tenTK = ?";
			ConnectDB.getInstance().connect();;
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, ten);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
//				tenTk,matKhau,chucVu
				String taiKhoan = rs.getString("tenTk");
				String Mk =rs.getString("matKhau");
				String VaiTro= rs.getString("chucVu");
				NhanVien nv = new NhanVien(rs.getString("maNv"));
				tk = new TaiKhoan(taiKhoan, Mk, VaiTro, nv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tk;
	}
	public boolean createTK(TaiKhoan tk) throws SQLException {
		
		ConnectDB.getInstance().connect();;
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			if(gettaiKhoan(tk.getTaiKhoan()) != null) {
				return false;
			}else {
				stmt = con.prepareStatement("insert into TaiKhoan(tenTK , matKhau , maNhanVien) values(? , ? , ? )");
				stmt.setString(1, tk.getTaiKhoan());
				stmt.setString(2, tk.getMatKhau());
				stmt.setString(3 ,tk.getVaiTro());
				stmt.setString(4, tk.getNhanVien().getMaNV());
				n = stmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
//	public boolean chinhSuaTaiKhoan(TaiKhoan tk) throws SQLException {
//		String sql = "UPDATE [dbo].[TaiKhoan]\r\n"
//				+ "   SET \r\n"
//				+ "      [matKhau] = '"+tk.getMatKhau()+"'"
//				+ " WHERE [gmail] = '"+tk.getGmail()+"'";
//		PreparedStatement stmt = con.prepareStatement(sql);
//		int rs = stmt.executeUpdate();
//		return rs>0;
//	}
	
}
