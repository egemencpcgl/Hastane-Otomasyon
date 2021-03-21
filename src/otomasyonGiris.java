import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.UIManager;

public class otomasyonGiris {

	public JFrame frmOtomasyonGiri;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					otomasyonGiris window = new otomasyonGiris();
					window.frmOtomasyonGiri.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public otomasyonGiris() {
		initialize();
	}

	private void initialize() {
		frmOtomasyonGiri = new JFrame();
		frmOtomasyonGiri.getContentPane().setBackground(new Color(178, 34, 34));
		frmOtomasyonGiri.setTitle("Otomasyon Giri\u015F");
		frmOtomasyonGiri.setBounds(100, 100, 773, 566);
		frmOtomasyonGiri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmOtomasyonGiri.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Kay\u0131t Olmak \u0130\u00E7in T\u0131klay\u0131n.");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/// butonuna týklandýðý zaman diðer forma geçiþ
				hstKayit hk = new hstKayit(); // Baþka bir formu çaðýrmak için öncelikle burada tanýmlýyoruz.
				hk.setVisible(true);          //Görünürlüðünü true yaparak çaðýrdýðýmýz form ekrana geliyor.
				
			}
		});
		label.setForeground(Color.BLUE);
		label.setBounds(531, 334, 118, 14);
		frmOtomasyonGiri.getContentPane().add(label);
		
		JButton button = new JButton("Hasta Girisi i\u00E7in T\u0131klay\u0131n");
		button.setBackground(new Color(95, 158, 160));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// butonuna týklandýðý zaman diðer forma geçiþ
				hstGiris hg = new hstGiris();         // Baþka bir formu çaðýrmak için öncelikle burada tanýmlýyoruz.
				hg.frmHastaGiri.setVisible(true);     //Görünürlüðünü true yaparak çaðýrdýðýmýz form ekrana geliyor
				frmOtomasyonGiri.setVisible(false);   // Artýk bu form kullanýlmayacaksa görünürlüðünü false yapýyoruz.
				
			}
		});
		button.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		button.setBounds(36, 211, 484, 137);
		frmOtomasyonGiri.getContentPane().add(button);
		
		JButton button_1 = new JButton("Doktor Girisi i\u00E7in T\u0131klay\u0131n");
		button_1.setBackground(new Color(60, 179, 113));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// butonuna týklandýðý zaman diðer forma geçiþ				
				dktrGiris dg = new dktrGiris();     // Baþka bir formu çaðýrmak için öncelikle burada tanýmlýyoruz.
				dg.frmDoktorGiri.setVisible(true);  //Görünürlüðünü true yaparak çaðýrdýðýmýz form ekrana geliyor.
				frmOtomasyonGiri.setVisible(false); // Artýk bu form kullanýlmayacaksa görünürlüðünü false yapýyoruz.
			}
		});
		button_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		button_1.setBounds(36, 364, 484, 137);
		frmOtomasyonGiri.getContentPane().add(button_1);
		
		JLabel lblHastaneBilgiSistemi = new JLabel("Hastane Bilgi Sistemi Giri\u015F Paneli");
		lblHastaneBilgiSistemi.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\java-Tez-Resimlerii\\hospital-sign-icon.png"));
		lblHastaneBilgiSistemi.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
		lblHastaneBilgiSistemi.setBounds(169, 34, 532, 128);
		frmOtomasyonGiri.getContentPane().add(lblHastaneBilgiSistemi);
		
		JButton btnNewButton = new JButton("Y\u00F6netici Giri\u015Fi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yonetimGiris yg = new yonetimGiris();
				yg.frmYnetimGiri.setVisible(true);
			}
		});
		btnNewButton.setToolTipText("Y\u00F6netici Giri\u015Fi");
		btnNewButton.setBounds(0, 0, 89, 23);
		frmOtomasyonGiri.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Hakk\u0131nda");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "Egemen Çapacýoðlu Mat-Bil. 2019", "Hakkýnda",3); //Hakkýmda butonuna týklandýðýnda mesaj kutusu açýlacak ve bize mesajý verecek.
				//parametreler sýrasýyla(baðlý bileþen,"mesaj","mesaj kutusu baþlýðý", mesaj tipi);
				
			}
		});
		btnNewButton_1.setToolTipText("Hakk\u0131nda");
		btnNewButton_1.setBounds(90, 0, 89, 23);
		frmOtomasyonGiri.getContentPane().add(btnNewButton_1);
	}
}
