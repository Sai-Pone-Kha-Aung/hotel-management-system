package Pages;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Authentication.login;
import Database.*;

import java.sql.*;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;

public class adminHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminHome frame = new adminHome();
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
	public adminHome() {
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				ResultSet rs = Select.getData("select * from users");
				DefaultTableModel model =(DefaultTableModel)table.getModel();
				model.setRowCount(0);
				
				try
				{
					while(rs.next()) 
					{
						model.addRow(new Object[] {rs.getString(2), rs.getString(3), rs.getString(7)});
					}
					rs.close();
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
		contentPane.setBackground(new Color(101, 106, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
		});
		scrollPane.setBounds(18, 93, 765, 412);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.DARK_GRAY);
		table.setForeground(Color.WHITE);
		model = new DefaultTableModel();
		Object[] column = {"Name", "Email", "Status"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("LOGOUT");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setIcon(new ImageIcon(adminHome.class.getResource("/Images/logout1.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to close this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0) {
				
				setVisible(false);
				new login().setVisible(true);
			}
			}
		});
		btnNewButton.setBounds(683, 517, 100, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to close this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0)
					System.exit(0);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(adminHome.class.getResource("/Images/close.png")));
		btnNewButton_1.setBounds(754, 0, 40, 40);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Search Name or Email");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(162, 55, 136, 16);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(310, 53, 179, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setIcon(new ImageIcon(adminHome.class.getResource("/Images/searchbtn.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameOrEmail = textField.getText();
				ResultSet rs = Select.getData("select * from users where name like '%"+nameOrEmail+"%' or email like '%"+nameOrEmail+"%' ");
				DefaultTableModel model =(DefaultTableModel)table.getModel();
				model.setRowCount(0);
				
				try
				{
					while(rs.next()) 
					{
						model.addRow(new Object[] {rs.getString(2), rs.getString(3), rs.getString(7)});
					}
					rs.close();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnNewButton_2.setBounds(504, 53, 70, 20);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("");
		btnNewButton_2_1.setBorderPainted(false);
		btnNewButton_2_1.setIcon(new ImageIcon(adminHome.class.getResource("/Images/clearbtn.png")));
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new adminHome().setVisible(true);
			}
		});
		btnNewButton_2_1.setBounds(587, 53, 70, 20);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setIcon(new ImageIcon(adminHome.class.getResource("/Images/backwhite.png")));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new adminDashBoard().setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(18, 13, 30, 20);
		contentPane.add(btnNewButton_1_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(adminHome.class.getResource("/Images/adminhome.png")));
		label.setBounds(0, 0, 800, 572);
		contentPane.add(label);
	}
}
