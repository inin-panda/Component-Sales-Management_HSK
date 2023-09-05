package entity;

import java.util.Objects;

public class NhaCungCap {
//	[maNhaCungCap] [varchar](6) NOT NULL,
//	[tenNhaCungCap] [nvarchar](50) NOT NULL,
//	[diaChi] [nvarchar](50) NOT NULL,
//	[soLuongSp] [int] NOT NULL,
	private String maNhaCungCap;
	private String tenNhaCungCap;
	private String diaChi;
	private int soLuongSp;
	
	public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String diaChi, int soLuongSp) {
		super();
		this.maNhaCungCap = maNhaCungCap;
		this.tenNhaCungCap = tenNhaCungCap;
		this.diaChi = diaChi;
		this.soLuongSp = soLuongSp;
	}

	public NhaCungCap(String maNhaCungCap) {
		super();
		this.maNhaCungCap = maNhaCungCap;
	}

	public NhaCungCap(String tenNhaCungCap, String diaChi, int soLuongSp) {
		super();
		this.tenNhaCungCap = tenNhaCungCap;
		this.diaChi = diaChi;
		this.soLuongSp = soLuongSp;
	}

	public NhaCungCap() {
		super();
	}

	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}

	public void setMaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}

	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}

	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public int getSoLuongSp() {
		return soLuongSp;
	}

	public void setSoLuongSp(int soLuongSp) {
		this.soLuongSp = soLuongSp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhaCungCap);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhaCungCap other = (NhaCungCap) obj;
		return Objects.equals(maNhaCungCap, other.maNhaCungCap);
	}
	
	
}
