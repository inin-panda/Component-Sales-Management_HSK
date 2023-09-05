package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.LinhKien_DAO;
import dao.LoaiLinhKien_DAO;
import dao.NhaCungCap_DAO;

import entity.LinhKien;
import entity.LoaiLinhKien;
import entity.NhaCungCap;

public class GD_LinhKien extends JPanel implements ActionListener, MouseListener, PropertyChangeListener, ChangeListener{

	public static void main(String[] args) {
		new GD_LinhKien().setVisible(true);
	}

	private JTextField txtTenLK;
	private JTextField txtNsx;
	private JTextField txtGia;
	private JTextField txtTimKiem;
	private DefaultTableModel modelTableLinhKien;
	private JTable tableLinhKien;
	public JComboBox cbLocLinhKien;
	private JButton btnTimKiem;
	private JButton btnXoaRong;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JComboBox  cbLoaiLK;
	private JComboBox<String> cbNsx;
	private JDateChooser JdateNgaySX;
	private LinhKien_DAO daoLinhKien= new LinhKien_DAO();
	private LoaiLinhKien_DAO daoLoaiLk = new LoaiLinhKien_DAO();
	private NhaCungCap_DAO daoNcc = new NhaCungCap_DAO();
	private ArrayList<LinhKien> listLinhKien = daoLinhKien.getAllLinhKien();
	private JTextArea txtChiTiet;
	private JTextField txtNgaySX;
	private JTextField txtSoLuong;
	private JPanel pnCenter;
	public JButton btnLoc;
	private JButton btnCapNhat;
	
	public GD_LinhKien() {
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
		
		JLabel lblLK = new JLabel("LINH KIỆN");
		lblLK.setBackground(new Color(164, 184, 204));
		lblLK.setBounds(450, 5, 200, 26);
		
		lblLK.setHorizontalAlignment(SwingConstants.CENTER);
		lblLK.setForeground(Color.BLACK);
		lblLK.setFont(new Font("SansSerif", Font.BOLD, 20));
		pnTitle.add(lblLK);
		
		pnCenter.add(pnTitle);
//		
//		btnLoc = new JButton("Lọc: ");
//		btnLoc.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		btnLoc.setBounds(886, 6, 87, 26);
//		pnTitle.add(btnLoc);
//		
//		cbLocLinhKien = new JComboBox();
//		cbLocLinhKien.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		cbLocLinhKien.setModel(new DefaultComboBoxModel(new String[] {"Linh kiện", "Loại linh kiện", "Nhà cung cấp"}));
//		cbLocLinhKien.setBounds(983, 6, 105, 26);
//		pnTitle.add(cbLocLinhKien);
		
		/* -------------Content------------------ */
		JPanel pnContent = new JPanel();
		pnContent.setBackground(SystemColor.controlHighlight);
		pnContent.setLayout(null);
		pnContent.setBounds(0, 40, 1104, 575);
		
		pnCenter.add(pnContent);

		
		JLabel lblTenLK = new JLabel("Tên linh kiện: ");
		lblTenLK.setBounds(10, 13, 86, 22);
		lblTenLK.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnContent.add(lblTenLK);
		txtTenLK = new JTextField();
		txtTenLK.setBounds(103, 14, 174, 22);
		pnContent.add(txtTenLK);
		txtTenLK.setColumns(10);
		
		JLabel lblLoaiLK = new JLabel("Loại linh kiện: ");
		lblLoaiLK.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLoaiLK.setBounds(287, 13, 86, 22);
		pnContent.add(lblLoaiLK);
		
		cbLoaiLK = new JComboBox ();
		cbLoaiLK.setBounds(381, 14, 181, 22);
		pnContent.add(cbLoaiLK);
		
		try {
			cbLoaiLK.setModel(new DefaultComboBoxModel(getListLoaiLk()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblNgaySX = new JLabel("Ngày nhập hàng: ");
		lblNgaySX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgaySX.setBounds(10, 46, 95, 22);
		pnContent.add(lblNgaySX);
		
		JdateNgaySX = new JDateChooser();
		JdateNgaySX.setBounds(254, 46, 23, 22);
		JdateNgaySX.setDate(new java.util.Date());
		pnContent.add(JdateNgaySX);
		
		txtNgaySX= new JTextField();
		txtNgaySX.setEditable(false);
		txtNgaySX.setBounds(103, 46, 152, 22);
		pnContent.add(txtNgaySX);
		
		JLabel lblNhaSX = new JLabel("Nhà cung cấp:");
		lblNhaSX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNhaSX.setBounds(464, 46, 86, 22);
		pnContent.add(lblNhaSX);
		
		cbNsx = new JComboBox <String>();
		cbNsx.setBounds(560, 47, 190, 22);
		cbNsx.setEditable(true);
		pnContent.add(cbNsx);
		
		try {
			cbNsx.setModel(new DefaultComboBoxModel(getListNcc()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblGia = new JLabel("Giá: ");
		lblGia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGia.setBounds(287, 46, 33, 22);
		pnContent.add(lblGia);
		
		txtGia = new JTextField();
		txtGia.setColumns(10);
		txtGia.setBounds(315, 46, 139, 22);
		pnContent.add(txtGia);
		
		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSoLuong.setBounds(572, 13, 70, 22);
		pnContent.add(lblSoLuong);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(634, 13, 86, 22);
		pnContent.add(txtSoLuong);
		
		
		JSplitPane splitPaneBtn = new JSplitPane();
		splitPaneBtn.setBounds(10, 528, 1095, 35);
		pnContent.add(splitPaneBtn);
		
		JPanel pnLeft = new JPanel();
		splitPaneBtn.setLeftComponent(pnLeft);
		
		JLabel lblTimKiem = new JLabel("Nhập thông tin cần tìm: ");
		pnLeft.add(lblTimKiem);
		
		JLabel lblChiTiet = new JLabel("Chi tiết:");
		lblChiTiet.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChiTiet.setBounds(737, 13, 70, 22);
		pnContent.add(lblChiTiet);
		
		txtChiTiet = new JTextArea();
		txtChiTiet.setColumns(10);
		txtChiTiet.setBounds(788, 13, 306, 57);
		pnContent.add(txtChiTiet);
		JScrollPane scrollPanetxt;
		pnContent.add(scrollPanetxt = new JScrollPane(txtChiTiet));
		scrollPanetxt.setBounds(788, 13, 306, 57);
		
		txtTimKiem = new JTextField();
		pnLeft.add(txtTimKiem);
		txtTimKiem.setColumns(30);
		
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
		btnCapNhat = new JButton("Cập nhật");
		pnRight.add(btnCapNhat);
		
		
		String header[] = { "Mã linh kiện", "Tên linh kiện", "Số lượng", "Ngày nhập hàng", "Giá", "Chi tiết linh kiện" , "Nhà cung cấp", "Loại linh kiện"};
		modelTableLinhKien = new DefaultTableModel(header,0);
		tableLinhKien = new JTable(modelTableLinhKien);
		tableLinhKien.setRowHeight(35);
		tableLinhKien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JScrollPane scrollPane;
		pnContent.add(scrollPane = new JScrollPane(tableLinhKien));

		scrollPane.setBounds(10, 77, 1085, 440);
		

		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnCapNhat.addActionListener(this);
//		cbLocLinhKien.addActionListener(this);
		cbLoaiLK.addActionListener(this);
		cbNsx.addActionListener(this);
		JdateNgaySX.addPropertyChangeListener(this);
		tableLinhKien.addMouseListener(this);
//		btnLoc.addActionListener(this);
		loadDataToTable();
	}
	
	private Object[] getListNcc() throws SQLException{
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		daoNcc.getAllNhaCungCap().forEach(i-> list.add(i.getMaNhaCungCap()));
		return list.toArray();
	}

	private Object[] getListLoaiLk() throws SQLException{
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		daoLoaiLk.getAllLoaiLinhKien().forEach(i-> list.add(i.getMaLoai()));
		return list.toArray();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
			int row = tableLinhKien.getSelectedRow();
			LinhKien lk = listLinhKien.get(row);
			
			txtTenLK.setText(lk.getTenLk());
			txtSoLuong.setText(lk.getSoLuong()+"");
			JdateNgaySX.setDate(lk.getNgaySx()!=null? java.sql.Date.valueOf(lk.getNgaySx()):null);
			txtGia.setText(lk.getGia()+"");
			txtChiTiet.setText(lk.getChiTietLk());
			cbNsx.setSelectedItem(lk.getMaNhaCungCap().getMaNhaCungCap());
			cbLoaiLK.setSelectedItem(lk.getMaLoai().getMaLoai());
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

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnThem)) {
			if(checkInput()) {
				LinhKien lk =createLinhkien();
				
				if (daoLinhKien.checkExist(lk.getMaLoai().getMaLoai(), lk.getMaNhaCungCap().getMaNhaCungCap())) {
					JOptionPane.showMessageDialog(null, "Linh kiện đã tồn tại");
					return;
				}
				else
					if (daoLinhKien.addLinhKien(lk)) {
						JOptionPane.showMessageDialog(this,"Thêm thành công.");	
						modelTableLinhKien.setRowCount(0);
						listLinhKien= daoLinhKien.getAllLinhKien();
						loadDataToTable();
						xoaRong();
					}else {
						JOptionPane.showMessageDialog(null, "Thêm không Thành Công");
					}
			}
		}
		if(o.equals(btnXoa)) {
			int index = tableLinhKien.getSelectedRow();
			if(index<0) {
				JOptionPane.showMessageDialog(this, "Chưa chọn linh kiện!", "Quản lý linh kiện", 2);
				return;
			}
			
			int i = JOptionPane.showConfirmDialog(
					this, "Bạn có muốn xóa linh kiện có mã \""
							+ listLinhKien.get(index).getMaLk() + "\" không?",
					"Quản lý linh kiện", 2);
			if (i == 0) {
				try {
					daoLinhKien.deleteLinhKien(listLinhKien.get(index).getMaLk());
					modelTableLinhKien.setRowCount(0);
					listLinhKien = daoLinhKien.getAllLinhKien();
//					tableLinhKien.updateUI();
					xoaRong();
					JOptionPane.showMessageDialog(this, "Xóa thành công!", "Quản lý linh kiện", 1);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Quản lý linh kiện", 2);
				}
			} else
				return;
		}
		if(o.equals(btnXoaRong)) {
			xoaRong();
		}
		if(o.equals(btnSua)) {
			int index = tableLinhKien.getSelectedRow();
			if(index<0) {
				JOptionPane.showMessageDialog(this, "Chưa chọn linh kiện!", "Quản lý linh kiện", 2);
				return;
			}
			if(checkInput()) {
				LinhKien lk =createLinhkien();
				LinhKien lkCu = listLinhKien.get(index);
				lk.setMaLk(lkCu.getMaLk());
				if (daoLinhKien.updateLinhKien(lk)) {
					JOptionPane.showMessageDialog(this,"Chỉnh sửa thành công.");	
					modelTableLinhKien.setRowCount(0);
					listLinhKien= daoLinhKien.getAllLinhKien();
					loadDataToTable();
					xoaRong();
				}else {
					JOptionPane.showMessageDialog(null, "Chỉnh sửa không Thành Công");
				}
			}
			
		}
		if(o.equals(btnCapNhat)) {
			xoaRong();
			modelTableLinhKien.setRowCount(0);
			modelTableLinhKien.removeTableModelListener(tableLinhKien);
			loadDataToTable();
		}
		if (o.equals(btnTimKiem)) {
			if (txtTimKiem.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Phải nhập thông tin để tìm kiếm!", "Quản lý linh kiện", 2);
			}
			else {
				LinhKien lk = daoLinhKien.getLinhKienTheoMa(txtTimKiem.getText().trim());

				if (lk != null) {
					xoaRong();
					modelTableLinhKien.setRowCount(0);
					modelTableLinhKien.addRow(new Object[] { lk.getMaLk(), lk.getTenLk(), lk.getSoLuong(),
							lk.getNgaySx(), lk.getGia(), lk.getChiTietLk(), lk.getMaLoai().getMaLoai(), lk.getMaNhaCungCap().getMaNhaCungCap()});
				}
				else {
					ArrayList<LinhKien> listTen = daoLinhKien.getLinhKienTheoTen(txtTimKiem.getText().trim());
					if (listTen.size() != 0) {
						xoaRong();
						modelTableLinhKien.setRowCount(0);
						loadDataToTable(listTen);
					}
					else {
						ArrayList<LinhKien> listLoai = daoLinhKien.getLinhKienTheoLoaiLK(txtTimKiem.getText().trim());
						if (listLoai.size() != 0) {
							xoaRong();
							modelTableLinhKien.setRowCount(0);
							loadDataToTable(listLoai);
						}
						else {
							ArrayList<LinhKien> listNcc = daoLinhKien.getLinhKienTheoNhaCungCap(txtTimKiem.getText().trim());
							if (listNcc.size() != 0) {
								xoaRong();
								modelTableLinhKien.setRowCount(0);
								loadDataToTable(listNcc);
							}
							else {
//								ArrayList<LinhKien> listGia = daoLinhKien.getLinhKienTheoGia(Float.parseFloat(txtTimKiem.getText().trim()));
//								if (listGia.size() != 0) {
//									xoaRong();
//									modelTableLinhKien.setRowCount(0);
//									loadDataToTable(listGia);
//								}
//								else 
									if (lk==null && listTen.size() == 0 && listLoai.size() == 0 && listNcc.size() == 0 ) {
										JOptionPane.showMessageDialog(this, "Không tìm thấy linh kiện có thông tin \""
												+ txtTimKiem.getText() + "\"!", "Quản lý linh kiện", 2);
									}
							}
						
						}		
					}
				}
			}
		}
	}
	private void setPanel(JPanel pn) {
//		this.removeAll();
//		this.add(pn);
//		this.repaint();
//		this.invalidate();
		this.setVisible(false);
		this.add(pn);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		Object o = evt.getSource();
		if(o.equals(JdateNgaySX) && JdateNgaySX.getDate()!=null) 
			txtNgaySX.setText(new SimpleDateFormat("dd/MM/yyyy").format(JdateNgaySX.getDate()) );
		
	}

	public void xoaRong() {
		txtTenLK.setText("");
		txtSoLuong.setText("");
		txtChiTiet.setText("");
		JdateNgaySX.setDate(null);
		cbLoaiLK.setSelectedIndex(0);
		cbNsx.setSelectedIndex(0);
		txtTimKiem.setText("");
		txtGia.setText("");
	}
	
	private boolean checkInput() {
		String tenLk = txtTenLK.getText().trim();
		String soLuong = (txtSoLuong.getText().trim());
		LocalDate nsx = JdateNgaySX.getDate()!=null?LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(JdateNgaySX.getDate())):null;
		
		if (tenLk.length() < 0) {
			JOptionPane.showMessageDialog(this, "Tên linh kiện không được để trống");
			txtTenLK.requestFocus();
			return false;
		}
		try {
			if (soLuong.length() < 0) {
				JOptionPane.showMessageDialog(this, "Số lượng không được để trống");
				txtSoLuong.requestFocus();
				return false;
			} 
				if ((Integer.parseInt(txtSoLuong.getText().trim())) < 1 ) {
					JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
					txtSoLuong.requestFocus();
					return false;
				} 
		}catch (NumberFormatException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Số lượng phải là kí tự số");
		}
//		txtNgaySX.getText().trim().length() <0
		if ( nsx == null) {
			JOptionPane.showMessageDialog(this, "Ngày sản xuất không được để trống");
			JdateNgaySX.requestFocus();
			return false;
		}else {
			LocalDate ngayHienTai = java.time.LocalDate.now();
			if  (nsx.compareTo(ngayHienTai) > 0) {
				JOptionPane.showMessageDialog(this, "Ngày sản xuất không lớn hơn ngày hiện tại");
				JdateNgaySX.requestFocus();
				return false;
			}
		}
		
		try {
			if (txtGia.getText().trim().length() < 0) {
				JOptionPane.showMessageDialog(this, "Giá không được để trống");
				txtGia.requestFocus();
				return false;
			} 
				if (Double.parseDouble(txtGia.getText().trim()) < 1 ) {
					JOptionPane.showMessageDialog(this, "Giá phải lớn hơn 0");
					txtGia.requestFocus();
					return false;
				} 
		}catch (NumberFormatException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Giá phải là kí tự số");
		}
		
		
		
		return true;
	}
	
	private LinhKien createLinhkien() {
		String tenLK= txtTenLK.getText().trim();
		Integer soLuong = Integer.parseInt(txtSoLuong.getText().trim());
		LocalDate ngaySx= JdateNgaySX.getDate()!=null?LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(JdateNgaySX.getDate())):null;
		Double gia = Double.parseDouble(txtGia.getText().trim()); 
		String chiTiet = txtChiTiet.getText().trim();
		NhaCungCap ncc = new NhaCungCap(cbNsx.getSelectedItem().toString());
		LoaiLinhKien llk = new LoaiLinhKien(cbLoaiLK.getSelectedItem().toString());
		
		LinhKien lk= new LinhKien(tenLK, soLuong, ngaySx, gia, chiTiet, ncc, llk);
		return lk;
	}
	
	public void loadDataToTable() {
		try {
			for (LinhKien lk : listLinhKien) {
//				new DecimalFormat("###,###,###").format(tongTien)+" vnd"+""
//				lk.getGia()
				modelTableLinhKien.addRow(new Object[] { lk.getMaLk(), lk.getTenLk(), lk.getSoLuong(),
						lk.getNgaySx(), new DecimalFormat("###,###,###").format(lk.getGia())+" vnd"+"", lk.getChiTietLk(), lk.getMaLoai().getMaLoai(), lk.getMaNhaCungCap().getMaNhaCungCap()});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void loadDataToTable(ArrayList<LinhKien> listLk) {
		try {
			for (LinhKien lk : listLk) {
				modelTableLinhKien.addRow(new Object[] { lk.getMaLk(), lk.getTenLk(), lk.getSoLuong(),
						lk.getNgaySx(), lk.getGia(), lk.getChiTietLk(), lk.getMaLoai().getMaLoai(), lk.getMaNhaCungCap().getMaNhaCungCap()});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
}
