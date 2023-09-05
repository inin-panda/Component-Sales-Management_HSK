package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import dao.KhachHangDAO;

import entity.KhachHang;



public class GD_KhachHang extends  JPanel implements ActionListener, MouseListener {

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_KhachHang frame = new GD_KhachHang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

		private JTextField txtTenKH;
		private JPanel contentPane;
		private JTextField txtEmail;
		private JTextField txtTimKhachHang;
		private DefaultTableModel modelTableKhachHang;
		private JTable tableKhachHang;
		private JButton btnTimKiem;
		private JButton btnXoaRong;
		private JButton btnThem;
		private JButton btnXoa;
		private JButton btnSua;
		private JComboBox cbbGioiTinh;
		private JPanel pnCenter;
		private JButton btncCapNhat;
		
		private KhachHangDAO daoLinhKien= new KhachHangDAO();
		private ArrayList<KhachHang> listKhachHang =  (ArrayList<KhachHang>) daoLinhKien.getDanhSachKhachHang();
		private JTextField txtSDT;
		private JTextField txtDiaChi;
		
		public GD_KhachHang() {
			setBorder(null);
			
			this.setPreferredSize(new Dimension(1110, 612));
			setLayout(null);
			
			pnCenter = new JPanel();
			pnCenter.setBounds(1, 0, 1104, 611);
			add(pnCenter);
			pnCenter.setLayout(null);
			
			//----------title -------------//
			JPanel pnTitle = new JPanel();
			pnTitle.setBackground(new Color(220, 232, 243));
			pnTitle.setBounds(0, 0, 1104, 39);
			pnTitle.setLayout(null);
			
			JLabel lblLK = new JLabel("DANH SÁCH KHÁCH HÀNG");
			lblLK.setBackground(new Color(164, 184, 204));
			lblLK.setBounds(402, 5, 300, 26);
			
			lblLK.setHorizontalAlignment(SwingConstants.CENTER);
			lblLK.setForeground(Color.BLACK);
			lblLK.setFont(new Font("SansSerif", Font.BOLD, 20));
			pnTitle.add(lblLK);
			
			pnCenter.add(pnTitle);
			
			/* -------------Content------------------ */
			JPanel pnContent = new JPanel();
			pnContent.setBackground(SystemColor.controlHighlight);
			pnContent.setLayout(null);
			pnContent.setBounds(0, 39, 1310, 620);
			
			pnCenter.add(pnContent);

			
			JLabel lblTenKhachHang = new JLabel("Tên Khách Hàng:");
			lblTenKhachHang.setBounds(10, 13, 105, 22);
			lblTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 13));
			pnContent.add(lblTenKhachHang);
			txtTenKH = new JTextField();
			txtTenKH.setBounds(140, 13, 272, 22);
			pnContent.add(txtTenKH);
			txtTenKH.setColumns(10);
			
			JLabel lblDiaChi = new JLabel("Địa Chỉ:");
			lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDiaChi.setBounds(432, 13, 61, 22);
			pnContent.add(lblDiaChi);
			
			JLabel lblsdt = new JLabel("Số Điện Thoại");
			lblsdt.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblsdt.setBounds(10, 46, 95, 22);
			pnContent.add(lblsdt);
			
			JLabel lblGioiTinh = new JLabel("Giới Tính:");
			lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblGioiTinh.setBounds(904, 46, 78, 22);
			pnContent.add(lblGioiTinh);
			
			cbbGioiTinh = new JComboBox();
			cbbGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
			cbbGioiTinh.setBounds(990, 46, 105, 22);
			pnContent.add(cbbGioiTinh);
			
			JLabel lblEmail = new JLabel("Email:");
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblEmail.setBounds(432, 46, 61, 22);
			pnContent.add(lblEmail);
			
			txtEmail = new JTextField();
			txtEmail.setColumns(10);
			txtEmail.setBounds(503, 46, 391, 22);
			pnContent.add(txtEmail);
			
			
			JSplitPane splitPaneBtn = new JSplitPane();
			splitPaneBtn.setBounds(10, 528, 1095, 35);
			pnContent.add(splitPaneBtn);
			
			JPanel pnLeft = new JPanel();
			splitPaneBtn.setLeftComponent(pnLeft);
			
			JLabel lblTimKiem = new JLabel("Nhập thông tin cần tìm: ");
			pnLeft.add(lblTimKiem);
			
			txtTimKhachHang = new JTextField();
			pnLeft.add(txtTimKhachHang);
			txtTimKhachHang.setColumns(30);
			
			btnTimKiem = new JButton("Tìm kiếm");
			pnLeft.add(btnTimKiem);
			
			Box horizontalBox = Box.createHorizontalBox();
			pnLeft.add(horizontalBox);
			
			JPanel pnRight = new JPanel();
			splitPaneBtn.setRightComponent(pnRight);
			btnThem = new JButton("   Thêm   ");
			pnRight.add(btnThem);
			btnXoa = new JButton("   Xóa   ");
			pnRight.add(btnXoa);
			btnSua = new JButton("   Sửa   ");
			pnRight.add(btnSua);
			btnXoaRong = new JButton("Xóa rỗng");
			pnRight.add(btnXoaRong);
			btncCapNhat = new JButton("Cập Nhật");
			pnRight.add(btncCapNhat);
			
			
			String header[] = { "Mã Khách Hàng", "Tên Khách hàng", "Địa Chỉ", "Số Điện Thoại", "Email", "Giới Tính" };
			modelTableKhachHang = new DefaultTableModel(header,0);
			tableKhachHang = new JTable(modelTableKhachHang);
			tableKhachHang.setRowHeight(35);
			tableKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
			JScrollPane scrollPane;
			pnContent.add(scrollPane = new JScrollPane(tableKhachHang));
			
			txtSDT = new JTextField();
			txtSDT.setColumns(10);
			txtSDT.setBounds(140, 47, 272, 22);
			pnContent.add(txtSDT);
			
			txtDiaChi = new JTextField();
			txtDiaChi.setColumns(10);
			txtDiaChi.setBounds(504, 14, 591, 22);
			pnContent.add(txtDiaChi);
			scrollPane.setBounds(10, 77, 1085, 440);

			btnThem.addActionListener(this);
			btnXoa.addActionListener(this);
			btnSua.addActionListener(this);
			btnTimKiem.addActionListener(this);
			btnXoaRong.addActionListener(this);
			btncCapNhat.addActionListener(this);
			cbbGioiTinh.addActionListener(this);

			tableKhachHang.addMouseListener(this);
			loadDataToTable();
		}

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int row = tableKhachHang.getSelectedRow();
			KhachHang kh = listKhachHang.get(row);
			
			txtTenKH.setText(kh.getTenKH());
			txtDiaChi.setText(kh.getDiaChi());
			txtSDT.setText(kh.getSdt());
			txtEmail.setText(kh.getEmail());
			cbbGioiTinh.setSelectedItem(kh.getGioiTinh()? "Nam":"Nữ");
			
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent e) {
			
		}

		
		private void xoaRongTextfieldKH() {
			txtTenKH.setText("");
			txtDiaChi.setText("");
			txtSDT.setText("");
			txtEmail.setText("");
		}
		
		private void addTextField() {
			int row = tableKhachHang.getSelectedRow();
			if (row >= 0) {
				txtTenKH.setText(modelTableKhachHang.getValueAt(row, 1).toString());
				txtDiaChi.setText(modelTableKhachHang.getValueAt(row, 2).toString());
				txtSDT.setText(modelTableKhachHang.getValueAt(row, 3).toString());
				txtEmail.setText(modelTableKhachHang.getValueAt(row, 4).toString());
				cbbGioiTinh.setSelectedItem(modelTableKhachHang.getValueAt(row, 5));
				
			}
		}
		public void loadDataToTable() {
			try {
				
				KhachHangDAO khachHangDAO = new KhachHangDAO();
				ArrayList<KhachHang> listKH = (ArrayList<KhachHang>) khachHangDAO.getDanhSachKhachHang();

				for (KhachHang khachHang : listKH) {
					modelTableKhachHang.addRow(new Object[] {  khachHang.getMaKH(),khachHang.getTenKH(), khachHang.getDiaChi(),
							khachHang.getSdt(), khachHang.getEmail(), khachHang.getGioiTinh()?"Nam":"Nữ"  });
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public boolean KiemTra() {
			String tenKh = txtTenKH.getText().trim();
			String diaChi = txtDiaChi.getText().trim();
			String sdt = txtSDT.getText().trim();
			String email = txtEmail.getText().trim();
			
			
			if(tenKh.length() == 0 || diaChi.length() == 0 || sdt.length() == 0 || email.length() == 0 ) {
				JOptionPane.showMessageDialog(null, "Phải điền đầy đủ thông tin");
				return false;
			}
			
			if(!(tenKh.matches("^(([a-zA-Z\\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềếểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)([a-zA-Z\\s\\'ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*)([a-zA-Z\\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]))*$"))) {
				JOptionPane.showMessageDialog(null, "Chỉ nhập chữ");
				return false;
			}
			
			if (!( sdt.matches("^(0[3|5|7|8|9])+([0-9]{8}$)"))) {
				JOptionPane.showMessageDialog(null, "Số điện thoại phải là số và gồm 10 số, bắt đầu bằng các cặp số 03, 05, 07, 07, 08");
				return false;
			}
			if (!email.matches("^[\\w\\.]+@([\\w]+\\.)+[\\w]{2,4}$")) {
				JOptionPane.showMessageDialog(null, "Email phải đúng định dạng example@mail.com");
				return false; }
			
			
			return true;
			}
		
			private KhachHang createKhachHang() {
				KhachHang khachHang = new KhachHang();
				khachHang.setTenKH(txtTenKH.getText());
				khachHang.setDiaChi(txtDiaChi.getText());
				khachHang.setSdt(txtSDT.getText());
				khachHang.setEmail(txtEmail.getText());
				String gt = cbbGioiTinh.getSelectedItem().toString();
				Boolean tam = null;
				if (gt == "Nam") {
					tam = true;
				}else {
					tam = false;
				}
				khachHang.setGioiTinh(tam);
				return khachHang;
			}


			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();


				if (o.equals(btnThem)) {
					if (KiemTra()) {
						
					
					KhachHang khachHang = createKhachHang();
					KhachHangDAO khachHangDAO = new KhachHangDAO();

					

					if (khachHangDAO.checkExist(txtSDT.getText())) {
						JOptionPane.showMessageDialog(null, "Khách Hàng Đã Tồn Tại");
						return;
					} else {
						if (khachHangDAO.addKhachHang(khachHang)) {
							JOptionPane.showMessageDialog(null, "Thêm Thành Công ");
							modelTableKhachHang.setRowCount(0);
							listKhachHang = (ArrayList<KhachHang>) khachHangDAO.getDanhSachKhachHang();
							loadDataToTable();
							xoaRongTextfieldKH();
						} else {
							JOptionPane.showMessageDialog(null, "Lỗi Thêm Không Thành Công");
						}
					}
					}
				}

				// CẬP NHẬT THÔNG TIN KHÁCH HÀNG
				if (o.equals(btnSua)) {
					int row = tableKhachHang.getSelectedRow();
					KhachHang kh = createKhachHang();
					KhachHang khcu = listKhachHang.get(row);
					kh.setMaKH(khcu.getMaKH());
					KhachHangDAO khachHangDAO = new KhachHangDAO();
					if (row >= 0) {
						 
							if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn cập nhật?") == JOptionPane.NO_OPTION) {
								return;
							}
							try {

								if (khachHangDAO.updateKhachHang(kh)) {
									JOptionPane.showMessageDialog(null, "Cập nhật thành công");
									listKhachHang =  (ArrayList<KhachHang>) khachHangDAO.getDanhSachKhachHang();
									modelTableKhachHang.setRowCount(0);
									loadDataToTable();

								} else {
									JOptionPane.showMessageDialog(null,  "Cập nhật không thành công");
								}
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						

					} else {
						JOptionPane.showMessageDialog(null,  "Phải chọn một dòng trong bảng");
					}
					
					xoaRongTextfieldKH();
					
				}
				// XÓA KH
				if (o.equals(btnXoa)) {

					int row = tableKhachHang.getSelectedRow();
					KhachHangDAO khDAO = new KhachHangDAO();

					if (row >= 0) {
						String maKH = (String) tableKhachHang.getValueAt(row, 0);
						String stt = maKH;
						if (JOptionPane.showConfirmDialog(null, 
								"Bạn có chắc muốn xóa khách hàng này") == JOptionPane.WARNING_MESSAGE) {
							return;
						}

						try {

							if (khDAO.deleteKhachHang(stt)) {
								JOptionPane.showMessageDialog(null,  "Xóa thành công");
								modelTableKhachHang.setRowCount(0);
								loadDataToTable();
								xoaRongTextfieldKH();

							} else {
								JOptionPane.showMessageDialog(null, "Xóa không thành công");
							}
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Phải chọn 1 dòng trong bảng");
					}

				}
				if (o.equals(btnXoaRong)) {
					xoaRongTextfieldKH();
				}
				if (o.equals(btnTimKiem)) {
					String maNV = txtTimKhachHang.getText();
					int totalRow, timThay = 0;
					if (!maNV.equals("")) {
						totalRow = tableKhachHang.getRowCount() - 1;
						for (int i = totalRow; i >= 0; i--) {
							if (maNV.equalsIgnoreCase((String) tableKhachHang.getValueAt(i, 0))) {
								tableKhachHang.setRowSelectionInterval(i, i);
								addTextField();
								timThay = 1;
								break;
							}
						}
						if (timThay == 0) {
							JOptionPane.showMessageDialog(this, "Mã Khách Hàng không tồn tại");
						}
					} else {
						JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng cần tìm");
					}

					
				}
				if (o.equals(btncCapNhat)) {
					xoaRongTextfieldKH();
					modelTableKhachHang.setRowCount(0);
					modelTableKhachHang.removeTableModelListener(tableKhachHang);
					loadDataToTable();
				}
			}
}

