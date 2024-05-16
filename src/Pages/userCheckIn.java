package Pages;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;
import Database.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Authentication.login;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class userCheckIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField mobileTextField;
	private JTextField emailTextField;
	private JTextField nationTextField;
	private JTextField checkinDateTextField;
	private JTextField price;
	private JComboBox roomNoComboBox;
	private JComboBox roomComboBox;
	private JComboBox genderComboBox;
	String roomNo;
	String roomType;
	String bed;
	String roomPrice;
	private JTextField nrcPassport;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userCheckIn frame = new userCheckIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void roomDetail() {
		roomNoComboBox.removeAllItems();
		price.setText("");
		roomType = (String) roomComboBox.getSelectedItem();
		try
		{
			ResultSet rs = Select.getData("select * from users where roomType='"+roomType+"' and status='Booked'");
			while(rs.next())
			{
				roomNoComboBox.addItem(rs.getString(5));
			}
		}
		
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public userCheckIn() {
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(101,106,105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


		setContentPane(contentPane);
		
		JButton btnBack = new JButton("");
		btnBack.setIcon(new ImageIcon(userCheckIn.class.getResource("/Images/backwhite.png")));
		btnBack.setBounds(18, 13, 30, 20);
		btnBack.setBorderPainted(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new userDashBoard().setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnBack);
		
		JButton btnExit = new JButton("");
		btnExit.setBounds(754, 0, 40, 40);
		btnExit.setBorderPainted(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to close this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0)
					System.exit(0);
			}
		});
		btnExit.setIcon(new ImageIcon(userCheckIn.class.getResource("/Images/close.png")));
		contentPane.add(btnExit);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(67, 171, 36, 16);
		contentPane.add(lblNewLabel_1);
		
		nameTextField = new JTextField();
		nameTextField.setBackground(Color.LIGHT_GRAY);
		nameTextField.setBounds(67, 199, 193, 26);
		nameTextField.setText(login.userName);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Mobile Number");
		lblNewLabel_2.setBounds(539, 171, 96, 16);
		contentPane.add(lblNewLabel_2);
		
		mobileTextField = new JTextField();
		mobileTextField.setBackground(Color.LIGHT_GRAY);
		mobileTextField.setBounds(539, 195, 191, 26);
		contentPane.add(mobileTextField);
		mobileTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setBackground(Color.LIGHT_GRAY);
		emailTextField.setBounds(539, 257, 191, 26);
		emailTextField.setText(login.userEmail);
		emailTextField.setColumns(10);
		contentPane.add(emailTextField);
		
		JLabel lblNewLabel_2_1 = new JLabel("Email");
		lblNewLabel_2_1.setBounds(539, 233, 34, 16);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Gender");
		lblNewLabel_2_2.setBounds(67, 237, 44, 16);
		contentPane.add(lblNewLabel_2_2);
		
		nationTextField = new JTextField();
		nationTextField.setBackground(Color.LIGHT_GRAY);
		nationTextField.setBounds(67, 326, 193, 26);
		nationTextField.setColumns(10);
		contentPane.add(nationTextField);
		
		JLabel lblNewLabel_2_3 = new JLabel("Nationality");
		lblNewLabel_2_3.setBounds(67, 302, 69, 16);
		contentPane.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("Check In Date");
		lblNewLabel_1_1.setBounds(302, 302, 88, 16);
		contentPane.add(lblNewLabel_1_1);
		
		checkinDateTextField = new JTextField();
		checkinDateTextField.setBackground(Color.LIGHT_GRAY);
		checkinDateTextField.setBounds(302, 328, 193, 26);
		checkinDateTextField.setEditable(false);
		
		SimpleDateFormat myDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar myCal = Calendar.getInstance();
		
		checkinDateTextField.setText(myDateFormat.format(myCal.getTime()));
		
		checkinDateTextField.setColumns(10);
		contentPane.add(checkinDateTextField);
		
		JLabel lblNewLabel_2_4 = new JLabel("Room Type");
		lblNewLabel_2_4.setBounds(302, 171, 70, 16);
		contentPane.add(lblNewLabel_2_4);
		
		roomComboBox = new JComboBox();
		roomComboBox.setBounds(302, 196, 193, 26);
		roomComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomDetail();
			}
		});
		roomComboBox.setModel(new DefaultComboBoxModel(new String[] {"Single", "Double", "Triple"}));
		contentPane.add(roomComboBox);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Room Number");
		lblNewLabel_2_1_1_1.setBounds(302, 237, 90, 16);
		contentPane.add(lblNewLabel_2_1_1_1);
		
		roomNoComboBox = new JComboBox();
		roomNoComboBox.setBounds(302, 265, 193, 27);
		roomNoComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomNo = (String) roomNoComboBox.getSelectedItem();
			
				try
				{
					ResultSet rs = Select.getData("select * from room where roomNo='"+roomNo+"'");
					while(rs.next()) 
					{
						price.setText(rs.getString(4));
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		contentPane.add(roomNoComboBox);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Price");
		lblNewLabel_2_3_1.setBounds(302, 366, 30, 16);
		contentPane.add(lblNewLabel_2_3_1);
		
		price = new JTextField();
		price.setBackground(Color.LIGHT_GRAY);
		price.setBounds(302, 390, 193, 26);
		price.setEditable(false);
		price.setColumns(10);
		contentPane.add(price);
		
		genderComboBox = new JComboBox();
		genderComboBox.setBounds(67, 265, 193, 27);
		genderComboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		contentPane.add(genderComboBox);
		
		JButton btnClear = new JButton("");
		btnClear.setBorderPainted(false);
		btnClear.setIcon(new ImageIcon(userCheckIn.class.getResource("/Images/clearbtn1.png")));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new bookingSpa().setVisible(true);
			}
		});
		btnClear.setBounds(623, 529, 117, 20);
		contentPane.add(btnClear);
		
		JButton btnCheckIn = new JButton("");
		btnCheckIn.setBorderPainted(false);
		btnCheckIn.setIcon(new ImageIcon(userCheckIn.class.getResource("/Images/checkinbtn1.png")));
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = 1;
				String name = nameTextField.getText();
				String mobileNumber = mobileTextField.getText();
				String email = emailTextField.getText();
				String gender = (String) genderComboBox.getSelectedItem();
				String nationality = nationTextField.getText();
				String checkinDate = checkinDateTextField.getText();
				String nrcID = nrcPassport.getText();
				String rmType = (String)roomComboBox.getSelectedItem();
				String roomNumber = (String) roomNoComboBox.getSelectedItem();
				String rmPrice = price.getText();
				String Query = "select max(id) from customers";
				
				try
				{
					ResultSet rs = Select.getData(Query);
					while(rs.next()) {
						id=rs.getInt(1);
					}
					id = id+1;
					
					if(!price.equals("")) {
						Query= "update room set status='Check In' where roomNo='"+roomNumber+"' ";
						InsertUpdateDelete.setData(Query, "");
						
						Query = "update customers set name='"+name+"', mobileNumber='"+mobileNumber+"', nationality = '"+nationality+"', gender = '"+gender+"', nrc = '"+nrcID+"', email = '"+email+"', checkIn = '"+checkinDate+"', roomNo = '"+roomNumber+"', roomType = '"+rmType+"', pricePerDay = '"+rmPrice+"' WHERE id = '"+id+"' ";
						InsertUpdateDelete.setData(Query, "Check In Successful");
						
						Query= "update users set status='Check In',roomNo='"+roomNumber+"', roomType='"+roomType+"' where email='"+email+"'";
						InsertUpdateDelete.setData(Query, "");
						
						Query= "update customers set status = 'Check In' where email='"+email+"'";
						InsertUpdateDelete.setData(Query, "");
						
						setVisible(false);
						new userCheckIn().setVisible(true);
					}
				}
				
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
				
				
				
			}
		});
		btnCheckIn.setBounds(460, 529, 117, 20);
		contentPane.add(btnCheckIn);
		
		JLabel lblNewLabel_3 = new JLabel("NRC / Passport ID");
		lblNewLabel_3.setBounds(67, 364, 114, 16);
		contentPane.add(lblNewLabel_3);
		
		nrcPassport = new JTextField();
		nrcPassport.setBackground(Color.LIGHT_GRAY);
		nrcPassport.setBounds(67, 390, 193, 26);
		contentPane.add(nrcPassport);
		nrcPassport.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(userCheckIn.class.getResource("/Images/usercheckin.png")));
		lblNewLabel.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel);
	}
}
