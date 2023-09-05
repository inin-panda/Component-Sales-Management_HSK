package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

import dao.LoaiLinhKien_DAO;
import entity.LoaiLinhKien;
import entity.NhaCungCap;


public class GD_LoaiLinhKien  extends JPanel implements ActionListener, MouseListener{

	public static void main(String[] args) {
		new GD_LoaiLinhKien().setVisible(true);
	}

	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JButton btnXoaRong;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private DefaultTableModel modelTableLoaiLK;
	private JTable tableLoaiLK;
	private JTextField txtTenLoai;
	private JButton btnCapNhat;
	
	private LoaiLinhKien_DAO daoLoaiLK = new LoaiLinhKien_DAO();
	private ArrayList<LoaiLinhKien> listLoaiLK = daoLoaiLK.getAllLoaiLinhKien();
	private JTextField txtSoLuong;
	private JComboBox cbLocLinhKien;
	private JPanel pnCenter;
	private JButton btnLoc;
	
	public GD_LoaiLinhKien() {
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
		
		JLabel lblLK = new JLabel("LOẠI LINH KIỆN");
		lblLK.setBackground(new Color(164, 184, 204));
		lblLK.setBounds(450, 5, 200, 26);
		
		lblLK.setHorizontalAlignment(SwingConstants.CENTER);
		lblLK.setForeground(Color.BLACK);
		lblLK.setFont(new Font("SansSerif", Font.BOLD, 20));
		pnTitle.add(lblLK);
		
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
		
		/* -------------Content------------------ */
		JPanel pnContent = new JPanel();
		pnContent.setBackground(SystemColor.controlHighlight);
		pnContent.setLayout(null);
		pnContent.setBounds(0, 40, 1104, 575);
		
		pnCenter.add(pnContent);
		

		JLabel lblTenLoaiLK = new JLabel("Tên loại linh kiện: ");
		lblTenLoaiLK.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenLoaiLK.setBounds(10, 13, 124, 22);
		pnContent.add(lblTenLoaiLK);
		
		txtTenLoai = new JTextField();
		txtTenLoai.setColumns(10);
		txtTenLoai.setBounds(147, 13, 546, 22);
		pnContent.add(txtTenLoai);
		
		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSoLuong.setBounds(730, 13, 84, 22);
		pnContent.add(lblSoLuong);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(800, 13, 294, 22);
		pnContent.add(txtSoLuong);
		
		String header[] = { "Mã loại linh kiện", "Tên loại linh kiện", "Số lượng của loại" }; 
		modelTableLoaiLK = new DefaultTableModel(header,0);
		
		tableLoaiLK = new JTable(modelTableLoaiLK);
		tableLoaiLK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tableLoaiLK.setRowHeight(35);
		
		tableLoaiLK.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane;
		pnContent.add(scrollPane = new JScrollPane(tableLoaiLK));
		scrollPane.setBounds(10, 46, 1085, 471);

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
		tableLoaiLK.addMouseListener(this);
//		btnLoc.addActionListener(this);
		loadDataToTable();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
			int row = tableLoaiLK.getSelectedRow();
			LoaiLinhKien llk = listLoaiLK.get(row);
			txtTenLoai.setText(llk.getTenLoai());
			txtSoLuong.setText(llk.getSoLuongLinhKien()+"");
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
				LoaiLinhKien llk =createLoaiLK();
				if (daoLoaiLK.checkExist(llk.getTenLoai())) {
					JOptionPane.showMessageDialog(null, "Loại linh kiện đã tồn tại");
					return;
				}
				else
					if (daoLoaiLK.addLoaiLinhKien(llk)) {
						JOptionPane.showMessageDialog(this,"Thêm thành công.");	
						modelTableLoaiLK.setRowCount(0);
						listLoaiLK= daoLoaiLK.getAllLoaiLinhKien();
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
		if(o.equals(btnXoa)) {
			int index = tableLoaiLK.getSelectedRow();
			if(index<0) {
				JOptionPane.showMessageDialog(this, "Chưa chọn loại linh kiện!", "Quản lý linh kiện", 2);
				return;
			}
			
			int i = JOptionPane.showConfirmDialog(
					this, "Bạn có muốn xóa loai linh kiện có mã \""
							+ listLoaiLK.get(index).getMaLoai() + "\" không?",
					"Quản lý linh kiện", 2);
			if (i == 0) {
				try {
					daoLoaiLK.deleteLoaiLinhKien(listLoaiLK.get(index).getMaLoai());
					modelTableLoaiLK.setRowCount(0);
					listLoaiLK = daoLoaiLK.getAllLoaiLinhKien();
					loadDataToTable();
//					tableLoaiLK.updateUI();
					xoaRong();
					JOptionPane.showMessageDialog(this, "Xóa thành công!", "Quản lý linh kiện", 1);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Quản lý linh kiện", 2);
				}
			} else
				return;
		}
		if(o.equals(btnCapNhat)) {
			xoaRong();
			modelTableLoaiLK.setRowCount(0);
			modelTableLoaiLK.removeTableModelListener(tableLoaiLK);
			loadDataToTable();
		}
		
		if(o.equals(btnSua)) {
			int index = tableLoaiLK.getSelectedRow();
			if(index<0) {
				JOptionPane.showMessageDialog(this, "Chưa chọn loại linh kiện!", "Quản lý linh kiện", 2);
				return;
			}
			
			if(checkInput()) {
				LoaiLinhKien llk =createLoaiLK();
				
				LoaiLinhKien llkCu = listLoaiLK.get(index);
				llk.setMaLoai(llkCu.getMaLoai());
				if (daoLoaiLK.updateLoaiLinhKien(llk)) {
					JOptionPane.showMessageDialog(this,"Chỉnh sửa thành công.");	
					modelTableLoaiLK.setRowCount(0);
					listLoaiLK= daoLoaiLK.getAllLoaiLinhKien();
					loadDataToTable();
					xoaRong();
				}else {
					JOptionPane.showMessageDialog(null, "Chỉnh sửa không Thành Công");
				}
			}
		}
		
		if (o.equals(btnTimKiem)) {
			if (txtTimKiem.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Phải nhập thông tin để tìm kiếm!", "Quản lý linh kiện", 2);
			}
			else {
				LoaiLinhKien llk = daoLoaiLK.getLoaiLinhKienTheoMa(txtTimKiem.getText().trim());

				if (llk != null) {
					xoaRong();
					modelTableLoaiLK.setRowCount(0);
					modelTableLoaiLK.addRow(new Object[] { llk.getMaLoai(), llk.getTenLoai(), llk.getSoLuongLinhKien()});
				}
				else {
					LoaiLinhKien llkTheoTen = daoLoaiLK.getLoaiLinhKienTheoTen(txtTimKiem.getText().trim());
					if (llkTheoTen != null) {
						xoaRong();
						modelTableLoaiLK.setRowCount(0);
						modelTableLoaiLK.addRow(new Object[] { llkTheoTen.getMaLoai(), llkTheoTen.getTenLoai(), llkTheoTen.getSoLuongLinhKien()});
					}
					else
						if (llk==null && llkTheoTen == null) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy loại linh kiện có thông tin \""
									+ txtTimKiem.getText() + "\"!", "Quản lý linh kiện", 2);
						}
			}
			}		
		}
		
	}

	private void xoaRong() {
		// TODO Auto-generated method stub
		txtTenLoai.setText("");
		txtSoLuong.setText("");
		txtTimKiem.setText("");
	}
	private void loadDataToTable() {
		// TODO Auto-generated method stub
		try {
			for (LoaiLinhKien lk : listLoaiLK) {
				modelTableLoaiLK.addRow(new Object[] { lk.getMaLoai(), lk.getTenLoai(), lk.getSoLuongLinhKien()});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private LoaiLinhKien createLoaiLK() {
		// TODO Auto-generated method stub
		String tenLLK = txtTenLoai.getText().trim();
		Integer soLuong = Integer.parseInt(txtSoLuong.getText().trim());
		LoaiLinhKien llk = new LoaiLinhKien(tenLLK, soLuong);
		return llk;
	}
	private boolean checkInput() {
		if (txtTenLoai.getText().trim().length() < 0) {
			JOptionPane.showMessageDialog(this, "Tên loại linh kiện không được để trống");
			txtTenLoai.requestFocus();
			return false;
		}
		try {
			if (txtSoLuong.getText().trim().length() < 0) {
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
		return true;
	}
}
