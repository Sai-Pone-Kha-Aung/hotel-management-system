package Pages;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;

import Authentication.login;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class adminDashBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminDashBoard frame = new adminDashBoard();
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
	public adminDashBoard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(101,106,105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(adminDashBoard.class.getResource("/Images/home.png")));
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new adminHome().setVisible(true);
			}
		});
		
		JButton btnNewButton_6 = new JButton("LOGOUT");
		btnNewButton_6.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewButton_6.setIcon(new ImageIcon(adminDashBoard.class.getResource("/Images/logout.png")));
		btnNewButton_6.setBackground(Color.LIGHT_GRAY);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to logout this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0) {
					
					setVisible(false);
					new login().setVisible(true);
				}
			}
		});
		btnNewButton_6.setBounds(552, 497, 150, 50);
		contentPane.add(btnNewButton_6);
		btnNewButton.setBounds(95, 125, 175, 145);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(adminDashBoard.class.getResource("/Images/checkInbtn.png")));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new checkIn().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(312, 125, 175, 145);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(adminDashBoard.class.getResource("/Images/checkoutbtn.png")));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new checkOut().setVisible(true);
			}
		});
		btnNewButton_2.setBounds(526, 125, 175, 145);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon(adminDashBoard.class.getResource("/Images/manageroombtn.png")));
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new manageRoom().setVisible(true);
			}
		});
		btnNewButton_3.setBounds(95, 297, 175, 145);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setIcon(new ImageIcon(adminDashBoard.class.getResource("/Images/managefoodbtn.png")));
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new manageFood().setVisible(true);
			}
		});
		btnNewButton_4.setBounds(312, 297, 175, 145);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setIcon(new ImageIcon(adminDashBoard.class.getResource("/Images/managespabtn.png")));
		btnNewButton_5.setBorderPainted(false);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new manageSPA().setVisible(true);
			}
		});
		btnNewButton_5.setBounds(526, 297, 175, 145);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_7 = new JButton("");
		btnNewButton_7.setIcon(new ImageIcon(adminDashBoard.class.getResource("/Images/close.png")));
		btnNewButton_7.setBorderPainted(false);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to exit this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0) {
					System.exit(0);
				}
			}
		});
		btnNewButton_7.setBounds(746, 6, 40, 40);
		contentPane.add(btnNewButton_7);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(adminDashBoard.class.getResource("/Images/admin page.png")));
		lblNewLabel.setBounds(0, -11, 800, 611);
		contentPane.add(lblNewLabel);
	}
}
