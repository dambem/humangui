package dentist;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

import javax.swing.JMenuBar;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import java.awt.Label;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPopupMenu;
import javax.swing.JLayeredPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import dentist.SqlCreation;




public class Pricing extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pricing frame = new Pricing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public Pricing() throws Exception {
			
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(150, 150, 1000, 450);
			
	Container contentPane = getContentPane();
		
	}
	

		
	public static int calculateTotalPrice(int patient) throws Exception {
		
		List<String> appointments = SqlCreation.getPatientAppointments(patient);
		
		int totalApps = appointments.size();
		int totalCost = 0;
		
		for (int i=0;i<(totalApps/9);i++){
			System.out.println(appointments.get(3));
			totalCost += Integer.getInteger(appointments.get(3));
		}
		
		
		return totalCost;
	}
	
}
    
    

