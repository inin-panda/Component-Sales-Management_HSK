# Quản Lý Cửa Hàng Bán Linh Kiện Máy Tính

## Tổng Quan
Dự án này là một ứng dụng Java dựa trên lập trình hướng sự kiện, được thiết kế để quản lý một cửa hàng bán linh kiện máy tính. Cửa hàng có nhiều bộ phận kinh doanh, nhưng bộ phận quản lý đơn đặt hàng, phân phối hàng, lưu trữ thông tin khách hàng và quản lý nhân viên bán hàng là bộ phận hoạt động thường xuyên với nhu cầu xử lý dữ liệu lớn.

## Kịch Bản
Khi một khách hàng cần mua một mặt hàng, nhân viên bán hàng sẽ kiểm tra xem mặt hàng đó có trong cửa hàng không. Nếu không có, họ sẽ thông báo với khách hàng. Nếu có, nhân viên bán hàng sẽ tiến hành nhập thông tin khách hàng gồm: mã khách hàng, tên khách hàng, địa chỉ, số điện thoại, email (nếu có). Sau đó, họ sẽ nhập thông tin đơn đặt hàng bao gồm: số hóa đơn, mã khách hàng, mã nhân viên, ngày đặt hàng, ngày giao hàng, ngày chuyển hàng, nơi nhận giao hàng.

Tiếp theo, nhân viên bán hàng sẽ nhập chi tiết hóa đơn, bao gồm số hóa đơn đã lập ở trên, mã hàng, giá bán, số lượng, mức giảm giá (khuyến mãi). Hóa đơn bán hàng sẽ chứa các thông tin sau: mã khách hàng, tên khách hàng, địa chỉ, số điện thoại, tên mặt hàng mua, số lượng, giá bán, nhân viên bán hàng, ngày giao dự kiến. Hóa đơn được giao cho khách để đối chiếu khi nhận hàng và một bản được gửi đến phòng xuất kho để tiến hành xuất hàng.

## Chức Năng
Ứng dụng cung cấp các chức năng tối thiểu sau:

### Nhập Dữ Liệu
- Nhân viên bán hàng có thể nhập thông tin khách hàng, đơn đặt hàng, chi tiết đơn hàng, loại hàng, mặt hàng, nhà cung cấp.
- Chức năng nhập thông tin nhân viên thuộc về bộ phận nhân sự.

### Xem Dữ Liệu
- Nhân viên có thể xem thông tin khách hàng, đơn đặt hàng, chi tiết đơn hàng, loại hàng, mặt hàng, nhà cung cấp, thông tin của nhân viên khác.
- Có thể cập nhật dữ liệu khi có thay đổi, riêng dữ liệu bảng nhân viên chỉ có phòng nhân sự mới có quyền sửa đổi.

### Tìm Kiếm Dữ Liệu
- Có thể tìm kiếm dữ liệu theo khách hàng, nhân viên, hóa đơn, mặt hàng, v.v.

### Thống Kê Dữ Liệu
- Thống kê lương nhân viên.
- Thống kê doanh thu theo tháng.
- Thống kê mặt hàng bán chạy.
- Thống kê khách hàng tiềm năng.
- Thống kê số tiền trả theo mỗi hóa đơn.

### Báo Cáo
- Xem báo cáo về mặt hàng, khách hàng, nhân viên, đơn đặt hàng, v.v.

### Chức Năng Bổ Sung
- Quản lý thông tin nhân viên của cửa hàng.
