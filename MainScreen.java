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

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.GridLayout;
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
import javax.swing.JPopupMenu;
import javax.swing.JLayeredPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import dentistApp.SqlCreation;
import dentistApp.Timetable;
import dentistApp.Pricing;

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

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // for date format

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
		contentPane.add(menuBar, BorderLayout.NORTH);
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
				appointmentForm.setLayout(new GridLayout(0,2));
				JComboBox<String> type = new JComboBox<String>();
				type.addItem("Check Up");
				type.addItem("Hygiene");
				type.addItem("Repair");

				appointmentForm.add(new JLabel("Appointment Type:", JLabel.RIGHT));
				appointmentForm.add(type);

				appointmentForm.add(new JLabel("Patient Forename:", JLabel.RIGHT));
				JTextField forename = new JTextField(25);
				appointmentForm.add(forename);

				appointmentForm.add(new JLabel("Patient Surname:", JLabel.RIGHT));
				JTextField surname = new JTextField(25);
				appointmentForm.add(surname);

				appointmentForm.add(new JLabel("Start Time (hh:mm:ss):", JLabel.RIGHT));
				JTextField start = new JTextField(25);
				appointmentForm.add(start);

				appointmentForm.add(new JLabel("Length(mins):", JLabel.RIGHT));
				JTextField length = new JTextField(32);
				appointmentForm.add(length);

				appointmentForm.add(new JLabel("Cost:", JLabel.RIGHT));
				JTextField cost = new JTextField(25);
				appointmentForm.add(cost);

				appointmentForm.add(new JLabel("Partner:", JLabel.RIGHT));
				JTextField postcode = new JTextField(25);
				appointmentForm.add(postcode);

				JPanel buttons = new JPanel();
				buttons.setLayout(new FlowLayout());


				JButton bookApp = new JButton("Book Appointment");
				bookApp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						contentPane.remove(appointmentForm);
						contentPane.add(datePanel, BorderLayout.CENTER);
						contentPane.add(menuBar, BorderLayout.NORTH);
						setContentPane(contentPane);
						contentPane.repaint();
					}
				});
				appointmentForm.add(bookApp);

				JButton calReturn = new JButton("View Calendar");
				calReturn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						contentPane.remove(appointmentForm);
						contentPane.add(datePanel, BorderLayout.CENTER);
						contentPane.add(menuBar, BorderLayout.NORTH);
						setContentPane(contentPane);
						contentPane.repaint();
					}
				});
				appointmentForm.add(calReturn);

				appointmentForm.add(buttons);
				contentPane.add(appointmentForm, BorderLayout.CENTER);


				setContentPane(contentPane);

				contentPane.repaint();
			}
		});
		toolBar_1.add(btnNewButton_1);


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
				JFormattedTextField birth = new JFormattedTextField(dateFormat);
				//JTextField birth = new JTextField(32);
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
						contentPane.add(datePanel, BorderLayout.CENTER);
						contentPane.add(menuBar, BorderLayout.NORTH);
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
						contentPane.add(datePanel, BorderLayout.CENTER);
						contentPane.add(menuBar, BorderLayout.NORTH);
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




		LocalDate localDate = LocalDate.now();
		String localDateStr = localDate.toString();
		System.out.println(localDateStr);

		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println( sdf.format(cal.getTime()) );

        JLabel currentUserL = (JLabel) menuBar.getComponent(3);
		String partner = currentUserL.getText();

		List<String> nextApp = SqlCreation.getNextApp(partner, localDateStr,  sdf.format(cal.getTime()), 0 );

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

						contentPane.add(toolBar_1, BorderLayout.EAST);
						contentPane.add(datePanel, BorderLayout.CENTER);
						contentPane.add(menuBar, BorderLayout.NORTH);
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
			pricingInfo.setLayout(new GridLayout(0,2));

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
						float totalCost = Pricing.calculateTotalPrice(patient);
						List<String> patientApps = SqlCreation.getPatientAppointments(patient);

						JPanel costingInfo = new JPanel();
						costingInfo.setLayout(new GridLayout(0,2));
						Border paddingBorder = BorderFactory.createEmptyBorder(15,15,15,15);
						Border outside = BorderFactory.createBevelBorder(1);
						costingInfo.setBorder(BorderFactory.createCompoundBorder(outside, paddingBorder));

						int totalApps = patientApps.size();
						System.out.println(totalApps);
						for (int i=0;i<(totalApps/11);i++){
							costingInfo.add(new JLabel("Appointment Type: " +  patientApps.get(i*11) )  );
							costingInfo.add(new JLabel("Date of Appointment: " +  patientApps.get( (i*11)+3 ) ));
							costingInfo.add(new JLabel("Cost: " +  patientApps.get( (i*11)+4 ) ) );
							costingInfo.add(new JLabel("Partner Performing Procedure: " +  patientApps.get( (i*11)+5 ) ) );
							costingInfo.add(new JLabel("Pre-paid? : "));
							if (Integer.valueOf(patientApps.get((i*11)+10))==1)
								costingInfo.add(new JLabel("YES"));
							else
								costingInfo.add(new JLabel("NO"));
						}
						costingInfo.add(new JLabel("Total Cost: " +  totalCost )  );

						JButton returnButton = new JButton("RETURN TO CALENDAR");
						returnButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								contentPane.removeAll();

								contentPane.add(toolBar_1, BorderLayout.EAST);
								contentPane.add(datePanel, BorderLayout.CENTER);
								contentPane.add(menuBar, BorderLayout.NORTH);
								contentPane.setVisible(true);
								contentPane.revalidate();
								contentPane.repaint();
							}
						});
						contentPane.removeAll();

						costingInfo.add(returnButton);
						contentPane.add(costingInfo);
						contentPane.setVisible(true);
						contentPane.revalidate();
						contentPane.repaint();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					contentPane.revalidate();
					contentPane.repaint();
				}
			});

			pricingInfo.add(pricePatient);




			JButton returnButton = new JButton("RETURN TO CALENDAR");
			returnButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					contentPane.removeAll();

					contentPane.add(toolBar_1, BorderLayout.EAST);
					contentPane.add(datePanel, BorderLayout.CENTER);
					contentPane.add(menuBar, BorderLayout.NORTH);
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



		JButton getPatientAppointments = new JButton("Find Patient's Next App");
		getPatientAppointments.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			contentPane.removeAll();

			JPanel appInfo = new JPanel();
			appInfo.setLayout(new GridLayout(7,1));

			appInfo.add(new JLabel("Forname:", JLabel.RIGHT));
			JTextField forename = new JTextField(25);
			appInfo.add(forename);

			appInfo.add(new JLabel("Surname:", JLabel.RIGHT));
			JTextField surname = new JTextField(25);
			appInfo.add(surname);

			appInfo.add(new JLabel("Birth date (form yyyy-mm-dd):", JLabel.RIGHT));
			JTextField birth = new JTextField(25);
			appInfo.add(birth);

			appInfo.add(new JLabel("Phone No:", JLabel.RIGHT));
			JTextField phone = new JTextField(25);
			appInfo.add(phone);

			appInfo.add(new JLabel("Partner:", JLabel.RIGHT));
			JComboBox<String> partner = new JComboBox<String>();
			partner.addItem("Secretary");
			partner.addItem("Hygenist");
			partner.addItem("Dentist");
			appInfo.add(partner);


			JButton getPatientApp = new JButton("View Patient's Next Appointment");
			getPatientApp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String forenameInput = forename.getText();
					String surnameInput = surname.getText();
					String birthInput = birth.getText();
					String phoneInput = phone.getText();
					String partnerInput = (String) partner.getSelectedItem();

					try {

						LocalDate localDate = LocalDate.now();
						String localDateStr = localDate.toString();
						System.out.println(localDateStr);

						Calendar cal = Calendar.getInstance();
				        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				        System.out.println( sdf.format(cal.getTime()) );

						int patient = SqlCreation.getPatientId(forenameInput, surnameInput, birthInput, phoneInput);
						List<String> patientApp = SqlCreation.getNextApp(partnerInput, localDateStr,  sdf.format(cal.getTime()), patient);

						JPanel appInfo = new JPanel();
						appInfo.setLayout(new GridLayout(7,1));
						Border paddingBorder = BorderFactory.createEmptyBorder(15,15,15,15);
						Border outside = BorderFactory.createBevelBorder(1);
						appInfo.setBorder(BorderFactory.createCompoundBorder(outside, paddingBorder));
						System.out.print(patientApp.size());
						if(patientApp.size() != 0) {
							appInfo.add(new JLabel("Appointment Type: " +  patientApp.get(0) )  );
							appInfo.add(new JLabel("Date of Appointment: " +  patientApp.get(2) ));
							appInfo.add(new JLabel("Time: " +  patientApp.get(1) ));
							appInfo.add(new JLabel("Cost: " +  patientApp.get(4) ) );
							appInfo.add(new JLabel("Partner Performing Procedure: " +  patientApp.get(5) ) );
						}
						else {
							appInfo.add(new JLabel("NO APPOINTMENTS WITH - " + partnerInput )  );
						}

						JButton returnButton = new JButton("RETURN TO CALENDAR");
						returnButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								contentPane.removeAll();

								contentPane.add(toolBar_1, BorderLayout.EAST);
								contentPane.add(datePanel, BorderLayout.CENTER);
								contentPane.add(menuBar, BorderLayout.NORTH);
								contentPane.setVisible(true);
								contentPane.revalidate();
								contentPane.repaint();
							}
						});
						contentPane.removeAll();

						appInfo.add(returnButton);
						contentPane.add(appInfo);
						contentPane.setVisible(true);
						contentPane.revalidate();
						contentPane.repaint();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					contentPane.revalidate();
					contentPane.repaint();
				}
			});

			appInfo.add(getPatientApp);



			JButton returnButton = new JButton("RETURN TO CALENDAR");
			returnButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					contentPane.removeAll();

					contentPane.add(toolBar_1, BorderLayout.EAST);
					contentPane.add(datePanel, BorderLayout.CENTER);
					contentPane.add(menuBar, BorderLayout.NORTH);
					contentPane.setVisible(true);
					contentPane.revalidate();
					contentPane.repaint();
				}
			});


			appInfo.add(returnButton);
			contentPane.add(appInfo);
			contentPane.setVisible(true);
			contentPane.revalidate();
			contentPane.repaint();
		}
	});

		toolBar_1.add(getPatientAppointments);
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


		JButton recordApps = new JButton("Record Last Appointment");
		recordApps.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			contentPane.removeAll();

			LocalDate localDate = LocalDate.now();
			String localDateStr = localDate.toString();
			System.out.println(localDateStr);

			Calendar cal = Calendar.getInstance();
        	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        	System.out.println( sdf.format(cal.getTime()) );

        	List<String> lastApp = null;
			try {
				lastApp = SqlCreation.getLastApp(localDateStr, sdf.format(cal.getTime()));
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			JPanel appInfo = new JPanel();
			appInfo.setLayout(new GridLayout(0,2));

			if ( lastApp.size() != 0 ){


				appInfo.add(new JLabel("Forename: " +  lastApp.get(6) )  );
				appInfo.add(new JLabel("Surname: " +  lastApp.get(7) )  );



				int lengthI = Integer.valueOf(lastApp.get(2));
				String[] split = lastApp.get(1).split(":");
				int startHour = Integer.valueOf(split[0]);
				int startMin = Integer.valueOf(split[1]);
				int endMin = (startMin+lengthI)%60;
				int hoursGained = (int)(startMin+lengthI)/60;
				int endHour = startHour+hoursGained;
				String id = lastApp.get(9);
				String date = lastApp.get(10);
				String time = lastApp.get(1);

				String endTime = String.format("%02d:%02d", endHour, endMin);

				appInfo.add(new JLabel("Appointment Time: " + lastApp.get(1) + " - "+ endTime));

				appInfo.add(new JLabel("Assumed Cost: "  + lastApp.get(3) ));

				appInfo.add(new JLabel("Enter Appointment Details:", JLabel.RIGHT));
				JTextField details = new JTextField(25);
				appInfo.add(details);

				appInfo.add(new JLabel("Enter Actual Cost:", JLabel.RIGHT));
				JTextField cost = new JTextField(25);
				appInfo.add(cost);


				JButton pricePatient = new JButton("Update Appointment Info");
				pricePatient.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						String detailsInput = details.getText();
						String costInput = cost.getText();
						try {
							SqlCreation.updateLastApp(costInput, detailsInput, id, date, time);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						contentPane.removeAll();

						contentPane.add(toolBar_1, BorderLayout.EAST);
						contentPane.add(datePanel, BorderLayout.CENTER);
						contentPane.add(menuBar, BorderLayout.NORTH);
						contentPane.setVisible(true);
						contentPane.revalidate();
						contentPane.repaint();
					}
				});
				contentPane.removeAll();

				appInfo.add(pricePatient);
				JButton returnButton = new JButton("RETURN TO CALENDAR");
				returnButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						contentPane.removeAll();

						contentPane.add(toolBar_1, BorderLayout.EAST);
						contentPane.add(datePanel, BorderLayout.CENTER);
						contentPane.add(menuBar, BorderLayout.NORTH);
						contentPane.setVisible(true);
						contentPane.revalidate();
						contentPane.repaint();
					}
				});

				appInfo.add(returnButton);
			}
			else{
				appInfo.add(new JLabel("NO APPOINTMENTS BEFORE" ), BorderLayout.CENTER  );


				JButton returnButton = new JButton("RETURN TO CALENDAR");
				returnButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						contentPane.removeAll();

						contentPane.add(toolBar_1, BorderLayout.EAST);
						contentPane.add(datePanel, BorderLayout.CENTER);
						contentPane.add(menuBar, BorderLayout.NORTH);
						contentPane.setVisible(true);
						contentPane.revalidate();
						contentPane.repaint();
					}
				});

				appInfo.add(returnButton);

			}

			contentPane.add(appInfo);
			contentPane.setVisible(true);
			contentPane.revalidate();
			contentPane.repaint();

			contentPane.revalidate();
			contentPane.repaint();
			}
		});

		toolBar_1.add(recordApps);



	}
}
