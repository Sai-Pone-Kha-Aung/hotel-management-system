package Pages;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Authentication.login;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Database.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileOutputStream;

public class userCheckOut extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_RoomNo;
	private JTextField textField_CustomerName;
	private JTextField textField_CheckIn;
	private JTextField textField_CheckOutDate;
	private JTextField textField_MobileNo;
	private JTextField textField_PricePerDay;
	private JTextField textField_NumToStay;
	private JTextField textField_Total;
	private JTextField textField_Email;
	private JTable table;
	private DefaultTableModel model;
	
	int id = 0;
	String Query;
	String roomType;
	String roomNo;
	String roomNum = login.roomNum;
	String userEmail = login.userEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userCheckOut frame = new userCheckOut();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public userCheckOut() {
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				ResultSet rs = Select.getData("select * from customers where checkout is NULL and email='"+userEmail+"' ");
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				try
				{
					while(rs.next()) {
						model.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)});
					}
				}
				
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(101,106,105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 271, 788, 280);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.DARK_GRAY);
		table.setForeground(Color.WHITE);
		model = new DefaultTableModel();
		Object[] column = {"ID", "Name", "Mobile Number", "Nationality", "Gender","NRC", "Email", "Check In Date", "Room Number", "Room Type", "Price Per Day"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
	
		
		JButton btnBack = new JButton("");
		btnBack.setBorderPainted(false);
		btnBack.setIcon(new ImageIcon(userCheckOut.class.getResource("/Images/backwhite.png")));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new userDashBoard().setVisible(true);
			}
		});
		btnBack.setBounds(18, 13, 30, 20);
		contentPane.add(btnBack);
		
		JButton btnClose = new JButton("");
		btnClose.setBorderPainted(false);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to close this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0)
					System.exit(0);
			}
		});
		btnClose.setIcon(new ImageIcon(userCheckOut.class.getResource("/Images/close.png")));
		btnClose.setBounds(754, 0, 40, 40);
		contentPane.add(btnClose);
		
		JLabel lblNewLabel = new JLabel("Room Number");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel.setBounds(225, 58, 90, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnSearch = new JButton("");
		btnSearch.setIcon(new ImageIcon(userCheckOut.class.getResource("/Images/searchbtn.png")));
		btnSearch.setBorderPainted(false);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchRoom = textField_RoomNo.getText();
				try
				{
					ResultSet rs = Select.getData("select * from customers where roomNo= '"+searchRoom+"' and checkout is NULL");
					
					if(rs.next()) {
						textField_RoomNo.setEditable(false);
						id = rs.getInt(1);
						textField_CustomerName.setText(rs.getString(2));
						textField_CheckIn.setText(rs.getString(8));
						textField_MobileNo.setText(rs.getString(3));
						textField_PricePerDay.setText(rs.getString(11));
						textField_Email.setText(rs.getString(7));
						
						SimpleDateFormat myDateFormat = new SimpleDateFormat("dd/MM/yyyy");
						Calendar myCal = Calendar.getInstance();
						textField_CheckOutDate.setText(myDateFormat.format(myCal.getTime()));
						
						String dateBeforeString = rs.getString(8);
						Date dateBefore = myDateFormat.parse(dateBeforeString);
						
						String dateAfterString = myDateFormat.format(myCal.getTime());
						Date dateAfter = myDateFormat.parse(dateAfterString);
						
						long difference = dateAfter.getTime() - dateBefore.getTime();
						int noOfDayStay = (int) (difference/ (1000*60*60*24));
						
						if(noOfDayStay == 0)
							noOfDayStay = 1;
					
						textField_NumToStay.setText(String.valueOf(noOfDayStay));
						
						float price = Float.parseFloat(textField_PricePerDay.getText());
						
						textField_Total.setText(String.valueOf(noOfDayStay * price));
						
						roomType = rs.getString(10);
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Room Number is not Booked or Room Number Doesn't Exist");
					}
				}
				
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
		});
		btnSearch.setBounds(478, 58, 70, 20);
		contentPane.add(btnSearch);
		
		JLabel lblNewLabel_1 = new JLabel("Customer Name");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(22, 97, 101, 16);
		contentPane.add(lblNewLabel_1);
		
		textField_RoomNo = new JTextField();
		textField_RoomNo.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		textField_RoomNo.setBounds(336, 58, 130, 20);
		textField_RoomNo.setText(roomNum);
		contentPane.add(textField_RoomNo);
		textField_RoomNo.setColumns(10);
		
		textField_CustomerName = new JTextField();
		textField_CustomerName.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		textField_CustomerName.setBounds(22, 117, 169, 26);
		textField_CustomerName.setEditable(false);
		contentPane.add(textField_CustomerName);
		textField_CustomerName.setColumns(10);
		
		textField_CheckIn = new JTextField();
		textField_CheckIn.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		textField_CheckIn.setBounds(225, 117, 169, 26);
		textField_CheckIn.setEditable(false);
		textField_CheckIn.setColumns(10);
		contentPane.add(textField_CheckIn);
		
		JLabel lblNewLabel_1_1 = new JLabel("Check In Date");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(225, 97, 88, 16);
		contentPane.add(lblNewLabel_1_1);
		
		textField_CheckOutDate = new JTextField();
		textField_CheckOutDate.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		textField_CheckOutDate.setBounds(420, 117, 169, 26);
		textField_CheckOutDate.setEditable(false);
		textField_CheckOutDate.setColumns(10);
		contentPane.add(textField_CheckOutDate);
		
		JLabel lblNewLabel_1_2 = new JLabel("Check Out Date (Today)");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(420, 97, 149, 16);
		contentPane.add(lblNewLabel_1_2);
		
		textField_MobileNo = new JTextField();
		textField_MobileNo.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		textField_MobileNo.setBounds(609, 117, 169, 26);
		textField_MobileNo.setEditable(false);
		textField_MobileNo.setColumns(10);
		contentPane.add(textField_MobileNo);
		
		JLabel lblNewLabel_1_3 = new JLabel("Mobile Number");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(609, 97, 96, 16);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Price Per Day");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel_1_4.setBounds(22, 165, 81, 16);
		contentPane.add(lblNewLabel_1_4);
		
		textField_PricePerDay = new JTextField();
		textField_PricePerDay.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		textField_PricePerDay.setColumns(10);
		textField_PricePerDay.setBounds(22, 185, 169, 26);
		textField_PricePerDay.setEditable(false);
		contentPane.add(textField_PricePerDay);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Number of Days Stay");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(225, 165, 132, 16);
		contentPane.add(lblNewLabel_1_1_1);
		
		textField_NumToStay = new JTextField();
		textField_NumToStay.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		textField_NumToStay.setColumns(10);
		textField_NumToStay.setEditable(false);
		textField_NumToStay.setBounds(225, 185, 169, 26);
		contentPane.add(textField_NumToStay);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Total Amount");
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel_1_2_1.setBounds(420, 165, 86, 16);
		contentPane.add(lblNewLabel_1_2_1);
		
		textField_Total = new JTextField();
		textField_Total.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		textField_Total.setColumns(10);
		textField_Total.setBounds(420, 185, 169, 26);
		textField_Total.setEditable(false);
		contentPane.add(textField_Total);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Email");
		lblNewLabel_1_3_1.setForeground(Color.WHITE);
		lblNewLabel_1_3_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel_1_3_1.setBounds(609, 165, 34, 16);
		contentPane.add(lblNewLabel_1_3_1);
		
		textField_Email = new JTextField();
		textField_Email.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		textField_Email.setColumns(10);
		textField_Email.setBounds(609, 185, 169, 26);
		textField_Email.setEditable(false);
		contentPane.add(textField_Email);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setIcon(new ImageIcon(userCheckOut.class.getResource("/Images/checkoutbtn1.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = textField_Email.getText();
				String checkOut = textField_CheckOutDate.getText();
				String numberOfDaysStay = textField_NumToStay.getText();
				String totalAmount = textField_Total.getText();
				
				roomNo = textField_RoomNo.getText();
				
				Payment payment = new Payment();
			    payment.setVisible(true);
				
				Query = "update customers set numberOfDaysStay='"+numberOfDaysStay+"', totalAmount='"+totalAmount+"', checkout='"+checkOut+"', status='Not Booked' where id='"+id+"'";
				InsertUpdateDelete.setData(Query, "");
				
				Query = "update room set status='Not Booked' where roomNo='"+roomNo+"' ";
				InsertUpdateDelete.setData(Query, "");
				
				Query = "update users set status='Not Booked', roomType=NULL, roomNo=NULL where email='"+email+"'";
				InsertUpdateDelete.setData(Query, "");
				
			}
		});
		btnNewButton.setBounds(18, 239, 117, 20);
		contentPane.add(btnNewButton);
		
		JButton btnClear = new JButton("");
		btnClear.setBorderPainted(false);
		btnClear.setIcon(new ImageIcon(userCheckOut.class.getResource("/Images/clearbtn1.png")));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new userCheckOut().setVisible(true);
			}
		});
		btnClear.setBounds(325, 239, 117, 20);
		contentPane.add(btnClear);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(userCheckOut.class.getResource("/Images/generatebill.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField_CustomerName.getText();
				String mobileNumber = textField_MobileNo.getText();
				String email = textField_Email.getText();
				String checkOut = textField_CheckOutDate.getText();
				String numberOfDaysStay = textField_NumToStay.getText();
				String totalAmount = textField_Total.getText();
				String path="src/export bill/Hotel Transylvania";
				
				com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
				
				try 
				{
					PdfWriter.getInstance(doc, new FileOutputStream(path+" "+id+".pdf"));
					doc.open();
					
					Paragraph p1 = new Paragraph("HOTEL TRANSYLVANIA\n");
					doc.add(p1);
				
					Paragraph p2 = new Paragraph("--------------------------------------------------------------------------------------------------------------------------------\n"
							+ "\n");
					doc.add(p2);
					
					Paragraph p3 = new Paragraph("\tBill ID: "+id+"\nCustomer Details: \nName: "+name+"\nMobile Number: "+mobileNumber+"\nEmail: "+email+"\n");
					doc.add(p3);
					doc.add(p2);
					
					Paragraph p4 = new Paragraph("\tRoom Details: \nNumber:"+textField_RoomNo.getText()+"\nType: "+roomType+"\nPrice Per Day: "+textField_PricePerDay.getText()+"");
					doc.add(p4);
					doc.add(p2);
					
					PdfPTable tb1 = new PdfPTable(4);
					tb1.addCell("Check In Date: " + textField_CheckIn.getText());
					tb1.addCell("Check Out Date: " + checkOut);
					tb1.addCell("Number of Days Stay: " + numberOfDaysStay);
					tb1.addCell("Total Amount: " + totalAmount);
					doc.add(tb1);
					doc.add(p2);
					
					Paragraph p5 = new Paragraph("Thank you, Please Visit Again.");
					doc.add(p5);
					doc.close();
					JOptionPane.showConfirmDialog(null, "Do you want to Print Bill?", "Select", JOptionPane.YES_NO_OPTION);
						
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
				
				setVisible(false);
				new userCheckOut().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(171, 239, 117, 20);
		contentPane.add(btnNewButton_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(userCheckOut.class.getResource("/Images/usercheckout.png")));
		label.setBounds(0, 0, 800, 572);
		contentPane.add(label);
	}
}
