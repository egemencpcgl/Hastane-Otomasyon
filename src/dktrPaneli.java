import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
public class dktrPaneli {

	public JFrame frmDoktorPaneli;
	public JTextField txtAdSoyad;
	public JTextField textField_1;
	public JTextField textField_2;
	public JTextField txtTc;
	public JTable table;
	Connection con=sqlBaglanti.dbConnector();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dktrPaneli window = new dktrPaneli();
					window.frmDoktorPaneli.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	DefaultTableModel modelim1= new DefaultTableModel();
	
	Object[] kolonlar1 = {"Duyurular"};
	Object[] satirlar1 = new Object[1];
	

	DefaultTableModel modelim = new DefaultTableModel();
	
	Object[] kolonlar = {"Randevu Tarih","Randevu Saati","HastaAdSoyad","HastaSikayet"};
	Object[] satirlar = new Object[4];
	public JTable table_1;
	

	
	
	
	public dktrPaneli() {
		initialize();
		//Aktarmalar(textField_1.getText());
	}
	
	
	public String Aktarmalar(String tc) {
		 
		txtTc.setText(tc);
		
		try {
			
			String sorgu = ("select DoktorAd, DoktorSoyad from tbl_doktorlar where DoktorTc=?");
			PreparedStatement st = con.prepareStatement(sorgu);
			st.setString(1, txtTc.getText());
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				txtAdSoyad.setText(rs.getString(1)+" "+rs.getString(2));
			}
		}
			catch (SQLException e) {
				e.printStackTrace();
		}
		
		
		try {  /// tabloya randevu listesini çekiyoruz.
			String sorgu2 = ("select RandevuTarih,RandevuSaat,HastaTc,HastaSikayet from randevular where RandevuDoktor=? order by RandevuTarih");
			PreparedStatement pst = con.prepareStatement(sorgu2);
			pst.setString(1, txtAdSoyad.getText());
			ResultSet myRs = pst.executeQuery();
						
			while(myRs.next())
			{
				satirlar[0]= myRs.getString("RandevuTarih");
				satirlar[1]= myRs.getString("RandevuSaat");
				satirlar[2]= myRs.getString("HastaTc");
				satirlar[3]= myRs.getString("HastaSikayet");
				modelim.addRow(satirlar);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		try {
			String sorgu = ("Select duyuru from tbl_duyuru ");
			Statement st = con.createStatement();			
			ResultSet rs = st.executeQuery(sorgu);
			while(rs.next()) {
				satirlar1[0]=rs.getString("Duyuru");
				modelim1.addRow(satirlar1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return tc ;
	}


	private void initialize() {
		frmDoktorPaneli = new JFrame();
		frmDoktorPaneli.getContentPane().setBackground(new Color(60, 179, 113));
		frmDoktorPaneli.setTitle("Doktor Paneli");
		frmDoktorPaneli.setBounds(100, 100, 1000, 493);
		frmDoktorPaneli.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDoktorPaneli.getContentPane().setLayout(null);
		
		JLabel lblBilgileriDzenle = new JLabel("Bilgileri D\u00FCzenle");
		lblBilgileriDzenle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DktBilgiDuz dbd = new DktBilgiDuz();
				dbd.bilgiAl(txtTc.getText());
				dbd.setVisible(true);
			}
		});

		lblBilgileriDzenle.setToolTipText("Bilgileri D\u00FCzenle");
		lblBilgileriDzenle.setForeground(Color.BLUE);
		lblBilgileriDzenle.setBackground(SystemColor.control);
		lblBilgileriDzenle.setBounds(773, 429, 73, 14);
		frmDoktorPaneli.getContentPane().add(lblBilgileriDzenle);
		
		JLabel lblRandevular = new JLabel("Randevular:");
		lblRandevular.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRandevular.setBounds(480, 33, 125, 14);
		frmDoktorPaneli.getContentPane().add(lblRandevular);
		
		JLabel label_1 = new JLabel("Ad Soyad:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(24, 32, 95, 16);
		frmDoktorPaneli.getContentPane().add(label_1);
		
		JLabel lblHastaBilgileri = new JLabel("Hasta Bilgileri:");
		lblHastaBilgileri.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHastaBilgileri.setBounds(24, 106, 125, 16);
		frmDoktorPaneli.getContentPane().add(lblHastaBilgileri);
		
		JLabel label_3 = new JLabel("Tc No:");
		label_3.setBounds(24, 147, 46, 14);
		frmDoktorPaneli.getContentPane().add(label_3);
		
		txtAdSoyad = new JTextField();
		txtAdSoyad.setEditable(false);
		txtAdSoyad.setBounds(150, 31, 157, 20);
		frmDoktorPaneli.getContentPane().add(txtAdSoyad);
		txtAdSoyad.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(109, 144, 86, 20);
		frmDoktorPaneli.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAdSoyad = new JLabel("Ad Soyad:");
		lblAdSoyad.setBounds(24, 182, 91, 14);
		frmDoktorPaneli.getContentPane().add(lblAdSoyad);
		
		JLabel lblHastaSikayet = new JLabel("Hasta Sikayet:");
		lblHastaSikayet.setBounds(24, 217, 125, 14);
		frmDoktorPaneli.getContentPane().add(lblHastaSikayet);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(109, 179, 127, 20);
		frmDoktorPaneli.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(24, 242, 304, 175);
		frmDoktorPaneli.getContentPane().add(textPane);
		
		JLabel lblTcNo = new JLabel("Tc No:");
		lblTcNo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTcNo.setBounds(24, 59, 95, 16);
		frmDoktorPaneli.getContentPane().add(lblTcNo);
		
		txtTc = new JTextField();
		txtTc.setEditable(false);
		txtTc.setBounds(150, 56, 86, 20);
		frmDoktorPaneli.getContentPane().add(txtTc);
		txtTc.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(490, 58, 435, 173);
		frmDoktorPaneli.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelim);
		table.repaint();
        try {
             Thread.sleep(1000);
        } catch (Exception ex) {
        }
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(490, 277, 435, 101);
		frmDoktorPaneli.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(modelim1);
		table_1.repaint();
        try {
             Thread.sleep(1000);
        } catch (Exception ex) {
        }
		
		JLabel lblDuyurular = new JLabel("Duyurular:");
		lblDuyurular.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDuyurular.setBounds(480, 255, 125, 14);
		frmDoktorPaneli.getContentPane().add(lblDuyurular);
		
		JLabel lblkYap = new JLabel("\u00C7\u0131k\u0131\u015F Yap");
		lblkYap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				otomasyonGiris og = new otomasyonGiris();
				og.frmOtomasyonGiri.setVisible(true);
				frmDoktorPaneli.setVisible(false);
				
			}
		});
		lblkYap.setToolTipText("\u00C7\u0131k\u0131\u015F Yap");
		lblkYap.setForeground(Color.BLUE);
		lblkYap.setBackground(SystemColor.menu);
		lblkYap.setBounds(869, 429, 91, 14);
		frmDoktorPaneli.getContentPane().add(lblkYap);
		table.repaint();
        try {
             Thread.sleep(1000);
        } catch (Exception ex) {
        }
    	table.addMouseListener(new MouseAdapter() {
			//@Override
			public void mousePressed(MouseEvent e) {
				int satir = table.getSelectedRow();      //Seçilen satýrýn deðerini int tanýmladýðýmýz deðiþkene atýyoruz.
			    textField_1.setText(String.valueOf(table.getValueAt(satir,1))); //seçilen satýrdaki birinci sütunda bulunan deðeri textField_1 e otomatik dolduruyoruz.
			    textField_2.setText(String.valueOf(table.getValueAt(satir,2))); //seçilen satýrdaki ikinci sütunda bulunan deðeri textField_2 e otomatik dolduruyoruz.
			    textPane.setText(String.valueOf(table.getValueAt(satir,3)));    //seçilen satýrdaki üçüncü sütunda bulunan deðeri textField_3 e otomatik dolduruyoruz.
			}
		});
		
		
		modelim.setColumnIdentifiers(kolonlar);
		modelim.addRow(satirlar);
		modelim1.setColumnIdentifiers(kolonlar1);
		modelim1.addRow(satirlar1);
	}
}
