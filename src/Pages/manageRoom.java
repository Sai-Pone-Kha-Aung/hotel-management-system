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

public class manageRoom extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField roomNumber;
	private JTextField roomPrice;
	private JComboBox rtype;
	private JComboBox bookingComboBox;
	private JLabel roomID;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageRoom frame = new manageRoom();
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
	public manageRoom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(101,106,105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 79, 418, 467);
		scrollPane.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		contentPane.setLayout(null);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model =(DefaultTableModel)table.getModel();
				int selectIndex = table.getSelectedRow();
				
				roomID.setText(model.getValueAt(selectIndex, 0).toString());
				roomNumber.setText(model.getValueAt(selectIndex, 1).toString());
				rtype.setSelectedItem(model.getValueAt(selectIndex, 2).toString());
				roomPrice.setText(model.getValueAt(selectIndex, 3).toString());
				bookingComboBox.setSelectedItem(model.getValueAt(selectIndex, 4).toString());
				
				
				
			}
		});
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				ResultSet rs = Select.getData("select * from room");
				DefaultTableModel model =(DefaultTableModel)table.getModel();
				model.setRowCount(0);
				
				try
				{
					while(rs.next()) 
					{
						model.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
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
		Object[] column = {"No","Room Number", "Room Type", "Price", "Status"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(manageRoom.class.getResource("/Images/backwhite.png")));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(18, 13, 30, 20);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new adminDashBoard().setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton1 = new JButton("");
		btnNewButton1.setBounds(754, 0, 40, 40);
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you want to close this?", "Select", JOptionPane.YES_NO_OPTION);
				if(a == 0)
					System.exit(0);
			}
		});
		btnNewButton1.setIcon(new ImageIcon(manageRoom.class.getResource("/Images/close.png")));
		btnNewButton1.setBorderPainted(false);
		contentPane.add(btnNewButton1);
		
		JLabel lblNewLabel = new JLabel("ROOM NUMBER");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(498, 152, 104, 16);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(lblNewLabel);
		
		roomNumber = new JTextField();
		roomNumber.setBounds(498, 178, 221, 26);
		contentPane.add(roomNumber);
		roomNumber.setColumns(10);
		
		JLabel lblRoomType = new JLabel("ROOM TYPE");
		lblRoomType.setForeground(Color.WHITE);
		lblRoomType.setBounds(498, 214, 81, 16);
		lblRoomType.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(lblRoomType);
		
		JLabel lblPrice = new JLabel("PRICE");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setBounds(498, 279, 38, 16);
		lblPrice.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(lblPrice);
		
		roomPrice = new JTextField();
		roomPrice.setBounds(498, 305, 221, 26);
		roomPrice.setColumns(10);
		contentPane.add(roomPrice);
		
		rtype = new JComboBox();
		rtype.setBounds(498, 240, 221, 27);
		rtype.setModel(new DefaultComboBoxModel(new String[] {"Single", "Double", "Triple"}));
		contentPane.add(rtype);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setIcon(new ImageIcon(manageRoom.class.getResource("/Images/edit.png")));
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setBounds(623, 457, 117, 20);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String roomNo = roomNumber.getText();
				String roomType = (String) rtype.getSelectedItem();
				String price = roomPrice.getText();
				String book = (String) bookingComboBox.getSelectedItem();
				String roomId = roomID.getText();
				

				 String checkQuery = "select * from room where roomID='"+roomId+"'";
				    ResultSet rs = Select.getData(checkQuery);
				    try {
				        if(rs.next()) {
				            String Query = "update room set roomNo='"+roomNo+"', roomType='"+roomType+"', price='"+price+"', status='"+book+"' where roomID='"+roomId+"' " ;
				            InsertUpdateDelete.setData(Query, "Successfully Updated");
				            Query = "update users set status='"+book+"', roomNo=Null, roomType=Null";
				            InsertUpdateDelete.setData(Query, "");
				            setVisible(false);
				            new manageRoom().setVisible(true);
				        } else {
				            JOptionPane.showMessageDialog(null, "Room number does not exist");
				        }
				    } catch (SQLException ex) {
				        JOptionPane.showMessageDialog(null, ex);
				    }
			}
		});
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(manageRoom.class.getResource("/Images/addbtn.png")));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBounds(481, 457, 117, 20);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String roomNo = roomNumber.getText();
				String roomType = (String) rtype.getSelectedItem();
				String price = roomPrice.getText();
				String book = (String) bookingComboBox.getSelectedItem();
				
				String Query = "insert into room(roomNo, roomType, price, status) values ('"+roomNo+"', '"+roomType+"', '"+price+"', '"+book+"')";
				InsertUpdateDelete.setData(Query, "Successfully Added");
				setVisible(false);
				new manageRoom().setVisible(true);
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_2 = new JButton("");
		btnNewButton_1_2.setIcon(new ImageIcon(manageRoom.class.getResource("/Images/deletebtn.png")));
		btnNewButton_1_2.setBorderPainted(false);
		btnNewButton_1_2.setBounds(553, 499, 117, 20);
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String roomNo = roomNumber.getText().trim();
			    
			    String checkQuery = "select * from room where roomNo='"+roomNo+"'";
			    ResultSet rs = Select.getData(checkQuery);
			    try {
			        if(rs.next()) {
			            String Query = "delete from room where roomNo='"+roomNo+"'" ;
			            InsertUpdateDelete.setData(Query, "Successfully Deleted");
			            setVisible(false);
			            new manageRoom().setVisible(true);
			        } else {
			            JOptionPane.showMessageDialog(null, "Room number does not exist");
			        }
			    } catch (SQLException ex) {
			        JOptionPane.showMessageDialog(null, ex);
			    }
			}
		});
		contentPane.add(btnNewButton_1_2);
		
		JLabel IbIBooking = new JLabel("STATUS");
		IbIBooking.setForeground(Color.WHITE);
		IbIBooking.setBounds(498, 343, 61, 16);
		IbIBooking.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(IbIBooking);
		
		bookingComboBox = new JComboBox();
		bookingComboBox.setBounds(498, 369, 221, 27);
		bookingComboBox.setModel(new DefaultComboBoxModel(new String[] {"Not Booked", "Booked"}));
		contentPane.add(bookingComboBox);
		
		JLabel lblRoomId = new JLabel("ROOM ID");
		lblRoomId.setForeground(Color.WHITE);
		lblRoomId.setBounds(498, 96, 104, 16);
		lblRoomId.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(lblRoomId);
		
		roomID = new JLabel("ID");
		roomID.setBackground(Color.BLACK);
		roomID.setBounds(498, 124, 104, 19);
		roomID.setForeground(Color.WHITE);
		contentPane.add(roomID);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(498, 124, 221, 19);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(manageRoom.class.getResource("/Images/manageroom.png")));
		lblNewLabel_1.setBounds(0, 0, 800, 572);
		contentPane.add(lblNewLabel_1);
	}
}
