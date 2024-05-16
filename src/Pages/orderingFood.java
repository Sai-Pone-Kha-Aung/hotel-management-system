package Pages;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import Database.*;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import Authentication.login;

public class orderingFood extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField roomNo;
	private JComboBox foodTypeComboBox;
	private JComboBox foodname;
	private JSpinner quantitySpinner;
	private JTextField foodprice;
	private JTable table;
	private DefaultTableModel model;
	private JTextField grandTotal;
	private JTextField totalAmount;
	String foodType;
	String name;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					orderingFood frame = new orderingFood();
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
	
	
	public void foodDetail() {
		foodname.removeAllItems();
		foodprice.setText("");
		foodType = (String) foodTypeComboBox.getSelectedItem();
		name = (String) foodname.getSelectedItem();
		
		try
		{
			ResultSet rs = Select.getData("select * from food where foodType='"+foodType+"' and status='Available' ");
			while(rs.next())
			{
				foodname.addItem(rs.getString(2));
			}
		}
		
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void setGrandTotal(double grandTotalValue) {
		grandTotal.setText(String.valueOf(grandTotalValue));
	}
	
	public void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);
	}
	public orderingFood() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(101,106,105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setIcon(new ImageIcon(orderingFood.class.getResource("/Images/backwhite.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new userDashBoard().setVisible(true);
			
			}
		});
		btnNewButton.setBounds(18, 13, 30, 20);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setIcon(new ImageIcon(orderingFood.class.getResource("/Images/close.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int a = JOptionPane.showConfirmDialog(null, "Do you want to close this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0)
					System.exit(0);
		
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(orderingFood.class.getResource("/Images/close.png")));
		btnNewButton_1.setBounds(753, 6, 41, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Room Number");
		lblNewLabel_1.setBounds(123, 93, 102, 16);
		contentPane.add(lblNewLabel_1);
		
		roomNo = new JTextField();
		roomNo.setBackground(Color.LIGHT_GRAY);
		roomNo.setBounds(123, 121, 150, 26);
		roomNo.setText(login.roomNum);
		contentPane.add(roomNo);
		roomNo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Food Type");
		lblNewLabel_2.setBounds(341, 92, 102, 16);
		contentPane.add(lblNewLabel_2);
		
		foodTypeComboBox = new JComboBox();
		foodTypeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foodDetail();
				Integer quantity = (Integer) quantitySpinner.getValue();
				
				String foodPriceText = foodprice.getText();
				if (!foodPriceText.isEmpty()) {
				        float foodPrice = Float.parseFloat(foodPriceText);
				        totalAmount.setText(String.valueOf(foodPrice * quantity.intValue()));
				    }
			}
		});
		foodTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Breakfast", "Lunch", "Dinner", "Appetizer", "Drink", "Snack"}));
		foodTypeComboBox.setBounds(335, 121, 150, 26);
		contentPane.add(foodTypeComboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Food Name");
		lblNewLabel_3.setBounds(538, 93, 108, 16);
		contentPane.add(lblNewLabel_3);
		
		foodname = new JComboBox();
		foodname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer quantity = (Integer) quantitySpinner.getValue();
				
				String foodPriceText = foodprice.getText();
				if (!foodPriceText.isEmpty()) {
				        float foodPrice = Float.parseFloat(foodPriceText);
				        totalAmount.setText(String.valueOf(foodPrice * quantity.intValue()));
				    }
				
				name = (String) foodname.getSelectedItem();
				try
				{
					ResultSet rs = Select.getData("select * from food where foodName='"+name+"'");
					while(rs.next()) 
					{
						foodprice.setText(rs.getString(5));
						
					}
					
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		foodname.setBounds(538, 121, 150, 26);
		contentPane.add(foodname);
		
		JLabel lblNewLabel_4 = new JLabel("Quantity");
		lblNewLabel_4.setBounds(123, 177, 54, 16);
		contentPane.add(lblNewLabel_4);
		SpinnerNumberModel spinner_model = new SpinnerNumberModel(
				1, 
                1, 
                Integer.MAX_VALUE, 
                1);
		quantitySpinner = new JSpinner(spinner_model);
		quantitySpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Integer quantity = (Integer) quantitySpinner.getValue();
				
				float foodPrice = Float.parseFloat(foodprice.getText());
				
				totalAmount.setText(String.valueOf(foodPrice * quantity.intValue()));
				
			}
		});
		quantitySpinner.setBounds(123, 205, 150, 26);
		contentPane.add(quantitySpinner);
		
		JLabel lblNewLabel_5 = new JLabel("Price");
		lblNewLabel_5.setBounds(341, 177, 30, 16);
		contentPane.add(lblNewLabel_5);
		
		foodprice = new JTextField();
		foodprice.setBackground(Color.LIGHT_GRAY);
		foodprice.setBounds(341, 205, 150, 26);
		contentPane.add(foodprice);
		foodprice.setColumns(10);
		
		JButton order = new JButton("");
		order.setIcon(new ImageIcon(orderingFood.class.getResource("/Images/orderbtn.png")));
		order.setBorderPainted(false);
		order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					 DefaultTableModel model = (DefaultTableModel) table.getModel();

					   
					    for (int i = 0; i < model.getRowCount(); i++) {
					    
					        String roomNumber = model.getValueAt(i, 0).toString();
					        String foodName = model.getValueAt(i, 1).toString();
					        String foodType = model.getValueAt(i, 2).toString();
					        String foodPrice = model.getValueAt(i, 3).toString();
					        int quantity = Integer.parseInt(model.getValueAt(i, 4).toString());
					        double totalValue = Double.parseDouble(model.getValueAt(i, 5).toString());

					   
					        String Query = "INSERT INTO orders(roomNumber, foodName, foodType, quantity, price, total) VALUES('"
					                + roomNumber + "', '" + foodName + "', '" + foodType + "', '" + quantity + "', '" + foodPrice + "', '"
					                + totalValue + "')";
					        InsertUpdateDelete.setData(Query, "");
					    }
				
					    
				}
				
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			    Payment payment = new Payment();
			    payment.setVisible(true);
			}

			
		});
		
		order.setBounds(492, 533, 117, 20);
		contentPane.add(order);
		
		JButton clear = new JButton("");
		clear.setIcon(new ImageIcon(orderingFood.class.getResource("/Images/clearbtn.png")));
		clear.setBorderPainted(false);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new orderingFood().setVisible(true);
			}
		});
		clear.setBounds(252, 263, 117, 20);
		contentPane.add(clear);
		
		JButton btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon(orderingFood.class.getResource("/Images/addbtn.png")));
		btnAdd.setBorderPainted(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String roomNumber = roomNo.getText();
				String foodName = (String) foodname.getSelectedItem();
				String foodType = (String) foodTypeComboBox.getSelectedItem();
				Integer quantity = (Integer) quantitySpinner.getValue();
				String foodPrice = foodprice.getText();
				double grandTotalValue = 0.0;
				double price = Double.parseDouble(foodPrice);
				double totalValue = price * quantity.doubleValue();
				
				if(roomNumber.equals("")) {
					JOptionPane.showMessageDialog(null, "Room Number Is Required");
				}
				else
				{
					model = (DefaultTableModel) table.getModel();
					
					model.addRow(new Object[] {
							roomNumber,
							foodName,
							foodType,
							foodPrice,
							quantity,
							Double.valueOf(totalValue)
					});
					
					for(int i = 0; i < table.getRowCount(); i++) {
						grandTotalValue = grandTotalValue + Double.parseDouble(table.getValueAt(i, 5).toString());
					}
					
					grandTotal.setText(Double.toString(grandTotalValue));
				}
				
				
			}
		});
		btnAdd.setBounds(123, 262, 117, 20);
		contentPane.add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 321, 680, 194);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		
		table = new JTable();
		table.setBackground(Color.DARK_GRAY);
		table.setForeground(Color.WHITE);
		model = new DefaultTableModel();
		Object[] column = {"Room Number", "Food Name", "Type","Price","Quantity", "Total"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_6 = new JLabel("Grand Total");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(66, 537, 73, 16);
		contentPane.add(lblNewLabel_6);
		
		grandTotal = new JTextField();
		grandTotal.setBackground(Color.LIGHT_GRAY);
		grandTotal.setBounds(151, 532, 150, 26);
		contentPane.add(grandTotal);
		grandTotal.setColumns(10);
		
		totalAmount = new JTextField();
		totalAmount.setBackground(Color.LIGHT_GRAY);
		totalAmount.setBounds(538, 205, 150, 26);
		contentPane.add(totalAmount);
		totalAmount.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Total Price");
		lblNewLabel_7.setBounds(538, 177, 66, 16);
		contentPane.add(lblNewLabel_7);
		
		JButton btnGenerateBill = new JButton("");
		btnGenerateBill.setIcon(new ImageIcon(orderingFood.class.getResource("/Images/generatebill.png")));
		btnGenerateBill.setBorderPainted(false);
		btnGenerateBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double total = 0;
				String roomNum = roomNo.getText();
		
				
				final String path = "src/export bill/Hotel Transylvania";
				com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
				
				try 
				{
					    billGenerate(roomNum,total, path, doc);
				}
				
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
				setVisible(false);
				new orderingFood().setVisible(true);
			}
			
			public void billGenerate(String roomNumber, double total, String path, com.itextpdf.text.Document doc)
					throws DocumentException, FileNotFoundException {
				PdfWriter.getInstance(doc, new FileOutputStream(path+" "+roomNumber+".pdf"));
				doc.open();
				
				Paragraph p1 = new Paragraph("HOTEL TRANSYLVANIA\n");
				doc.add(p1);

				Paragraph p2 = new Paragraph("--------------------------------------------------------------------------------------------------------------------------------\n");
				doc.add(p2);
				
				Paragraph p3 = new Paragraph("Customer: \nRoom Number: "+roomNumber+"\n");
				doc.add(p3);
				doc.add(p2);
				Paragraph p4 = new Paragraph(" ");
				doc.add(p4);
				
				PdfPTable tb1 = new PdfPTable(4);
				tb1.addCell("Name" );
				tb1.addCell("Price");
				tb1.addCell("Quantity");
				tb1.addCell("Total");
				for(int i = 0; i < table.getRowCount(); i++) {
					String n = table.getValueAt(i, 1).toString();
					String d = table.getValueAt(i, 3).toString();
					String r = table.getValueAt(i, 4).toString();
					String p = table.getValueAt(i, 5).toString();
					tb1.addCell(n);
					tb1.addCell(d);
					tb1.addCell(r);
					tb1.addCell(p);
					total += Double.parseDouble(d) * Integer.parseInt(r);
				}
				tb1.addCell("Total");
				tb1.addCell("");
				tb1.addCell("");
				tb1.addCell(String.valueOf(total));
				doc.add(tb1);
				doc.add(p2);
				
				Paragraph p5 = new Paragraph("Thank you, Please Visit Again.");
				doc.add(p5);
				doc.close();
				JOptionPane.showConfirmDialog(null, "Do you want to Print Bill?", "Select", JOptionPane.YES_NO_OPTION);
			}
		});
		btnGenerateBill.setBounds(619, 533, 117, 20);
		contentPane.add(btnGenerateBill);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(orderingFood.class.getResource("/Images/foodorder.png")));
		lblNewLabel.setBounds(0, 0, 800, 572);
		contentPane.add(lblNewLabel);
		
	}
}
