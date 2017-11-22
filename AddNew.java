import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class AddNew extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPanel firstname;
	private JPanel surname;
	private JLabel lblSurname;
	private JTextField textField_1;
	private JPanel panel;
	private JLabel lblAddress;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JPanel panel_4;
	private JLabel lblSecurityCode;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNew frame = new AddNew();
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
	public AddNew() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewPatient = new JLabel("New Patient");
		lblNewPatient.setVerticalAlignment(SwingConstants.TOP);
		lblNewPatient.setFont(new Font("Dialog", Font.BOLD, 22));
		lblNewPatient.setBounds(138, 12, 184, 55);
		contentPane.add(lblNewPatient);
		
		firstname = new JPanel();
		firstname.setBounds(50, 50, 280, 20);
		contentPane.add(firstname);
		firstname.setLayout(null);
		
		JLabel lblName = new JLabel("Firstname");
		lblName.setBounds(0, 2, 105, 19);
		firstname.add(lblName);
		lblName.setVerticalAlignment(SwingConstants.TOP);
		
		textField = new JTextField();
		textField.setBounds(94, 0, 184, 19);
		firstname.add(textField);
		textField.setColumns(10);
		
		surname = new JPanel();
		surname.setBounds(50, 80, 280, 20);
		contentPane.add(surname);
		surname.setLayout(null);
		
		lblSurname = new JLabel("Surname");
		lblSurname.setVerticalAlignment(SwingConstants.TOP);
		lblSurname.setBounds(0, 2, 105, 19);
		surname.add(lblSurname);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(94, 0, 184, 19);
		surname.add(textField_1);
		
		panel = new JPanel();
		panel.setBounds(50, 110, 280, 20);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblAddress = new JLabel("Address");
		lblAddress.setVerticalAlignment(SwingConstants.TOP);
		lblAddress.setBounds(0, 2, 105, 19);
		panel.add(lblAddress);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(94, 0, 184, 19);
		panel.add(textField_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(50, 140, 280, 20);
		contentPane.add(panel_1);
		
		JLabel lblPostcode = new JLabel("Postcode");
		lblPostcode.setVerticalAlignment(SwingConstants.TOP);
		lblPostcode.setBounds(0, 2, 105, 19);
		panel_1.add(lblPostcode);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(94, 0, 70, 19);
		panel_1.add(textField_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(50, 170, 280, 20);
		contentPane.add(panel_2);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setVerticalAlignment(SwingConstants.TOP);
		lblMobile.setBounds(0, 2, 105, 19);
		panel_2.add(lblMobile);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(94, 0, 120, 19);
		panel_2.add(textField_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(50, 200, 280, 20);
		contentPane.add(panel_3);
		
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setVerticalAlignment(SwingConstants.TOP);
		lblCardNumber.setBounds(0, 2, 105, 19);
		panel_3.add(lblCardNumber);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(110, 0, 120, 19);
		panel_3.add(textField_5);
		
		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(50, 221, 280, 20);
		contentPane.add(panel_4);
		
		lblSecurityCode = new JLabel("Security Code");
		lblSecurityCode.setVerticalAlignment(SwingConstants.TOP);
		lblSecurityCode.setBounds(0, 2, 105, 19);
		panel_4.add(lblSecurityCode);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(110, 0, 40, 19);
		panel_4.add(textField_6);
		
		JButton btnAddNewPatient = new JButton("Register");
		btnAddNewPatient.setBounds(200, 245, 117, 25);
		contentPane.add(btnAddNewPatient);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Book Appointments");
		chckbxNewCheckBox.setBounds(32, 250, 180, 23);
		contentPane.add(chckbxNewCheckBox);
	}
}
