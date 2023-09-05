create database QLLinhKien
go
USE [QLLinhKien]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 5/1/2023 9:33:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

--- Function 
CREATE FUNCTION AUTO_maKh()
RETURNS [VARCHAR](6)
AS
BEGIN
	DECLARE @ID VARCHAR(6)
	IF (SELECT COUNT(maKh) FROM KhachHang) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(maKh, 4)) FROM KhachHang
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'KH000' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 and @ID < 99 THEN 'KH00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 99 and @ID < 999 THEN 'KH0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 999 THEN 'KH' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END

GO
CREATE FUNCTION AUTO_maHD()
RETURNS [VARCHAR](10)
AS
BEGIN
	DECLARE @ID VARCHAR(10)
	IF (SELECT COUNT(maHD) FROM HoaDon) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(maHD, 8)) FROM HoaDon
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'HD0000000' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 and @ID < 99 THEN 'HD000000' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 99 and @ID < 999 THEN 'HD00000' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 999 and @ID < 9999 THEN 'HD0000' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9999 and @ID < 99999 THEN 'HD0000' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 99999 and @ID < 999999 THEN 'HD00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 999999 and @ID < 9999999 THEN 'HD0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9999999 THEN 'HD' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END

GO

CREATE FUNCTION AUTO_maLoaiLinhKien()
RETURNS [VARCHAR](7)
AS
BEGIN
	DECLARE @ID VARCHAR(7)
	IF (SELECT COUNT(maLoai) FROM LoaiLinhKien) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(maLoai, 3)) FROM LoaiLinhKien
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'LLK_00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 and @ID < 99 THEN 'LLK_0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 99 THEN 'LLK_' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END

GO
CREATE FUNCTION AUTO_maLinhKien()
RETURNS [VARCHAR](6)
AS
BEGIN
	DECLARE @ID VARCHAR(6)
	IF (SELECT COUNT(maLk) FROM LinhKien) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(maLk, 4)) FROM LinhKien
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'LK000' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 and @ID < 99 THEN 'LK00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 99 and @ID < 999 THEN 'LK0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 999 THEN 'LK' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
CREATE FUNCTION AUTO_maNhaCungCap()
RETURNS [VARCHAR](6)
AS
BEGIN
	DECLARE @ID VARCHAR(6)
	IF (SELECT COUNT(maNhaCungCap) FROM NhaCungCap) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(maNhaCungCap, 2)) FROM NhaCungCap
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'NCC_0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'NCC_' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END

GO
CREATE FUNCTION AUTO_maNhanVien()
RETURNS [VARCHAR](6)
AS
BEGIN
	DECLARE @ID VARCHAR(6)
	IF (SELECT COUNT(maNv) FROM NhanVien) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(maNv, 3)) FROM NhanVien
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'NV_00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 and @ID < 99 THEN 'NV_0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 99 THEN 'NV_' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END


GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[maHD] [varchar](10) NOT NULL,
	[maLk] [varchar](6) NOT NULL,
	[soLuong] [int] NOT NULL,
	[mucGiamGia] [Float](5)
	PRIMARY KEY (maHD, MaLK));
GO

/****** Object:  Table [dbo].[HoaDon]    Script Date: 5/1/2023 9:33:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHD] [varchar](10) NOT NULL,
	[maKh] [varchar](8) NOT NULL,
	[maNv] [varchar](6) NOT NULL,
	[diaChiGH] [nvarchar](50) NULL,
	[ngayLapHoaDon] [date] NOT NULL,	
	[trangThaiThanhToan] [bit] null
 CONSTRAINT [PK_HoaDon] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 5/1/2023 9:33:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKh] [varchar](8) NOT NULL,
	[tenKh] [nvarchar](Max) NOT NULL,
	[diaChi] [nvarchar](Max) NOT NULL,
	[sdt] [varchar](10) NOT NULL,
	[email] [nvarchar](max) NOT NULL,
	[gioiTinh] [bit] NOT NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[maKh] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LinhKien]    Script Date: 5/1/2023 9:33:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LinhKien](
	[maLk] [varchar](6) NOT NULL,
	[tenLk] [nvarchar](50) NOT NULL,
	[soLuong] [int] NULL,
	[ngaySx] [date] NULL,
	[gia] [float] NOT NULL,
	[chiTietLk] [nvarchar](MAX) NULL,
	[maNhaCungCap] [varchar](6) NOT NULL,
	[maLoai] [varchar](7) NOT NULL,
 CONSTRAINT [PK_LinhKien] PRIMARY KEY CLUSTERED 
(
	[maLk] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiLinhKien]    Script Date: 5/1/2023 9:33:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiLinhKien](
	[maLoai] [varchar](7) NOT NULL,
	[tenLoai] [nvarchar](50) NOT NULL,
	[soLuongLinhKien] [int] NOT NULL,
 CONSTRAINT [PK_LoaiLinhKien] PRIMARY KEY CLUSTERED 
(
	[maLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhaCungCap]    Script Date: 5/1/2023 9:33:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhaCungCap](
	[maNhaCungCap] [varchar](6) NOT NULL,
	[tenNhaCungCap] [nvarchar](100) NOT NULL,
	[diaChi] [nvarchar](500) NOT NULL,
	[soLuongSp] [int] NOT NULL,
 CONSTRAINT [PK_NhaCungCap] PRIMARY KEY CLUSTERED 
(
	[maNhaCungCap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 5/1/2023 9:33:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNv] [varchar](6) NOT NULL,
	[tenNv] [nvarchar](50) NOT NULL,
	[gioiTinh] [bit] NOT NULL,
	[namSinh] [date] NOT NULL,
	[sdt] [varchar](10) NULL,
	[diaChi] [nvarchar](50) NULL,
	[chucVu] [nvarchar](50) NOT NULL,
	[email] [nvarchar](50) NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[maNv] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 5/1/2023 9:33:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[tenTk] [nvarchar](20) NOT NULL,
	[matKhau] [nvarchar](20) NOT NULL,
	[chucVu] [nvarchar](50) NOT NULL,
	[maNv] [varchar](6) NOT NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[tenTk] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

--- create Foreign Key
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietHoaDon_LinhKien] FOREIGN KEY([maLk])
REFERENCES [dbo].[LinhKien] ([maLk])
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietHoaDon_HoaDon] FOREIGN KEY([maHD])
REFERENCES [dbo].[HoaDon] ([maHD])


GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_ChiTietHoaDon_LinhKien]
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_ChiTietHoaDon_HoaDon]

GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_KhachHang] FOREIGN KEY([maKh])
REFERENCES [dbo].[KhachHang] ([maKh])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_KhachHang]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_NhanVien] FOREIGN KEY([maNv])
REFERENCES [dbo].[NhanVien] ([maNv])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_NhanVien]
GO
ALTER TABLE [dbo].[LinhKien]  WITH CHECK ADD  CONSTRAINT [FK_LinhKien_LoaiLinhKien] FOREIGN KEY([maLoai])
REFERENCES [dbo].[LoaiLinhKien] ([maLoai])
GO
ALTER TABLE [dbo].[LinhKien] CHECK CONSTRAINT [FK_LinhKien_LoaiLinhKien]
GO
ALTER TABLE [dbo].[LinhKien]  WITH CHECK ADD  CONSTRAINT [FK_LinhKien_NhaCungCap] FOREIGN KEY([maNhaCungCap])
REFERENCES [dbo].[NhaCungCap] ([maNhaCungCap])
GO
ALTER TABLE [dbo].[LinhKien] CHECK CONSTRAINT [FK_LinhKien_NhaCungCap]
GO

ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_TaiKhoan_NhanVien] FOREIGN KEY([maNv])
REFERENCES [dbo].[NhanVien] ([maNv])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_TaiKhoan_NhanVien]
GO
--------------------------------------------------------------------------------------------------------
ALTER TABLE [dbo].[KhachHang] ADD  DEFAULT ([dbo].[AUTO_maKh]()) FOR [maKh]
GO 
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT ([dbo].[AUTO_maHD]()) FOR [maHD]
GO

ALTER TABLE  [dbo].[ChiTietHoaDon] WITH CHECK ADD  CONSTRAINT [FK_HoaDon_ChiTietHoaDon] FOREIGN KEY([maHD])
REFERENCES [dbo].[HoaDon]([maHD])
GO

ALTER TABLE [dbo].[LoaiLinhKien] ADD  DEFAULT ([dbo].[AUTO_maLoaiLinhKien]()) FOR [maLoai]
GO
ALTER TABLE [dbo].[NhaCungCap] ADD  DEFAULT ([dbo].[AUTO_maNhaCungCap]()) FOR [maNhaCungCap]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT ([dbo].[AUTO_maNhanVien]()) FOR [maNv]
GO
ALTER TABLE [dbo].[LinhKien] ADD  DEFAULT ([dbo].[AUTO_maLinhKien]()) FOR [maLk]

/*--------------------------------------------------------------------INSERT DATA----------------------------------------------------------------------------------------------------------*/
---------Insert Nhà cung cấp
GO 
INSERT INTO [dbo].[NhaCungCap] ( [tenNhaCungCap] , [diaChi] , [soLuongSp]) 
	VALUES (N'An Khang Computer ' , N'Số 25 Yên Lãng, Đống Đa, Hà Nội' , 200)
GO 
INSERT INTO [dbo].[NhaCungCap] ( [tenNhaCungCap] , [diaChi] , [soLuongSp]) 
	VALUES (N'FPT Shop',N'D1/1 Quốc lộ 1A (Ngay chợ Bình Chánh), Ấp 4, Xã Bình Chánh, Huyện Bình Chánh, TP. Hồ Chí Minh' , 20)
GO
INSERT INTO [dbo].[NhaCungCap] ( [tenNhaCungCap] , [diaChi] , [soLuongSp]) 
	VALUES (N'CellphoneS' , N'55B Trần Quang Khải, P. Tân Định, Q. 1' , 100)
GO
INSERT INTO [dbo].[NhaCungCap] ( [tenNhaCungCap] , [diaChi] , [soLuongSp]) 
	VALUES (N'Hangchinhhieu.vn' , N'184/41 Nguyễn Xí, Phường 26, Q. Bình Thạnh, HCM' , 100)


--------Insert loại linh kiện
GO
INSERT INTO [dbo].[LoaiLinhKien] ([tenLoai], [soLuongLinhKien])
     VALUES (N'CPU – Bộ vi xử lý', 100 )
GO
INSERT INTO [dbo].[LoaiLinhKien] ([tenLoai], [soLuongLinhKien])
     VALUES (N'Mainboard – Bo mạch chủ', 10)
GO
INSERT INTO [dbo].[LoaiLinhKien] ([tenLoai], [soLuongLinhKien])
     VALUES (N'Ram – Bộ nhớ trong', 20)
GO
INSERT INTO [dbo].[LoaiLinhKien] ([tenLoai], [soLuongLinhKien])
     VALUES (N'VGA – Card đồ hoạ', 10)
GO
INSERT INTO [dbo].[LoaiLinhKien] ([tenLoai], [soLuongLinhKien])
     VALUES (N'Ổ cứng', 5)
GO
INSERT INTO [dbo].[LoaiLinhKien] ([tenLoai], [soLuongLinhKien])
     VALUES (N'PSU – Nguồn máy tính ', 10)
GO
INSERT INTO [dbo].[LoaiLinhKien] ([tenLoai], [soLuongLinhKien])
     VALUES (N'Cooling – Tản nhiệt', 3)
GO
INSERT INTO [dbo].[LoaiLinhKien] ([tenLoai], [soLuongLinhKien])
     VALUES (N'Phím, chuột và các phụ kiện khác', 54)
GO


----------------Insert Linh kiện
GO
INSERT INTO [dbo].[LinhKien] ([tenLk] ,[soLuong] ,[ngaySx]  ,[gia] ,[chiTietLk] ,[maNhaCungCap] ,[maLoai])
	VALUES (N'CPU Intel Core i5 10400' ,5 ,'2022-07-11'  ,3000000 
	,
	N'Hãng CPU	Intel
Loại CPU	Core i5 - 10400
Tên thế hệ	Comet Lake
Socket	LGA 1200
Số nhân	6
Số luồng	12
Tốc độ xử lý	Cơ bản 2.9 GHz, Tối đa 4.5 GHz
Cache	12 MB
TDP	65 W
Thời gian bảo hành (tháng)	36'
	,'NCC_01' ,'LLK_001')
GO
INSERT INTO [dbo].[LinhKien] ([tenLk] ,[soLuong] ,[ngaySx]  ,[gia] ,[chiTietLk] ,[maNhaCungCap] ,[maLoai])
	VALUES (N'CPU Intel Core i5-11400 + Quạt' ,10 ,'2022-07-11'  ,4000000
	,N'
Hãng CPU	Intel
Loại CPU	Core i5 - 11400
Tên thế hệ	Rocket Lake
Socket	LGA 1200
Số nhân	6
Số luồng	12
Tốc độ xử lý	Cơ bản 2.6 GHz, Tối đa 4.4 GHz
Cache	12 MB
TDP	65 W
Thời gian bảo hành (tháng)	36'
	,'NCC_01' ,'LLK_001')
GO
INSERT INTO [dbo].[LinhKien] ([tenLk] ,[soLuong] ,[ngaySx]  ,[gia] ,[chiTietLk] ,[maNhaCungCap] ,[maLoai])
	VALUES (N'SWTA7722-L' ,5 ,'2022-07-11'  ,10000 
	,''
	,'NCC_01' ,'LLK_002')
GO
INSERT INTO [dbo].[LinhKien] ([tenLk] ,[soLuong] ,[ngaySx]  ,[gia] ,[chiTietLk] ,[maNhaCungCap] ,[maLoai])
	VALUES ('
SSD WD 480 GB M.2 NVMe' ,5 ,'2022-07-11'  ,900000
	,'Kiểu ổ cứng	SSD
Dung lượng ổ cứng	480 GB
Tốc độ đọc/ghi	Đọc 2400 MB/s - Ghi 1650 MB/s
Chuẩn kết nối	M2 NVME
Thương hiệu	WD
Xuất xứ	Trung QuốcMalaysia
Thời gian bảo hành (tháng)	36'
	,'NCC_01' ,'LLK_004')
GO
INSERT INTO [dbo].[LinhKien] ([tenLk] ,[soLuong] ,[ngaySx]  ,[gia] ,[chiTietLk] ,[maNhaCungCap] ,[maLoai])
	VALUES (N'CPU Intel Core i3-10100F + Quạt ' ,5 ,'2022-07-11'  ,1999000 
	,
	N'Thông số kỹ thuật
Hãng CPU	Intel
Loại CPU	Core i3 - 10100F
Tên thế hệ	Comet Lake
Socket	LGA 1200
Số nhân	4
Số luồng	8
Tốc độ xử lý	Tối đa 4.3 GHz, Cơ bản 3.6 GHz
Cache	6 MB
TDP	65 W
Thời gian bảo hành (tháng)	36'
	,'NCC_01' ,'LLK_001')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk] ,[soLuong] ,[ngaySx]  ,[gia] ,[chiTietLk] ,[maNhaCungCap] ,[maLoai])
	VALUES (N'CPU Intel Core i5-11400F + Quạt' ,10 ,'2022-07-11'  ,3399000 
	,
	N'Hãng CPU	Intel
Loại CPU	Core i5 - 11400F
Tên thế hệ	Rocket Lake
Socket	LGA 1200
Số nhân	6
Số luồng	12
Tốc độ xử lý	Cơ bản 2.6 GHz, Tối đa 4.4 GHz
Cache	12 MB
TDP	65 W
Thời gian bảo hành (tháng)	36'
	,'NCC_01' ,'LLK_001')
GO
INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'ASUS Dual GeForce RTX 3050 V2 OC Edition 8GB',30,'2023-3-5',6500000,N'GPU:	NVIDIA® GeForce RTX™ 3050
Chuẩn Bus:	PCI Express 4.0
OpenGL:	OpenGL®4.6
Bộ nhớ:	8GB GDDR6 (14 Gbps, 128-bit)
Engine Clock:	OC mode : 1852 MHz (Boost Clock)
Gaming mode : 1822 MHz (Boost Clock)
Số lượng đơn vị xử lý:	2560
Độ phân giải:	7680 x 4320
Cổng kết nối:	
1 x Native HDMI 2.1
1 x Native DVI-D
1 x Native DisplayPort 1.4a
Hỗ trợ HDCP (2.3)
Hiển thị tối đa:	3
Hỗ trợ NVlink/ Crossfire:	Không
Phụ kiện:
1 x Collection card
1 x HDSD
Phần mềm:	ASUS GPU Tweak II & GeForce Game Ready Driver & Studio Driver
Kích thước:	200 x 123 x 38 mm
PSU đề nghị:	550W
Đầu cấp nguồn:	1 x 8-pin
Slot:	2 Slot','NCC_04','LLK_004')
GO
INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'ASUS ROG Strix GeForce RTX 4090 ',20,'2022-3-5',64900000,N'hân đồ họa 	NVIDIA GeForce RTX 4090
Bus tiêu chuẩn	PCI Express 4.0
Xung nhịp	Chế độ OC: 2640 MHz
Chế độ mặc định: 2610 MHz (Boost Clock)
Nhân CUDA	16384
Tốc độ bộ nhớ	21 Gbps
OpenGL	OpenGL 4.6
Bộ nhớ Video	24 GB GDDR6X
Giao thức bộ nhớ	384-bit
Độ phân giải	Độ phân giải tối đa 7680 x 4320
Giao thức	
Có x 2 (Native HDMI 2.1)
Có x 3 (Native DisplayPort 1.4a)
Hỗ trợ HDCP (2.3)
Số lượng màn hình tối đa hỗ trợ	4
Hỗ trợ NVlink/ Crossfire 	Không
Phụ kiện	1 x Thẻ sưu tập
1 x Hướng dẫn nhanh
1 x Cáp chuyển đổi
1 x Giá đỡ cạc đồ họa ROG
1 x Móc & vòng lặp ROG Velcro
1 x Thẻ cảm ơn
Phần mềm	ASUS GPU Tweak III & GeForce Game Ready Driver & Studio Driver: vui lòng tải xuống tất cả phần mềm từ trang web hỗ trợ.
Kích thước	357,6 x 149,3 x 70,1mm
PSU kiến nghị	1000W
Kết nối nguồn	1 x 16 pin
Khe cắm	3.5
AURA SYNC	ARGB','NCC_04','LLK_004')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'MSI Radeon RX 6600 ARMOR 8G',100,'2021-10-10',99900000,N'Thương hiệu:	MSI
Model:	Radeon™ RX 6600 MECH 2X 8G
Core Clock:	Boost: Up to 2491 MHz
Game: Up to 2044 MHz
Chipset:	Radeon™ RX 6600 XT
Giao thức kết nối:	PCI Express® 4.0 X 8
Nhân xử lý:	1792
Bộ nhớ:	Dung lượng: 8GB
Loại: GDDR6
Tốc độ: 14 Gbps
Giao thức: 128-bit
Độ phân giải kỹ thuật số tối đa:	7680 x 4320
Số màn hình hỗ trợ xuất tối đa:	4
Cổng xuất hình:	DisplayPort x 3 (v1.4)
HDMI x 1 (Supports 4K@120Hz/8K@60Hz and VRR as specified in HDMI 2.1)
Phiên bản DirectX:	12 API
Phiên bản OpenGL:	4.6
Kích thước:	238 x 128 x 39 mm
Nguồn đề nghị:	500W
Đầu cấp nguồn:	1 x 8-pin
Tản nhiệt:	2 quạt','NCC_01','LLK_004')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Palit GeForce GT 730 2G',40,'2019-03-15',1190000,N'Thương hiệu: PALIT
Model:	Palit GeForce GT 730 2G
Hỗ trợ BUS:	PCI-E 2.0 x 8
Nhân CUDA:	384
Bộ nhớ	2G DDR3 64-bit
Số lượng bộ nhớ	:2048 MB
Đồng hồ đồ họa:	902
Độ phân giải kỹ thuật số tối đa"	2560 x 1600
Độ phân giải VGA tối đa: 2048 x 1536
Chiều cao:2 Slot
Kích thước bảng:	115mm x 69mm
DVI	Dual-Link DVI-D
Nguồn tối thiểu: 300 W
Cổng xuất hình:VGA,HDMI,DirectX	12,OpenGL4.5','NCC_01','LLK_004')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'MSI GeForce RTX 3060 Ti VENTUS 3X OC (LHR)',30,'2023-3-5',6500000,N'GRAPHICS PROCESSING UNIT:NVIDIA® GeForce RTX™ 3060 Ti 
INTERFACE: PCI Express® Gen 4
CORES:4864
CORE CLOCKS:Boost: 1695 MHz
MEMORY SPEED:14 Gbps
MEMORY:8GB GDDR6
MEMORY BUS:256-bit
DisplayPort x 3 (v1.4a) / HDMI 2.1 x 1
HDCP SUPPORT:Y
POWER CONSUMPTION:200W
POWER CONNECTORS:8-pin x 1
RECOMMENDED PSU:650W','NCC_01','LLK_004')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'MSI Radeon RX 6600 XT GAMING X 8G',65,'2020-09-10',14490000,N'Nhân đồ họa	AMD Radeon™ RX 6600 XT
Giao thức kết nối	PCI Express® Gen 4
Nhân xử lý	2048
Xung nhịp nhân	Boost: Up to 2607 MHz
Game: Up to 2428 MHz
Bộ nhớ	Dung lượng: 8GB
Loại: GDDR6
Tốc độ: 16 Gbps
Giao thức: 128-bit
Độ phân giải kỹ thuật số tối đa	7680 x 4320
Số màn hình hỗ trợ xuất tối đa	4
Cổng xuất hình 	DisplayPort x 3 (v1.4)
HDMI x 1 (Supports 4K@120Hz/8K@60Hz and VRR as specified in HDMI 2.1)','NCC_02','LLK_004')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'MSI GeForce RTX 4090 GAMING TRIO 24G',40,'2021-12-28',49990000,N'
Nhân đồ họa:NVIDIA GeForce RTX 4090
Giao thức kết nối:PCI Express® Gen 4
Xung nhịp:Nhân CUDA	16384
Tốc độ bộ nhớ:21 Gbps
Bộ nhớ:24GB GDDR6X
Bus bộ nhớ:384-bit
Cổng xuất hình:DisplayPort x 3 (v1.4a)
HDMI x 1 ','NCC_02','LLK_004')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'GIGABYTE GeForce RTX 4080 EAGLE 16G',10,'2022-01-02',29990000,N'Nhân đồ họa	GeForce RTX™ 4080
Xung nhịp:2505 MHz
Nhân CUDA:9728
Tốc độ bộ nhớ:22.4 Gbps
Dung lượng bộ nhớ:16 GB
Loại bộ nhớ:GDDR6X
Bus bộ nhớ:256 bit
Bus card:PCI-E 4.0 x 16','NCC_03','LLK_004')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'MSI GeForce RTX 4070 Ti GAMING X TRIO WHITE 12G',20,'2020-3-5',29990000,N'
Nhân đồ họa:NVIDIA® GeForce RTX™ 4070 Ti
Giao thức kết nối:PCI Express® Gen 4
Xung nhịp:Extreme Performance: 2760 MHz (MSI Center)
Boost: 2745 MHz (GAMING & SILENT Mode)
Nhân CUDA:7680 đơn vị
Tốc độ bộ nhớ:21 Gbps
Bộ nhớ:12GB GDDR6X
Bus bộ nhớ:192-bit','NCC_03','LLK_004')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'MANLI GeForce GTX 1050 Ti 4GB GDDR5',35,'2017-7-30',3490000,N'Chipset:	
GeForce® GTX 1050 Ti
Giao thức kết nối:	PCI Express 3.0 x 4
Nhân xử lý:	768
Xung nhịp nhân:	1290 MHz - Boost Clock:  1392 MHz
Bộ nhớ:	Dung lượng: 4GB
Loại: GDDR5
Tốc độ: 7008 MHz
Bus: 128 bit
Băng thông: 112.1 GB/s
Độ phân giải kỹ thuật số tối đa:4096x2160@60Hz','NCC_03','LLK_004')
GO
/****** PSU NÒ  ******/
INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Nguồn máy tính mini Jetek A200M',300,'2011-4-4',390000,N'Công suất tối đa:200W
Tương thích:	Micro ATX
Hiệu suất:72%
Hỗ trợ	Tối đa: Intel core i5 và AMD
Quạt làm mát:1 x 8 cm
Nguồn đầu vào:170 - 240VAC
Tiêu chuẩn an toàn:CB, UVP, OPP, OVP
Số cổng cắm:2 x SATA, 2 x Peripheral (4-pin), 1 x 24-pin Main, 1 x 4-pin CPU','NCC_03','LLK_006')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Nguồn máy tính Xigmatek X-POWER III 350',100,'2016-10-03',450000,N'Công suất tối đa:350W
Số cổng cắm:20+4pin * 1 / CPU 4+4pin * 1 / PCI-E 6+2pin * 1 / SATA * 3 / Molex 4pin * 2
Quạt làm mát:1 x 120 mm
Kích thước:86 x 150 x 140 mm','NCC_03','LLK_006')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Nguồn Gigabyte P550B - 80 Plus Bronze',100,'2016-10-03',450000,N'Công suất tối đa:550W
Số cổng cắm	:1 x 24-pin Main, 1 x 8-pin (4+4) EPS, 1 x 8-pin (6+2) PCIE, 6 x SATA, 3 x Peripheral (4-pin)
Hiệu suất:80 Plus Bronze
Quạt làm mát:1 x 120 mm
Nguồn đầu vào:100 - 240VAC','NCC_02','LLK_006')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Nguồn Corsair CV550 - 80 Plus Bronze',80,'2019-03-13',1190000,N'Total Power:550 Watts
80 PLUS Efficiency:Bronze
AC Input Rating:100 - 240V
Input Current:10A-5A
Frequency:47 - 63Hz
PSU Form Factor	ATX
Modular:No','NCC_02','LLK_006')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Nguồn Corsair RM850X - 80 Plus Gold - Full Modular',20,'2022-07-10',3890000,N'
Thương hiệu:Corsair
Công suất tối đa:850 W
Số cổng cắm:Full-Modular
Hiệu suất:80 Plus Gold
Quạt làm mát:135 mm
Nguồn đầu vào:100 - 240 V','NCC_02','LLK_006')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Nguồn NZXT C1000W - 80 Plus Gold - Full Modular',30,'2021-04-25',4490000,N'
Kết nối đầu ra:24-pin ATX power
Cổng kết nối:24-pin ATX power
Điện áp đầu vào:100-240Vac
Hoạt động:PF Correction
Thông số quạt:Dimensions 135 x 135 x 25mm','NCC_02','LLK_006')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Nguồn SilverStone DA850 - 80 Plus Gold',65,'2020-06-12',2490000,N'
Công suất tối đa:850 W
Loại cổng cắm:Full-Modular
Hiệu suất:87% ~ 90% (at 20% ~ 100% loading)
Chứng nhận:	80 Plus Gold
Quạt làm mát:Silent 120mm FDB fan
Nguồn đầu vào:100 - 240 V','NCC_01','LLK_006')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Nguồn MSI MPG A1000G PCIe5 - 80 Plus Gold',25,'2022-07-05',5990000,N'
Model:MSI MPG A1000G - 80 Plus Gold - Full modular
Màu sắc:Đen
Chuẩn kích thước:ATX
Quạt làm mát;135 mm
Kích thước nguồn;150mm x 150mm x 86mm
Công suất tối đa:1000W
Điện áp đầu vào	100~240 Vac','NCC_04','LLK_006')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Nguồn máy tính Jetek J400',50,'2015-04-22',500000,N'
Công suất tối đa:400W
Số cổng cắm:	1 x 24-pin Main, 1 x 8-pin (4+4) CPU, 4 x SATA, 2 x Peripheral (4-pin)
Quạt làm mát:1 x 120 mm
Nguồn đầu vào:230VAC
PFC:Active
Chứng nhận bảo vệ:OPP, OVP, UVP, SCP
Kích thước:86 x 150 x 140 mm','NCC_03','LLK_006')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Nguồn Corsair CV750 - 80 Plus Bronze',40,'2017-10-05',1790000,N'
Tổng công suất:750 W
Hiệu quả 80 PLUS:Bronze
Đánh giá đầu vào AC:100 - 240V
Đầu vào hiện tại:10A-5A
Tính thường xuyên:47 - 63Hz
Yếu tố hình thức PSU:ATX','NCC_01','LLK_006')
GO
/****** Cooling NÒ  ******/
INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Tản nhiệt DEEPCOOL AG400 ARGB',300,'2020-02-15',790000,N'
Thương hiệu: Deepcool
Model:	AG400 ARGB
Loại tản nhiệt khí:	Single Tower
Socket CPU hỗ trợ:	Intel LGA1700/1200/1151/1150/1155 AMD AM5 / AM4 
Ống dẫn nhiệt:Ø6 mm×4 pcs
Tốc độ quạt:500 ~ 2000 vòng / phút +-10%','NCC_04','LLK_007')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Tản nhiệt ID COOLING SE-214-XT ARGB WHITE',100,'2018-10-07',590000,N'
TDP:180W
Kích thước tổng	124 × 72 × 150mm (L×W×H)
Chất liệu lõi tản nhiệt kim loại:4×Ф6mm Heatpipe(Direct Touch) + Aluminum Fin
Trọng lượng:850g
Kích thước quạt:120 × 120 × 25mm
Tốc độ quạt:500±200~1500±10%RPM(PWM)(ARGB)
Lượng khí lưu thông tối đa: 68.2CFM
Áp suất tối đa:1.87mmH2O','NCC_02','LLK_007')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Tản nhiệt Noctua NH - L9x65',80,'2014-05-11',1650000,N'
Thương hiệu:Noctua
Loại tản nhiệt:Tản nhiệt khí 
Tương thích CPU:Intel LGA1150, LGA1151, LGA1155, LGA1156, LGA1200, LGA1700 AMD AM2, AM2+, AM3, AM3+, FM1, FM2, FM2+ AM4, AM5
Số lượng quạt:1
Tốc độ quạt:1800 - 2500 RPM
Kích thước quạt:92x92x14mm
Kích thước tổng thể:HxWxD (Không fan) 51x95x95mm, HxWxD (Kèm FAN) 65x95x95mm','NCC_03','LLK_007')
GO
/****** Phụ Kiện Nò - Bàn Phím ******/
INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Bàn Phím Cơ Vortex PC66 (68 Key)',20,'2020-06-10',2790000,N'
Thương hiệu:Vortex
Model:Vortex Vortex PC66 (68%)
Kết nối:Có dây / Bluetooth 5.1 / 2.4GHz
Thiết kế:68 Phím
Kích thước (L x W x H):33 x 17 x 4 cm 
Trọng lượng:	850 (g)','NCC_03','LLK_008')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Bàn phím IKBC CD87HR',20,'2020-06-10',1790000,N'
Thương hiệu:iKBC
Model:CD87HR
Kết nối:Có dây
Thiết kế:87 Phím
Switch:Gateron G Pro Blue / Brown / Red / Yellow / Silver 
Led	RGB','NCC_01','LLK_008')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Bàn phím Mistel X8 Nimitz',15,'2018-09-22',2100000,N'
Kết nối:USB Type-C
Loại bàn phím:Fullsize (108 phím)
Keycap:PBT Double Shot
Polling Rate:1000hz
Chất liệu bàn phím:ABS
Kích thước	(L) 442 x (W) 138 x (H) 42 mm
Trọng lượng	1100g','NCC_04','LLK_008')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Bàn phím cơ AKKO PC98B Plus AIR',5,'2020-01-12',2490000,N'
Thương hiệu:Akko
Thiết kế:98 Phím
Pin:3000 mah – Có chỗ để USB Receiver 2.4Ghz ở đáy phím
Kích thước:382 x 134 x 40 mm
Stab pre-lubed:Được tinh chỉnh sẵn
Trọng lượng~ 1.2kg
Keycap:PBT Double-Shot, OSA profile
Switch:AKKO CS Switch (AIR)','NCC_03','LLK_008')
GO	

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Bàn phím cơ AKKO 5075B Plus Naruto',15,'2022-10-10',2990000,N'
Thương hiệu:AKKO
Series/Model:AKKO 5075B Plus Naruto
Layout:75%
Kích thước:335 x 146 x 42 mm 
Trọng lượng~ 1.1Kg
Switch:AKKO CS Switch – Crystal
Phần mềm:Tích hợp AKKO Cloud Driver','NCC_03','LLK_008')
GO	
/****** Phụ Kiện Nò- Chuột  ******/
INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Chuột Havit MS1028',150,'2022-11-01',220000,N'
Kích thước:125 x 71 x 41mm.
Trọng lượng:111g
Thiết kế:Công thái học
Kết nối:Có dây
Đèn LED:RGB
DPI:1200-1800-2400-3200-4800-7200
Tuổi thọSwitch:3 triệu lần nhấn
Chiều dài cáp:1.5m','NCC_02','LLK_008')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Chuột Logitech G102 Lightsync RGB White',100,'2018-02-22',400000,N'
Tên sản phẩm;Logitech G102 Lightsync RGB
Thiết kế:Đối xứng - Ambidextrous
Mắt đọc:	Mercury Sensor
Điểm ảnh trên 1 inch: (DPI)	8000
IPS:	200
Gia tốc:	30g
Tần số phản hồi:	1000Hz','NCC_02','LLK_008')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Chuột Gaming Cougar Minos XT',100,'2017-03-23',500000,N'
Hãng sản xuất:Cougar 
Model:Minos XT
Màu:Đen
Cảm biến:Avago 3050 
Độ phân giải:4000 dpi
Chiều dài cable: 	1.8m
Kích thước:	125 x 68 x 38 mm
Khối lượng:	96 g','NCC_02','LLK_008')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Chuột Razer Basilisk Ultimate',100,'2023-01-15',2200000,N'
Model:Razer Basilisk Ultimate
Cảm biến: Razer Focus+
Kết nối: Có dây/ Công nghệ không dây Razer Hyperspeed độc quyền
DPI:20.000 dpi
Switch:Razer Switch ( 70 triệu lần nhấn )
IPS:650','NCC_01','LLK_008')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Chuột Logitech G Pro X Superlight Wireless White',100,'2022-08-20',2990000,N'
Model:G Pro X Superlight Wireless White
Màu:White
Tần suất gửi tín hiệu USB:1000 Hz (1ms)
Bộ vi xử lý:32-bit ARM
Chuyển động liên tục:70h
Tương thích:POWERPLAY
Công nghệ không dây:LIGHTSPEED','NCC_03','LLK_008')
GO
/****** Phụ Kiện Nò- Tai Nghe  ******/
INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Tai nghe Corsair HS70 Pro Wireless Carbon',60,'2023-02-11',1790000,N'
Kiểu tai nghe:	Over-ear
Kiểu kết nối:	Không dây (USB Wireless Receiver)
Thời lượng Pin:	16 giờ
Phạm vi tai nghe không dây:	40ft ( 12.2m)
Loại dây:USB charging cable 1.8m
Tần số:	Tai nghe: 20Hz - 20000Hz
Micro: 100Hz - 10000Hz
Độ nhạy:Tai nghe: 111dB (+/- 3dB)
Micro: -40dB (+/- 3dB)
Chất lượng âm thanh:7.1 Surround','NCC_02','LLK_008')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Tai nghe Havit H2008D',60,'2017-12-07',590000,N'
Thương hiệu :Havit
Microphone:Có
Trở kháng:64±15%Ω
Driver:50mm
Độ nhạy:115dB ± 3dB
Tần số:20 hz - 20khz
Chiều dài dây:1.7m','NCC_02','LLK_008')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Tai nghe Logitech G735 Off White',20,'2022-09-02',3990000,N'
Series/Model:Logitech G735 Off White
Kiểu tai nghe:Over-ear
Kiểu kết nối:Không dây LIGHTSPEED / Bluetooth
Trong lượng:273g
Driver:40 mm
Độ nhạy tần số: 20 Hz-20 KHz','NCC_01','LLK_008')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Tai nghe Logitech G331',100,'2015-04-27',990000,N'
Series/Model:G331
Kiểu tai nghe:Over-ear
Kiểu kết nối:Có dây
LED:Không
Chuẩn kết nối:3.5mm 
Microphone:Có thể gấp gọn khi không sử dụng
Trở kháng: 1 kHz 32Ohm
Tần số:20Hz - 20KHz','NCC_03','LLK_008')
GO

INSERT INTO [dbo].[LinhKien] ([tenLk],[soLuong],[ngaySx],[gia],[chiTietLk],[maNhaCungCap],[maLoai])  VALUES (N'Tai nghe gaming DareU EH925S Pink RGB',35,'2020-06-10',890000,N'
Model:DareU EH925S RGB
Chuẩn cắm hỗ trợ:USB
Thiết kế:Tai nghe Over Ear
Microphone:	Có
Đệm tai:Da mềm
Trở kháng:32 Ohm
Tần số:	20 Hz - 20 KHz
Kích cỡ màng loa:	53mm
Trọng lượng:	300 + 10g
Dây:2.0m','NCC_04','LLK_008')
GO




-------------Insert Khách hàng
GO
INSERT INTO [dbo].[KhachHang]
           ( [tenKh],[diaChi], [sdt], [email], [gioiTinh])
     VALUES
           (N'Nguyễn Thành An', N'Gò Vấp', '0123456789', 'an2002@gmail.com', 1)	   
GO
INSERT INTO [dbo].[KhachHang]
           ( [tenKh],[diaChi], [sdt], [email], [gioiTinh])
     VALUES
           (N'Bùi Thị Yến Yến', N'12, Nguyễn Văn Bảo, Gò Vấp', '0123456798', 'yen2002@gmail.com', 0)	   
GO
INSERT INTO [dbo].[KhachHang]
           ( [tenKh],[diaChi], [sdt], [email], [gioiTinh])
     VALUES
           (N'Hà Huy hùng', N'Nguyễn Văn Nghi', '0123456779', 'hung2002@gmail.com', 1)		   
GO
INSERT INTO [dbo].[KhachHang]
           ( [tenKh],[diaChi], [sdt], [email], [gioiTinh])
     VALUES
           (N'Nguyễn Tuấn Hùng', N'Ở nhà', '013423425', 'hung2002@gmail.com', 1)		   
GO	   
INSERT INTO [dbo].[KhachHang]
           ( [tenKh],[diaChi], [sdt], [email], [gioiTinh])
     VALUES
           (N'Nguyễn Văn B', N'home', '0134212425', 'van2002@gmail.com', 1)	   
GO		   
INSERT INTO [dbo].[KhachHang]( [tenKh],[diaChi], [sdt], [email], [gioiTinh])
     VALUES(N'Nguyễn Văn B', N'Mỹ', '0312342354', 'van2002@gmail.com', 1)
GO	
INSERT INTO [dbo].[KhachHang] ( [tenKh],[diaChi], [sdt], [email], [gioiTinh])
     VALUES (N'Nguyễn Thị B', N'Lào', '0912342345', 'thi2002@gmail.com', 0)
GO	
INSERT INTO [dbo].[KhachHang] ( [tenKh],[diaChi], [sdt], [email], [gioiTinh])
     VALUES (N'Nguyễn Ngọc Lan', N'Sơn La', '0510167345', 'lan2002@gmail.com', 0)
GO	

INSERT INTO [dbo].[KhachHang] ( [tenKh],[diaChi], [sdt], [email], [gioiTinh])
     VALUES (N'Nguyễn Hào', N'Sơn La', '0312506345', 'hao2002@gmail.com', 1)
GO	
INSERT INTO [dbo].[KhachHang] ( [tenKh],[diaChi], [sdt], [email], [gioiTinh])
     VALUES (N'Lê Ngọc Hoa', N'Thanh Hóa', '0810967345', 'hoa2002@gmail.com', 1)
GO	
INSERT INTO [dbo].[KhachHang] ( [tenKh],[diaChi], [sdt], [email], [gioiTinh])
     VALUES (N'Đỗ Trung Hiếu', N'Đà Nẵng', '0710967345', 'hieu2002@gmail.com', 1)
GO	
INSERT INTO [dbo].[KhachHang]
           ( [tenKh],[diaChi], [sdt], [email], [gioiTinh])
     VALUES
           (N'Nguyễn Thị B', N'home', '0421456798', 'thi2002@gmail.com', 0)

-----------------insert Nhân viên
GO	
INSERT INTO NhanVien(tenNv, gioiTinh, namSinh, sdt, diachi, chucVu,email)VALUES(N'Hà Huy Hùng',0 ,'2002-03-30', '0929635572', N'F5 Dương Quảng Hàm','NV_NHANSU','hungro3270@gmail.com');
GO	
INSERT INTO NhanVien(tenNv, gioiTinh, namSinh, sdt, diachi, chucVu,email)VALUES(N'Nguyễn Thành An',1 ,'2002-01-01', '0353227722', N'TimeCity','NV_BANHANG','kazuto@gmail.com');
GO	
INSERT INTO NhanVien(tenNv, gioiTinh, namSinh, sdt, diachi, chucVu,email)VALUES(N'Tuấn Hùng',0 ,'2002-03-30', '0708335141', N'332 Tôn Đức Thắng Bình Thạnh','NV_KETOAN','tuanhungprovip@gmail.com');
GO	
INSERT INTO NhanVien(tenNv, gioiTinh, namSinh, sdt, diachi, chucVu,email)VALUES(N'Bùi Thị Yến Yến',1 ,'2002-11-04', '0913963128', 'KTX','QL','conyenconcon89@gmail.com');	
GO
INSERT INTO NhanVien(tenNv, gioiTinh, namSinh, sdt, diachi, chucVu,email)VALUES(N'Sơn Tùng',0 ,'1994-05-07', '0353660771', N'HCM','NV_NHANSU','sontungmtp@gmail.com');
GO	
INSERT INTO NhanVien(tenNv, gioiTinh, namSinh, sdt, diachi, chucVu,email)VALUES(N'Trần Thanh Thiện Bảo',0 ,'1993-10-11', '0737009332', N'TimeCity','NV_BANHANG','bray@gmail.com');
GO	
INSERT INTO NhanVien(tenNv, gioiTinh, namSinh, sdt, diachi, chucVu,email)VALUES(N'Trần Huyền My',1 ,'2000-03-23', '0912665987', N'Chung cư SkyHouse Bình Thạnh','NV_KETOAN','amee2000@gmail.com');
GO	
INSERT INTO NhanVien(tenNv, gioiTinh, namSinh, sdt, diachi, chucVu,email)VALUES(N'Phùng Thanh Độ',0 ,'1989-09-12', '0913963127', N'Đống Đa, Hà Nội','QL','mixigamming@gmail.com');


------------Insert Tài khoản
GO
INSERT INTO TaiKhoan(tenTk,matKhau,chucVu,maNv)VALUES('nhansu','nhansu','NV_NHANSU','NV_001');
GO
INSERT INTO TaiKhoan(tenTk,matKhau,chucVu,maNv)VALUES('kazuto','tayhovippro','NV_BANHANG','NV_002');
GO
INSERT INTO TaiKhoan(tenTk,matKhau,chucVu,maNv)VALUES('ketoan','ketoan','NV_KETOAN','NV_003');
GO
INSERT INTO TaiKhoan(tenTk,matKhau,chucVu,maNv)VALUES('admin','admin','QL','NV_004');
GO
INSERT INTO TaiKhoan(tenTk,matKhau,chucVu,maNv)VALUES('sontungmtp','123456aa','NV_NHANSU','NV_005');
GO
INSERT INTO TaiKhoan(tenTk,matKhau,chucVu,maNv)VALUES('braykhongkhoc','bray3070','NV_BANHANG','NV_006');
GO
INSERT INTO TaiKhoan(tenTk,matKhau,chucVu,maNv)VALUES('ameecute','1234cute','NV_KETOAN','NV_007');
GO
INSERT INTO TaiKhoan(tenTk,matKhau,chucVu,maNv)VALUES('mixigaming','mixi1989','QL','NV_008');

Go
INSERT INTO [dbo].[HoaDon]([maKh],[maNv],[diaChiGH],[ngayLapHoaDon],[trangThaiThanhToan]) VALUES('KH0001','NV_004',N'Nguyễn văn thụ' ,'2023-05-05',1)
GO
INSERT INTO [dbo].[HoaDon]([maKh],[maNv],[diaChiGH],[ngayLapHoaDon],[trangThaiThanhToan]) VALUES('KH0002','NV_004',N'Hà Nội' ,'2023-05-05',1)
GO
INSERT INTO [dbo].[HoaDon]([maKh],[maNv],[diaChiGH],[ngayLapHoaDon],[trangThaiThanhToan]) VALUES('KH0003','NV_004',N'Nguyễn văn Bảo' ,'2023-05-05',1)
GO
INSERT INTO [dbo].[HoaDon]([maKh],[maNv],[diaChiGH],[ngayLapHoaDon],[trangThaiThanhToan]) VALUES('KH0004','NV_004',N'Nguyễn Nghi' ,'2023-05-05',1)
GO
INSERT INTO [dbo].[HoaDon]([maKh],[maNv],[diaChiGH],[ngayLapHoaDon],[trangThaiThanhToan]) VALUES('KH0005','NV_004',N'Nguyễn văn thụ' ,'2023-05-05',1)
Go
INSERT INTO [dbo].[HoaDon]([maKh],[maNv],[diaChiGH],[ngayLapHoaDon],[trangThaiThanhToan]) VALUES('KH0006','NV_004',N'Nguyễn văn thụ' ,'2023-05-05',1)
GO
INSERT INTO [dbo].[HoaDon]([maKh],[maNv],[diaChiGH],[ngayLapHoaDon],[trangThaiThanhToan]) VALUES('KH0007','NV_001',N'Hà Nội' ,'2023-05-05',1)
GO
INSERT INTO [dbo].[HoaDon]([maKh],[maNv],[diaChiGH],[ngayLapHoaDon],[trangThaiThanhToan]) VALUES('KH0008','NV_002',N'12, Nguyễn Văn Bảo, Phường 4, Gò Vâp, TPHCM' ,'2023-05-05',1)
GO
INSERT INTO [dbo].[HoaDon]([maKh],[maNv],[diaChiGH],[ngayLapHoaDon],[trangThaiThanhToan]) VALUES('KH0009','NV_002',N'Nguyễn Nghi' ,'2023-05-05',1)
GO
INSERT INTO [dbo].[HoaDon]([maKh],[maNv],[diaChiGH],[ngayLapHoaDon],[trangThaiThanhToan]) VALUES('KH0010','NV_002',N'Nguyễn văn thụ' ,'2023-05-05',1)
GO
/*
select * from HoaDon where maKh = ''
select * from HoaDon 
select * from ChiTietHoaDon
select *from ChiTietHoaDon where maHD = 'HD00000001'
select *from TaiKhoan
select *from NhanVien
select * from HoaDon where  diaChi LIKE  N'%Nguyễn%'
update HoaDon set trangThaiThanhToan = true;
delete from HoaDon where maHD= 'HD00000006'
delete from ChiTietHoaDon
SELECT * FROM LinhKien " + "WHERE maLoai = '%MSI%'
update HoaDon set maKh= 'KH0011', maNv= 'NV_004', diaChi= N'12, Nguyễn Văn Bảo, Phường 4, Gò Vâp, TPHCM', ngayLapHoaDon = '2023-05-05' where maHD= 'HD00000010' 
update HoaDon set [trangThaiThanhToan]= 0

select *from HoaDon join KhachHang on HoaDon.maKh=KhachHang.maKh join NhanVien on HoaDon.maNV=NhanVien.maNV
*/

INSERT INTO ChiTietHoaDon (maHD, maLk, soLuong, mucGiamGia) VALUES ('HD00000001', 'LK0001', 2, 10);
Go
INSERT INTO ChiTietHoaDon (maHD, maLk, soLuong, mucGiamGia) VALUES ('HD00000001', 'LK0010', 1, 0);
Go
INSERT INTO ChiTietHoaDon (maHD, maLk, soLuong, mucGiamGia) VALUES ('HD00000001', 'LK0018', 1, 0);
Go
INSERT INTO ChiTietHoaDon (maHD, maLk, soLuong, mucGiamGia) VALUES ('HD00000001', 'LK0015', 2, 0);
Go
INSERT INTO ChiTietHoaDon (maHD, maLk, soLuong, mucGiamGia) VALUES ('HD00000001', 'LK0035', 2, 0);
Go
INSERT INTO ChiTietHoaDon (maHD, maLk, soLuong, mucGiamGia) VALUES ('HD00000002', 'LK0018', 1, 0);
Go
INSERT INTO ChiTietHoaDon (maHD, maLk, soLuong, mucGiamGia) VALUES ('HD00000003', 'LK0015', 2, 0);
Go
INSERT INTO ChiTietHoaDon (maHD, maLk, soLuong, mucGiamGia) VALUES ('HD00000003', 'LK0035', 2, 0);