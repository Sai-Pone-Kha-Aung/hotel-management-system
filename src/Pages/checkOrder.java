package Pages;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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

public class checkOrder extends JFrame {

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
					checkOrder frame = new checkOrder();
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
	public checkOrder() {
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				ResultSet rs = Select.getData("select * from orders");
				DefaultTableModel model =(DefaultTableModel)table.getModel();
				model.setRowCount(0);
				
				try
				{
					while(rs.next()) 
					{
						model.addRow(new Object[] {rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
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
		scrollPane.setBounds(18, 100, 765, 412);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.DARK_GRAY);
		table.setForeground(Color.WHITE);
		model = new DefaultTableModel();
		Object[] column = {"Room Number", "Food Name", "Food Type", "Quantity", "Price", "Total"};
		//Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to close this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0)
					System.exit(0);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(checkOrder.class.getResource("/Images/close.png")));
		btnNewButton_1.setBounds(754, 0, 40, 40);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Search Room");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(204, 62, 81, 16);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(310, 60, 179, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setIcon(new ImageIcon(checkOrder.class.getResource("/Images/searchbtn.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String roomNum = textField.getText();
				ResultSet rs = Select.getData("select * from orders where roomNumber like '%"+roomNum+"%' ");
				DefaultTableModel model =(DefaultTableModel)table.getModel();
				model.setRowCount(0);
				
				try
				{
					while(rs.next()) 
					{
						model.addRow(new Object[] {rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
					}
					rs.close();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnNewButton_2.setBounds(504, 60, 70, 20);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("");
		btnNewButton_2_1.setBorderPainted(false);
		btnNewButton_2_1.setIcon(new ImageIcon(checkOrder.class.getResource("/Images/clearbtn.png")));
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new checkOrder().setVisible(true);
			}
		});
		btnNewButton_2_1.setBounds(587, 60, 70, 20);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setIcon(new ImageIcon(checkOrder.class.getResource("/Images/backwhite.png")));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new manageFood().setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(18, 13, 30, 20);
		contentPane.add(btnNewButton_1_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(checkOrder.class.getResource("/Images/checkorder.png")));
		label.setBounds(0, 0, 800, 572);
		contentPane.add(label);
	}
}
