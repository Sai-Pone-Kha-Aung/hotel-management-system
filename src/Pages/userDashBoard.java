package Pages;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Authentication.login;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class userDashBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userDashBoard frame = new userDashBoard();
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
	
	public userDashBoard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(101,106,105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(userDashBoard.class.getResource("/Images/bookroombtn.png")));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(83, 123, 175, 145);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new bookingRoom().setVisible(true);
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(userDashBoard.class.getResource("/Images/bookspabtn.png")));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new bookingSpa().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(540, 123, 175, 145);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(userDashBoard.class.getResource("/Images/orderfoodbtn.png")));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new orderingFood().setVisible(true);
			}
		});
		btnNewButton_2.setBounds(312, 123, 175, 145);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon(userDashBoard.class.getResource("/Images/checkoutbtn.png")));
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new userCheckOut().setVisible(true);
			}
		});
		btnNewButton_3.setBounds(447, 321, 175, 145);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_6 = new JButton("LOGOUT");
		btnNewButton_6.setIcon(new ImageIcon(userDashBoard.class.getResource("/Images/logout.png")));
		btnNewButton_6.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewButton_6.setBounds(565, 505, 150, 50);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to logout this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0) {
					
					setVisible(false);
					new login().setVisible(true);
				}
			}
		});
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setIcon(new ImageIcon(userDashBoard.class.getResource("/Images/checkInbtn.png")));
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new userCheckIn().setVisible(true);
			}
		});
		btnNewButton_4.setBounds(190, 321, 175, 145);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setBorderPainted(false);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to exit this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0) {
					
					System.exit(0);
				}
			}
		});
		btnNewButton_5.setIcon(new ImageIcon(userDashBoard.class.getResource("/Images/close.png")));
		btnNewButton_5.setBounds(754, 0, 40, 40);
		contentPane.add(btnNewButton_5);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(userDashBoard.class.getResource("/Images/userpage.png")));
		lblNewLabel.setBounds(0, 0, 800, 600);
		contentPane.add(lblNewLabel);
	}
}
