package entity;

import java.time.LocalDate;
import java.util.Objects;

public class HoaDon {
//	[maHoaDon] [varchar](10) NOT NULL,
//	[maKh] [varchar](8) NOT NULL,
//	[maNv] [varchar](6) NOT NULL,
//	[diaChiGH] [nvarchar](Max) NULL,
//	[ngayLapHoaDon] [date] NOT NULL,
//	[trangThaiThanhToan] [bit] null
	private String maHD;
	private NhanVien NV;
	private KhachHang KH;
	private String diaChiGH;
	private LocalDate ngayLapHD;
	private boolean trangThaiThanhToan;
	
	public HoaDon(NhanVien nV, KhachHang kH, String diaChiGH, LocalDate ngayLapHD) {
		super();
		NV = nV;
		KH = kH;
		this.diaChiGH = diaChiGH;
		this.ngayLapHD = ngayLapHD;
	}
	public HoaDon(String maHD, NhanVien nV, KhachHang kH, String diaChiGH, LocalDate ngayLapHD) {
		super();
		this.maHD = maHD;
		NV = nV;
		KH = kH;
		this.diaChiGH = diaChiGH;
		this.ngayLapHD = ngayLapHD;
	}
	public HoaDon(String maHD) {
		super();
		this.maHD = maHD;
	}
	public HoaDon(NhanVien nV, KhachHang kH, String diaChiGH, LocalDate ngayLapHD, boolean trangThaiThanhToan) {
		super();
		NV = nV;
		KH = kH;
		this.diaChiGH = diaChiGH;
		this.ngayLapHD = ngayLapHD;
		this.trangThaiThanhToan = trangThaiThanhToan;
	}
	public HoaDon(String maHD, NhanVien nV, KhachHang kH, String diaChiGH, LocalDate ngayLapHD,
			boolean trangThaiThanhToan) {
		super();
		this.maHD = maHD;
		NV = nV;
		KH = kH;
		this.diaChiGH = diaChiGH;
		this.ngayLapHD = ngayLapHD;
		this.trangThaiThanhToan = trangThaiThanhToan;
	}
	public HoaDon() {
		super();
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public NhanVien getNV() {
		return NV;
	}
	public void setNV(NhanVien nV) {
		NV = nV;
	}
	public KhachHang getKH() {
		return KH;
	}
	public void setKH(KhachHang kH) {
		KH = kH;
	}
	public String getDiaChiGH() {
		return diaChiGH;
	}
	public void setDiaChiGH(String diaChiGH) {
		this.diaChiGH = diaChiGH;
	}
	public LocalDate getNgayLapHD() {
		return ngayLapHD;
	}
	public void setNgayLapHD(LocalDate ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}
	
	public boolean isTrangThaiThanhToan() {
		return trangThaiThanhToan;
	}
	public void setTrangThaiThanhToan(boolean trangThaiThanhToan) {
		this.trangThaiThanhToan = trangThaiThanhToan;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maHD);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHD, other.maHD);
	}
	
}