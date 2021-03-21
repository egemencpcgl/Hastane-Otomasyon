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


public class dktrGiris {

	public JFrame frmDoktorGiri;
	private JTextField txtTc;
	private JPasswordField txtSifre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dktrGiris window = new dktrGiris();
					window.frmDoktorGiri.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public dktrGiris() {
		initialize();
	}

	Connection conn = sqlBaglanti.dbConnector();
	private JLabel lblNewLabel;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDoktorGiri = new JFrame();
		frmDoktorGiri.setTitle("Doktor Giri\u015F");
		frmDoktorGiri.getContentPane().setBackground(new Color(60, 179, 113));
		frmDoktorGiri.setBackground(new Color(102, 205, 170));
		frmDoktorGiri.setBounds(100, 100, 495, 279);
		frmDoktorGiri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDoktorGiri.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("TC No:");
		label.setFont(new Font("Arial", Font.BOLD, 13));
		label.setBounds(149, 66, 85, 18);
		frmDoktorGiri.getContentPane().add(label);
		
		txtTc = new JTextField();
		txtTc.setToolTipText("");
		txtTc.setColumns(10);
		txtTc.setBounds(233, 66, 86, 20);
		frmDoktorGiri.getContentPane().add(txtTc);
		
		JLabel label_1 = new JLabel("\u015Eifre:");
		label_1.setFont(new Font("Arial", Font.BOLD, 13));
		label_1.setBounds(149, 112, 85, 18);
		frmDoktorGiri.getContentPane().add(label_1);
		
		txtSifre = new JPasswordField();
		txtSifre.setBounds(233, 112, 86, 20);
		frmDoktorGiri.getContentPane().add(txtSifre);
		
		JButton button = new JButton("Giri\u015F");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dktrPaneli dp = new dktrPaneli();
				dp.Aktarmalar(txtTc.getText());
					try {
					
					String sorgu = ("select * from tbl_doktorlar where DoktorTc=? and DoktorSifre=?"); //girilen verileri tabloda kontrol edecek baðlantý adresimiz
					PreparedStatement pst = conn.prepareStatement(sorgu);   //sorgudaki bilinmeyen parametrelerin nereden geleceðini belli etmek icin.
					pst.setString(1, txtTc.getText());               //ilk girilen veriyi tc sütununda arayacak komut satýrý. 
					pst.setString(2, txtSifre.getText());            //ikinci girilen veriyi sifre sütununda arayacak komut satýrý.
					ResultSet rs = pst.executeQuery();
					int count=0 ;
					while(rs.next())
					{
						count++;					
					}
					if(count==1) {
					
						dp.frmDoktorPaneli.setVisible(true);
						frmDoktorGiri.setVisible(false);

					}
					else {
						JOptionPane.showConfirmDialog(null, "Girdiðiniz bilgiler yanlýþ. Lütfen tekrar deneyin.");
					}
					
					
				} catch (Exception e) {
				}
			}
		});
		button.setBounds(233, 165, 89, 23);
		frmDoktorGiri.getContentPane().add(button);
		
		lblNewLabel = new JLabel("Doktor Giriþ Ekraný");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(149, 11, 232, 51);
		frmDoktorGiri.getContentPane().add(lblNewLabel);
	}

}
