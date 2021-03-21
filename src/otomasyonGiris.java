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
				/// butonuna t�kland��� zaman di�er forma ge�i�
				hstKayit hk = new hstKayit(); // Ba�ka bir formu �a��rmak i�in �ncelikle burada tan�ml�yoruz.
				hk.setVisible(true);          //G�r�n�rl���n� true yaparak �a��rd���m�z form ekrana geliyor.
				
			}
		});
		label.setForeground(Color.BLUE);
		label.setBounds(531, 334, 118, 14);
		frmOtomasyonGiri.getContentPane().add(label);
		
		JButton button = new JButton("Hasta Girisi i\u00E7in T\u0131klay\u0131n");
		button.setBackground(new Color(95, 158, 160));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// butonuna t�kland��� zaman di�er forma ge�i�
				hstGiris hg = new hstGiris();         // Ba�ka bir formu �a��rmak i�in �ncelikle burada tan�ml�yoruz.
				hg.frmHastaGiri.setVisible(true);     //G�r�n�rl���n� true yaparak �a��rd���m�z form ekrana geliyor
				frmOtomasyonGiri.setVisible(false);   // Art�k bu form kullan�lmayacaksa g�r�n�rl���n� false yap�yoruz.
				
			}
		});
		button.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		button.setBounds(36, 211, 484, 137);
		frmOtomasyonGiri.getContentPane().add(button);
		
		JButton button_1 = new JButton("Doktor Girisi i\u00E7in T\u0131klay\u0131n");
		button_1.setBackground(new Color(60, 179, 113));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// butonuna t�kland��� zaman di�er forma ge�i�				
				dktrGiris dg = new dktrGiris();     // Ba�ka bir formu �a��rmak i�in �ncelikle burada tan�ml�yoruz.
				dg.frmDoktorGiri.setVisible(true);  //G�r�n�rl���n� true yaparak �a��rd���m�z form ekrana geliyor.
				frmOtomasyonGiri.setVisible(false); // Art�k bu form kullan�lmayacaksa g�r�n�rl���n� false yap�yoruz.
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
				
				JOptionPane.showMessageDialog(null, "Egemen �apac�o�lu Mat-Bil. 2019", "Hakk�nda",3); //Hakk�mda butonuna t�kland���nda mesaj kutusu a��lacak ve bize mesaj� verecek.
				//parametreler s�ras�yla(ba�l� bile�en,"mesaj","mesaj kutusu ba�l���", mesaj tipi);
				
			}
		});
		btnNewButton_1.setToolTipText("Hakk\u0131nda");
		btnNewButton_1.setBounds(90, 0, 89, 23);
		frmOtomasyonGiri.getContentPane().add(btnNewButton_1);
	}
}
