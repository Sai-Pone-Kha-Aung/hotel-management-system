package Pages;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Database.*;
import java.sql.*;

public class manageFood extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JTextField foodname;
	private JTextField foodPrice;
	private JComboBox foodtype;
	private JTextField food_Quantity;
	private JComboBox foodStatus;
	private JLabel foodId;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageFood frame = new manageFood();
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
	
	public manageFood() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(101,106,105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		scrollPane.setBounds(13, 89, 442, 446);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model =(DefaultTableModel)table.getModel();
				int selectIndex = table.getSelectedRow();
				
				foodId.setText(model.getValueAt(selectIndex, 0).toString());
				foodname.setText(model.getValueAt(selectIndex, 1).toString());
				foodtype.setSelectedItem(model.getValueAt(selectIndex, 2).toString());
				food_Quantity.setText(model.getValueAt(selectIndex, 3).toString());
				foodPrice.setText(model.getValueAt(selectIndex, 3).toString());
				foodStatus.setSelectedItem(model.getValueAt(selectIndex, 4).toString());
				
			}
		});
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				ResultSet rs = Select.getData("select * from food");
				DefaultTableModel model =(DefaultTableModel)table.getModel();
				model.setRowCount(0);
				
				try
				{
					while(rs.next()) 
					{
						model.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
					}
					rs.close();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		table.setBackground(Color.DARK_GRAY);
		table.setForeground(Color.WHITE);
		model = new DefaultTableModel();
		Object[] column = {"ID", "Food Name", "Type", "Quantity", "Price", "Status"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(manageFood.class.getResource("/Images/backwhite.png")));
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new adminDashBoard().setVisible(true);
			}
		});
		btnNewButton.setBounds(18, 13, 30, 20);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton1 = new JButton("");
		btnNewButton1.setBorderPainted(false);
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to close this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0)
					System.exit(0);
			}
		});
		btnNewButton1.setBounds(754, 0, 40, 40);
		btnNewButton1.setIcon(new ImageIcon(manageFood.class.getResource("/Images/close.png")));
		contentPane.add(btnNewButton1);
		
		JLabel lblNewLabel = new JLabel("FOOD NAME");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(511, 162, 104, 16);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(lblNewLabel);
		
		foodname = new JTextField();
		foodname.setBackground(Color.LIGHT_GRAY);
		foodname.setBounds(511, 188, 221, 26);
		contentPane.add(foodname);
		foodname.setColumns(10);
		
		JLabel lblRoomType = new JLabel("FOOD TYPE");
		lblRoomType.setForeground(Color.WHITE);
		lblRoomType.setBounds(511, 224, 81, 16);
		lblRoomType.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(lblRoomType);
		
		JLabel lblPrice = new JLabel("PRICE");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setBounds(511, 354, 38, 16);
		lblPrice.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(lblPrice);
		
		foodPrice = new JTextField();
		foodPrice.setBackground(Color.LIGHT_GRAY);
		foodPrice.setBounds(511, 380, 221, 26);
		foodPrice.setColumns(10);
		contentPane.add(foodPrice);
		
		foodtype = new JComboBox();
		foodtype.setModel(new DefaultComboBoxModel(new String[] {"Breakfast", "Lunch", "Dinner", "Appetizer", "Drink"}));
		foodtype.setBounds(511, 250, 221, 27);
		contentPane.add(foodtype);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setIcon(new ImageIcon(manageFood.class.getResource("/Images/edit.png")));
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setBounds(639, 495, 117, 20);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String foodID = foodId.getText();
				String foodName = foodname.getText();
				String foodType = (String) foodtype.getSelectedItem();
				String foodQuantity = food_Quantity.getText();
				String price = foodPrice.getText();
				String status = (String) foodStatus.getSelectedItem();

				

				 String checkQuery = "select * from food where foodID='"+foodID+"'";
				    ResultSet rs = Select.getData(checkQuery);
				    try {
				        if(rs.next()) {
				            String Query = "update food set foodName= '"+foodName+"', foodType='"+foodType+"', quantity='"+foodQuantity+"', price='"+price+"', status='"+status+"' " ;
				            InsertUpdateDelete.setData(Query, "Successfully Updated");
				            setVisible(false);
				            new manageFood().setVisible(true);
				        } else {
				            JOptionPane.showMessageDialog(null, "FoodID does not exist");
				        }
				    } catch (SQLException ex) {
				        JOptionPane.showMessageDialog(null, ex);
				    }
			}
		});
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(manageFood.class.getResource("/Images/addbtn.png")));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String foodName = foodname.getText();
				String foodType = (String) foodtype.getSelectedItem();
				String quantity = food_Quantity.getText();
				String price = foodPrice.getText();
				String status = (String) foodStatus.getSelectedItem();
				
				
				String Query = "insert into food(foodName, foodType, quantity, price, status) values ('"+foodName+"', '"+foodType+"', '"+quantity+"', '"+price+"', '"+status+"')";
				InsertUpdateDelete.setData(Query, "Successfully Added");
				setVisible(false);
				new manageFood().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(488, 495, 117, 20);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_2 = new JButton("");
		btnNewButton_1_2.setIcon(new ImageIcon(manageFood.class.getResource("/Images/deletebtn.png")));
		btnNewButton_1_2.setBorderPainted(false);
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String foodID = foodId.getText();
			    
			    String checkQuery = "select * from food where foodID='"+foodID+"'";
			    ResultSet rs = Select.getData(checkQuery);
			    try {
			        if(rs.next()) {
			            String Query = "delete from food where foodID='"+foodID+"'" ;
			            InsertUpdateDelete.setData(Query, "Successfully Deleted");
			            setVisible(false);
			            new manageFood().setVisible(true);
			        } else {
			            JOptionPane.showMessageDialog(null, "FoodID does not exist");
			        }
			    } catch (SQLException ex) {
			        JOptionPane.showMessageDialog(null, ex);
			    }
			}
		});
		
		btnNewButton_1_2.setBounds(489, 527, 117, 20);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_3 = new JButton("");
		btnNewButton_1_3.setIcon(new ImageIcon(manageFood.class.getResource("/Images/checkorderbtn.png")));
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new checkOrder().setVisible(true);
			}
		});
		btnNewButton_1_3.setBorderPainted(false);
		btnNewButton_1_3.setBounds(639, 527, 117, 20);
		contentPane.add(btnNewButton_1_3);
		
		JLabel lblNewLabel_2 = new JLabel("FOOD ID");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel_2.setBounds(511, 101, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		foodId = new JLabel("ID");
		foodId.setForeground(Color.BLACK);
		foodId.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		foodId.setBounds(521, 129, 61, 16);
		contentPane.add(foodId);
		
		JLabel lblQuantity = new JLabel("QUANTITY");
		lblQuantity.setForeground(Color.WHITE);
		lblQuantity.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblQuantity.setBounds(511, 289, 221, 16);
		contentPane.add(lblQuantity);
		
		food_Quantity = new JTextField();
		food_Quantity.setBackground(Color.LIGHT_GRAY);
		food_Quantity.setColumns(10);
		food_Quantity.setBounds(511, 315, 221, 26);
		contentPane.add(food_Quantity);
		
		foodStatus = new JComboBox();
		foodStatus.setModel(new DefaultComboBoxModel(new String[] {"Available", "Not Available", "Out of Stock"}));
		foodStatus.setBounds(511, 444, 221, 26);
		contentPane.add(foodStatus);
		
		JLabel lblStatus = new JLabel("STATUS");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblStatus.setBounds(511, 418, 221, 16);
		contentPane.add(lblStatus);
		
		JPanel panel = new JPanel();
		panel.setBounds(511, 127, 221, 21);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(manageFood.class.getResource("/Images/managefood.png")));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(0, 0, 800, 572);
		contentPane.add(lblNewLabel_1);
	}
}
