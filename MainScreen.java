import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.UtilDateModel;

import dentistApp.Pricing;
import dentistApp.SqlCreation;
import dentistApp.Timetable;

public class MainScreen extends JFrame {

	private JPanel contentPane;
	protected Component frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public class JTextFieldLimit extends PlainDocument {
		  private int limit;

		  JTextFieldLimit(int limit) {
		   super();
		   this.limit = limit;
		   }

		  public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
		    if (str == null) return;

		    if ((getLength() + str.length()) <= limit) {
		      super.insertString(offset, str, attr);
		    }
		  }
		}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public MainScreen() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 720, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		
		JComboBox<String> user = new JComboBox<String>();
		user.addItem("Secretary");
		user.addItem("Hygenist");
		user.addItem("Dentist");
		menuBar.add(user);

		String currentUser = (String) user.getSelectedItem();
		JLabel current = new JLabel("Current User:");
		JLabel currentUserLabel = new JLabel(currentUser);
		Border paddingBorder = BorderFactory.createEmptyBorder(15,10,15,30);
		currentUserLabel.setBorder(paddingBorder);
		Border paddingBorder2 = BorderFactory.createEmptyBorder(15,15,15,0);
		current.setBorder(paddingBorder2);

		
		JButton logIn = new JButton("Log in as Selected User");
		logIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuBar.removeAll();
				String currentUser = (String) user.getSelectedItem();
				JLabel current = new JLabel("Current User:");
				JLabel currentUserLabel = new JLabel(currentUser);
				Border paddingBorder = BorderFactory.createEmptyBorder(15,15,15,15);
				currentUserLabel.setBorder(paddingBorder);
				Border paddingBorder2 = BorderFactory.createEmptyBorder(15,15,15,0);
				current.setBorder(paddingBorder2);
				menuBar.add(user);
				menuBar.add(logIn);
				menuBar.add(current);
				menuBar.add(currentUserLabel);
				menuBar.revalidate();
				contentPane.revalidate();
				
			}
		});
		
		menuBar.add(logIn);
		menuBar.add(current);
		menuBar.add(currentUserLabel);	
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		UtilDateModel model = new UtilDateModel();
		JDatePanel datePanel = new JDatePanel(model);
		datePanel.setToolTipText("Double Click to View appointments");
		datePanel.setFont(new Font("Arial", Font.PLAIN, 12));
		datePanel.setBackground(Color.LIGHT_GRAY);
		datePanel.setDoubleClickAction(true);
		datePanel.setForeground(Color.LIGHT_GRAY);
		datePanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				JOptionPane.showMessageDialog(frame, "Date Clicked:");
				if (event.getClickCount() == 2) {
					JOptionPane.showMessageDialog(frame, "Date Clicked:");
				}
			}
		});

	
		

		contentPane.add(datePanel, BorderLayout.CENTER);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(toolBar_1, BorderLayout.EAST);
		
		JLabel lblNewLabel_1 = new JLabel("Date Chosen:");
		toolBar_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("12/11/18");
		toolBar_1.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("Appointments");
		toolBar_1.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnNewButton_1 = new JButton("Add New");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll(); 
				contentPane.add(toolBar_1, BorderLayout.EAST);
				
				JPanel appointmentForm = new JPanel();
				JComboBox<String> type = new JComboBox<String>();
				type.addItem("  Check Up");
				type.addItem("  Hygiene");
				type.addItem("  Repair");
				
				appointmentForm.add(new JLabel("              Appointment Type:", JLabel.LEFT));
				appointmentForm.add(type);
				
				appointmentForm.add(new JLabel("Patient Forename:", JLabel.LEFT));
				JTextField forename = new JTextField(25);
				appointmentForm.add(forename);
				
				appointmentForm.add(new JLabel("Patient Surname:", JLabel.LEFT));
				JTextField surname = new JTextField(25);
				appointmentForm.add(surname);
				
				appointmentForm.add(new JLabel("Start Time (hh:mm:ss):", JLabel.LEFT));
				JTextField start = new JTextField(25);
				appointmentForm.add(start);
				
				appointmentForm.add(new JLabel("Length(mins):", JLabel.LEFT));
				JTextField length = new JTextField(32);
				appointmentForm.add(length);
				
				appointmentForm.add(new JLabel("Cost:", JLabel.LEFT));
				JTextField cost = new JTextField(25);
				appointmentForm.add(cost);
				
				appointmentForm.add(new JLabel("Partner:", JLabel.LEFT));
				JTextField postcode = new JTextField(25);
				appointmentForm.add(postcode);

				JPanel buttons = new JPanel();
				buttons.setLayout(new FlowLayout());
				
				
				JButton bookApp = new JButton("Book Appointment");
				bookApp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						contentPane.remove(appointmentForm);
						contentPane.add(datePanel);
						setContentPane(contentPane);
						contentPane.repaint();
					}
				});
				buttons.add(bookApp);
				
				JButton calReturn = new JButton("View Calendar");
				calReturn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						contentPane.remove(appointmentForm);
						contentPane.add(datePanel);
						setContentPane(contentPane);
						contentPane.repaint();
					}
				});
				buttons.add(calReturn);
				
				appointmentForm.add(buttons);
				contentPane.add(appointmentForm, BorderLayout.CENTER);
				
				
				setContentPane(contentPane);

				contentPane.repaint();
			}
		});
		toolBar_1.add(btnNewButton_1);
		
		JButton btnRemove = new JButton("Remove ...");
		toolBar_1.add(btnRemove);
		
		JButton btnNewButton_2 = new JButton("View Selected Week");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JLabel currentUserL = (JLabel) menuBar.getComponent(3);
				String user = currentUserL.getText();
				System.out.print(user);
				String date = datePanel.getModel().getYear() + "-" + (datePanel.getModel().getMonth()+1) + "-" + datePanel.getModel().getDay();
				Date today = Date.valueOf(date);
				System.out.println(today);
				Timetable frame = null;
				try {
					frame = new Timetable(today, user);
					frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		toolBar_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Check Out");
		toolBar_1.add(btnNewButton_3);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.add(toolBar);
		
		JLabel lblPatients = new JLabel("Patients");
		toolBar.add(lblPatients);
		
		JButton btnRegister = new JButton("Add New Patient");
		btnRegister.setHorizontalAlignment(SwingConstants.RIGHT);
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				contentPane.add(toolBar_1, BorderLayout.EAST);
				
				JPanel registerForm = new JPanel();
				registerForm.setLayout(new GridLayout(0,2));
				
				JComboBox<String> plan = new JComboBox<String>();
				plan.addItem("No Dental Plan");
				plan.addItem("NHS Free Plan");
				plan.addItem("Maintenance Plan");
				plan.addItem("Oral Health Plan");
				plan.addItem("Dental Repair Plan");
				
				registerForm.add(new JLabel("                    Dental Plan:", JLabel.RIGHT));
				registerForm.add(plan);
				
				registerForm.add(new JLabel("        Title:", JLabel.RIGHT));
				JTextField title = new JTextField(25);
				registerForm.add(title);
				title.setDocument(new JTextFieldLimit(4));
				
				registerForm.add(new JLabel(" Forname:", JLabel.RIGHT));
				JTextField forename = new JTextField(25);
				registerForm.add(forename);
				
				registerForm.add(new JLabel(" Surname:", JLabel.RIGHT));
				JTextField surname = new JTextField(25);
				registerForm.add(surname);
				
				registerForm.add(new JLabel("Birth date (form yyyy-mm-dd):", JLabel.RIGHT));
				JTextField birth = new JTextField(32);
				registerForm.add(birth);
				
				registerForm.add(new JLabel("Phone No:", JLabel.RIGHT));
				JTextField phone = new JTextField(25);
				registerForm.add(phone);
				
				registerForm.add(new JLabel("Postcode:", JLabel.RIGHT));
				JTextField postcode = new JTextField(25);
				registerForm.add(postcode);
				
				registerForm.add(new JLabel("    Street:", JLabel.RIGHT));
				JTextField street = new JTextField(25);
				registerForm.add(street);
				
				registerForm.add(new JLabel(" District:", JLabel.RIGHT));
				JTextField district = new JTextField(25);
				registerForm.add(district);
				
				registerForm.add(new JLabel("      City:", JLabel.RIGHT));
				JTextField city = new JTextField(25);
				registerForm.add(city);
				
				registerForm.add(new JLabel("House No:", JLabel.RIGHT));
				JTextField house = new JTextField(25);
				registerForm.add(house);
				
				
				JButton regPatient = new JButton("Register Patient");
				regPatient.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						String forenameInput = forename.getText();
						String surnameInput = surname.getText();
						String titleInput = title.getText();
						String birthInput = birth.getText();
						String phoneInput = phone.getText();
						String postcodeInput = postcode.getText();
						String streetInput = street.getText();
						String districtInput = district.getText();
						String cityInput = city.getText();
						String houseInput = house.getText();
						
						try {
							SqlCreation.registerPatient(postcodeInput, streetInput, districtInput, cityInput, Integer.parseInt(houseInput), plan.getSelectedIndex(), titleInput, forenameInput, surnameInput, Date.valueOf(birthInput), phoneInput);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						contentPane.remove(registerForm);
						contentPane.add(datePanel);
						setContentPane(contentPane);
						contentPane.repaint();
					}
				});
				registerForm.add(regPatient);
				
				JButton calReturn = new JButton("Return to Calendar");
				calReturn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						contentPane.remove(registerForm);
						contentPane.add(datePanel);
						setContentPane(contentPane);
						contentPane.repaint();
					}
				});
				
				registerForm.add(calReturn);
				contentPane.add(registerForm, BorderLayout.CENTER);
				
				
				setContentPane(contentPane);

				contentPane.repaint();
			}
		});
		toolBar.add(btnRegister);
		
		
		JButton btnNewButton = new JButton("View All Patients");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				contentPane.removeAll();
				contentPane.add(toolBar_1, BorderLayout.EAST);
				
				JPanel viewPatients = new JPanel();
				viewPatients.setLayout(new GridLayout(0,1));
				
				JButton returnCal = new JButton("Return to calendar");
				returnCal.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						contentPane.remove(viewPatients);
						contentPane.add(datePanel);
						setContentPane(contentPane);
						contentPane.repaint();
					}
				});
				viewPatients.add(returnCal);
				
				contentPane.add(viewPatients, BorderLayout.CENTER);
				
				
				setContentPane(contentPane);

				contentPane.repaint();
			}
		});
		toolBar.add(btnNewButton);
		
		
		
		
		LocalDate localDate = LocalDate.now();
		String localDateStr = localDate.toString();
		System.out.println(localDateStr); 
		
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println( sdf.format(cal.getTime()) );

        JLabel currentUserL = (JLabel) menuBar.getComponent(3);
		String partner = currentUserL.getText();
        
		List<String> nextApp = SqlCreation.getNextApp(partner, localDateStr,  sdf.format(cal.getTime()) );
		
		JButton nextAppBtn = new JButton("No More Appointments Today ");
		
		
		if( nextApp.size() != 0 ) {
			nextAppBtn = new JButton("Next App: " + nextApp.get(1));
			nextAppBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				
				contentPane.removeAll();
				
				JPanel appointmentInfo = new JPanel();
				appointmentInfo.setLayout(new GridLayout(7,1));
				Border paddingBorder = BorderFactory.createEmptyBorder(15,15,15,15);
				Border outside = BorderFactory.createBevelBorder(1);
				appointmentInfo.setBorder(BorderFactory.createCompoundBorder(outside, paddingBorder));
				
	
				appointmentInfo.add(new JLabel("Patient Name: " + nextApp.get(6) + " " + nextApp.get(7)));
				
				appointmentInfo.add(new JLabel("Patient Contact No: " +  nextApp.get(8)) );
				appointmentInfo.add(new JLabel("Appointment Type: " +  nextApp.get(0) )  );
				appointmentInfo.add(new JLabel("Cost: " +  nextApp.get(3) )  );
				appointmentInfo.add(new JLabel("Partner Performing Procedure: " +  nextApp.get(4) ) );
				try {
					appointmentInfo.add(new JLabel("Time of Appointment: " +  nextApp.get(1) + "  -  " + Timetable.getEnd(nextApp.get(1), nextApp.get(2)) +":00" ) );
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JButton returnButton = new JButton("RETURN TO CALENDAR");
				returnButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						contentPane.removeAll();
	
						contentPane.add(toolBar_1);
						contentPane.add(datePanel);
						contentPane.add(menuBar);
						contentPane.setVisible(true);
						contentPane.revalidate();
						contentPane.repaint();
					}
				});
				appointmentInfo.add(returnButton);
				contentPane.add(appointmentInfo);
				contentPane.setVisible(true);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		}
		
		toolBar_1.add(nextAppBtn);
		
		/*
		 * 
		 * 
		 * 
		 * BELOW NEEDS TESTING
		 * 
		 * 
		 * 
		 * 
		 */
		
		JButton getPatientPricing = new JButton("Patient's Costs");
		getPatientPricing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			contentPane.removeAll();
			
			JPanel pricingInfo = new JPanel();
			pricingInfo.setLayout(new GridLayout(7,1));
			
			pricingInfo.add(new JLabel("Forname:", JLabel.RIGHT));
			JTextField forename = new JTextField(25);
			pricingInfo.add(forename);
			
			pricingInfo.add(new JLabel("Surname:", JLabel.RIGHT));
			JTextField surname = new JTextField(25);
			pricingInfo.add(surname);
			
			pricingInfo.add(new JLabel("Birth date (form yyyy-mm-dd):", JLabel.RIGHT));
			JTextField birth = new JTextField(25);
			pricingInfo.add(birth);
			
			pricingInfo.add(new JLabel("Phone No:", JLabel.RIGHT));
			JTextField phone = new JTextField(25);
			pricingInfo.add(phone);
			
			JButton pricePatient = new JButton("View Patient's Pricing");
			pricePatient.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String forenameInput = forename.getText();
					String surnameInput = surname.getText();
					String birthInput = birth.getText();
					String phoneInput = phone.getText();
					
					try {
						int patient = SqlCreation.getPatientId(forenameInput, surnameInput, birthInput, phoneInput);
						List<String> patientApps = SqlCreation.getPatientAppointments(patient);
						int totalCost = Pricing.calculateTotalPrice(patient);
						
						JPanel costingInfo = new JPanel();
						costingInfo.setLayout(new GridLayout(7,1));
						Border paddingBorder = BorderFactory.createEmptyBorder(15,15,15,15);
						Border outside = BorderFactory.createBevelBorder(1);
						costingInfo.setBorder(BorderFactory.createCompoundBorder(outside, paddingBorder));
						
						int totalApps = patientApps.size();
						for (int i=0;i<(totalApps/10);i++){
							costingInfo.add(new JLabel("Appointment Type: " +  patientApps.get(i) )  );
							costingInfo.add(new JLabel("Date of Appointment: " +  patientApps.get( (i*10)+3 ) ));
							costingInfo.add(new JLabel("Cost: " +  patientApps.get( (i*10)+4 ) ) );
							costingInfo.add(new JLabel("Partner Performing Procedure: " +  nextApp.get( (i*10)+5 ) ) );
						}
						costingInfo.add(new JLabel("Total Cost: " +  totalCost )  );
						
						JButton returnButton = new JButton("RETURN TO CALENDAR");
						returnButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								contentPane.removeAll();

								contentPane.add(toolBar_1);
								contentPane.add(datePanel);
								contentPane.add(menuBar);
								contentPane.setVisible(true);
								contentPane.revalidate();
								contentPane.repaint();
							}
						});
						
						costingInfo.add(returnButton);
						contentPane.add(costingInfo);
						contentPane.setVisible(true);
						contentPane.revalidate();
						contentPane.repaint();
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			
			pricingInfo.add(pricePatient);
			
			
			
			JButton returnButton = new JButton("RETURN TO CALENDAR");
			returnButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					contentPane.removeAll();

					contentPane.add(toolBar_1);
					contentPane.add(datePanel);
					contentPane.add(menuBar);
					contentPane.setVisible(true);
					contentPane.revalidate();
					contentPane.repaint();
				}
			});
			
			
			pricingInfo.add(returnButton);
			contentPane.add(pricingInfo);
			contentPane.setVisible(true);
			contentPane.revalidate();
			contentPane.repaint();
		}
	});
		
		toolBar_1.add(getPatientPricing);	
		
		/*
		 * 
		 * 
		 * 
		 * 
		 * ABOVE NEEDS TESTING
		 * 
		 * JPanel container = new JPanel();
		 * JScrollPane scrPane = new JScrollPane(container);
		 * getContentPane().add(scrPane);
		 * ADDS SCROLLING APPARENTLY 
		 * 
		 */
		

	}
}
