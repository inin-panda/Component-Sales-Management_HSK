package entity;

import java.io.Serializable;
import java.util.Objects;

public class TaiKhoan  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String taiKhoan;
	private String matKhau;
	private String vaiTro;
	private NhanVien nhanVien;
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getVaiTro() {
		return vaiTro;
	}
	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "TaiKhoan [taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + ", vaiTro=" + vaiTro + ", nhanVien="
				+ nhanVien + "]";
	}
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TaiKhoan(String taiKhoan, String matKhau, String vaiTro, NhanVien nhanVien) {
		super();
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.vaiTro = vaiTro;
		this.nhanVien = nhanVien;
	}
	public TaiKhoan(String taiKhoan, String matKhau) {
		super();
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
	}
	@Override
	public int hashCode() {
		return Objects.hash(taiKhoan);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(taiKhoan, other.taiKhoan);
	}
	
}
