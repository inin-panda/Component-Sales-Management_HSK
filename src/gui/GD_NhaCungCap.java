package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.NhaCungCap_DAO;
import entity.NhaCungCap;

public class GD_NhaCungCap extends JPanel implements ActionListener, MouseListener{

	private JComboBox cbLocLinhKien;
	private DefaultTableModel modelTableNhaCungCap;
	private JTable tableNhaCungCap;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaRong;
	private JButton btnCapNhat;
	private NhaCungCap_DAO daoNcc = new NhaCungCap_DAO();
	private ArrayList<NhaCungCap> listNcc = daoNcc.getAllNhaCungCap();
	private JTextField txtTenNcc;
	private JTextField txtSoLuongSp;
	private JTextField txtDiaChi;
	private JPanel pnCenter;
	private JButton btnLoc;

	public static void main(String[] args) {
		new GD_NhaCungCap().setVisible(true);
	}
	
	public GD_NhaCungCap() {
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
		
		JLabel lblNCC = new JLabel("NHÀ CUNG CẤP");
		lblNCC.setBackground(new Color(164, 184, 204));
		lblNCC.setBounds(400, 5, 200, 26);
		
		lblNCC.setHorizontalAlignment(SwingConstants.CENTER);
		lblNCC.setForeground(Color.BLACK);
		lblNCC.setFont(new Font("SansSerif", Font.BOLD, 20));
		pnTitle.add(lblNCC);
		
		pnCenter.add(pnTitle);
		

		
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
		
		JPanel pnContent = new JPanel();
		pnContent.setBackground(SystemColor.controlHighlight);
		pnContent.setLayout(null);
		pnContent.setBounds(0, 40, 1104, 575);
		pnCenter.add(pnContent);
		
		JLabel lblTenNcc = new JLabel("Tên nhà cung cấp: ");
		lblTenNcc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenNcc.setBounds(10, 13, 124, 22);
		pnContent.add(lblTenNcc);
		
		txtTenNcc = new JTextField();
		txtTenNcc.setColumns(10);
		txtTenNcc.setBounds(160, 13, 533, 22);
		pnContent.add(txtTenNcc);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ nhà cung cấp: ");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDiaChi.setBounds(10, 46, 145, 22);
		pnContent.add(lblDiaChi);
		
		JLabel lblSoLuong = new JLabel("Số lượng sản phẩm nhập về:");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSoLuong.setBounds(722, 13, 175, 22);
		pnContent.add(lblSoLuong);
		
		txtSoLuongSp = new JTextField();
		txtSoLuongSp.setColumns(10);
		txtSoLuongSp.setBounds(907, 13, 187, 22);
		pnContent.add(txtSoLuongSp);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(160, 46, 934, 22);
		pnContent.add(txtDiaChi);

		String header[] = { "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Số lượng sản phẩm" }; 
		modelTableNhaCungCap = new DefaultTableModel(header,0);
		
		tableNhaCungCap = new JTable(modelTableNhaCungCap);
		tableNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tableNhaCungCap.setRowHeight(35);
		
		tableNhaCungCap.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane;
		pnContent.add(scrollPane = new JScrollPane(tableNhaCungCap));
		scrollPane.setBounds(10, 77, 1085, 440);
		
		//-------------------south----------------------//
		JSplitPane splitPaneBtn = new JSplitPane();
		splitPaneBtn.setBounds(10, 528, 1095, 35);
		pnContent.add(splitPaneBtn);
		
		JPanel pnLeft = new JPanel();
		splitPaneBtn.setLeftComponent(pnLeft);
		
		JLabel lblTimKiem = new JLabel("Nhập thông tin cần tìm: ");
		pnLeft.add(lblTimKiem);
		
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
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnCapNhat.addActionListener(this);
		tableNhaCungCap.addMouseListener(this);
//		cbLocLinhKien.addActionListener(this);
//		btnLoc.addActionListener(this);
		loadDataToTable();		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableNhaCungCap.getSelectedRow();
		NhaCungCap ncc = listNcc.get(row);
		txtTenNcc.setText(ncc.getTenNhaCungCap());
		txtSoLuongSp.setText(ncc.getSoLuongSp()+"");
		txtDiaChi.setText(ncc.getDiaChi());
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
		Object o= e.getSource();
		if(o.equals(btnThem)) {
			if(checkInput()) {
				NhaCungCap ncc =createNCC();
				
				if (daoNcc.checkExist(ncc.getTenNhaCungCap(), ncc.getDiaChi())) {
					JOptionPane.showMessageDialog(null, "Nhà cung cấp đã tồn tại");
					return;
				}
				else
					if (daoNcc.addNCC(ncc)) {
						JOptionPane.showMessageDialog(this,"Thêm thành công.");	
						modelTableNhaCungCap.setRowCount(0);
						listNcc= daoNcc.getAllNhaCungCap();
						loadDataToTable();
						xoaRong();
					}else {
						JOptionPane.showMessageDialog(null, "Thêm không Thành Công");
					}
			}
		}
		if(o.equals(btnXoaRong)) {
			xoaRong();
		}
		if(o.equals(btnSua)) {
			int index = tableNhaCungCap.getSelectedRow();
			if(index<0) {
				JOptionPane.showMessageDialog(this, "Chưa chọn loại linh kiện!", "Quản lý linh kiện", 2);
				return;
			}
			
			if(checkInput()) {
				NhaCungCap ncc = createNCC();
				
				NhaCungCap nccCu = listNcc.get(index);
				ncc.setMaNhaCungCap(nccCu.getMaNhaCungCap());
				if (daoNcc.updateNCC(ncc)) {
					JOptionPane.showMessageDialog(this,"Chỉnh sửa thành công.");	
					modelTableNhaCungCap.setRowCount(0);
					listNcc= daoNcc.getAllNhaCungCap();
					loadDataToTable();
					xoaRong();
				}else {
					JOptionPane.showMessageDialog(null, "Chỉnh sửa không Thành Công");
				}
			}
		}
		if(o.equals(btnCapNhat)) {
			xoaRong();
			modelTableNhaCungCap.setRowCount(0);
			modelTableNhaCungCap.removeTableModelListener(tableNhaCungCap);
			loadDataToTable();
		}
		if(o.equals(btnXoa)) {
			int index = tableNhaCungCap.getSelectedRow();
			if(index<0) {
				JOptionPane.showMessageDialog(this, "Chưa chọn đối tượng!", "Quản lý linh kiện", 2);
				return;
			}
			
			int i = JOptionPane.showConfirmDialog(
					this, "Bạn có muốn xóa nhà cung cấp có mã \""
							+ listNcc.get(index).getMaNhaCungCap() + "\" không?",
					"Quản lý linh kiện", 2);
			if (i == 0) {
				try {
					daoNcc.deleteNCC(listNcc.get(index).getMaNhaCungCap());
					modelTableNhaCungCap.setRowCount(0);
					listNcc = daoNcc.getAllNhaCungCap();
					loadDataToTable();
					xoaRong();
					JOptionPane.showMessageDialog(this, "Xóa thành công!", "Quản lý linh kiện", 1);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Quản lý linh kiện", 2);
				}
			} else
				return;
		}
		if (o.equals(btnTimKiem)) {
			if (txtTimKiem.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Phải nhập thông tin để tìm kiếm!", "Quản lý linh kiện", 2);
			}
			else {
				NhaCungCap ncc = daoNcc.getNCCTheoMa(txtTimKiem.getText().trim());

				if (ncc != null) {
					xoaRong();
					modelTableNhaCungCap.setRowCount(0);
					modelTableNhaCungCap.addRow(new Object[] { ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getDiaChi(), ncc.getSoLuongSp()});
				}
				else {
					NhaCungCap nccTheoTen = daoNcc.getNCCTheoTen(txtTimKiem.getText().trim());
					if (nccTheoTen != null) {
						xoaRong();
						modelTableNhaCungCap.setRowCount(0);
						modelTableNhaCungCap.addRow(new Object[] { nccTheoTen.getMaNhaCungCap(), nccTheoTen.getTenNhaCungCap(), nccTheoTen.getDiaChi(), nccTheoTen.getSoLuongSp()});
					}
					else {
						NhaCungCap nccTheoDiaChi = daoNcc.getNCCTheoDiaChi(txtTimKiem.getText().trim());
						if (nccTheoDiaChi != null){
							xoaRong();
							modelTableNhaCungCap.setRowCount(0);
							modelTableNhaCungCap.addRow(new Object[] { nccTheoDiaChi.getMaNhaCungCap(), nccTheoDiaChi.getTenNhaCungCap(), nccTheoDiaChi.getDiaChi(), nccTheoDiaChi.getSoLuongSp()});
						}
						else 
							if (ncc==null && nccTheoDiaChi == null && nccTheoTen == null) {
								JOptionPane.showMessageDialog(this, "Không tìm thấy nhà cung cấp có thông tin \""
										+ txtTimKiem.getText() + "\"!", "Quản lý linh kiện", 2);
							}
					}
						
				}		
			}
			/*		
					ArrayList<NhaCungCap> listTheoTen = daoNcc.getNCCTheoTen(txtTimKiem.getText().trim());
					ArrayList<NhaCungCap> listTheoDc = daoNcc.getNCCTheoDiaChi(txtTimKiem.getText().trim());
					if (listTheoTen.size() != 0) {
						xoaRong();
						modelTableNhaCungCap.setRowCount(0);
						loadDataToTable(listTheoTen);
					}else 
						if (listTheoDc.size() != 0){
							xoaRong();
							modelTableNhaCungCap.setRowCount(0);
							loadDataToTable(listTheoDc);
						}
						else 
							if (ncc==null && listTheoTen.size()== 0 && listTheoDc.size()==0) {
								JOptionPane.showMessageDialog(this, "Không tìm thấy nhà cung cấp có thông tin \""
										+ txtTimKiem.getText() + "\"!", "Quản lý linh kiện", 2);
							}
							
					} */
		}
	}

	
	private NhaCungCap createNCC() {
		// TODO Auto-generated method stub
		String tenNcc = txtTenNcc.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		Integer soLuong = Integer.parseInt(txtSoLuongSp.getText().trim());
		NhaCungCap ncc = new NhaCungCap(tenNcc, diaChi, soLuong);
		return ncc;
	}

	private boolean checkInput() {
		if (txtTenNcc.getText().trim().length() < 0) {
			JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không được để trống");
			txtTenNcc.requestFocus();
			return false;
		}
		if (txtDiaChi.getText().trim().length() < 0) {
			JOptionPane.showMessageDialog(this, "Địa chỉ nhà cung cấp không được để trống");
			txtDiaChi.requestFocus();
			return false;
		}
		try {
			if (txtSoLuongSp.getText().trim().length() < 0) {
				JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không được để trống");
				txtSoLuongSp.requestFocus();
				return false;
			} 
				if ((Integer.parseInt(txtSoLuongSp.getText().trim())) < 1 ) {
					JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
					txtSoLuongSp.requestFocus();
					return false;
				} 
		}catch (NumberFormatException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Số lượng phải là kí tự số");
		}
		return true;
	}

	private void xoaRong() {
		// TODO Auto-generated method stub
		txtTenNcc.setText("");
		txtSoLuongSp.setText("");
		txtDiaChi.setText("");
		txtTimKiem.setText("");
	}

	private void loadDataToTable() {
		// TODO Auto-generated method stub
		try {
			for (NhaCungCap ncc : listNcc) {
				modelTableNhaCungCap.addRow(new Object[] { ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getDiaChi(), ncc.getSoLuongSp()});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void loadDataToTable(ArrayList<NhaCungCap> listNCC) {
		// TODO Auto-generated method stub
		try {
			for (NhaCungCap ncc : listNCC) {
				modelTableNhaCungCap.addRow(new Object[] { ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getDiaChi(), ncc.getSoLuongSp()});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
