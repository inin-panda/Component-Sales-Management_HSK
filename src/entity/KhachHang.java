package entity;

import java.util.Objects;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String diaChi;
	private String sdt;
	private String email;
	private Boolean gioiTinh;
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(Boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maKH);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKH, other.maKH);
	}
	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", diaChi=" + diaChi + ", sdt=" + sdt + ", email="
				+ email + ", gioiTinh=" + gioiTinh + "]";
	}
	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}
	
	public KhachHang() {
		super();
	}
	public KhachHang(String maKH, String tenKH, String diaChi, String sdt, String email, Boolean gioiTinh) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.email = email;
		this.gioiTinh = gioiTinh;
	}
	public KhachHang(String maKH, String tenKH) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
	}	
}
