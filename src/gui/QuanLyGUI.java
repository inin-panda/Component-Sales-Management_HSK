package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entity.NhanVien;
import connectDB.ConnectDB;

import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;
import java.awt.Component;
import java.awt.Dimension;
public class QuanLyGUI extends JPanel implements ActionListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTenNv, txtTenTk;
	private JTextField txtDiaChi;
	private JTextField txtSdt;
	private JTextField txtEmail;
	private JTextField txtTim;
	private JTable tblNhanVien;
	private JPasswordField txtMatKhau;
	private DefaultTableModel modelNhanVien;
	private JButton btnTim, btnThem, btnXoa,btnSua, btnXoaRong, btnLuu;
	private JComboBox<Object> cbbChucVu;
	private JDateChooser ngaySinh;
	private JRadioButton radGioiTinh;
	private NhanVienDAO nv_dao = new NhanVienDAO();
	private TaiKhoanDAO tk_dao = new TaiKhoanDAO(ConnectDB.con);
	private ArrayList<NhanVien> listNV = nv_dao.getalltbNhanVien();
	private JDialog dialog;
	private int i;
	private JPanel pnCenter;
//	public static void main(String[admi] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					QuanLyGUI frame = new QuanLyGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	public QuanLyGUI() {
		setBorder(null);
		
		this.setPreferredSize(new Dimension(1110, 612));
		setLayout(null);
		
		pnCenter = new JPanel();
		pnCenter.setBounds(1, 0, 1104, 611);
		add(pnCenter);
		pnCenter.setLayout(null);
		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(new Color(220, 232, 243));
		pnTitle.setBounds(0, 0, 1104, 39);
		pnTitle.setLayout(null);
		
		JLabel lblNV = new JLabel("NHÂN VIÊN");
		lblNV.setBackground(new Color(164, 184, 204));
		lblNV.setBounds(450, 5, 200, 26);
		
		lblNV.setHorizontalAlignment(SwingConstants.CENTER);
		lblNV.setForeground(Color.BLACK);
		lblNV.setFont(new Font("SansSerif", Font.BOLD, 20));
		pnTitle.add(lblNV);
		
		pnCenter.add(pnTitle);
		JPanel pnContent = new JPanel();
		pnContent.setBackground(SystemColor.controlHighlight);
		pnContent.setLayout(null);
		pnContent.setBounds(0, 40, 1104, 575);
		
		pnCenter.add(pnContent);
		
		JLabel lblTenNv = new JLabel("Tên nhân viên:");
		lblTenNv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenNv.setBounds(10, 11, 86, 22);
		pnContent.add(lblTenNv);
		
		txtTenNv = new JTextField();
		txtTenNv.setColumns(10);
		txtTenNv.setBounds(115, 13, 254, 20);
		pnContent.add(txtTenNv);
		
		JLabel lblSdt = new JLabel("Số điện thoại: ");
		lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSdt.setBounds(393, 11, 86, 22);
		pnContent.add(lblSdt);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(684, 11, 86, 22);
		pnContent.add(lblEmail);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ: ");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDiaChi.setBounds(10, 44, 86, 22);
		pnContent.add(lblDiaChi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(115, 46, 254, 20);
		pnContent.add(txtDiaChi);
		
		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChucVu.setBounds(682, 44, 70, 22);
		pnContent.add(lblChucVu);
		
		JSplitPane splitPaneBtn = new JSplitPane();
		splitPaneBtn.setBounds(10, 529, 1084, 35);
		pnContent.add(splitPaneBtn);
		
		JPanel pnLeft = new JPanel();
		splitPaneBtn.setLeftComponent(pnLeft);
		
		JLabel lblTimKiem = new JLabel("Tìm theo mã:");
		pnLeft.add(lblTimKiem);
		
		txtTim = new JTextField();
		txtTim.setColumns(20);
		pnLeft.add(txtTim);
		
		 btnTim = new JButton("Tìm kiếm");
		pnLeft.add(btnTim);
		 
		Box horizontalBox = Box.createHorizontalBox();
		pnLeft.add(horizontalBox);
		
		JPanel pnRight = new JPanel();
		splitPaneBtn.setRightComponent(pnRight);
		pnRight.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		 btnThem = new JButton("      Thêm      ");
		pnRight.add(btnThem);
		
		 btnXoa = new JButton("      Xoá      ");
		pnRight.add(btnXoa);
		
		 btnSua = new JButton("      Sửa      ");
		pnRight.add(btnSua);
		
		btnXoaRong = new JButton("      Xoá rỗng      ");
		pnRight.add(btnXoaRong);
		
		 btnLuu = new JButton("      Đổi mật khẩu      ");
		pnRight.add(btnLuu);
		
		txtSdt = new JTextField();
		txtSdt.setColumns(10);
		txtSdt.setBounds(499, 13, 156, 20);
		pnContent.add(txtSdt);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(752, 13, 342, 20);
		pnContent.add(txtEmail);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGioiTinh.setBounds(973, 47, 63, 16);
		pnContent.add(lblGioiTinh);
		
		JLabel lblBirthDate = new JLabel("Ngày sinh:");
		lblBirthDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBirthDate.setBounds(393, 44, 70, 22);
		pnContent.add(lblBirthDate);
		
		ngaySinh = new JDateChooser();
		ngaySinh.setBounds(499, 46, 156, 20);
		pnContent.add(ngaySinh);
		
		Object[] chucVu = {"QL","NV_NHANSU","NV_KETOAN","NV_BANHANG"};
		cbbChucVu = new JComboBox<Object>(chucVu);
		cbbChucVu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbbChucVu.setBounds(752, 45, 199, 22);
		pnContent.add(cbbChucVu);
		
		String header[] = { "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày Sinh", "Số điện thoại", "Địa chỉ" , "Chức vụ", "Email"};
		modelNhanVien = new DefaultTableModel(header,0);
		tblNhanVien = new JTable(modelNhanVien);
		tblNhanVien.setRowHeight(35);
		tblNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JScrollPane scrollPane;
		pnContent.add(scrollPane = new JScrollPane(tblNhanVien));
		scrollPane.setBounds(10, 77, 1084, 440);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setColumnHeaderView(scrollPane_1);
		updataTableData();
		tblNhanVien.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblNhanVien.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblNhanVien.getColumnModel().getColumn(2).setPreferredWidth(15);
		tblNhanVien.getColumnModel().getColumn(2).setMinWidth(5);
		tblNhanVien.getColumnModel().getColumn(3).setPreferredWidth(40);
		tblNhanVien.getColumnModel().getColumn(4).setPreferredWidth(70);
		tblNhanVien.getColumnModel().getColumn(5).setResizable(false);
		tblNhanVien.getColumnModel().getColumn(5).setPreferredWidth(150);
		tblNhanVien.getColumnModel().getColumn(6).setPreferredWidth(40);
		tblNhanVien.getColumnModel().getColumn(7).setPreferredWidth(70);
		
		radGioiTinh = new JRadioButton("Nữ");
		radGioiTinh.setForeground(Color.LIGHT_GRAY);
		radGioiTinh.setBounds(1035, 45, 21, 22);
		pnContent.add(radGioiTinh);
		
		JLabel lblNu = new JLabel("Nữ");
		lblNu.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblNu.setBounds(1062, 47, 32, 17);
		pnContent.add(lblNu);
	
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		btnTim.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		tblNhanVien.addMouseListener(this);
	}
	private void xoatrang() {
		txtTenNv.setText("");
		txtSdt.setText("");
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtTenNv.requestFocus();
		ngaySinh.setDate(null);
		radGioiTinh.setSelected(false);
	}
	private void addNVvaoTextField() {
		int row = tblNhanVien.getSelectedRow();
		if (row >= 0) {
			txtTenNv.setText(modelNhanVien.getValueAt(row, 1).toString());
			radGioiTinh.setSelected(modelNhanVien.getValueAt(row, 2).toString() == "Nữ" ? true : false);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = null;
			try {
				date = format.parse(tblNhanVien.getValueAt(row, 3).toString());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ngaySinh.setDate(date);
			txtSdt.setText(modelNhanVien.getValueAt(row, 4).toString());
			txtDiaChi.setText(modelNhanVien.getValueAt(row, 5).toString());
			txtEmail.setText(modelNhanVien.getValueAt(row, 7).toString());
			cbbChucVu.setSelectedItem(modelNhanVien.getValueAt(row, 6).toString());
		}
	}
	private void updataTableData() {
		modelNhanVien.setRowCount(0);
		List<NhanVien> list = nv_dao.getalltbNhanVien();
		for (NhanVien nv : list) {
			modelNhanVien.addRow(new Object[] {nv.getMaNV(),nv.getTenNV(),nv.isGioiTinh()?"Nữ":"Nam",nv.getNgaySinh(),nv.getSdt(),nv.getDiaChi(),nv.getChucVu(),nv.getEmail()});
		}
	}
	public boolean KiemTra() {
		String tenNv = txtTenNv.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String email = txtEmail.getText().trim();
		String sdt = txtSdt.getText().trim();
		String chucVu = cbbChucVu.getSelectedItem().toString();
		
		if(tenNv.length() == 0 || diaChi.length() == 0 || email.length() == 0 || sdt.length() == 0 || chucVu.length()==0) {
			JOptionPane.showMessageDialog(null, "Phải điền đầy đủ thông tin");
			return false;
		}
		
		if (!tenNv.matches("^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸ][a-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ]*\\s?)+$")) {
			JOptionPane.showMessageDialog(null, "Tên nhân viên có 1 hoặc nhiều từ, viết hoa chữ cái đầu mỗi từ (không chứa kí tự số)");
			return false;
		}
//		if (!(diaChi.length() >0 && diaChi.matches("[A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸ][a-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ]*\\\\s?"))) {
//			JOptionPane.showMessageDialog(this, "địa chỉ ko chứa kí tự đặc biệt");
//			txtDiaChi.requestFocus();
//			return false;
//		}
		if (!(email.length() >0 && email.matches("^[a-zA-Z]([a-zA-Z_.0-9])+@{1}(yahoo|google|iuh|hotmail|gmail|email)((.com|.edu|.vn)+$)"))) {
			JOptionPane.showMessageDialog(this, "email nhân viên ko đúng form");
			txtEmail.requestFocus();
			return false;
		}
		if(!(sdt.length() > 0 && sdt.matches("^[0]{1}[973]{1}\\d{8}$"))) {
			JOptionPane.showMessageDialog(this, "Số điện thoại bắt đầu bằng 09-07-03 ");
			return false;
		}
		return true;
		}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		  if (o.equals(btnThem)) {
			  if(KiemTra()) {
				  int i = tblNhanVien.getRowCount() + 1;
				  String maNV = "";
				  if(i > 0 && i<10) {
					   maNV = "NV_00" + i++ ; }
				  if(i >=10 && i<100) {
					  maNV = "NV_0" + i++;}
			  	if(i>100) {
			  		maNV = "NV_" + i++;
			  	}
					String TenNV = txtTenNv.getText();
					Date ngaysinh = new Date(ngaySinh.getDate().getTime());
					boolean gioiTinh = (radGioiTinh.isSelected())? true : false;
					String sdt = (txtSdt.getText());
					String diachi  = txtDiaChi.getText();
					String chucVu = (String) cbbChucVu.getSelectedItem();
					String email = txtEmail.getText();
					NhanVien nv = new NhanVien(maNV, TenNV, gioiTinh, ngaysinh, sdt, diachi, chucVu, email);
					try {
						nv_dao.addNhanVien(nv);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			 		modelNhanVien.addRow(new Object[] { nv.getMaNV(),nv.getTenNV(), nv.isGioiTinh()?"Nữ":"Nam",nv.getNgaySinh(),nv.getSdt(),nv.getDiaChi(),nv.getChucVu(),nv.getEmail()});
					JOptionPane.showMessageDialog(this, "Thêm thành công");
			 		xoatrang(); 
//			 		HienThiModal(getName());
			  }
		  }
		  if (o.equals(btnLuu)) { 
//			  addNVvaoTextField();
//			  HienThiModal(getName());
				new changePassword().setVisible(true);
//			  	updataTableData();
		  }
		if (o.equals(btnSua)) {
			if (KiemTra()) {
				int r = tblNhanVien.getSelectedRow();
				if(r<=-1) {
					JOptionPane.showMessageDialog(null,"chọn dòng cần sửa ");
				}
				String TenNV = txtTenNv.getText();
				Date ngaysinh = new Date(ngaySinh.getDate().getTime());
				boolean gioiTinh = (radGioiTinh.isSelected())? true : false;
				String sdt = txtSdt.getText();
				String diachi  = txtDiaChi.getText();
				String chucVu = (String) cbbChucVu.getSelectedItem();
				String email = txtEmail.getText();
				NhanVien nv = new NhanVien(TenNV, gioiTinh, ngaysinh, sdt, diachi, chucVu, email);
				NhanVien nvOld = listNV.get(r);
				nv.setMaNV(nvOld.getMaNV());
				try {
					if(nv_dao.updateNhanVien(nv)) {
							xoatrang(); 
						JOptionPane.showMessageDialog(this,"Sửa Thành Công");
						updataTableData();
					} else {
						JOptionPane.showMessageDialog(null, "Chỉnh sửa không Thành Công");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (o.equals(btnXoa)) {
			int index = tblNhanVien.getSelectedRow();
	 		if(index != -1) {
	 			int tb = JOptionPane.showConfirmDialog(null,"Xác nhận xoá?","Delete",JOptionPane.YES_NO_CANCEL_OPTION);
	 			if(tb == JOptionPane.YES_OPTION) {
	 				xoatrang();
	 				String ma = (String) tblNhanVien.getValueAt(index, 0);
	            	modelNhanVien.removeRow(index);
	            	try {
						nv_dao.deleteNhanVien(ma);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	 			}
	 		
	 		}
	 		if(index<=-1) {
	 			JOptionPane.showMessageDialog(null,"chọn dòng cần xóa");
	 		}
			}

		if (o.equals(btnXoaRong)) {
			xoatrang();
		} 
		if (o.equals(btnTim)) {
			String maNV = txtTim.getText();
			int totalRow, timThay = 0;
			if (!maNV.equals("")) {
				totalRow = tblNhanVien.getRowCount() - 1;
				for (int i = totalRow; i >= 0; i--) {
					if (maNV.equalsIgnoreCase((String) tblNhanVien.getValueAt(i, 0))) {
						tblNhanVien.setRowSelectionInterval(i, i);
						addNVvaoTextField();
						timThay = 1;
						JOptionPane.showMessageDialog(this, "Tìm thành công");
						requestFocus();
						break;
					}
				}
				if (timThay == 0) {
					JOptionPane.showMessageDialog(this, "Mã nhân viên không tồn tại");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên cần tìm");
			}

			
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tblNhanVien.getSelectedRow();
		if (row >= 0) {
			txtTenNv.setText(modelNhanVien.getValueAt(row, 1).toString());
			radGioiTinh.setSelected(modelNhanVien.getValueAt(row, 2).toString() == "Nữ" ? true : false);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = null;
			try {
				date = format.parse(tblNhanVien.getValueAt(row, 3).toString());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ngaySinh.setDate(date);
			txtSdt.setText(modelNhanVien.getValueAt(row, 4).toString());
			txtDiaChi.setText(modelNhanVien.getValueAt(row, 5).toString());
			txtEmail.setText(modelNhanVien.getValueAt(row, 7).toString());
			cbbChucVu.setSelectedItem(modelNhanVien.getValueAt(row, 6).toString());
			
		}
		}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public int HienThiModal(String maNV) {
		dialog = new JDialog();
	    dialog.setSize(300, 200);
        Dimension frameSize = contentPane.getSize();
        Dimension dialogSize = dialog.getSize();
        int x = contentPane.getLocationOnScreen().x + (frameSize.width - dialogSize.width) / 2;
        int y = contentPane.getLocationOnScreen().y + (frameSize.height - dialogSize.height) / 2;
        dialog.setLocation(x, y);
	    JPanel panel = new JPanel();
	    panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.Y_AXIS));

	    JPanel pTen = new JPanel();
	    JLabel lblTen = new JLabel("Tên tài khoản:");
	    txtTenTk = new JTextField(22);
	    pTen.add(lblTen);
	    pTen.add(txtTenTk);
	    panel.add(pTen);

	    JPanel pmk = new JPanel();
	    JLabel lblmk = new JLabel("Mật khẩu:");

	    txtMatKhau = new JPasswordField(22);
	    pmk.add(lblmk);
	    pmk.add(txtMatKhau); 
	    panel.add(pmk);
	    
	    // Tạo JButton để lưu dữ liệu
	    JButton btnSave = new JButton("TẠO");
		btnSave.setHorizontalTextPosition(JButton.RIGHT);
		btnSave.setVerticalTextPosition(JButton.CENTER);
	    btnSave.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 60)); 
	    btnSave.addActionListener(new ActionListener() {
	       
			@Override
	        public void actionPerformed(ActionEvent e) {
	            // Lưu dữ liệu vào database
				dialog.addWindowListener(new WindowAdapter() {
			         public void windowClosing(WindowEvent windowEvent) {
			            i =0;
			        
			         }
			      });
	            // Thực hiện lưu dữ liệu vào database ở đây
	        	try {
					TaiKhoanDAO tk = new TaiKhoanDAO(connectDB.ConnectDB.getInstance().getConnection());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	Object o = e.getSource();
	        	if(o.equals(btnSave)) {
	        		entity.TaiKhoan TK = new entity.TaiKhoan();
	        	    TK.setTaiKhoan(txtTenTk.getText());
	        	    TK.setMatKhau(txtMatKhau.getText());
	        	   // boolean createTK = tk_dao.createTK(TK);
	        	    JOptionPane.showMessageDialog(btnSave, "Tạo thành công");
	        	}
	            // Đóng JDialog
	            dialog.dispose();
	        }
	    });
	    panel.add(btnSave , BorderLayout.WEST);
	    panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
	    dialog.getContentPane().add(panel);
	    dialog.setVisible(true);
	    return i;
	}
}
