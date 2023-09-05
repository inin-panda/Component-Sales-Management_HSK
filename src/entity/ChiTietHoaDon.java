package entity;

import java.util.Objects;

public class ChiTietHoaDon {
//	[maLk] [varchar](6) NOT NULL,
//	[maHD] [varchar](10) NOT NULL,
//	[soLuong] [int] NOT NULL,
//	[mucGiamGia] [float] NOT NULL,
	private HoaDon maHD;
	private LinhKien maLk;
	private int soLuong;
	private float mucGiamGia;
	public HoaDon getMaHD() {
		return maHD;
	}
	public void setMaHD(HoaDon maHD) {
		this.maHD = maHD;
	}
	public LinhKien getMaLk() {
		return maLk;
	}
	public void setMaLk(LinhKien maLk) {
		this.maLk = maLk;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public float getMucGiamGia() {
		return mucGiamGia;
	}
	public void setMucGiamGia(float mucGiamGia) {
		this.mucGiamGia = mucGiamGia;
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [maHD=" + maHD + ", maLk=" + maLk + ", soLuong=" + soLuong + ", mucGiamGia=" + mucGiamGia
				+ "]";
	}
	public ChiTietHoaDon(HoaDon maHD, LinhKien maLk, int soLuong, float mucGiamGia) {
		super();
		this.maHD = maHD;
		this.maLk = maLk;
		this.soLuong = soLuong;
		this.mucGiamGia = mucGiamGia;
	}
	public ChiTietHoaDon(LinhKien maLk, int soLuong, float mucGiamGia) {
		super();
		this.maLk = maLk;
		this.soLuong = soLuong;
		this.mucGiamGia = mucGiamGia;
	}
	public ChiTietHoaDon(HoaDon maHD, LinhKien maLk) {
		super();
		this.maHD = maHD;
		this.maLk = maLk;
	}
	
}
