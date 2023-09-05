package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.NhanVienDAO ;
import dao.KhachHangDAO;
import dao.LinhKien_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;
import dao.ChiTietHoaDon_Dao;
import dao.HoaDon_DAO;
import java.awt.Component;
import javax.swing.JSpinner;
import javax.swing.JList;

public class GD_LapHoaDon extends JPanel implements ActionListener, MouseListener, PropertyChangeListener, ChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new GD_LapHoaDon().setVisible(true);
	}
	

	private JTextField txtMaHD;
	private DefaultTableModel modelTableHoaDon;
	private JTable tableHoaDon;
	private JButton btnXoaRong;
	private JButton btnThemHD;
	private JButton btnCapNhat;
	private JButton btnSuaHD;
	private JButton btnThemLK;
	private JButton btnSuaLK;
	private JButton btnXoaLK;
	private JButton btnThanhToan;
	
	private JComboBox<String>  cbKhachHang_HoaDon;
	private JDateChooser JdateNgayLapHD;
	private JTextField txtNgayLap;
	private JTextField txtTongTien;
	private JPanel pnCenter;
	private JComboBox<String> cbNhanVien_HoaDon;

	private HoaDon_DAO hdDAO = new HoaDon_DAO();
	private ArrayList<HoaDon> listHD = hdDAO.getAllHoaDon();
	private JTextField txtTimKiem;
	private DefaultTableModel modelTableCTHD;
	private JTable tableCTHD;
	private JLabel lblmaLk;
	private JTextField txtMaLK;
	private JLabel lblsoLuong;
	private JTextField txtTienNhan;
	private JTextField txtTienThua;
	private JTextArea txtDiaChiGH;
	private JLabel lblTenLk;
	private JComboBox<String> cbLinhKien_HoaDon;
	private JButton btnTimKiem;
	private JLabel lblTngTin;
	private JTextField txtTienHang;
	private JButton btnLmRng;
	private boolean trangThai= false;
	private double tongTien;
	private JComboBox cbTimKiemHD;
	private JTextField txtMucGiamGia;
	private JSpinner spnSoLuong;
	private ChiTietHoaDon_Dao ctDao = new ChiTietHoaDon_Dao();
	private ArrayList<LinhKien> listLk= null;
	private LinhKien_DAO lkDAO = new LinhKien_DAO();
	
	public GD_LapHoaDon() {
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
		
		JLabel lblHoaDon = new JLabel("HÓA ĐƠN");
		lblHoaDon.setBackground(new Color(164, 184, 204));
		lblHoaDon.setBounds(450, 5, 200, 26);
		
		lblHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoaDon.setForeground(Color.BLACK);
		lblHoaDon.setFont(new Font("SansSerif", Font.BOLD, 20));
		pnTitle.add(lblHoaDon);
		
		pnCenter.add(pnTitle);
		
		/* -------------Content------------------ */
		JPanel pnContent = new JPanel();
		pnContent.setBackground(SystemColor.controlHighlight);
		pnContent.setLayout(null);
		pnContent.setBounds(0, 40, 1104, 575);
		
		pnCenter.add(pnContent);
		
		JPanel pnHoaDon = new JPanel();
		pnHoaDon.setBackground(SystemColor.controlHighlight);
		pnHoaDon.setLayout(null);
		pnHoaDon.setBounds(0, 0, 1104, 265);
		
		pnContent.add(pnHoaDon);
		
		
		JLabel lblBangHoaDon = new JLabel("Danh sách Hóa đơn");
		lblBangHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblBangHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBangHoaDon.setBounds(711, 0, 165, 32);
		pnHoaDon.add(lblBangHoaDon);
		
		JLabel lblMaHD = new JLabel("Mã hóa đơn: ");
		lblMaHD.setBounds(15, 13, 86, 22);
		lblMaHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnHoaDon.add(lblMaHD);
		
		txtMaHD = new JTextField();
		txtMaHD.setBounds(132, 14, 226, 22);
		pnHoaDon.add(txtMaHD);
		txtMaHD.setColumns(10);
		txtMaHD.setEditable(false);
		
		JLabel lblNhanVien_HoaDon = new JLabel("Nhân viên: ");
		lblNhanVien_HoaDon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNhanVien_HoaDon.setBounds(15, 79, 86, 22);
		pnHoaDon.add(lblNhanVien_HoaDon);
		
		cbNhanVien_HoaDon = new JComboBox ();
		cbNhanVien_HoaDon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbNhanVien_HoaDon.setBounds(132, 80, 226, 22);
		pnHoaDon.add(cbNhanVien_HoaDon);
		cbNhanVien_HoaDon.setModel(layDuLieuCB("NhanVien", "tenNv", "maNv"));
		
		JLabel lblNgayLapHD = new JLabel("Ngày lập hóa đơn:");
		lblNgayLapHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgayLapHD.setBounds(15, 46, 112, 22);
		pnHoaDon.add(lblNgayLapHD);
		
		JdateNgayLapHD = new JDateChooser();
		JdateNgayLapHD.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 15));
		JdateNgayLapHD.setBounds(335, 46, 23, 22);
		JdateNgayLapHD.setDate(new java.util.Date());
		pnHoaDon.add(JdateNgayLapHD);
		
		txtNgayLap= new JTextField();
		txtNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNgayLap.setEditable(false);
		txtNgayLap.setBounds(132, 46, 203, 22);
		pnHoaDon.add(txtNgayLap);
		
		JLabel lblKhachHang_HoaDon = new JLabel("Khách hàng: ");
		lblKhachHang_HoaDon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblKhachHang_HoaDon.setBounds(15, 112, 86, 22);
		pnHoaDon.add(lblKhachHang_HoaDon);
		
		cbKhachHang_HoaDon = new JComboBox <String>();
		cbKhachHang_HoaDon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbKhachHang_HoaDon.setBounds(132, 113, 226, 22);
		cbKhachHang_HoaDon.setEditable(false);
		pnHoaDon.add(cbKhachHang_HoaDon);
		cbKhachHang_HoaDon.setModel(layDuLieuCB("KhachHang", "tenkh", "makh"));
		
		JLabel TongTien = new JLabel("Tổng tiền cần thanh toán: ");
		TongTien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		TongTien.setBounds(15, 202, 152, 22);
		pnHoaDon.add(TongTien);
		
		txtTongTien = new JTextField();
		txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTongTien.setText("0");
		txtTongTien.setBounds(177, 203, 181, 22);
		pnHoaDon.add(txtTongTien);
		txtTongTien.setColumns(20);
		txtTongTien.setEditable(false);
		
		
		String header[] = { "Mã hóa đơn", "Tên nhân viên", "Tên khách hàng",  "Ngày lập hóa đơn", "Địa chỉ giao hàng" , "Tổng tiền", "Trạng thái" };
		modelTableHoaDon = new DefaultTableModel(header,0);
		tableHoaDon = new JTable(modelTableHoaDon);
		tableHoaDon.setRowHeight(35);
		tableHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JScrollPane scrollPane;
		pnHoaDon.add(scrollPane = new JScrollPane(tableHoaDon));
		
		btnThemHD = new JButton("   Thêm   ");
		btnThemHD.setBackground(new Color(146, 220, 150));
		btnThemHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThemHD.setBounds(368, 25, 107, 35);
		pnHoaDon.add(btnThemHD);
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBackground(new Color(255, 155, 155));
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCapNhat.setBounds(368, 65, 107, 35);
		pnHoaDon.add(btnCapNhat);
		btnSuaHD = new JButton("   Sửa   ");
		btnSuaHD.setBackground(new Color(181, 190, 223));
		btnSuaHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSuaHD.setBounds(368, 105, 107, 35);
		pnHoaDon.add(btnSuaHD);
		btnXoaRong = new JButton("Làm rỗng");
		btnXoaRong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnXoaRong.setBounds(368, 203, 107, 23);
		pnHoaDon.add(btnXoaRong);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTimKiem.setBounds(177, 236, 298, 22);
		pnHoaDon.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		cbTimKiemHD = new JComboBox();
		cbTimKiemHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbTimKiemHD.setModel(new DefaultComboBoxModel(new String[] {"Mã hóa đơn", "Khách hàng", "Nhân viên", "Địa chỉ giao hàng", "Linh Kiện"}));
		cbTimKiemHD.setEditable(false);
		cbTimKiemHD.setBounds(15, 235, 152, 22);
		pnHoaDon.add(cbTimKiemHD);
		
		JLabel lblDiaChiGH = new JLabel("Địa chỉ giao hàng:");
		lblDiaChiGH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDiaChiGH.setBounds(15, 146, 112, 22);
		pnHoaDon.add(lblDiaChiGH);
		
		txtDiaChiGH = new JTextArea();
		txtDiaChiGH.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtDiaChiGH.setBounds(132, 147, 343, 46);
		pnHoaDon.add(txtDiaChiGH);
		JScrollPane scrollPaneChiTiet;
		pnHoaDon.add(scrollPaneChiTiet = new JScrollPane(txtDiaChiGH));
		scrollPaneChiTiet.setBounds(132, 146, 343, 46);
		
		
		/////// Chi tiet Hoa Don
		JPanel pnChiTietHoaDon = new JPanel();
		pnChiTietHoaDon.setBackground(SystemColor.controlHighlight);
		pnChiTietHoaDon.setLayout(null);
		pnChiTietHoaDon.setBounds(0, 265, 1104, 299);
		
		pnContent.add(pnChiTietHoaDon);
		
		String row[] = {"Mã linh kiện","Tên Linh Kiện",  "Số Lượng", "Đơn Giá", "Mức giảm giá", "Thành Tiền"};
		modelTableCTHD = new DefaultTableModel(row,0);
		tableCTHD = new JTable(modelTableCTHD);
		tableCTHD.setRowHeight(35);
		tableCTHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JScrollPane scrollPane_1;
		
		pnChiTietHoaDon.add(scrollPane_1 = new JScrollPane(tableCTHD));
		
		lblmaLk = new JLabel("Mã linh kiện:");
		lblmaLk.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblmaLk.setBounds(15, 13, 95, 22);
		pnChiTietHoaDon.add(lblmaLk);
		
		txtMaLK = new JTextField();
		txtMaLK.setEditable(false);
		txtMaLK.setBounds(132, 13, 226, 22);
		pnChiTietHoaDon.add(txtMaLK);
		txtMaLK.setColumns(10);
		
		lblsoLuong = new JLabel("Số Lượng:");
		lblsoLuong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblsoLuong.setBounds(15, 79, 119, 22);
		pnChiTietHoaDon.add(lblsoLuong);
		
		spnSoLuong = new JSpinner(new SpinnerNumberModel(1, 0, 100, 1));
		spnSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spnSoLuong.setBounds(132, 79, 155, 20);
		pnChiTietHoaDon.add(spnSoLuong);
		
		JLabel lblGiamGia = new JLabel("Mức Giảm Giá:");
		lblGiamGia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGiamGia.setBounds(15, 113, 86, 22);
		pnChiTietHoaDon.add(lblGiamGia);
		
		txtMucGiamGia = new JTextField();
		txtMucGiamGia.setBounds(132, 113, 155, 22);
		pnChiTietHoaDon.add(txtMucGiamGia);
		txtMucGiamGia.setColumns(10);
		txtMucGiamGia.setText("0");
		
		btnThemLK = new JButton("   Thêm   ");
		btnThemLK.setBackground(new Color(146, 220, 150));
		btnThemLK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThemLK.setBounds(15, 180, 130, 51);
		pnChiTietHoaDon.add(btnThemLK);
		
		btnXoaLK = new JButton("   Xóa   ");
		btnXoaLK.setBackground(new Color(255, 155, 155));
		btnXoaLK.setBounds(157, 237, 130, 51);
		pnChiTietHoaDon.add(btnXoaLK);
		
		btnSuaLK = new JButton("   Sửa   ");
		btnSuaLK.setBackground(new Color(181, 190, 223));
		btnSuaLK.setBounds(157, 180, 130, 51);
		pnChiTietHoaDon.add(btnSuaLK);
		
		
		
		JLabel lblBangCTHoaDon_1 = new JLabel("Chi tiết Hóa đơn");
		lblBangCTHoaDon_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblBangCTHoaDon_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBangCTHoaDon_1.setBounds(713, 0, 219, 32);
		pnChiTietHoaDon.add(lblBangCTHoaDon_1);
		
		JLabel lblTienNhan = new JLabel("Số tiền nhận:");
		lblTienNhan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTienNhan.setBounds(347, 79, 106, 22);
		pnChiTietHoaDon.add(lblTienNhan);
		
		txtTienNhan = new JTextField();
		txtTienNhan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTienNhan.setBounds(297, 108, 175, 32);
		pnChiTietHoaDon.add(txtTienNhan);
		txtTienNhan.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Số tiền thừa:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(347, 151, 106, 22);
		pnChiTietHoaDon.add(lblNewLabel);
		
		txtTienThua = new JTextField();
		txtTienThua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTienThua.setEditable(false);
		txtTienThua.setBounds(297, 171, 175, 32);
		pnChiTietHoaDon.add(txtTienThua);
		txtTienThua.setColumns(10);
		
		btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.setBackground(new Color(162, 221, 163));
		btnThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThanhToan.setBounds(297, 214, 175, 74);
		pnChiTietHoaDon.add(btnThanhToan);
		
		lblTenLk = new JLabel("Tên linh kiện:");
		lblTenLk.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenLk.setBounds(15, 46, 95, 22);
		pnChiTietHoaDon.add(lblTenLk);
		
		cbLinhKien_HoaDon = new JComboBox <String>();
		cbLinhKien_HoaDon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbLinhKien_HoaDon.setBounds(132, 45, 226, 22);
		cbLinhKien_HoaDon.setEditable(false);
		pnChiTietHoaDon.add(cbLinhKien_HoaDon);
		
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(new Color(119, 128, 227));
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTimKiem.setBounds(367, 13, 106, 57);
		pnChiTietHoaDon.add(btnTimKiem);
		
		lblTngTin = new JLabel("Tổng tiền hàng: ");
		lblTngTin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTngTin.setBounds(15, 146, 106, 22);
		pnChiTietHoaDon.add(lblTngTin);
		
		txtTienHang = new JTextField();
		txtTienHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTienHang.setText("0");
		txtTienHang.setEditable(false);
		txtTienHang.setColumns(10);
		txtTienHang.setBounds(132, 146, 155, 22);
		pnChiTietHoaDon.add(txtTienHang);
		
		btnLmRng = new JButton("Làm rỗng");
		btnLmRng.setBackground(new Color(255, 255, 255));
		btnLmRng.setBounds(15, 237, 130, 51);
		pnChiTietHoaDon.add(btnLmRng);
		
		btnXoaRong.addActionListener(this);
		btnSuaHD.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnThemHD.addActionListener(this);
		btnThemLK.addActionListener(this);
		btnSuaLK.addActionListener(this);
		btnXoaLK.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnLmRng.addActionListener(this);
		
		scrollPane.setBounds(485, 31, 609, 227);
		scrollPane_1.setBounds(482, 35, 612, 253);
		JdateNgayLapHD.addPropertyChangeListener(this);
		tableHoaDon.addMouseListener(this);
		loadDataToTable();
		tableHoaDon.addMouseListener(new MyMouseListener1());
		tableCTHD.addMouseListener(new MyMouseListener2());
	
		
		
	}
	public class MyMouseListener1 extends MouseAdapter {
	    public void mouseClicked(MouseEvent e) {
			int row = tableHoaDon.getSelectedRow();
			HoaDon hd = listHD.get(row);
			
			txtMaHD.setText(hd.getMaHD());
			JdateNgayLapHD.setDate(hd.getNgayLapHD()!=null? java.sql.Date.valueOf(hd.getNgayLapHD()):null);
			setSelectedCombobox(tableHoaDon.getValueAt(row, 1).toString(), cbNhanVien_HoaDon);
			setSelectedCombobox(tableHoaDon.getValueAt(row, 2).toString(), cbKhachHang_HoaDon);
			txtDiaChiGH.setText(hd.getDiaChiGH());
			loadDataToTableCT(hd.getMaHD());
			lamRonglk();
			long tongTien = ctDao.tinhTongTienTheoMaHD(txtMaHD.getText());
			txtTongTien.setText(new DecimalFormat("###,###,###").format(tongTien)+" vnd"+"");
//			cbLinhKien_HoaDon.getSelectedItem();
			
			
	    }
	    
	}

	public class MyMouseListener2 extends MouseAdapter {
	    public void mouseClicked(MouseEvent e) {
			int rowlk = tableCTHD.getSelectedRow();
			String malk = tableCTHD.getValueAt(rowlk, 0).toString();
			String tenlk = tableCTHD.getValueAt(rowlk, 1).toString();
			LinhKien lk = new LinhKien(malk,tenlk);
			int sl = Integer.parseInt(tableCTHD.getValueAt(rowlk, 2).toString());
			float mgg = Float.parseFloat(tableCTHD.getValueAt(rowlk, 4).toString());
			ChiTietHoaDon cthd = new ChiTietHoaDon (lk,sl,mgg);
			
			txtMaLK.setText(cthd.getMaLk().getMaLk());
			setSelectedCombobox(tableCTHD.getValueAt(rowlk, 1).toString(), cbLinhKien_HoaDon);
			txtMucGiamGia.setText(cthd.getMucGiamGia()+"");
			spnSoLuong.setValue(cthd.getSoLuong());
			ChiTietHoaDon cthd1 = createCTHD();
			long tongtienLK = ctDao.tinhTongTienTheoMalK(cthd1);
			txtTienHang.setText(new DecimalFormat("###,###,###").format(tongtienLK)+" vnd"+"");
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

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThemHD) ) {
			HoaDon hd = createHD();
			
				if (hdDAO.addHoaDon(hd)) {
					JOptionPane.showMessageDialog(this,"Thêm thành công.");	
					modelTableHoaDon.setRowCount(0);
					listHD= hdDAO.getAllHoaDon();
					loadDataToTable();
					xoaRongHD();
				}else {
					JOptionPane.showMessageDialog(null, "Thêm không Thành Công");
				}
		}
		if(o.equals(btnCapNhat)) {
			modelTableHoaDon.setRowCount(0);
//			listHD= hdDAO.getAllHoaDon();
			loadDataToTable();
        	cbLinhKien_HoaDon.setModel(layDuLieuCB("LinhKien", "tenLk", "maLk"));
			xoaRongHD();
		}
		if(o.equals(btnXoaRong)) {
			xoaRongHD();
		}
		if(o.equals(btnSuaHD)) {
			int row = tableHoaDon.getSelectedRow();
			if (row >= 0) {
				String maHD = (String) tableHoaDon.getValueAt(row, 0);
				HoaDon hd = createHD(maHD);
	 			int tb = JOptionPane.showConfirmDialog(null,"Xác nhận sửa?","Update",JOptionPane.YES_NO_OPTION);
	 			if(tb == JOptionPane.YES_OPTION)
				try {
					if (hdDAO.updateHoaDon(hd)) {
						JOptionPane.showMessageDialog(null,  "Sửa thành công");
						modelTableHoaDon.setRowCount(0);
						listHD= hdDAO.getAllHoaDon();
						loadDataToTable();
						xoaRongHD();

					} else {
						JOptionPane.showMessageDialog(null, "Sửa không thành công");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Phải chọn 1 dòng trong bảng");
			}
		}
        if (o.equals(btnTimKiem)) {
        	if(cbTimKiemHD.getSelectedItem().toString().equals("Nhân viên")) {
                String tenNV = txtTimKiem.getText();
                if(tenNV.isEmpty() == true) {
                	listHD = hdDAO.getAllHoaDon();
                	loadDataToTable();
                }
                else {
                	modelTableHoaDon.setRowCount(0);
                	listHD = hdDAO.getHoaDonTheoNhanVien(tenNV);
                	if(listHD.size()!=0) {
                		loadDataToTableTheoNhanVien(tenNV);
                	}
                	else {
                		JOptionPane.showMessageDialog(null, "Không tìm thấy");
                	}
                }
                
        	}
        	if(cbTimKiemHD.getSelectedItem().toString().equals("Khách hàng")) {
                String tenKH = txtTimKiem.getText();
                if(tenKH.isEmpty() == true) {
                	listHD = hdDAO.getAllHoaDon();
                	loadDataToTable();
                }
                else {
                	modelTableHoaDon.setRowCount(0);
                	listHD = hdDAO.getHoaDonTheoKhachHang(tenKH);
                	if(listHD.size()!=0) {
                		loadDataToTableTheoKhachHang(tenKH);
                	}
                	else {
                		JOptionPane.showMessageDialog(null, "Không tìm thấy");
                	}
                }
        	}
        	if(cbTimKiemHD.getSelectedItem().toString().equals("Mã hóa đơn")) {
                String ma = txtTimKiem.getText();
                if(ma.isEmpty() == true) {
                	listHD = hdDAO.getAllHoaDon();
                	loadDataToTable();
                }
                else {
                	modelTableHoaDon.setRowCount(0);
                	listHD = hdDAO.getHoaDonTheoMa(ma);
                	if(listHD.size()!=0) {
                		loadDataToTableTheoMaHD(ma);
                	}
                	else {
                		JOptionPane.showMessageDialog(null, "Không tìm thấy");
                	}
                }
                
        	}
        	if(cbTimKiemHD.getSelectedItem().toString().equals("Địa chỉ giao hàng")) {
                String ma = txtTimKiem.getText();
                if(ma.isEmpty() == true) {
                	listHD = hdDAO.getAllHoaDon();
                	loadDataToTable();
                }
                else {
                	modelTableHoaDon.setRowCount(0);
                	listHD = hdDAO.getHoaDonTheoDiaChiGH(ma);
                	if(listHD.size()!=0) {
                		loadDataToTableTheoDiaChiGH(ma);
                	}
                	else {
                		JOptionPane.showMessageDialog(null, "Không tìm thấy");
                	}
                }
                
        	}
        	if (cbTimKiemHD.getSelectedItem().toString().equals("Linh Kiện")) {
        		String ttLk = txtTimKiem.getText().trim();
//        		cbLinhKien_HoaDon.setModel(layDuLieuCB("LinhKien", "tenLk", "maLk"));
        		txtTimKiem.setText("");
        		if (ttLk.isEmpty() == true) {
                	listLk = lkDAO.getAllLinhKien();
                	loadDataToTable();
                	cbLinhKien_HoaDon.setModel(layDuLieuCB("LinhKien", "tenLk", "maLk"));
                }
        		else {
//        			modelTableCTHD.setRowCount(0);
        			listLk = lkDAO.getLinhKienTheoTen(ttLk);
        			if (listLk.size()!=0) {
        				cbLinhKien_HoaDon.removeAllItems();
//        				loadDataToTableTheoLinhKien(ttLk);
        				for (LinhKien lk: listLk) {
        					cbLinhKien_HoaDon.addItem( lk.getTenLk());
        				}
        			}	
        			else {
        				JOptionPane.showMessageDialog(null, "Không tìm thấy linh kiện");
        				cbLinhKien_HoaDon.setModel(layDuLieuCB("LinhKien", "tenLk", "maLk"));
        			}
        		}
//        		cbLinhKien_HoaDon.getSelectedItem()
//        		txtMucGiamGia.setText();
        	}
        }
        if(o.equals(btnLmRng)) {
        	lamRonglk();
        }
        if(o.equals(btnThemLK)) {
        	ChiTietHoaDon cthd = createCTHD();
        	if (ctDao.addCTHoaDon(cthd)) {
				JOptionPane.showMessageDialog(this,"Thêm thành công.");	
				modelTableHoaDon.setRowCount(0);
				listHD= hdDAO.getAllHoaDon();
				loadDataToTable();
				loadDataToTableCT(txtMaHD.getText());
				lamRonglk();
			}else {
				JOptionPane.showMessageDialog(null, "Thêm không Thành Công");
			}
        }
        if(o.equals(btnXoaLK)) {
			int row = tableCTHD.getSelectedRow();
			if (row >= 0) { 
				HoaDon hd = new HoaDon(txtMaHD.getText());
				LinhKien lk = new LinhKien(txtMaLK.getText());
				ChiTietHoaDon cthd = new ChiTietHoaDon(hd,lk);
				int tb = JOptionPane.showConfirmDialog(null,"Xác nhận xoá?","Delete",JOptionPane.YES_NO_OPTION);
	 			if(tb == JOptionPane.YES_OPTION)
				try {
					if (ctDao.deleteCTHoaDon(cthd)) {
						JOptionPane.showMessageDialog(null,  "Xóa thành công");
						modelTableHoaDon.setRowCount(0);
						listHD= hdDAO.getAllHoaDon();
						loadDataToTable();
						loadDataToTableCT(txtMaHD.getText());
						lamRonglk();
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
        if(o.equals(btnSuaLK)) {
			int row = tableCTHD.getSelectedRow();
			if (row >= 0) {
				String maLK = (String) tableCTHD.getValueAt(row, 0);
				ChiTietHoaDon cthd = createCTHD(maLK);
	 			int tb = JOptionPane.showConfirmDialog(null,"Xác nhận sửa?","Update",JOptionPane.YES_NO_OPTION);
	 			if(tb == JOptionPane.YES_OPTION)
				try {
					if (ctDao.updateCTHoaDon(cthd)) {
						JOptionPane.showMessageDialog(null,  "Sửa thành công");
						modelTableHoaDon.setRowCount(0);
						listHD= hdDAO.getAllHoaDon();
						loadDataToTable();
						loadDataToTableCT(txtMaHD.getText());
						lamRonglk();

					} else {
						JOptionPane.showMessageDialog(null, "Sửa không thành công");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Phải chọn 1 dòng trong bảng");
			}
		}
		if(o.equals(btnThanhToan)) {
			int row = tableHoaDon.getSelectedRow();
			
			if (row <0) {
				JOptionPane.showMessageDialog(null, "Chưa chọn hóa đơn");	
			}
			else {
				String maHD = tableHoaDon.getValueAt(row, 0).toString();
				String trangThai = tableHoaDon.getValueAt(row, 6).toString();
				
				if (trangThai.equals("Chưa Thanh Toán")) {
					if (txtTienNhan.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Phải nhập số tiền nhận");
						txtTienNhan.requestFocus();
					}
					else
						if (row >= 0 && txtTienNhan.getText().trim()!= "") {
							int tb = JOptionPane.showConfirmDialog(null,"Xác nhận thanh toán ?","Thanh toán",JOptionPane.YES_NO_OPTION);
				 			if(tb == JOptionPane.YES_OPTION) {
				 				try {
									if (hdDAO.updateThanhToan(maHD)) {
										JOptionPane.showMessageDialog(null,  "Thanh toán thành công");
										modelTableHoaDon.setRowCount(0);
										listHD= hdDAO.getAllHoaDon();
										long tongTien = ctDao.tinhTongTienTheoMaHD(maHD);
//									
			//							(Long.parseLong(txtTienNhan.getText().trim()) - tongTien)+""
						 				txtTienThua.setText(new DecimalFormat("###,###,###").format((Long.parseLong(txtTienNhan.getText().trim()) - tongTien))+" vnd");
										loadDataToTable();
										xoaRongHD();
				
									} 
									else {
										JOptionPane.showMessageDialog(null, "Thanh toán không thành công");
									}
				 				} 
				 				catch (Exception e2) {
									e2.printStackTrace();
				 				}
								
							}
						} 
				}else
					JOptionPane.showMessageDialog(null, "Hóa đơn đã được thanh toán");		
			}
				
		}
		
	}
	public void setPanel(JPanel pn) {
		pnCenter.removeAll();
		pnCenter.add(pn);
		pnCenter.repaint();
		pnCenter.revalidate();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		Object o = evt.getSource();
		if(o.equals(JdateNgayLapHD) && JdateNgayLapHD.getDate()!=null) 
			txtNgayLap.setText(new SimpleDateFormat("dd/MM/yyyy").format(JdateNgayLapHD.getDate()) );
		
	}

	public void xoaRongHD() {
		txtMaHD.setText("");
        cbKhachHang_HoaDon.setSelectedIndex(0);
        cbNhanVien_HoaDon.setSelectedIndex(0);
        txtNgayLap.setText("");
        txtDiaChiGH.setText("");
        txtTongTien.setText("");
        lamRonglk();
		modelTableCTHD.setRowCount(0);
		
	}
	public void lamRonglk() {
		txtMaLK.setText("");
//        cbLinhKien_HoaDon.setSelectedItem(0);
		cbLinhKien_HoaDon.setModel(layDuLieuCB("LinhKien", "tenLk", "maLk"));
        spnSoLuong.setValue(0);
        txtMucGiamGia.setText("0");
        txtTienHang.setText("0");
        txtTienNhan.setText("");
//		txtTienThua.setText("");
	}
	
	public void loadDataToTable() {
		try {
			
			HoaDon_DAO hdDao = new HoaDon_DAO();
			ArrayList<HoaDon> listhd =  hdDao.getAllHoaDon();
			
			for (HoaDon hd : listhd) {
				long tongTien = ctDao.tinhTongTienTheoMaHD(hd.getMaHD());
				txtTongTien.setText(tongTien+"");
				modelTableHoaDon.addRow(new Object[] {  hd.getMaHD(),hd.getNV().getTenNV(),hd.getKH().getTenKH(),hd.getNgayLapHD(),hd.getDiaChiGH(),
						new DecimalFormat("###,###,###").format(tongTien)+" vnd", hd.isTrangThaiThanhToan()?"Chưa Thanh Toán" : "Đã Thanh Toán" });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void loadDataToTableTheoNhanVien(String tenNV) {
		try {
			
			HoaDon_DAO hdDao = new HoaDon_DAO();
			ArrayList<HoaDon> listhd =  hdDao.getHoaDonTheoNhanVien(tenNV);

			for (HoaDon hd : listhd) {
				long tongTien = ctDao.tinhTongTienTheoMaHD(hd.getMaHD());
				txtTongTien.setText(tongTien+"");
				modelTableHoaDon.addRow(new Object[] {  hd.getMaHD(),hd.getNV().getTenNV(),hd.getKH().getTenKH(),hd.getNgayLapHD(),hd.getDiaChiGH(),
						new DecimalFormat("###,###,###").format(tongTien)+" vnd", hd.isTrangThaiThanhToan()?"Chưa Thanh Toán" : "Đã Thanh Toán" });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadDataToTableTheoKhachHang(String tenKH) {
		try {
			
			HoaDon_DAO hdDao = new HoaDon_DAO();
			ArrayList<HoaDon> listhd =  hdDao.getHoaDonTheoKhachHang(tenKH);

			for (HoaDon hd : listhd) {
				long tongTien = ctDao.tinhTongTienTheoMaHD(hd.getMaHD());
				txtTongTien.setText(tongTien+"");
				modelTableHoaDon.addRow(new Object[] {  hd.getMaHD(),hd.getNV().getTenNV(),hd.getKH().getTenKH(),hd.getNgayLapHD(),hd.getDiaChiGH(),
						new DecimalFormat("###,###,###").format(tongTien)+" vnd", hd.isTrangThaiThanhToan()?"Chưa Thanh Toán" : "Đã Thanh Toán" });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadDataToTableTheoMaHD(String maHD) {
		try {
			
			HoaDon_DAO hdDao = new HoaDon_DAO();
			ArrayList<HoaDon> listhd =  hdDao.getHoaDonTheoMa(maHD);

			for (HoaDon hd : listhd) {
				long tongTien = ctDao.tinhTongTienTheoMaHD(hd.getMaHD());
				txtTongTien.setText(tongTien+"");
				modelTableHoaDon.addRow(new Object[] {  hd.getMaHD(),hd.getNV().getTenNV(),hd.getKH().getTenKH(),hd.getNgayLapHD(),hd.getDiaChiGH(),
						new DecimalFormat("###,###,###").format(tongTien)+" vnd", hd.isTrangThaiThanhToan()?"Chưa Thanh Toán" : "Đã Thanh Toán" });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void loadDataToTableTheoDiaChiGH(String ma) {
		try {
			
			HoaDon_DAO hdDao = new HoaDon_DAO();
			ArrayList<HoaDon> listhd =  hdDao.getHoaDonTheoDiaChiGH(ma);

			for (HoaDon hd : listhd) {
				long tongTien = ctDao.tinhTongTienTheoMaHD(hd.getMaHD());
				txtTongTien.setText(tongTien+"");
				modelTableHoaDon.addRow(new Object[] {  hd.getMaHD(),hd.getNV().getTenNV(),hd.getKH().getTenKH(),hd.getNgayLapHD(),hd.getDiaChiGH(),
						 tongTien, hd.isTrangThaiThanhToan()?"Chưa Thanh Toán" : "Đã Thanh Toán" });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void loadDataToTableCT(String maHD) {
		try {
			
			ChiTietHoaDon_Dao ctDao = new ChiTietHoaDon_Dao();
			ArrayList<ChiTietHoaDon> ctList =  ctDao.getAllCTHoaDon(maHD);
			modelTableCTHD.setRowCount(0);
			for (ChiTietHoaDon cTHD : ctList) {
//				new DecimalFormat("###,###,###").format((cTHD.getSoLuong()*cTHD.getMaLk().getGia()))+" vnd"
				modelTableCTHD.addRow(new Object[] {cTHD.getMaLk().getMaLk(), cTHD.getMaLk().getTenLk(), cTHD.getSoLuong(), cTHD.getMaLk().getGia(), cTHD.getMucGiamGia(), (cTHD.getSoLuong()*cTHD.getMaLk().getGia())});
//			(cTHD.getSoLuong()*cTHD.getMaLk().getGia())
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

	private HoaDon createHD() {
		Boolean trangThai = false;
		String maNV = GetCbbSelected(cbNhanVien_HoaDon);
		String maKH = GetCbbSelected(cbKhachHang_HoaDon);
		NhanVien nv = new NhanVien(maNV);
		KhachHang kh = new KhachHang(maKH);
		LocalDate ngayLapHD = JdateNgayLapHD.getDate()!=null?LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(JdateNgayLapHD.getDate())):null;
		
		HoaDon hd = new HoaDon(nv, kh, txtDiaChiGH.getText(), ngayLapHD,trangThai);
		return hd;
		
	}
	private HoaDon createHD(String maHD) {
		Boolean trangThai = false;
		String maNV = GetCbbSelected(cbNhanVien_HoaDon);
		String maKH = GetCbbSelected(cbKhachHang_HoaDon);
		NhanVien nv = new NhanVien(maNV);
		KhachHang kh = new KhachHang(maKH);
		LocalDate ngayLapHD = JdateNgayLapHD.getDate()!=null?LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(JdateNgayLapHD.getDate())):null;
		
		HoaDon hd = new HoaDon(maHD,nv, kh, txtDiaChiGH.getText(), ngayLapHD,trangThai);
		return hd;
		
	}
	private ChiTietHoaDon createCTHD() {
		HoaDon hd = new HoaDon(txtMaHD.getText());
		String maLK = GetCbbSelected(cbLinhKien_HoaDon);
		LinhKien lk = new LinhKien(maLK);
		int sk = Integer.parseInt(spnSoLuong.getValue().toString());
		float mgg = Float.parseFloat(txtMucGiamGia.getText());
		ChiTietHoaDon cthd = new ChiTietHoaDon(hd, lk, sk, mgg);
		return cthd;
	}
	private ChiTietHoaDon createCTHD(String maLK) {
		HoaDon hd = new HoaDon(txtMaHD.getText());
		LinhKien lk = new LinhKien(maLK);
		int sk = Integer.parseInt(spnSoLuong.getValue().toString());
		float mgg = Float.parseFloat(txtMucGiamGia.getText());
		ChiTietHoaDon cthd = new ChiTietHoaDon(hd, lk, sk, mgg);
		return cthd;
	}
    public String GetCbbSelected(JComboBox cbb) {
        Object[] obj = cbb.getSelectedObjects();
        displayvalueModel item = (displayvalueModel) obj[0];
        return item.displayvalue.toString();

    }
    public void setSelectedCombobox(String cbbselected, JComboBox cbb) {
        for (int i = 0; i < cbb.getItemCount(); i++) {
            Object obj = cbb.getItemAt(i);
            if (obj != null) {
                displayvalueModel m = (displayvalueModel) obj;
                if (cbbselected.trim().equals(m.displayMember)) {
                    cbb.setSelectedItem(m);
                }
            }
        }
    }
    public DefaultComboBoxModel layDuLieuCB(String bang, String Ten, String Ma) {
        String sql = "select *from " + bang;
        PreparedStatement ps = null;

        DefaultComboBoxModel cbbmodel = new DefaultComboBoxModel();
        try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                displayvalueModel valueModel = new displayvalueModel(rs.getString(Ten), rs.getString(Ma));
                cbbmodel.addElement(valueModel);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return cbbmodel;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
