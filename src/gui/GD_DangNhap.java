package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import connectDB.ConnectDB;
import dao.TaiKhoanDAO;
import entity.TaiKhoan;

public class GD_DangNhap extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTaiKhoan;
	private JPasswordField txt_Password;
	private JButton btnDangNhap;
	private JButton btnThoat;
	private JButton btnHienPass;
	private boolean password = true;
	private TaiKhoanDAO daoTaiKhoan = new TaiKhoanDAO(ConnectDB.con);
	private JButton btnQuenMatKhau;
	private TaiKhoan tk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_DangNhap frame = new GD_DangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GD_DangNhap() {
	setTitle("Quản lý linh kiện");
		setIconImage(Toolkit.getDefaultToolkit().getImage("image\\logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 213);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_Gmail = new JLabel("Nhập tài khoản");
		lbl_Gmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_Gmail.setBounds(10, 30, 117, 32);
		contentPane.add(lbl_Gmail);

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTaiKhoan.setColumns(10);
		txtTaiKhoan.setBackground(UIManager.getColor("Button.highlight"));
		txtTaiKhoan.setBounds(151, 30, 289, 32);
		contentPane.add(txtTaiKhoan);

		JLabel lbl_Password = new JLabel("Nhập mật khẩu");
		lbl_Password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_Password.setBounds(10, 76, 117, 32);
		contentPane.add(lbl_Password);

		txt_Password = new JPasswordField();
		txt_Password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_Password.setColumns(10);
		txt_Password.setBackground(Color.WHITE);
		txt_Password.setBounds(151, 76, 255, 32);
		contentPane.add(txt_Password);

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setBackground(UIManager.getColor("Button.background"));
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDangNhap.setBounds(127, 128, 112, 25);
		contentPane.add(btnDangNhap);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.setBackground(UIManager.getColor("Button.background"));
		btnThoat.setBounds(249, 128, 100, 25);
		contentPane.add(btnThoat);

		btnHienPass = new JButton("");
		btnHienPass.setIcon(new ImageIcon("img\\password.png"));
		btnHienPass.setBounds(406, 76, 34, 32);
		contentPane.add(btnHienPass);

		btnQuenMatKhau = new JButton("Quên mật khẩu");
		btnQuenMatKhau.setEnabled(false);
		btnQuenMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnQuenMatKhau.setBackground(new Color(240, 240, 240));
		btnQuenMatKhau.setBounds(350, 150, 100, 25);
		contentPane.add(btnQuenMatKhau);
		btnQuenMatKhau.setBorderPainted(false);
		btnQuenMatKhau.setFocusPainted(false);

		btnHienPass.addActionListener(this);
		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);
		btnQuenMatKhau.addActionListener(this);
		
		txt_Password.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		            btnDangNhap.doClick();
				}
			}
		}); }

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThoat)) {
			this.setVisible(false);
		}
		if (o.equals(btnHienPass)) {
			if (password) {
				txt_Password.setEchoChar((char) 0);
				password = false;
				return;
			}
			txt_Password.setEchoChar('•');
			password = true;
		} else if (o.equals(btnDangNhap)) {
			if (txtTaiKhoan.getText().strip() == "" || txt_Password.getText().strip() == "") {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ các thông tin",
						"Quản lý linh kiện", 2);
				return;
			}
			try {
				String userName = txtTaiKhoan.getText().trim();
				String passWord = txt_Password.getText().trim();
				ResultSet dn = daoTaiKhoan.dangNhap(userName, passWord);
				if(dn.next()) {
					JOptionPane.showMessageDialog(this, "Đăng nhập thành công !");					
					setVisible(false);
					tk= daoTaiKhoan.gettaiKhoan(txtTaiKhoan.getText().trim());
					if (tk.getVaiTro().strip().equals("QL")) {
						this.setVisible(false);
						new GD_Chinh_Admin().setVisible(true);
					}
					if (tk.getVaiTro().strip().equals("NV_KETOAN")) {
						this.setVisible(false);
						new GD_QL_KeToan().setVisible(true);
					}
					if (tk.getVaiTro().strip().equals("NV_NHANSU")) {
						this.setVisible(false);
						new GD_QL_NhanSu().setVisible(true);
					}
					if (tk.getVaiTro().strip().equals("NV_BANHANG")) {
						this.setVisible(false);
						new GD_QL_BanHang().setVisible(true);
					}
				}else {
					JOptionPane.showMessageDialog(this, "Bạn nhập sai Tài khoản hoặc Mật khẩu. Vui lòng kiểm tra lại!");
					txt_Password.requestFocus();
				}
				
			}
				catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
