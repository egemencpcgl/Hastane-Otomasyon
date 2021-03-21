import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Window;
import java.sql.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class hstGiris {

	public JFrame frmHastaGiri;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					hstGiris window = new hstGiris();
					window.frmHastaGiri.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	
	Connection conn = null;  // boþ bir baðlantý deðiþkeni tanýmlýyoruz.
	
	public JTextField txtAd;
	private JPasswordField txtSýfre;
	private JLabel lblNewLabel;
	public hstGiris() {
		
		initialize();
		conn=sqlBaglanti.dbConnector();  // tanimladigimiz baglantiya sqlBaglanti sinifimizdaki metodunu atadýk.
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmHastaGiri = new JFrame();
		frmHastaGiri.getContentPane().setBackground(new Color(95, 158, 160));
		frmHastaGiri.setTitle("Hasta Giri\u015F");
		frmHastaGiri.setBounds(100, 100, 495, 279);
		frmHastaGiri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHastaGiri.getContentPane().setLayout(null);
		
		JLabel lblKullancAd = new JLabel("TC No:");
		lblKullancAd.setFont(new Font("Arial", Font.BOLD, 13));
		lblKullancAd.setBounds(147, 75, 85, 18);
		frmHastaGiri.getContentPane().add(lblKullancAd);
		
		JLabel lblifre = new JLabel("\u015Eifre:");
		lblifre.setFont(new Font("Arial", Font.BOLD, 13));
		lblifre.setBounds(147, 121, 85, 18);
		frmHastaGiri.getContentPane().add(lblifre);
		
		
		
		txtSýfre = new JPasswordField();
		txtSýfre.setBounds(231, 121, 86, 20);
		frmHastaGiri.getContentPane().add(txtSýfre);
		
		txtAd = new JTextField();
		txtAd.setBounds(231, 75, 86, 20);
		frmHastaGiri.getContentPane().add(txtAd);
		txtAd.setColumns(10);

		
		JButton btnGrs = new JButton("Giri\u015F");
		btnGrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hastaPaneli hp = new hastaPaneli();			
			    hp.tcTasima(txtAd.getText());
							// Giris yapmak için baðlantýmýzý açýýyoruz
				try {
					
					String sorgu = ("select * from tbl_hastalar where HastaTc=? and HastaSifre=?"); //girilen verileri tabloda kontrol edecek baðlantý adresimiz
					PreparedStatement pst = conn.prepareStatement(sorgu);   //sorgudaki bilinmeyen parametrelerin nereden geleceðini belli etmek icin.
					pst.setString(1, txtAd.getText());               //ilk girilen veriyi tc sütununda arayacak komut satýrý. 
					pst.setString(2, txtSýfre.getText());            //ikinci girilen veriyi sifre sütununda arayacak komut satýrý.
					ResultSet rs = pst.executeQuery();
					int count=0 ;
					while(rs.next())
					{
						count++;					
					}
					if(count==1) {
					    hp.frmHastaPaneli.setVisible(true);
					    frmHastaGiri.setVisible(false);
					}
					else {
						JOptionPane.showConfirmDialog(null, "Girdiðiniz bilgiler yanlýþ. Lütfen tekrar deneyin.");
					}
					
					
				} catch (Exception e) {
				}
			}
		});
		btnGrs.setBounds(231, 162, 89, 23);
		frmHastaGiri.getContentPane().add(btnGrs);
		
		lblNewLabel = new JLabel("Hasta Giri\u015F Ekran\u0131");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(147, 11, 235, 44);
		frmHastaGiri.getContentPane().add(lblNewLabel);
		

	}
}
