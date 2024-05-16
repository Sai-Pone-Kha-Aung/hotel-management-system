package Pages;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class Payment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cardHolderNameField,cardNumberField, expiryDateField, cvvField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment();
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
	public Payment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(101,106,105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 JLabel label = new JLabel("Card Number:");
		 label.setForeground(Color.WHITE);
		 label.setBackground(Color.BLACK);
	        label.setBounds(77, 37, 225, 54);
	        contentPane.add(label);
	        cardNumberField = new JTextField();
	        cardNumberField.setBounds(302, 37, 225, 54);
	        contentPane.add(cardNumberField);

	        JLabel label_1 = new JLabel("Card Holder Name:");
	        label_1.setForeground(Color.WHITE);
	        label_1.setBackground(Color.BLACK);
	        label_1.setBounds(77, 91, 225, 54);
	        contentPane.add(label_1);
	        cardHolderNameField = new JTextField();
	        cardHolderNameField.setBounds(302, 91, 225, 54);
	        contentPane.add(cardHolderNameField);
	        
	        JLabel label_2 = new JLabel("Expiry Date (MM/YY):");
	        label_2.setForeground(Color.WHITE);
	        label_2.setBackground(Color.BLACK);
	        label_2.setBounds(77, 145, 225, 54);
	        contentPane.add(label_2);
	        expiryDateField = new JTextField();
	        expiryDateField.setBounds(302, 145, 225, 54);
	        contentPane.add(expiryDateField);

	        JLabel label_3 = new JLabel("CVV:");
	        label_3.setForeground(Color.WHITE);
	        label_3.setBackground(Color.BLACK);
	        label_3.setBounds(77, 199, 225, 54);
	        contentPane.add(label_3);
	        cvvField = new JTextField();
	        cvvField.setBounds(302, 199, 225, 54);
	        contentPane.add(cvvField);
	        
	        JButton payButton = new JButton("");
	        payButton.setBorderPainted(false);
	        payButton.setIcon(new ImageIcon(Payment.class.getResource("/Images/paymentbtn.png")));
	        payButton.setBounds(246, 265, 107, 41);
	        payButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (validateInput()) {
	                    JOptionPane.showMessageDialog(null, "Payment Successful!");
	                    setVisible(false);
	                } else {
	                    JOptionPane.showMessageDialog(null, "Invalid Input!");
	                }
	            }
	        });
	        contentPane.add(payButton);
	        
	        JButton btnClose = new JButton("");
	        btnClose.setBorderPainted(false);
	        btnClose.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		setVisible(false);
	        	}
	        });
	        btnClose.setIcon(new ImageIcon(Payment.class.getResource("/Images/close.png")));
	        btnClose.setBounds(560, 0, 40, 40);
	        contentPane.add(btnClose);
	        
	        JLabel lblNewLabel = new JLabel("");
	        lblNewLabel.setForeground(Color.WHITE);
	        lblNewLabel.setBackground(Color.BLACK);
	        lblNewLabel.setIcon(new ImageIcon(Payment.class.getResource("/Images/payment.png")));
	        lblNewLabel.setBounds(0, 0, 600, 322);
	        contentPane.add(lblNewLabel);
	}
    protected boolean validateInput() {
        return validateCardHolderName() && validateCardNumber() && validateExpiryDate() && validateCVV();
    }
    
    private boolean validateCardHolderName() {
        String cardHolderName = cardHolderNameField.getText();
        return !cardHolderName.isEmpty();
    }

    private boolean validateCardNumber() {
        String cardNumber = cardNumberField.getText();
        return cardNumber.matches("\\d{16}");
    }

    private boolean validateExpiryDate() {
        String expiryDate = expiryDateField.getText();
        if (!expiryDate.matches("\\d{2}/\\d{2}")) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(expiryDate);
            return date.after(new Date());
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean validateCVV() {
        String cvv = cvvField.getText();
        return cvv.matches("\\d{3}");
    }
}
