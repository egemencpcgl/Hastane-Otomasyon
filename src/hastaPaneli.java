import java.awt.EventQueue;
import java.awt.*;

import javax.security.auth.Refreshable;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.naming.Binding;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.DateChooserPanel;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.components.JLocaleChooser;

public class hastaPaneli {

	public JFrame frmHastaPaneli;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hastaPaneli window = new hastaPaneli();
					window.frmHastaPaneli.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	///////////////////
	DefaultTableModel modelim = new DefaultTableModel(); // Boþ bir Tablo modeli oluþturuyoruz. burada içine çekeceðimiz sütunlarýn baþlýklarýný kolonlara uyumlu olarak dolduruyoruz.	 
	Object[] kolonlar = {"Randevu Tarih","Randevu Saati","Klinik","Doktor"};
	Object[] satirlar = new Object[4];	
	////////////////////
	Connection con=sqlBaglanti.dbConnector(); 
	public JTable table;
	public JScrollPane scrollPane;
	public JLabel lblAdSoyad;
	public JTextField txtTc;
	public JComboBox comboBox;
	public JComboBox comboBox_1;
	public JTextPane textPane;
	public JTextField txtAd;
	public JLabel lblDegisken;	
	////////////////////
	
	public hastaPaneli() {
		initialize();
		//AdSoyadCekme();
		//listele();
		
		

		
	}

	public String tcTasima(String Tc) {  // Bu method hastagiriþinden gelecek Tc numarasýný panelde kullanabilmek için oluþturuyoruz.
		txtTc.setText(Tc);               // Hastagiriþinden gelen tc'yi txtTc Componentimize atýyoruz.
		
		
		
		try {    
			
			String sorgu = ("select HastaAd, HastaSoyad from tbl_hastalar where HastaTc=?"); //Hastamýzýn Adýný ve Soyadýný çekebilmek için tc numarasýyla eþleþen isim soyismi bulan sorgumuz.
			PreparedStatement st = con.prepareStatement(sorgu);   // Sorguda ki bilinmeyen deyimi tanýmlamak için kullanýlan kod satýrý.
			st.setString(1, txtTc.getText());      //Hasta tc'yi sorguda kullanabilmek için hangi componentten alacaðýný belirtiyoruz. 
			ResultSet rs = st.executeQuery();
			while(rs.next()) //Sorgumuz doðru çalýþtýðý takdirde aþaðýdaki kod satýrýna geçer.
			{
				txtAd.setText(rs.getString(1)+" "+rs.getString(2));  // Sorgu çalýþtýktan sonra hastaAd ve hastaSoyad sütunlarýndan gelen verileri txtAd componentimize aktarýyoruz.
			}
		}
			catch (SQLException e) {
				e.printStackTrace();
		}
		
		try {  /// Tabloya randevu listesini çekiyoruz.
			String sorgu = ("select RandevuTarih,RandevuSaat,RandevuBrans,RandevuDoktor from randevular where HastaTc=? order by RandevuTarih"); //Hastamýzýn aldýðý randevularý hastaTc ye göre randevular tablosundan çeken sorgumuz.
			PreparedStatement pst = con.prepareStatement(sorgu);  // Sorguda ki bilinmeyen deyimi tanýmlamak için kullanýlan kod satýrý.
			pst.setString(1, txtTc.getText()); // HastaTc yi nerden alacaðýný belirtiyoruz.(bilinmeyen deyimi tanýmlýyoruz.
			ResultSet myRs = pst.executeQuery();
			while(myRs.next()) 
			{
				satirlar[0]= myRs.getString("RandevuTarih"); //Sorgudan gelen sütun deðerlerini oluþturduðumuz tablo sütun satýrlarýna yazdýrýyoruz.
				satirlar[1]= myRs.getString("RandevuSaat");
				satirlar[2]= myRs.getString("RandevuBrans");
				satirlar[3]= myRs.getString("RandevuDoktor");
				modelim.addRow(satirlar);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Tc;
		
	}

	

	public void initialize() {
		frmHastaPaneli = new JFrame();
		frmHastaPaneli.getContentPane().setBackground(new Color(95, 158, 160));
		frmHastaPaneli.setTitle("Hasta Paneli");
		frmHastaPaneli.setBounds(100, 100, 1000, 493);
		frmHastaPaneli.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHastaPaneli.getContentPane().setLayout(null);
		
		JLabel lblAdSoyad = new JLabel("Ad Soyad:");
		lblAdSoyad.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAdSoyad.setBounds(24, 32, 95, 16);
		frmHastaPaneli.getContentPane().add(lblAdSoyad);

		
						
		JLabel lblRandevu = new JLabel("Randevu:");
		lblRandevu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRandevu.setBounds(24, 81, 95, 16);
		frmHastaPaneli.getContentPane().add(lblRandevu);
		
		JLabel lblKlinikler = new JLabel("Klinikler:");
		lblKlinikler.setBounds(24, 155, 78, 14);
		frmHastaPaneli.getContentPane().add(lblKlinikler);
		
		JLabel lblDoktorAd = new JLabel("Doktor Ad\u0131:");
		lblDoktorAd.setBounds(24, 189, 78, 14);
		frmHastaPaneli.getContentPane().add(lblDoktorAd);
		
		JLabel lblikayetinizizetleyin = new JLabel("\u015Eikayetinizi \u00F6zetleyin:");
		lblikayetinizizetleyin.setBounds(24, 233, 177, 14);
		frmHastaPaneli.getContentPane().add(lblikayetinizizetleyin);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(24, 258, 345, 169);
		frmHastaPaneli.getContentPane().add(textPane);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		
		comboBox.setBounds(95, 152, 117, 20);
		frmHastaPaneli.getContentPane().add(comboBox);
		
		try {     /// Baðlantý tanýmlayarak combobox'a Branþ Adlarýný çekiyoruz.
			Statement st = (Statement) con.createStatement();
			ResultSet rs = st.executeQuery("select bransad from tbl_brans");
			while(rs.next())
			{
				comboBox.addItem(rs.getString("bransad"));
			}
		} catch (Exception e) {
			
		}

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(95, 186, 117, 20);
		frmHastaPaneli.getContentPane().add(comboBox_1);
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBox_1.removeAllItems();
				
				try {   
					PreparedStatement st = con.prepareStatement("select doktorad,doktorsoyad from tbl_doktorlar where Doktorbrans=?"); // Brans comboboxsýndan gelen sonuca göre doktorlarýn gözükeceði sorgu
					st.setString(1, comboBox.getSelectedItem().toString()); // Doktorbransýnýn alacaðý componenti belirtiyoruz.
					ResultSet rs = st.executeQuery();
					
					while(rs.next())
					{
						comboBox_1.addItem(rs.getString("Doktorad")+" "+rs.getString("doktorsoyad")); //Dönen sonuca göre doktorad ve doktorsoyadlarýný combobox'a ekliyoruz.
					}
				} catch (Exception e) {
					
				}
			}
		});
		modelim.setColumnIdentifiers(kolonlar);
		modelim.addRow(satirlar);
		
		JLabel lblRandevuGemii = new JLabel("Randevu Ge\u00E7mi\u015Fi:");
		lblRandevuGemii.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRandevuGemii.setBounds(502, 33, 125, 14);
		frmHastaPaneli.getContentPane().add(lblRandevuGemii);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd.MM.yyyy");
		dateChooser.setBounds(290, 152, 95, 20);
		frmHastaPaneli.getContentPane().add(dateChooser);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"--Se\u00E7iniz--", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00"}));
		comboBox_2.setBounds(290, 186, 95, 20);
		frmHastaPaneli.getContentPane().add(comboBox_2);
		
		JButton btnNewButton = new JButton("Randevu Al");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
					String sorgu = "insert into Randevular(RandevuDoktor,RandevuBrans,HastaSikayet,HastaTc,RandevuTarih,RandevuSaat) values(?,?,?,?,?,?)";
					PreparedStatement pst = con.prepareStatement(sorgu);
					pst.setString(2, comboBox.getSelectedItem().toString());
					pst.setString(1, comboBox_1.getSelectedItem().toString());
					pst.setString(3, textPane.getText());
					pst.setString(4, txtTc.getText());
					pst.setString(5, ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
					pst.setString(6, comboBox_2.getSelectedItem().toString());
					pst.executeUpdate();
					
					con.close();
					
					JOptionPane.showMessageDialog(null,"Randevunuz Baþarýyla Alýndý");					
					} 
				catch (Exception e) {}		
			}
		});
		btnNewButton.setBounds(394, 404, 117, 23);
		frmHastaPaneli.getContentPane().add(btnNewButton);
		
		JLabel lblBilgileriDzenle = new JLabel("Bilgileri D\u00FCzenle");
		lblBilgileriDzenle.setToolTipText("Bilgileri D\u00FCzenle");
		lblBilgileriDzenle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hstBilgiDuz hbd = new hstBilgiDuz();
				hbd.bilgileriAktar(txtTc.getText());
				hbd.setVisible(true);
			}
		});
		lblBilgileriDzenle.setForeground(Color.BLUE);
		lblBilgileriDzenle.setBounds(773, 429, 73, 14);
		frmHastaPaneli.getContentPane().add(lblBilgileriDzenle);
		
		
		scrollPane = new JScrollPane();		
		scrollPane.setBounds(502, 67, 464, 199);
		frmHastaPaneli.getContentPane().add(scrollPane);
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelim);
		table.repaint();
        try {
             Thread.sleep(1000);
        } catch (Exception ex) {
        }
		
		JLabel lblTcNo = new JLabel("Tc No:");
		lblTcNo.setBounds(24, 122, 46, 14);
		frmHastaPaneli.getContentPane().add(lblTcNo);
		
		JLabel lblkYap = new JLabel("\u00C7\u0131k\u0131\u015F Yap");
		lblkYap.setToolTipText("\u00C7\u0131k\u0131\u015F Yap");
		lblkYap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				otomasyonGiris og = new otomasyonGiris();
				og.frmOtomasyonGiri.setVisible(true);
				frmHastaPaneli.setVisible(false);
			}
		});
		lblkYap.setForeground(Color.BLUE);
		lblkYap.setBounds(869, 429, 105, 14);
		frmHastaPaneli.getContentPane().add(lblkYap);
		
		txtTc = new JTextField();
		txtTc.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtTc.setEnabled(false);
		txtTc.setBounds(95, 116, 86, 20);
		frmHastaPaneli.getContentPane().add(txtTc);
		txtTc.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(250, 59, 46, 14);
		frmHastaPaneli.getContentPane().add(lblNewLabel);
		lblNewLabel.setText(txtTc.getText());
		
		txtAd = new JTextField();
		txtAd.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAd.setEnabled(false);
		txtAd.setBounds(115, 31, 86, 20);
		frmHastaPaneli.getContentPane().add(txtAd);
		txtAd.setColumns(10);
		
		JLabel lblTarih = new JLabel("Tarih:");
		lblTarih.setBounds(231, 155, 78, 14);
		frmHastaPaneli.getContentPane().add(lblTarih);
		
		JLabel lblSaat = new JLabel("Saat:");
		lblSaat.setBounds(231, 189, 78, 14);
		frmHastaPaneli.getContentPane().add(lblSaat);
		

		

		frmHastaPaneli.getContentPane().add(comboBox_2);
		}
	}