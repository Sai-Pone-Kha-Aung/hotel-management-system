package Pages;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;
import Database.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import Authentication.login;

public class bookingSpa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField mobileTextField;
	private JTextField emailTextField;
	private JTextField price;
	private JComboBox timeComboBox;
	private JComboBox spaComboBox;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bookingSpa frame = new bookingSpa();
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
	String spaType;
	String time;
	
	public void spaDetail() {
		timeComboBox.removeAllItems();
		price.setText("");
		spaType = (String) spaComboBox.getSelectedItem();
		time = (String) timeComboBox.getSelectedItem();
		
		try
		{
			ResultSet rs = Select.getData("select * from spa where spaType='"+spaType+"' and status='Not Booked' ");
			while(rs.next())
			{
				timeComboBox.addItem(rs.getString(3));
			}
		}
		
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public bookingSpa() {
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(101,106,105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


		setContentPane(contentPane);
		
		JButton btnBack = new JButton("");
		btnBack.setBorderPainted(false);
		btnBack.setIcon(new ImageIcon(bookingSpa.class.getResource("/Images/backwhite.png")));
		btnBack.setBounds(18, 13, 30, 20);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new userDashBoard().setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnBack);
		
		JButton btnExit = new JButton("");
		btnExit.setBorderPainted(false);
		btnExit.setBounds(754, 0, 40, 40);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to close this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0)
					System.exit(0);
			}
		});
		btnExit.setIcon(new ImageIcon(bookingSpa.class.getResource("/Images/close.png")));
		contentPane.add(btnExit);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(97, 148, 36, 16);
		contentPane.add(lblNewLabel_1);
		
		nameTextField = new JTextField();
		nameTextField.setBackground(Color.LIGHT_GRAY);
		nameTextField.setBounds(196, 143, 150, 26);
		nameTextField.setText(login.userName);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Mobile Number");
		lblNewLabel_2.setBounds(97, 454, 96, 16);
		contentPane.add(lblNewLabel_2);
		
		mobileTextField = new JTextField();
		mobileTextField.setBackground(Color.LIGHT_GRAY);
		mobileTextField.setBounds(196, 449, 150, 26);
		contentPane.add(mobileTextField);
		mobileTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setBackground(Color.LIGHT_GRAY);
		emailTextField.setBounds(535, 449, 150, 26);
		emailTextField.setText(login.userEmail);
		emailTextField.setColumns(10);
		contentPane.add(emailTextField);
		
		JLabel lblNewLabel_2_1 = new JLabel("Email");
		lblNewLabel_2_1.setBounds(415, 454, 34, 16);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Booking Date");
		lblNewLabel_1_1.setBounds(417, 300, 84, 16);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_4 = new JLabel("SPA Type");
		lblNewLabel_2_4.setBounds(97, 300, 70, 16);
		contentPane.add(lblNewLabel_2_4);
		
		spaComboBox = new JComboBox();
		spaComboBox.setBounds(196, 296, 150, 26);
		spaComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spaDetail();
			}
		});
		spaComboBox.setModel(new DefaultComboBoxModel(new String[] {"Aromatherapy and oil massage", "Facial massage", "Foot massage", "Reflexology", "Thai massage spas and salons", "Wat Po traditional Thai massage"}));
		contentPane.add(spaComboBox);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Booking Time");
		lblNewLabel_2_1_1.setBounds(97, 361, 90, 16);
		contentPane.add(lblNewLabel_2_1_1);
		
		timeComboBox = new JComboBox();
		timeComboBox.setBounds(196, 357, 150, 26);
		timeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = (String) timeComboBox.getSelectedItem();
				try
				{
					ResultSet rs = Select.getData("select * from spa where time='"+time+"'");
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
		contentPane.add(timeComboBox);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Price");
		lblNewLabel_2_3_1.setBounds(415, 361, 30, 16);
		contentPane.add(lblNewLabel_2_3_1);
		
		price = new JTextField();
		price.setBackground(Color.LIGHT_GRAY);
		price.setBounds(535, 356, 150, 26);
		price.setEditable(false);
		price.setColumns(10);
		contentPane.add(price);
		
		JButton btnClear = new JButton("");
		btnClear.setBorderPainted(false);
		btnClear.setIcon(new ImageIcon(bookingSpa.class.getResource("/Images/clearbtn.png")));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new bookingSpa().setVisible(true);
			}
		});
		btnClear.setBounds(618, 519, 117, 20);
		contentPane.add(btnClear);
		
		JButton btnCheckIn = new JButton("");
		btnCheckIn.setBorderPainted(false);
		btnCheckIn.setIcon(new ImageIcon(bookingSpa.class.getResource("/Images/bookingbtn.png")));
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = 1;
				String email = emailTextField.getText();
				String spaTy = (String)spaComboBox.getSelectedItem();
				String Query = "select max(id) from customers";
				
				try
				{
					ResultSet rs = Select.getData(Query);
					while(rs.next()) {
						id=rs.getInt(1);
					}
					id = id+1;
					
					if(!price.equals("")) {
						Query= "update spa set status='Booked' where spaType='"+spaTy+"' ";
						InsertUpdateDelete.setData(Query, "Booking Is Successful");
						Query= "update users set status = 'Booked' where email='"+email+"'";
						InsertUpdateDelete.setData(Query, "");
						setVisible(false);
						new bookingSpa().setVisible(true);
					}
				}
				
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
				
				
				
			}
		});
		btnCheckIn.setBounds(472, 519, 117, 20);
		contentPane.add(btnCheckIn);
		
		dateChooser = new JDateChooser();
		dateChooser.setDate(new Date());
		dateChooser.setBounds(535, 296, 150, 26);
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(bookingSpa.class.getResource("/Images/spabook.png")));
		lblNewLabel_3.setBounds(0, 0, 800, 572);
		contentPane.add(lblNewLabel_3);
	}
}
