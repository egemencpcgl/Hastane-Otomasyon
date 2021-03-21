import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class hstBilgiDuz extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hstBilgiDuz frame = new hstBilgiDuz();
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
	
	Connection con = sqlBaglanti.dbConnector();
	private JTextField textField_4;
	public void bilgileriAktar(String Tc) {
	
		textField_4.setText(Tc);
		
		try {
			String sorgu=("select HastaAd, HastaSoyad, HastaSifre, HastaTelefon from tbl_hastalar where HastaTc=?");
			PreparedStatement pst = con.prepareStatement(sorgu);
			pst.setString(1, textField_4.getText());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				textField.setText(rs.getString("HastaAd"));
				textField_1.setText(rs.getString("HastaSoyad"));
				textField_2.setText(rs.getString("HastaSifre"));
				textField_3.setText(rs.getString("HastaTelefon"));
			}
			
		} catch (Exception e) {
		}	
		
	}

	public hstBilgiDuz() {
		setTitle("Bilgileri D\u00FCzenle");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 299, 299);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(95, 158, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ad");
		lblNewLabel.setBounds(40, 57, 46, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(125, 54, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(125, 93, 86, 20);
		contentPane.add(textField_1);
		
		JLabel lblSoyad = new JLabel("Soyad");
		lblSoyad.setBounds(40, 96, 46, 14);
		contentPane.add(lblSoyad);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(125, 133, 86, 20);
		contentPane.add(textField_2);
		
		JLabel lblTcNo = new JLabel("\u015Eifre:");
		lblTcNo.setBounds(40, 136, 46, 14);
		contentPane.add(lblTcNo);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(125, 173, 86, 20);
		contentPane.add(textField_3);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setBounds(40, 176, 46, 14);
		contentPane.add(lblTelefon);
		
		JButton btnGncelle = new JButton("G\u00FCncelle");
		btnGncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String sorgu = ("Update tbl_hastalar set hastaSifre=? where HastaTc="+textField_4.getText());					
					PreparedStatement ps = con.prepareStatement(sorgu);
					ps.setString(1, textField_2.getText());
					ps.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Bilgileriniz güncellendi");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Bilgileriniz güncellenmedi");
					e.printStackTrace();
				}
				
				
				
				
			}
		});
		btnGncelle.setBounds(155, 204, 107, 23);
		contentPane.add(btnGncelle);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(125, 22, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblTc = new JLabel("Tc:");
		lblTc.setBounds(40, 25, 46, 14);
		contentPane.add(lblTc);
	}
}
