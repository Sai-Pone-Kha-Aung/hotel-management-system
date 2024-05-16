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
import java.time.LocalTime;

import com.github.lgooddatepicker.components.TimePicker;

public class manageSPA extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField spaPrice;
	private JComboBox spatype;
	private JComboBox bookingComboBox;
	private JLabel spaID;
	private DefaultTableModel model;
	private TimePicker timePicker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageSPA frame = new manageSPA();
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
	public manageSPA() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(101,106,105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 73, 441, 467);
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
				spaID.setText(model.getValueAt(selectIndex, 0).toString());
				spatype.setSelectedItem(model.getValueAt(selectIndex, 1).toString());
				spaPrice.setText(model.getValueAt(selectIndex, 3).toString());
				bookingComboBox.setSelectedItem(model.getValueAt(selectIndex, 4).toString());
				String timeString = model.getValueAt(selectIndex, 2).toString();
				timePicker.setName(timeString);
			}
		});
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				ResultSet rs = Select.getData("select * from spa");
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
		Object[] column = {"ID","Spa Type", "Available Time", "Price", "Status"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(manageSPA.class.getResource("/Images/backwhite.png")));
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
		btnNewButton1.setIcon(new ImageIcon(manageSPA.class.getResource("/Images/close.png")));
		btnNewButton1.setBorderPainted(false);
		contentPane.add(btnNewButton1);
		
		JLabel lblNewLabel = new JLabel("TIME");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(498, 202, 221, 16);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(lblNewLabel);
		
		JLabel lblSpaType = new JLabel("SPA TYPE");
		lblSpaType.setForeground(Color.WHITE);
		lblSpaType.setBounds(498, 137, 81, 16);
		lblSpaType.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(lblSpaType);
		
		JLabel lblPrice = new JLabel("PRICE");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setBounds(498, 284, 38, 16);
		lblPrice.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(lblPrice);
		
		spaPrice = new JTextField();
		spaPrice.setBounds(498, 310, 255, 26);
		spaPrice.setColumns(10);
		contentPane.add(spaPrice);
		
		spatype = new JComboBox();
		spatype.setBounds(498, 163, 255, 27);
		spatype.setModel(new DefaultComboBoxModel(new String[] {"Aromatherapy and oil massage", "Facial massage", "Foot massage", "Reflexology", "Thai massage spas and salons", "Wat Po traditional Thai massage"}));
		contentPane.add(spatype);
		
		JButton btnUpdateSlot = new JButton("");
		btnUpdateSlot.setIcon(new ImageIcon(manageSPA.class.getResource("/Images/edit.png")));
		btnUpdateSlot.setBorderPainted(false);
		btnUpdateSlot.setBounds(640, 448, 117, 20);
		btnUpdateSlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String spaId = spaID.getText();
				String spaType = (String) spatype.getSelectedItem();
				LocalTime time = timePicker.getTime();
				String price = spaPrice.getText();
				String book = (String) bookingComboBox.getSelectedItem();
				
				if (spaId.isEmpty() || spaType.isEmpty() || time.equals("") || price.isEmpty() || book.isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Every field is required");
			    }
				else
				{
					String checkQuery = "select * from spa where spaID='"+spaId+"'";
				    ResultSet rs = Select.getData(checkQuery);
				    try {
				        if(rs.next()) {
				            String Query = "update spa set spaType='"+spaType+"', time='"+time+"', price='"+price+"',status='"+book+"' where spaID='"+spaId+"' " ;
				            InsertUpdateDelete.setData(Query, "Successfully Updated");
				            Query = "update users set status='"+book+"'";
				            InsertUpdateDelete.setData(Query, "");
				            setVisible(false);
				            new manageSPA().setVisible(true);
				        } else {
				            JOptionPane.showMessageDialog(null, "SpaID does not exist");
				        }
				    } catch (SQLException ex) {
				        JOptionPane.showMessageDialog(null, ex);
				    }
				}
				 
		}
		});
		contentPane.add(btnUpdateSlot);
		
		JButton btnAddSlot = new JButton("");
		btnAddSlot.setIcon(new ImageIcon(manageSPA.class.getResource("/Images/addbtn.png")));
		btnAddSlot.setBorderPainted(false);
		btnAddSlot.setBounds(498, 448, 117, 20);
		btnAddSlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String spaType = (String) spatype.getSelectedItem();
				LocalTime time = timePicker.getTime();
				String price = spaPrice.getText();
				String book = (String) bookingComboBox.getSelectedItem();
			
				if(spaType.equals("") || time.equals("") || price.equals("") || book.equals(""))
					JOptionPane.showMessageDialog(null, "Every Field Is Required");
				else {
					String Query = "insert into spa(spaType, time, price, status) values ( '"+spaType+"', '"+time+"', '"+price+"', '"+book+"')";
					InsertUpdateDelete.setData(Query, "Successfully Added");
					setVisible(false);
					new manageSPA().setVisible(true);
				}
			
			}
		});
		contentPane.add(btnAddSlot);
		
		JButton btnDeleteSlot = new JButton("");
		btnDeleteSlot.setIcon(new ImageIcon(manageSPA.class.getResource("/Images/deletebtn.png")));
		btnDeleteSlot.setBorderPainted(false);
		btnDeleteSlot.setBounds(574, 494, 117, 20);
		btnDeleteSlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String spaId = spaID.getText().trim();
			    
			    String checkQuery = "select * from spa where spaID='"+spaId+"'";
			    ResultSet rs = Select.getData(checkQuery);
			    try {
			        if(rs.next()) {
			            String Query = "delete from spa where spaID='"+spaId+"'" ;
			            InsertUpdateDelete.setData(Query, "Successfully Deleted");
			            setVisible(false);
			            new manageSPA().setVisible(true);
			        } else {
			            JOptionPane.showMessageDialog(null, "SpaID does not exist");
			        }
			    } catch (SQLException ex) {
			        JOptionPane.showMessageDialog(null, ex);
			    }
			}
		});
		contentPane.add(btnDeleteSlot);
		
		JLabel IbIBooking = new JLabel("BOOKED");
		IbIBooking.setForeground(Color.WHITE);
		IbIBooking.setBounds(498, 359, 61, 16);
		IbIBooking.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(IbIBooking);
		
		bookingComboBox = new JComboBox();
		bookingComboBox.setBounds(498, 385, 255, 27);
		bookingComboBox.setModel(new DefaultComboBoxModel(new String[] {"Not Booked", "Booked"}));
		contentPane.add(bookingComboBox);
		
		JLabel lblRoomId = new JLabel("SPA ID");
		lblRoomId.setForeground(Color.WHITE);
		lblRoomId.setBounds(498, 74, 221, 16);
		lblRoomId.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(lblRoomId);
		
		spaID = new JLabel("ID");
		spaID.setBounds(498, 102, 61, 19);
		spaID.setBackground(Color.BLACK);
		spaID.setForeground(Color.BLACK);
		contentPane.add(spaID);
		
		JPanel panel = new JPanel();
		panel.setBounds(498, 102, 255, 19);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		
		timePicker = new TimePicker();
		timePicker.setBounds(498, 230, 255, 29);
		contentPane.add(timePicker);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(manageSPA.class.getResource("/Images/managespa.png")));
		lblNewLabel_1.setBounds(0, 0, 800, 572);
		contentPane.add(lblNewLabel_1);
	}
}
