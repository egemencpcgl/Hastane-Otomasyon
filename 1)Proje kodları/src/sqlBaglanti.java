import java.sql.*;
import javax.swing.*;
public class sqlBaglanti {
	
	Connection con = null;
	public static Connection dbConnector() {
		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");     //Sql ba�lant�s� a�abilmek i�in projemizin i�ine jar dosyas�n� at�p onu �al��t�r�yoruz.
			Connection con= DriverManager.getConnection("jdbc:sqlserver://LOCALHOST;databaseName=hastane", "egemen", "q1w2e3"); //Ba�lan�lacak database komutu. 
			
			
			return con;
			
		} catch (Exception e) {
			// TODO: handle exception
			
			JOptionPane.showConfirmDialog(null, "Ba�lant� Ba�ar�s�z.");
			return null;
		}
	}
}
