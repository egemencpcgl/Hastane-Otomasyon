import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Color;

public class hstKayit extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hstKayit frame = new hstKayit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con = sqlBaglanti.dbConnector();
	
	public hstKayit() {
		setTitle("Hasta Kay\u0131t");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 376, 377);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(95, 158, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ad\u0131n\u0131z:");
		lblNewLabel.setBounds(63, 42, 67, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(152, 39, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblSoyadnz = new JLabel("Soyad\u0131n\u0131z:");
		lblSoyadnz.setBounds(63, 101, 86, 14);
		contentPane.add(lblSoyadnz);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(152, 98, 86, 20);
		contentPane.add(textField_1);
		
		JLabel lblTcNo = new JLabel("TC No:");
		lblTcNo.setBounds(63, 160, 67, 14);
		contentPane.add(lblTcNo);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(152, 157, 86, 20);
		contentPane.add(textField_2);
		
		JLabel lblTelefonNo = new JLabel("Telefon No:");
		lblTelefonNo.setBounds(63, 219, 86, 14);
		contentPane.add(lblTelefonNo);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(152, 216, 86, 20);
		contentPane.add(textField_3);
		
		JLabel lblifreniz = new JLabel("\u015Eifreniz:");
		lblifreniz.setBounds(63, 278, 67, 14);
		contentPane.add(lblifreniz);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(152, 275, 86, 20);
		contentPane.add(textField_4);
		
		JButton btnKaytOl = new JButton("Kay\u0131t Ol");
		btnKaytOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try { 
					
					String sorgu = "insert into tbl_hastalar (HastaAd,HastaSoyad,HastaTc,HastaTelefon,HastaSifre) values (?,?,?,?,?)";
					PreparedStatement pst = con.prepareStatement(sorgu);
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4, textField_3.getText());
					pst.setString(5, textField_4.getText());
					pst.executeUpdate();
					con.close();
					JOptionPane.showMessageDialog(null,"Kaydýnýz Baþarýyla Oluþturudu.");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btnKaytOl.setBounds(261, 304, 89, 23);
		contentPane.add(btnKaytOl);
	}
}
