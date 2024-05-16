package Database;

import java.sql.*;
import javax.swing.JOptionPane;

public class table {
	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		try {
			con = ConnectionProvider.getCon();
			st = con.createStatement();
			//st.executeUpdate("create table users (userID int AUTO_INCREMENT primary key, name varchar(200), email varchar(200), password varchar(50), roomNo varchar(10), roomType varchar(50), status varchar(20))");
			//st.executeUpdate("create table room (roomID int AUTO_INCREMENT primary key, roomNo varchar(10), roomType varchar(50), price int, status varchar(20))");
			//st.executeUpdate("create table customers(id int AUTO_INCREMENT primary key, name varchar(200), mobileNumber varchar(20), nationality varchar(200), gender varchar(50),nrc varchar(50), email varchar(200), checkIn varchar(50), roomNo varchar(10), roomType varchar(200), pricePerDay int(10), numberOfDaysStay int(10), totalAmount varchar(200), checkout varchar(50), status varchar(50))");
			//st.executeUpdate("create table spa(spaID int AUTO_INCREMENT primary key, spaType varchar(200), time varchar(50), price varchar(50), status varchar(50))");
			//st.executeUpdate("create table food(foodID int AUTO_INCREMENT primary key, foodName varchar(300), foodType varchar(200), quantity int, price decimal(5,2), status varchar(100))");
			//st.executeUpdate("create table orders(orderID int AUTO_INCREMENT primary key, roomNumber varchar(200),foodName varchar(300), foodType varchar(200), quantity int, price decimal(5,2), total decimal(5,2))");
			JOptionPane.showMessageDialog(null, "Table Created");
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		finally
		{
			try {
				con.close();
				st.close();
			}
			catch(Exception e) {
				
			}
		}
	}
}
