package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class LinhKien {
//	[maLk] [varchar](6) NOT NULL,
//	[tenLk] [nvarchar](50) NOT NULL,
//	[soLuong] [int] NULL,
//	[ngaySx] [date] NULL,
//	[gia] [float] NOT NULL,
//	[chiTietLk] [nvarchar](50) NULL,
//	[maNhaCungCap] [varchar](6) NOT NULL,
//	[maLoai] [varchar](7) NOT NULL,
	private String maLk;
	private String tenLk;
	private int soLuong;
	private LocalDate ngaySx;
	private double gia;
	private String chiTietLk;
	private NhaCungCap maNhaCungCap;
	private LoaiLinhKien maLoai;
	
	public LinhKien(String tenLk, int soLuong, LocalDate ngaySx, double gia, String chiTietLk, NhaCungCap maNhaCungCap,
			LoaiLinhKien maLoai) {
		super();
		this.tenLk = tenLk;
		this.soLuong = soLuong;
		this.ngaySx = ngaySx;
		this.gia = gia;
		this.chiTietLk = chiTietLk;
		this.maNhaCungCap = maNhaCungCap;
		this.maLoai = maLoai;
	}

	public LinhKien(String maLk, String tenLk, int soLuong, LocalDate ngaySx, double gia, String chiTietLk,
			NhaCungCap maNhaCungCap, LoaiLinhKien maLoai) {
		super();
		this.maLk = maLk;
		this.tenLk = tenLk;
		this.soLuong = soLuong;
		this.ngaySx = ngaySx;
		this.gia = gia;
		this.chiTietLk = chiTietLk;
		this.maNhaCungCap = maNhaCungCap;
		this.maLoai = maLoai;
	}

	public LinhKien() {
		super();
	}
	
	public String getChiTietLk() {
		return chiTietLk;
	}

	public void setChiTietLk(String chiTietLk) {
		this.chiTietLk = chiTietLk;
	}

	public LinhKien(String maLk) {
		super();
		this.maLk = maLk;
	}
	public String getMaLk() {
		return maLk;
	}
	public void setMaLk(String maLk) {
		this.maLk = maLk;
	}
	public String getTenLk() {
		return tenLk;
	}
	public void setTenLk(String tenLk) {
		this.tenLk = tenLk;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public LocalDate getNgaySx() {
		return ngaySx;
	}
	public void setNgaySx(LocalDate ngaySx) {
		this.ngaySx = ngaySx;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public NhaCungCap getMaNhaCungCap() {
		return maNhaCungCap;
	}
	public void setMaNhaCungCap(NhaCungCap maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}
	public LoaiLinhKien getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(LoaiLinhKien maLoai) {
		this.maLoai = maLoai;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLk);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinhKien other = (LinhKien) obj;
		return Objects.equals(maLk, other.maLk);
	}

	public LinhKien(String maLk, String tenLk, double gia) {
		super();
		this.maLk = maLk;
		this.tenLk = tenLk;
		this.gia = gia;
	}

	public LinhKien(String maLk, String tenLk) {
		super();
		this.maLk = maLk;
		this.tenLk = tenLk;
	}
	
}
