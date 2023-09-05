package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class GD_ThongKe extends JPanel implements ActionListener, MouseListener, PropertyChangeListener, ChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new GD_ThongKe().setVisible(true);
	}

	private JButton btnHienThi;
	private JButton btnTim;
	private DefaultTableModel modelTableDoanhThu;
	private JTable tableLinhKien;
	private JComboBox<String>  cbHienThiTheo;
	private JDateChooser JdateNgaySX;
	private JTextField txtNgaySX;
	private JPanel pnCenter;
	String chonHt=new String();
	
	public GD_ThongKe() {
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
		
		JLabel lblLK = new JLabel("Thống Kê Doanh Thu");
		lblLK.setBackground(new Color(164, 184, 204));
		lblLK.setBounds(415, 5, 279, 26);
		
		lblLK.setHorizontalAlignment(SwingConstants.CENTER);
		lblLK.setForeground(Color.BLACK);
		lblLK.setFont(new Font("SansSerif", Font.BOLD, 20));
		pnTitle.add(lblLK);
		
		pnCenter.add(pnTitle);

		/* -------------Content------------------ */
		JPanel pnContent = new JPanel();
		pnContent.setBackground(SystemColor.controlHighlight);
		pnContent.setLayout(null);
		pnContent.setBounds(0, 40, 1104, 575);
		
		pnCenter.add(pnContent);

		JLabel lblLoaiHT = new JLabel("Hiển thị theo: ");
		lblLoaiHT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLoaiHT.setBounds(250, 46, 90, 22);
		pnContent.add(lblLoaiHT);
		
		cbHienThiTheo = new JComboBox ();
		cbHienThiTheo.setBounds(343, 46, 177, 22);
		pnContent.add(cbHienThiTheo);
		cbHienThiTheo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(cbHienThiTheo.getSelectedIndex()==0) chonHt="0";
				else if(cbHienThiTheo.getSelectedIndex()==1) chonHt="1";
				else if(cbHienThiTheo.getSelectedIndex()==2) chonHt="2";
				else if(cbHienThiTheo.getSelectedIndex()==3) chonHt="3";
			}
		});
		cbHienThiTheo.setModel(new DefaultComboBoxModel(new String[] {"--Chọn--", "Ngày", "Tháng", "Năm"}));
		
		JLabel lblNgaySX = new JLabel("Tìm theo ngày: ");
		lblNgaySX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgaySX.setBounds(250, 13, 95, 22);
		pnContent.add(lblNgaySX);
		
		JdateNgaySX = new JDateChooser();
		JdateNgaySX.setBounds(500, 14, 20, 22);
		JdateNgaySX.setDate(new java.util.Date());
		pnContent.add(JdateNgaySX);
		
		txtNgaySX= new JTextField();
		txtNgaySX.setEditable(false);
		txtNgaySX.setBounds(343, 14, 155, 22);
		pnContent.add(txtNgaySX);
		
		
		btnTim = new JButton();
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnTim.setText("Tìm");
		btnTim.setBounds(655, 14, 100, 22);
		pnContent.add(btnTim);

		btnHienThi = new JButton();
        btnHienThi.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnHienThi.setText("Hiển thị");
		btnHienThi.setBounds(655, 46, 100, 22);
		pnContent.add(btnHienThi);

		
		String header[] = { "Thời gian","Doanh thu"};
		modelTableDoanhThu = new DefaultTableModel(header,0);
		tableLinhKien = new JTable(modelTableDoanhThu);
		tableLinhKien.setRowHeight(35);
		tableLinhKien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JScrollPane scrollPane;
		pnContent.add(scrollPane = new JScrollPane(tableLinhKien));
		scrollPane.setBounds(10, 77, 1085, 440);
		
		JdateNgaySX.addPropertyChangeListener(this);
		btnHienThi.addActionListener(this);
		btnTim.addActionListener(this);
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
	} 
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		Object o = evt.getSource();
		if(o.equals(JdateNgaySX) && JdateNgaySX.getDate()!=null) 
			txtNgaySX.setText(new SimpleDateFormat("dd/MM/yyyy").format(JdateNgaySX.getDate()) );
		
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
