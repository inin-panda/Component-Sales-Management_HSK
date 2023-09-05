package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;

import connectDB.ConnectDB;
import dao.TaiKhoanDAO;
import entity.TaiKhoan;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class changePassword extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField txtPass;
	private JButton btnCapNhat, btnHienPass,btnThoat;
	private TaiKhoanDAO tk_dao = new TaiKhoanDAO(ConnectDB.con);
	private JTextField txtTaiKhoan;
//	private TaiKhoan tk;
	private boolean password = true;
	public changePassword() {
		setResizable(false);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setSize(500,200);
		JLabel lblNewLabel = new JLabel("Đổi mật khẩu");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblNewLabel.setBounds(207, 11, 92, 14);
		getContentPane().add(lblNewLabel);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(179, 36, 153, 20);
		getContentPane().add(txtPass);
		txtPass.setColumns(10);
		
		JLabel lblMk = new JLabel("Nhập mật khẩu mới");
		lblMk.setBounds(10, 42, 119, 14);
		getContentPane().add(lblMk);
		
		 btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBounds(285, 107, 89, 23);
		getContentPane().add(btnCapNhat);
		
		 btnThoat = new JButton("Thoát");
		btnThoat.setBounds(179, 107, 89, 23);
		getContentPane().add(btnThoat);
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setBounds(179, 67, 153, 20);
		getContentPane().add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		
		JLabel lblTK = new JLabel("Nhập tên tài khoản");
		lblTK.setBounds(10, 70, 119, 14);
		getContentPane().add(lblTK);
		
	 btnHienPass = new JButton("");
		btnHienPass.setIcon(new ImageIcon("img\\password.png"));
		btnHienPass.setBounds(334, 36, 29, 23);
		getContentPane().add(btnHienPass);
		btnCapNhat.addActionListener(this);
		btnHienPass.addActionListener(this);
		btnThoat.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnCapNhat)) {
			 try {
				 String pass = txtPass.getText();
				 String tk = txtTaiKhoan.getText();
                 Connection con = ConnectDB.getConnection();
                 PreparedStatement st = con
                         .prepareStatement("Update TaiKhoan set matKhau=? where tenTk=?");
                 st.setString(1, pass);
                 st.setString(2,tk);
                 st.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
                 setVisible(false);
             } catch (SQLException sqlException) {
                 sqlException.printStackTrace();
             }
		}if (o.equals(btnHienPass)) {
			if (password) {
				txtPass.setEchoChar((char) 0);
				password = false;
				return;
			}
			txtPass.setEchoChar('•');
			password = true;
		}
		if(o.equals(btnThoat)) {
			setVisible(false);
		}
	}
}
