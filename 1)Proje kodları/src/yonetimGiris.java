import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.*;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class yonetimGiris {

	public JFrame frmYnetimGiri;
	public JTextField textField;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					yonetimGiris window = new yonetimGiris();
					window.frmYnetimGiri.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	Connection baglanti = sqlBaglanti.dbConnector();  //bu panel için baglantımızı tanımlıyoruz.
	private JPasswordField passwordField;

	public yonetimGiris() {
		initialize();
	}

	private void initialize() {
		frmYnetimGiri = new JFrame();
		frmYnetimGiri.getContentPane().setBackground(new Color(240, 230, 140));
		frmYnetimGiri.setTitle("Y\u00F6netim Giri\u015F");
		frmYnetimGiri.setBounds(100, 100, 496, 279);
		frmYnetimGiri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmYnetimGiri.getContentPane().setLayout(null);
		
		JLabel lblKullancAd = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		lblKullancAd.setFont(new Font("Arial", Font.BOLD, 13));
		lblKullancAd.setBounds(124, 87, 93, 14);
		frmYnetimGiri.getContentPane().add(lblKullancAd);
		
		JLabel lblifre = new JLabel("\u015Eifre:");
		lblifre.setFont(new Font("Arial", Font.BOLD, 13));
		lblifre.setBounds(124, 133, 64, 14);
		frmYnetimGiri.getContentPane().add(lblifre);
		
		JLabel lblYneticiGiriPaneli = new JLabel("Y\u00F6netici Giri\u015F Paneli");
		lblYneticiGiriPaneli.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblYneticiGiriPaneli.setBounds(146, 27, 200, 14);
		frmYnetimGiri.getContentPane().add(lblYneticiGiriPaneli);
		
		textField = new JTextField();
		textField.setBounds(240, 85, 86, 20);
		frmYnetimGiri.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnGiri = new JButton("Giri\u015F");
		btnGiri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				yonetimPaneli yp =new yonetimPaneli(); //geçeğimiz formu burada kullanabilmek için tanıtıyoruz.
				try {
					
					String sorgu = ("select * from tbl_yonetici where kullaniciAdi=? and kullaniciSifre=?"); //girilen verileri tabloda kontrol edecek bağlantı adresimiz
					PreparedStatement pst = baglanti.prepareStatement(sorgu);   //sorgudaki bilinmeyen parametrelerin nereden geleceğini belli etmek icin.
					pst.setString(1, textField.getText());               //ilk girilen veriyi tc sütununda arayacak komut satırı. 
					pst.setString(2, passwordField.getText());            //ikinci girilen veriyi sifre sütununda arayacak komut satırı.
					ResultSet rs = pst.executeQuery();
					int count=0 ;
					while(rs.next())
					{
						count++;		// eğer ki sorgumuz doğru çalıştığında sayacımızı bir arttıracağız.			 
					}
					if(count==1) {    //sayacımız 1 olduğu koşulda formlar arası geçiş gerçekleşcek.
						
						yp.frmYnetimPaneli.setVisible(true);
						frmYnetimGiri.setVisible(false);

					}
					else {// sorgu yanlış çalıştığında hata penceresi açılacak.
						JOptionPane.showMessageDialog(null, "Girdiğiniz bilgiler yanlış. Lütfen tekrar deneyin.","Hata",2);
					}
					
					
				} catch (Exception e1) {
				}
			}
		});
		btnGiri.setBounds(240, 179, 89, 23);
		frmYnetimGiri.getContentPane().add(btnGiri);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(240, 131, 86, 20);
		frmYnetimGiri.getContentPane().add(passwordField);
	}
}
