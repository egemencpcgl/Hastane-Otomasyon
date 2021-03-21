import java.awt.EventQueue;
import java.awt.Font;
import java.awt.EventQueue;

import javax.swing.border.*;
import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.*;

import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class yonetimPaneli {

	public JFrame frmYnetimPaneli;
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;
	public JTextField textField_3;
	public JTextField textField_4;
	public JTextField textField_5;
	public JTextField textField_6;
	public JTextField textField_7;
	public JTextField textField_8;
	public JTable table;
	public JTable table_1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					yonetimPaneli window = new yonetimPaneli();
					window.frmYnetimPaneli.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public yonetimPaneli() {
		initialize();
		baglantiDoktor();
	}	
	
	DefaultTableModel modelim = new DefaultTableModel();
	
	Object[] kolonlar = {"Doktor Ad","Doktor Soyad","Brans","Doktor Tc","Sifre"};
	Object[] satirlar = new Object[5];
	
	DefaultTableModel modelim1 = new DefaultTableModel();
	
	Object[] kolonlar1 = {"Hasta Ad","Hasta Soyad","Hasta Tc","Hasta Sifre"};
	Object[] satirlar1 = new Object[4];
	
	Connection baglanti = sqlBaglanti.dbConnector();
	
    public void baglantiDoktor() {   //tablolarýmýza verileri çekme iþlemleri.
		
		try {
		String sorgu =("select * from tbl_doktorlar");
		Statement st = baglanti.createStatement();
		ResultSet rs = st.executeQuery(sorgu);		
		while(rs.next()) {
			
			satirlar[0]=rs.getString("DoktorAd");
			satirlar[1]=rs.getString("DoktorSoyad");
			satirlar[2]=rs.getString("DoktorBrans");
			satirlar[3]=rs.getString("DoktorTc");
			satirlar[4]=rs.getString("DoktorSifre");
			modelim.addRow(satirlar);
			
		}		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		try {
			String sorgu2 = ("Select * from tbl_hastalar");
			Statement st2 = baglanti.createStatement();			
			ResultSet rs2 = st2.executeQuery(sorgu2);
			while(rs2.next()) {
				satirlar1[0]=rs2.getString("HastaAd");
				satirlar1[1]=rs2.getString("HastaSoyad");
				satirlar1[2]=rs2.getString("HastaTc");
				satirlar1[3]=rs2.getString("HastaSifre");
				modelim1.addRow(satirlar1);
			}
		
	} 
		catch (SQLException e) {		
		e.printStackTrace();
	}	
		
	}
	
	private void initialize() {
		frmYnetimPaneli = new JFrame();
		frmYnetimPaneli.getContentPane().setBackground(new Color(240, 230, 140));
		frmYnetimPaneli.setTitle("Y\u00F6netim Paneli");
		frmYnetimPaneli.setBounds(100, 100, 983, 579);
		frmYnetimPaneli.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmYnetimPaneli.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Doktor Bilgileri D\u00FCzenle");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(20, 11, 178, 14);
		frmYnetimPaneli.getContentPane().add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 36, 348, 201);
		frmYnetimPaneli.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int satir = table.getSelectedRow();      //Seçilen satýrýn deðerini int tanýmladýðýmýz deðiþkene atýyoruz.
			    textField.setText(String.valueOf(table.getValueAt(satir,0))); //seçilen satýrdaki birinci sütunda bulunan deðeri textField_1 e otomatik dolduruyoruz.
			    textField_1.setText(String.valueOf(table.getValueAt(satir,1))); //seçilen satýrdaki ikinci sütunda bulunan deðeri textField_2 e otomatik dolduruyoruz.
			    textField_2.setText(String.valueOf(table.getValueAt(satir,2)));
			    textField_3.setText(String.valueOf(table.getValueAt(satir,3)));//seçilen satýrdaki üçüncü sütunda bulunan deðeri textField_3 e otomatik dolduruyoruz.
			    textField_4.setText(String.valueOf(table.getValueAt(satir,4)));
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(modelim);
		
		JLabel label_1 = new JLabel("Hasta Bilgileri D\u00FCzenle");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(20, 276, 178, 14);
		frmYnetimPaneli.getContentPane().add(label_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 304, 346, 201);
		frmYnetimPaneli.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int satir = table_1.getSelectedRow();      //Seçilen satýrýn deðerini int tanýmladýðýmýz deðiþkene atýyoruz.
			    textField_5.setText(String.valueOf(table_1.getValueAt(satir,0))); //seçilen satýrdaki birinci sütunda bulunan deðeri textField_1 e otomatik dolduruyoruz.
			    textField_6.setText(String.valueOf(table_1.getValueAt(satir,1))); //seçilen satýrdaki ikinci sütunda bulunan deðeri textField_2 e otomatik dolduruyoruz.
			    textField_7.setText(String.valueOf(table_1.getValueAt(satir,2)));
			    textField_8.setText(String.valueOf(table_1.getValueAt(satir,3)));//seçilen satýrdaki üçüncü sütunda bulunan deðeri textField_3 e otomatik dolduruyoruz.
			}
		});
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(modelim1);
		
		JLabel label_2 = new JLabel("Doktor Ad\u0131:");
		label_2.setBounds(396, 37, 72, 14);
		frmYnetimPaneli.getContentPane().add(label_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(486, 36, 86, 20);
		frmYnetimPaneli.getContentPane().add(textField);
		
		JLabel label_3 = new JLabel("Doktor Soyad\u0131:");
		label_3.setBounds(396, 66, 84, 14);
		frmYnetimPaneli.getContentPane().add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(486, 65, 86, 20);
		frmYnetimPaneli.getContentPane().add(textField_1);
		
		JLabel label_4 = new JLabel("Doktor \u015Eifre:");
		label_4.setBounds(396, 157, 86, 14);
		frmYnetimPaneli.getContentPane().add(label_4);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(486, 94, 86, 20);
		frmYnetimPaneli.getContentPane().add(textField_2);
		
		JLabel label_5 = new JLabel("Doktor Tc:");
		label_5.setBounds(396, 128, 86, 14);
		frmYnetimPaneli.getContentPane().add(label_5);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(486, 124, 86, 20);
		frmYnetimPaneli.getContentPane().add(textField_3);
		
		JLabel label_6 = new JLabel("Doktor Bran\u015F:");
		label_6.setBounds(396, 97, 86, 14);
		frmYnetimPaneli.getContentPane().add(label_6);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(486, 154, 86, 20);
		frmYnetimPaneli.getContentPane().add(textField_4);
		
		JButton button = new JButton("Doktor Ekle");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sorgu = ("insert into tbl_doktorlar(DoktorAd,DoktorSoyad,DoktorBrans,DoktorTc,DoktorSifre)values(?,?,?,?,?)"); //Ekleme iþlemini gerçekleþirecek sorgumuz.
					PreparedStatement ps = baglanti.prepareStatement(sorgu);//Eklemek isteiðimiz parametreleri çaðýrmak için tanýmlýyoruz.
					ps.setString(1, textField.getText());    //1.parametre.
					ps.setString(2, textField_1.getText());  //2.parametre.
					ps.setString(3, textField_2.getText());  // ...
					ps.setString(4, textField_3.getText());  // ...
					ps.setString(5, textField_4.getText());  // ...
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Doktor baþarýyla eklendi.","Bilgi",3); //sorgu gerçekleþtiðinde bilgi mesajý
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Doktor bilgileri güncellenemedi.","Hata",2);	//sorgu gerçekletiðinde hata mesajý
				}
			}
		});
		button.setBounds(393, 183, 112, 23);
		frmYnetimPaneli.getContentPane().add(button);
		
		JButton button_1 = new JButton("Doktor G\u00FCncelle");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {   
					String sorgu = ("Update tbl_doktorlar set doktorAd=?, doktorSoyad=?, doktorBrans=?, doktorTc=?, doktorSifre=? where doktorTc="+textField_3.getText());// Güncelle butonuna basýldýktan sonra doktor þifresini güncelleyen sorgumuz.		
					PreparedStatement ps = baglanti.prepareStatement(sorgu);  //Sorguda bilinmeyen parametreleri çaðýrmak için tanýmlýyoruz.
					ps.setString(1, textField.getText());    //1.parametre.
					ps.setString(2, textField_1.getText());  //2.parametre.
					ps.setString(3, textField_2.getText());  // ... 
					ps.setString(4, textField_3.getText());  // ...
					ps.setString(5, textField_4.getText());  // ...
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Doktor bilgileri güncellendi.","Bilgi",3); //sorgu gerçekletiðinde bilgi mesajý
				} catch (SQLException e) {
					
					JOptionPane.showMessageDialog(null, "Doktor bilgileri güncellenemedi.","Hata",2);	//sorgu gerçekletiðinde hata mesajý
				}
				
			}
		});
		button_1.setBounds(393, 210, 112, 23);
		frmYnetimPaneli.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Doktor Sil");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				String sorgu = "delete from tbl_doktorlar where doktorTc=?";  //silme iþlemini gerçekleþtirecek sorgumuz.
				PreparedStatement ps = baglanti.prepareStatement(sorgu); //Sorguda bilinmeyen parametreleri çaðýrmak için tanýmlýyoruz.
				ps.setString(1, textField_3.getText());  //1.parametre. 
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Doktor kaydý silindi."); //sorgu gerçekletiðinde bilgi mesajý
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Doktor bilgileri güncellenemedi.","Hata",2);	//sorgu gerçekletiðinde hata mesajý				
			}				
			}
		});
		button_2.setBounds(393, 238, 112, 23);
		frmYnetimPaneli.getContentPane().add(button_2);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(479, 299, 86, 20);
		frmYnetimPaneli.getContentPane().add(textField_5);
		
		JLabel label_7 = new JLabel("Hasta Ad\u0131:");
		label_7.setBounds(397, 302, 72, 14);
		frmYnetimPaneli.getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("Hasta Soyad\u0131:");
		label_8.setBounds(396, 328, 84, 14);
		frmYnetimPaneli.getContentPane().add(label_8);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(479, 325, 86, 20);
		frmYnetimPaneli.getContentPane().add(textField_6);
		
		JLabel label_9 = new JLabel("Hasta Tc:");
		label_9.setBounds(396, 353, 86, 14);
		frmYnetimPaneli.getContentPane().add(label_9);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(479, 350, 86, 20);
		frmYnetimPaneli.getContentPane().add(textField_7);
		
		JLabel label_10 = new JLabel("Hasta \u015Eifre:");
		label_10.setBounds(396, 381, 72, 14);
		frmYnetimPaneli.getContentPane().add(label_10);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(479, 378, 86, 20);
		frmYnetimPaneli.getContentPane().add(textField_8);
		
		JButton button_3 = new JButton("Hasta G\u00FCncelle");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				try {
					String sorgu = "update tbl_hastalar set HastaAd=?,HastaSoyad=?,HastaTc=?,HastaSifre=? where hastaTc="+textField_7.getText(); //güncelleme iþlemleri.
					PreparedStatement ps = baglanti.prepareStatement(sorgu);
					ps.setString(1, textField_5.getText());
					ps.setString(2, textField_6.getText());
					ps.setString(3, textField_7.getText());
					ps.setString(4, textField_8.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Hasta baþarýyla güncellendi.");   //sorgu gerçekletiðinde bilgi mesajý
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Doktor bilgileri güncellenemedi.","Hata",2);	//sorgu gerçekletiðinde hata mesajý
				}				
			}
		});
		button_3.setBounds(396, 439, 112, 23);
		frmYnetimPaneli.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("Hasta Sil");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {					
					String sorgu = "delete from tbl_hastalar where hastaTc=?";  //silme iþlemleri.
					PreparedStatement ps = baglanti.prepareStatement(sorgu);
					ps.setString(1, textField_7.getText());
					ps.executeUpdate(); 
					JOptionPane.showMessageDialog(null, "Hasta kaydý silindi.");  //sorgu gerçekletiðinde bilgi mesajý
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Doktor bilgileri güncellenemedi.","Hata",2);	//sorgu gerçekletiðinde hata mesajý
				}
			}
		});
		button_4.setBounds(396, 473, 112, 23);
		frmYnetimPaneli.getContentPane().add(button_4);
		
		JLabel label_11 = new JLabel("Duyuru Yay\u0131nla");
		label_11.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_11.setBounds(733, 110, 146, 14);
		frmYnetimPaneli.getContentPane().add(label_11);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(687, 139, 212, 215);
		frmYnetimPaneli.getContentPane().add(textPane);
		
		JButton button_5 = new JButton("Yay\u0131nla");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sorgu = "insert into tbl_duyuru(duyuru) values (?)";  //ekleme iþlemleri.
					PreparedStatement ps = baglanti.prepareStatement(sorgu);
					ps.setString(1, textPane.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Yeni duyuru yayýnlandý.");  //sorgu gerçekletiðinde bilgi mesajý
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Doktor bilgileri güncellenemedi.","Hata",2);	//sorgu gerçekletiðinde hata mesajý
				}
			}
		});
		button_5.setBounds(745, 377, 89, 23);
		frmYnetimPaneli.getContentPane().add(button_5);
		
		modelim.setColumnIdentifiers(kolonlar);
		modelim.addRow(satirlar);
		modelim1.setColumnIdentifiers(kolonlar1);
		modelim1.addRow(satirlar1);
	}
}
