package Authentication;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import Database.InsertUpdateDelete;
import javax.swing.ImageIcon;

public class signupForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signupForm frame = new signupForm();
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
	public signupForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(101, 106, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(454, 182, 75, 17);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setBounds(454, 240, 75, 17);
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setBounds(454, 298, 75, 17);
		lblNewLabel_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(signupForm.class.getResource("/Images/signupbtn.png")));
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				String email = textField_1.getText();
				String password = passwordField.getText();
				
				String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		        Pattern pattern = Pattern.compile(emailRegex);
		        Matcher matcher = pattern.matcher(email);
		        
		        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		        Pattern passwordPattern = Pattern.compile(passwordRegex);
		        Matcher passwordMatcher = passwordPattern.matcher(password);
		        
				if(name.equals("") || email.equals("") || password.equals(""))
					JOptionPane.showMessageDialog(null, "Every Field Is Required");
				else if(!matcher.matches()) {
					JOptionPane.showMessageDialog(null, "add @ to your email");
				} 
				else if(password.length() < 8){
					JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long");
				}
				else {
					String Query;
					Query = "insert into users(name, email, password) values('"+name+"','"+email+"','"+password+"')";
					InsertUpdateDelete.setData(Query, "Registered Successfully");
					setVisible(false);
					new signupForm().setVisible(true);
				}
				
			}
		});
		btnNewButton.setBounds(540, 374, 153, 29);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(174, 174, 174));
		passwordField.setBounds(555, 298, 214, 26);
		contentPane.add(passwordField);
		
		textField = new JTextField();
		textField.setBackground(new Color(174, 174, 174));
		textField.setBounds(555, 182, 214, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(174, 174, 174));
		textField_1.setColumns(10);
		textField_1.setBounds(555, 240, 214, 26);
		contentPane.add(textField_1);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to close this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0)
					System.exit(0);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(signupForm.class.getResource("/Images/close.png")));
		btnNewButton_1.setBounds(753, 6, 41, 37);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(signupForm.class.getResource("/Images/loginbtn.png")));
		btnNewButton_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new login().setVisible(true);
			}
		});
		btnNewButton_2.setBounds(675, 425, 93, 29);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Already have an account?");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel.setBounds(474, 430, 173, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(signupForm.class.getResource("/Images/createaccount.png")));
		lblNewLabel_2.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel_2);
	}
}
