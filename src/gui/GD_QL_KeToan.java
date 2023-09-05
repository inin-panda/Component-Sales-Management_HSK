package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GD_QL_KeToan extends JFrame implements ActionListener{
	private JPanel contentPane;
	private JButton btnTrangChu;
	private JButton btnLinhKien;
	private JButton btnNhanVien;
	private JButton btnKhachHang;
	private JButton btnHoaDon, btnThongKe, btnDangXuat;
	private JPanel pnCenter;
	private JButton btnLoaiLinhKien;
	private JButton btnNhaCungCap;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_QL_KeToan frame = new GD_QL_KeToan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GD_QL_KeToan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1311,700);
		this.setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(new Color(238, 238, 238));
		panel.setBounds(0, 0, 1311, 49);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblXinChao = new JLabel("NHÂN VIÊN KẾ TOÁN");
		lblXinChao.setBackground(new Color(238, 238, 238));
		lblXinChao.setBounds(0, 0, 300, 49);
		lblXinChao.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblXinChao);
		lblXinChao.setForeground(new Color(121, 138, 153));
		lblXinChao.setFont(new Font("SansSerif", Font.BOLD, 25));
//---------- MENU ---------------		
		JPanel pnMenu = new JPanel();
		pnMenu.setLayout(null);
		pnMenu.setBackground(new Color(205, 221, 237));
		pnMenu.setBounds(0, 48, 201, 611);
		contentPane.add(pnMenu);
		
		btnTrangChu = new JButton("TRANG CHỦ");
		btnTrangChu.setBounds(0, 0, 200, 65);
		btnTrangChu.setFont(new Font("SansSerif", Font.BOLD, 20));
//		btnTrangChu.setBackground(new Color(205, 221, 237));
		pnMenu.add(btnTrangChu);
		
		btnLinhKien = new JButton("LINH KIỆN");
		btnLinhKien.setBounds(0, 65, 200, 65);
		btnLinhKien.setFont(new Font("SansSerif", Font.BOLD, 20));
//		btnLinhKien.setBackground(new Color(205, 221, 237));
		pnMenu.add(btnLinhKien);
		
		btnNhanVien = new JButton("NHÂN VIÊN");
		btnNhanVien.setBounds(0, 260, 200, 65);
		btnNhanVien.setFont(new Font("SansSerif", Font.BOLD, 20));
//		btnNhanVien.setBackground(new Color(205, 221, 237));
		pnMenu.add(btnNhanVien);
		
		btnKhachHang= new JButton("KHÁCH HÀNG");
		btnKhachHang.setBounds(0, 325, 200, 65);
		btnKhachHang.setFont(new Font("SansSerif", Font.BOLD, 20));
//		btnKhachHang.setBackground(new Color(205, 221, 237));
		pnMenu.add(btnKhachHang);
		
		btnHoaDon= new JButton("HÓA ĐƠN");
		btnHoaDon.setBounds(0, 390, 200, 65);
		btnHoaDon.setFont(new Font("SansSerif", Font.BOLD, 20));
//		btnHoaDon.setBackground(new Color(205, 221, 237));
		pnMenu.add(btnHoaDon);
		
		btnThongKe= new JButton("THỐNG KÊ");
		btnThongKe.setBounds(0, 455, 200, 65);
		btnThongKe.setFont(new Font("SansSerif", Font.BOLD, 20));
//		btnThongKe.setBackground(new Color(205, 221, 237));
		pnMenu.add(btnThongKe);
		
		btnDangXuat= new JButton("Đăng xuất");
		btnDangXuat.setBounds(0, 546, 200, 65);
		btnDangXuat.setFont(new Font("SansSerif", Font.BOLD, 18));
//		btnDangXuat.setBackground(new Color(205, 221, 237));
		pnMenu.add(btnDangXuat);
		
		btnLoaiLinhKien = new JButton("LOẠI LINH KIỆN");
		btnLoaiLinhKien.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnLoaiLinhKien.setBounds(0, 130, 200, 65);
		pnMenu.add(btnLoaiLinhKien);
		
		btnNhaCungCap = new JButton("NHÀ CUNG CẤP");
		btnNhaCungCap.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnNhaCungCap.setBounds(0, 195, 200, 65);
		pnMenu.add(btnNhaCungCap);
	/* -----------------GD_ThaoTac---------------------*/
		pnCenter = new JPanel();
		pnCenter.setBounds(201, 48, 1104, 611);	
		pnCenter.setBorder(null);
		pnCenter.add(new GD_TrangChu());

		contentPane.add(pnCenter);

		btnNhanVien.addActionListener(this);
		btnDangXuat.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnKhachHang.addActionListener(this);
		btnLinhKien.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnTrangChu.addActionListener(this);
		btnLoaiLinhKien.addActionListener(this);
		btnNhaCungCap.addActionListener(this);
	}

	private void setPanel(JPanel pn) {
		pnCenter.removeAll();
		pnCenter.add(pn);
		pnCenter.repaint();
		pnCenter.revalidate();
	}
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if (obj.equals(btnTrangChu)) {
			setPanel(new GD_TrangChu());
		}
		if (obj.equals(btnThongKe)) {
			setPanel(new GD_ThongKe());
		}
		if (obj.equals(btnHoaDon)) {
			setPanel(new GD_LapHoaDon());
		}
		if (obj.equals(btnKhachHang)) {
			setPanel(new GD_KhachHang());
		}
		
		else if (obj.equals(btnDangXuat)) {
			GD_DangNhap frmDangNhap = null;
			frmDangNhap = new GD_DangNhap();
			this.setVisible(false);
			frmDangNhap.setVisible(true);
		}
	}
}
