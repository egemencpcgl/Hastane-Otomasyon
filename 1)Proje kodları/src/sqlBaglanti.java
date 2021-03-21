import java.sql.*;
import javax.swing.*;
public class sqlBaglanti {
	
	Connection con = null;
	public static Connection dbConnector() {
		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");     //Sql baðlantýsý açabilmek için projemizin içine jar dosyasýný atýp onu çalýþtýrýyoruz.
			Connection con= DriverManager.getConnection("jdbc:sqlserver://LOCALHOST;databaseName=hastane", "egemen", "q1w2e3"); //Baðlanýlacak database komutu. 
			
			
			return con;
			
		} catch (Exception e) {
			// TODO: handle exception
			
			JOptionPane.showConfirmDialog(null, "Baðlantý Baþarýsýz.");
			return null;
		}
	}
}
