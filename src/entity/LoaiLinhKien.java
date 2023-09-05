package entity;

import java.util.Objects;

public class LoaiLinhKien {
//	[maLoai] [varchar](7) NOT NULL,
//	[tenLoai] [nvarchar](50) NOT NULL,
//	[soLuongLinhKien] [int] NOT NULL,
	private String maLoai;
	private String tenLoai;
	private int soLuongLinhKien;
	public LoaiLinhKien(String maLoai, String tenLoai, int soLuongLinhKien) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
		this.soLuongLinhKien = soLuongLinhKien;
	}
	public LoaiLinhKien(String tenLoai, int soLuongLinhKien) {
		super();
		this.tenLoai = tenLoai;
		this.soLuongLinhKien = soLuongLinhKien;
	}
	public LoaiLinhKien() {
		super();
	}
	
	public LoaiLinhKien(String maLoai) {
		super();
		this.maLoai = maLoai;
	}
	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	public int getSoLuongLinhKien() {
		return soLuongLinhKien;
	}
	public void setSoLuongLinhKien(int soLuongLinhKien) {
		this.soLuongLinhKien = soLuongLinhKien;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLoai);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiLinhKien other = (LoaiLinhKien) obj;
		return Objects.equals(maLoai, other.maLoai);
	}
	
	
}
