package Authentication;

import java.sql.*;
import Database.Select;
import Pages.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	public static String userName;
	public static String roomNum;
	public static String userEmail;
	public static String roomType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to close this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0)
					System.exit(0);
			}
		});
		btnNewButton.setBackground(new Color(247, 255, 249));
		btnNewButton.setIcon(new ImageIcon(login.class.getResource("/Images/close.png")));
		btnNewButton.setBounds(753, 6, 41, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(41, 288, 86, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(41, 339, 86, 23);
		contentPane.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.setBackground(new Color(174, 174, 174));
		textField.setBounds(133, 287, 231, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(165, 165, 165));
		passwordField.setBounds(133, 338, 231, 26);
		contentPane.add(passwordField);
		
		JButton btnNewButton_1 = new JButton("Create Account");
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new signupForm().setVisible(true);
			}
		});
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(login.class.getResource("/Images/loginbtn.png")));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBackground(new Color(161, 89, 180));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int check = 0;
				String email = textField.getText();
				String password = passwordField.getText();
				if(email.equals("") || password.equals("")) {
					check = 1;
					JOptionPane.showMessageDialog(null, "Every Field Is Required");
				}
				else if(email.equals("admin") && password.equals("admin")) {
					check = 1;
					setVisible(false);
					new adminDashBoard().setVisible(true);
				}
				else 
				{
					ResultSet rs = Select.getData("select * from users where email='"+email+"' and password='"+password+"'");
					try
					{
						if(rs.next()) 
						{
							check = 1;
							if(rs.getString(4).equals(password)) 
							{
								setVisible(false);
								new userDashBoard().setVisible(true);
							}
							
						}
						
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, e);
					}
				}
				
				if(check == 0) 
					JOptionPane.showMessageDialog(null, "Incorrect Email or Password");
				ResultSet rs = Select.getData("select name,email,roomNo,roomType from users where email='"+email+"'");
                try {
                    if (rs.next()) {
                        userName = rs.getString("name"); 
                        userEmail = rs.getString("email");
                        roomNum = rs.getString("roomNo");
                        roomType = rs.getString("roomType");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
					
			}
		});
		btnNewButton_2.setBounds(123, 429, 153, 29);
		contentPane.add(btnNewButton_2);
		btnNewButton_1.setBounds(113, 470, 174, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Forgot Password?");
		btnNewButton_3.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new forgotPassword().setVisible(true);
			}
		});
		btnNewButton_3.setBounds(213, 376, 165, 29);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(login.class.getResource("/Images/loginpage.png")));
		lblNewLabel_2.setBounds(0, 0, 800, 572);
		contentPane.add(lblNewLabel_2);
	}
}
