package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GD_TrangChu extends JPanel{
	private JLabel lblHinh;

	public GD_TrangChu() {
		setBorder(null);
		
		this.setPreferredSize(new Dimension(1110, 612));
		setLayout(null);
		
		JPanel pnCenter = new JPanel();
		pnCenter.setBounds(1, 0, 1104, 611);
		add(pnCenter);
		pnCenter.setLayout(null);
		
		//----------title -------------//
	
		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(new Color(220, 232, 243));
		pnTitle.setBounds(0, 0, 1104, 39);
		pnTitle.setLayout(null);
		
		JLabel lblLK = new JLabel("TRANG CHỦ");
		lblLK.setBackground(new Color(164, 184, 204));
		lblLK.setBounds(450, 5, 200, 26);
		
		lblLK.setHorizontalAlignment(SwingConstants.CENTER);
		lblLK.setForeground(Color.BLACK);
		lblLK.setFont(new Font("SansSerif", Font.BOLD, 20));
		pnTitle.add(lblLK);
		
		pnCenter.add(pnTitle);
		
		//-----------content ----------------//
		JPanel pnContent = new JPanel();
		
		pnContent.setBackground(SystemColor.controlHighlight);
		pnContent.setLayout(null);
		pnContent.setBounds(0, 40, 1104, 575);
		pnCenter.add(pnContent);
		
		
		JPanel pnImg = new JPanel();
		lblHinh = new JLabel(new ImageIcon("img\\MH_TrangChu.jpg"));
		pnImg.add(lblHinh);
		pnImg.setBounds(0, 30, 1104, 380);
		pnImg.setBackground(SystemColor.controlHighlight);
		pnContent.add(pnImg);
		
		JPanel pnSouth = new JPanel();
		JLabel lblDiaChi, lblSdt, lblFaceBook, lblWebSite, lblEmail;
		pnSouth.add(lblDiaChi = new JLabel("Địa chỉ", new ImageIcon("img\\dia_chi.png"), SwingConstants.CENTER));
		pnSouth.add(lblSdt = new JLabel("Sđt", new ImageIcon("img\\sdt.png"), SwingConstants.CENTER));
		pnSouth.add(lblFaceBook = new JLabel("FaceBook", new ImageIcon("img\\facebook.png"), SwingConstants.CENTER));
		pnSouth.add(lblWebSite = new JLabel("WebSite", new ImageIcon("img\\website.png"), SwingConstants.CENTER));
		pnSouth.add(lblEmail = new JLabel("E-Mail", new ImageIcon("img\\email.png"), SwingConstants.CENTER));
		pnSouth.setBounds(0, 439, 1104, 125);
		pnSouth.setBackground(SystemColor.controlHighlight);
		pnContent.add(pnSouth);
		
		lblDiaChi.setPreferredSize(lblFaceBook.getPreferredSize());
		lblSdt.setPreferredSize(lblFaceBook.getPreferredSize());
		lblWebSite.setPreferredSize(lblFaceBook.getPreferredSize());
		lblEmail.setPreferredSize(lblFaceBook.getPreferredSize());
		
		lblDiaChi.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblSdt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblWebSite.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblEmail.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblFaceBook.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		lblDiaChi.setToolTipText("Địa chỉ");
		lblSdt.setToolTipText("Số điện thoại");
		lblFaceBook.setToolTipText("FaceBook");
		lblWebSite.setToolTipText("Website");
		lblEmail.setToolTipText("E-mail");
	}
}
