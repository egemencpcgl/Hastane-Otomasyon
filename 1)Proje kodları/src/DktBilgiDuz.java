import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DktBilgiDuz extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public String TC;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DktBilgiDuz frame = new DktBilgiDuz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con = sqlBaglanti.dbConnector();
	public void bilgiAl(String Tc) {    //Doktor Tc sini giriþ panelinden alabilmek için methodumuzu tanýmlýyoruz.
		TC = Tc;                        //Bu satýrla Almýþ olduðumuz Tc yi kendimiz tanýmladýðýmýz TC stringine atýyoruz.
		try {
			String sorgu = ("select DoktorSifre from tbl_doktorlar where doktorTc=?");   //Aldýðýmýz TC stringine göre Doktor Sifresini textField a otomatik doldurmasý için oluþturduðumuz sorgu.
			PreparedStatement pst = con.prepareStatement(sorgu);
			pst.setString(1, Tc);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				textField.setText(rs.getString(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * Create the frame.
	 */
	public DktBilgiDuz() {
		setTitle("Bilgilerimi D\u00FCzenle");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 214, 139);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(60, 179, 113));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u015Eifre:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(27, 29, 46, 14);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(83, 26, 86, 20);
		contentPane.add(textField);
		
		JButton btnGncelle = new JButton("G\u00FCncelle");
		btnGncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {   
					String sorgu = ("Update tbl_doktorlar set doktorSifre=? where doktorTc="+TC);			// Güncelle butonuna basýldýktan sonra doktor þifresini güncelleyen sorgumuz.		
					PreparedStatement ps = con.prepareStatement(sorgu);
					ps.setString(1, textField.getText());
					ps.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Bilgileriniz güncellendi");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Bilgileriniz güncellenmedi");
					e.printStackTrace();
				}
			}
		});
		btnGncelle.setBounds(52, 57, 89, 23);
		contentPane.add(btnGncelle);
	}
}
