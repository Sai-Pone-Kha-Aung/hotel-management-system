package Authentication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import Database.*;

public class forgotPassword extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	String email;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					forgotPassword frame = new forgotPassword();
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
	public forgotPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(101, 106, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		emailLabel.setBounds(182, 211, 98, 23);
		contentPane.add(emailLabel);
		
		JLabel newPasswordLabel = new JLabel("New Password");
		newPasswordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		newPasswordLabel.setBounds(182, 262, 98, 23);
		contentPane.add(newPasswordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(174, 174, 174));
		passwordField.setBounds(308, 262, 209, 26);
		contentPane.add(passwordField);
		
		textField = new JTextField();
		textField.setBackground(new Color(174, 174, 174));
		textField.setColumns(10);
		textField.setBounds(308, 211, 209, 26);
		contentPane.add(textField);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(forgotPassword.class.getResource("/Images/signupbtn.png")));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new signupForm().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(451, 382, 153, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(forgotPassword.class.getResource("/Images/loginbtn.png")));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new login().setVisible(true);
			}
		});
		btnNewButton_2.setBounds(195, 382, 153, 29);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon(forgotPassword.class.getResource("/Images/savebtn.png")));
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check = 0;
				String newPassword = passwordField.getText();
				if(newPassword.equals("")) {
					check = 1;
					JOptionPane.showMessageDialog(null, "New Password Is Required");
				}
				else 
				{
					ResultSet rs = Select.getData("select * from users where email='"+email+"'");
					
					try 
					{
						if(rs.next()) {
							check = 1;
							InsertUpdateDelete.setData("update users set password='"+newPassword+"' where email='"+email+"'", "Password Set Successfully");
							setVisible(false);
							new forgotPassword().setVisible(true);
						}
					}
					
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, e1);
					}
				}
			}
		});
		btnNewButton_3.setBounds(318, 314, 153, 29);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to close this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0)
					System.exit(0);
			}
		});
		btnNewButton.setIcon(new ImageIcon(forgotPassword.class.getResource("/Images/close.png")));
		btnNewButton.setBackground(new Color(247, 255, 249));
		btnNewButton.setBounds(753, 6, 41, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_3_1 = new JButton("");
		btnNewButton_3_1.setBorderPainted(false);
		btnNewButton_3_1.setIcon(new ImageIcon(forgotPassword.class.getResource("/Images/searchbtn.png")));
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check = 0;
				email = textField.getText();
				if(email.equals("")) {
					check = 1;
					JOptionPane.showMessageDialog(null, "Email Field Is Required");
				}
				else 
				{
					ResultSet rs = Select.getData("select * from users where email='"+email+"'");
					
					try
					{
						if(rs.next()) {
							check=1;
							textField.setEditable(true);
							JOptionPane.showMessageDialog(null, "Correct Email");
							
						}
					}
					
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				
				if(check == 0) {
					JOptionPane.showMessageDialog(null, "Incorrect Email");
				}
				
			}
		});
		btnNewButton_3_1.setBounds(551, 212, 72, 23);
		contentPane.add(btnNewButton_3_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(forgotPassword.class.getResource("/Images/resetpassword.png")));
		lblNewLabel.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel);
	}
}
