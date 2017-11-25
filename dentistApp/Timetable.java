package dentistApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;
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
import javax.swing.JOptionPane;

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

import dentistApp.SqlCreation;




public class Timetable extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				LocalDate todayL = LocalDate.now();
				Date today = Date.valueOf(todayL);
				try {
					Timetable frame = new Timetable(today, "Secretary");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

public Timetable(Date date, String user) throws Exception {
	
	
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(150, 150, 1000, 450);
	
	Container contentPane1 = getContentPane();
	createTimetable(contentPane1, date, user);
	addAppForm(contentPane1, date, user);
	
	contentPane1.revalidate();
	
	
}

public static void createTimetable(Container contentPane1, Date currentDate, String user) throws Exception{
	
	JPanel contentPane = new JPanel();
	contentPane.removeAll();
	GridBagLayout grid = new GridBagLayout();
	contentPane.setLayout(grid);
	GridBagConstraints c = new GridBagConstraints();
	int width = contentPane.getWidth();
    int height = contentPane.getHeight();
    int wGap = contentPane.getInsets().left+contentPane.getInsets().right;
    int hGap = contentPane.getInsets().top+contentPane.getInsets().bottom;
    grid.columnWidths = new int[]{width/2-wGap, width/2-wGap};
    grid.rowHeights = new int[]{height-hGap};
	JLabel day;
	JLabel hour;
	JButton time;
	JLabel filler;
	SimpleDateFormat format = new SimpleDateFormat("EEEE");
	
	Date today = currentDate;
	Date tomorrow = addDay(today);
	Date day3  = addDay(tomorrow);
	Date day4  = addDay(day3);
	Date day5  = addDay(day4);
	Date day6  = addDay(day5);
	Date day7  = addDay(day6);
	System.out.println(today);

	
	List<String> day1apps = SqlCreation.getAppsOnDate(today, user);
	System.out.println(day1apps);
	addApps(day1apps, c, contentPane1, contentPane, 1, today, currentDate, user);
			
	List<String> day2apps = SqlCreation.getAppsOnDate(tomorrow, user);
	addApps(day2apps, c, contentPane1, contentPane, 2, tomorrow, currentDate, user);
	
	List<String> day3apps = SqlCreation.getAppsOnDate(day3, user);
	addApps(day3apps, c, contentPane1, contentPane, 3, day3, currentDate, user);
	
	List<String> day4apps = SqlCreation.getAppsOnDate(day4, user);
	addApps(day4apps, c, contentPane1, contentPane, 4, day4, currentDate, user);
	
	List<String> day5apps = SqlCreation.getAppsOnDate(day5, user);
	addApps(day5apps, c, contentPane1, contentPane, 5, day5, currentDate, user);
	
	List<String> day6apps = SqlCreation.getAppsOnDate(day6, user);
	addApps(day6apps, c, contentPane1, contentPane, 6, day6, currentDate, user);
	
	List<String> day7apps = SqlCreation.getAppsOnDate(day7, user);
	addApps(day7apps, c, contentPane1, contentPane, 7, day7, currentDate, user);
	
	String[] days = new String[]{"CALENDAR",format.format(today).toString()+" "+getDayOfMonthSuffix(today), format.format(tomorrow).toString()+" "+getDayOfMonthSuffix(tomorrow), format.format(day3).toString()+" "+getDayOfMonthSuffix(day3), format.format(day4).toString()+" "+getDayOfMonthSuffix(day4),format.format(day5).toString()+" "+getDayOfMonthSuffix(day5),format.format(day6).toString()+" "+getDayOfMonthSuffix(day6),format.format(day7).toString()+" "+getDayOfMonthSuffix(day7)};
	
	for(int i=0; i<=7; i++) {
		day = new JLabel(days[i]);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridheight = 4;
		c.ipadx =0;
		c.ipady =0;
		c.weightx = 0.4;
		c.gridx = i;
		c.gridy = 0;
		contentPane.add(day, c);
	}
	String[] hours = new String[]{"","9am","10am","11am","12am","1pm","2pm","3pm","4pm","5pm"};
	for(int y=1; y<=9; y++) {
		for(int i=0; i<=7; i++) {
			if (i==0){
				hour = new JLabel(hours[y]);
				c.fill = GridBagConstraints.RELATIVE;
				c.weightx = 0.4;
				c.gridwidth = 1;
				c.gridheight = 4;
				c.ipadx = 0;
				c.ipady = 0;
				c.gridx = i;
				c.gridy = y*4;
				
				contentPane.add(hour, c);
			}
		}
		
	}
	contentPane.revalidate();
	contentPane1.add(contentPane);
	
}

public static void addAppForm(Container contentPane1, Date currentDate, String user) throws Exception {
	JPanel bookings = new JPanel();
	bookings.setLayout(new FlowLayout());
	JButton addApp = new JButton("Add New Appointment");
	addApp.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			contentPane1.removeAll();
			
			JPanel appointmentForm = new JPanel();
			appointmentForm.setLayout(new GridLayout(0,2));
			JComboBox<String> type = new JComboBox<String>();
			type.addItem("Check Up");
			type.addItem("Hygiene");
			type.addItem("Repair");
			
			appointmentForm.add(new JLabel("              Appointment Type:", JLabel.LEFT));
			appointmentForm.add(type);
			
			appointmentForm.add(new JLabel("Date:", JLabel.LEFT));
			JTextField date = new JTextField(25);
			appointmentForm.add(date);
			
			appointmentForm.add(new JLabel("Patient Forename:", JLabel.LEFT));
			JTextField forename = new JTextField(25);
			appointmentForm.add(forename);
			
			appointmentForm.add(new JLabel("Patient Surname:", JLabel.LEFT));
			JTextField surname = new JTextField(25);
			appointmentForm.add(surname);
			
			appointmentForm.add(new JLabel("Birth date (form yyyy-mm-dd):", JLabel.LEFT));
			JTextField birth = new JTextField(32);
			appointmentForm.add(birth);
			
			appointmentForm.add(new JLabel("Phone No:", JLabel.LEFT));
			JTextField phone = new JTextField(25);
			appointmentForm.add(phone);
			
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
			JTextField partner = new JTextField(25);
			appointmentForm.add(partner);
		
			JPanel buttons = new JPanel();
			buttons.setLayout(new FlowLayout());
			
			JButton bookApp = new JButton("Book Appointment");
			bookApp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					contentPane1.remove(appointmentForm);
					try {
					String forenameInput = forename.getText();
					String surnameInput = surname.getText();
					Date dateInput = Date.valueOf(date.getText());
					String birthInput = birth.getText();
					String phoneInput = phone.getText();
					Time startInput = Time.valueOf(start.getText());
					int lengthInput = Integer.valueOf(length.getText());
					float costInput = Float.valueOf(cost.getText());
					String partnerInput = partner.getText();
					String typeInput = (String) type.getSelectedItem();
					
					int patient = 1;
					
					
					
					
					try {
						patient = SqlCreation.getPatientId(forenameInput, surnameInput, birthInput, phoneInput);
						int freeLeft = SqlCreation.getFreeRemaining(patient, typeInput);
						if (freeLeft==0)
							SqlCreation.insertAppointment(patient, typeInput, dateInput, startInput, lengthInput, costInput, partnerInput, false);
						else  {
							SqlCreation.insertAppointment(patient, typeInput, dateInput, startInput, lengthInput, costInput, partnerInput, true);
							SqlCreation.updateFreeRemaining(patient,typeInput,freeLeft);
						}
						createTimetable(contentPane1, currentDate, user);
						addAppForm(contentPane1, currentDate, user);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					
					contentPane1.revalidate();
					contentPane1.repaint();
					}
				
				}catch(IllegalArgumentException ec) {
					JOptionPane.showMessageDialog(contentPane1, "Not All Fields Completed!");
					contentPane1.add(appointmentForm);
					contentPane1.revalidate();
					contentPane1.repaint();
				}}
			});
			buttons.add(bookApp);
			
			JButton calReturn = new JButton("View Calendar");
			calReturn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					contentPane1.remove(appointmentForm);
					try {
						createTimetable(contentPane1, currentDate, user);
						addAppForm(contentPane1, currentDate, user);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					contentPane1.revalidate();
					contentPane1.repaint();
				}
			});
			
			buttons.add(calReturn);
			appointmentForm.add(buttons);
			contentPane1.add(appointmentForm);
			contentPane1.revalidate();
			contentPane1.repaint();
		}
	});
			
			JButton addHol = new JButton("Add Holiday");
			addHol.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					contentPane1.removeAll();
					
					JPanel holidayForm = new JPanel();
					
					holidayForm.add(new JLabel("Date:", JLabel.LEFT));
					JTextField dateHol = new JTextField(25);
					holidayForm.add(dateHol);
					
//					holidayForm.add(new JLabel("Partner:", JLabel.LEFT));
//					JTextField partnerHol = new JTextField(25);
//					holidayForm.add(partnerHol);
					JComboBox<String> partnerHol = new JComboBox<String>();
					partnerHol.addItem("Secretary");
					partnerHol.addItem("Hygenist");
					partnerHol.addItem("Dentist");
					holidayForm.add(partnerHol);
				
					JPanel buttons2 = new JPanel();
					buttons2.setLayout(new FlowLayout());	
					
					JButton bookHol = new JButton("Book Holiday");
					bookHol.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							contentPane1.remove(holidayForm);
							
							Date dateInput = Date.valueOf(dateHol.getText());
							String partnerHolInput = (String)partnerHol.getSelectedItem();
					
							int patient = 1;
							
							try {
								SqlCreation.insertAppointment(patient, "HOLIDAY", dateInput, Time.valueOf("09:00:00"), 480, 0, partnerHolInput, false);
							} catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							try {
								createTimetable(contentPane1, currentDate, user);
								addAppForm(contentPane1, currentDate, user);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							contentPane1.revalidate();
							contentPane1.repaint();
						}
					});
					JButton calReturn = new JButton("View Calendar");
					calReturn.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							contentPane1.remove(holidayForm);
							try {
								createTimetable(contentPane1, currentDate, user);
								addAppForm(contentPane1, currentDate, user);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							contentPane1.revalidate();
							contentPane1.repaint();
						}
					});
					buttons2.add(bookHol);
					buttons2.add(calReturn);
					holidayForm.add(buttons2);
					contentPane1.add(holidayForm);
					contentPane1.revalidate();
					contentPane1.repaint();

			
		}
		
	});
	bookings.add(addHol);
	bookings.add(addApp);
	contentPane1.add(bookings, BorderLayout.SOUTH);
	contentPane1.revalidate();
	
	
}

public static Date addDay(Date date) throws Exception {
	try{
		long tomorrowTime = date.getTime() + (1000 * 60 * 60 * 24);
		Date tomorrow = new Date(tomorrowTime);
		return tomorrow;
	}catch(Exception e) {
		
		e.printStackTrace();
		
	}
	return date;
	
	
}

public static String getEnd(String start, String length) throws Exception {
	
	try{
		int lengthI = Integer.valueOf(length);
		String[] split = start.split(":");
		int startHour = Integer.valueOf(split[0]);
		int startMin = Integer.valueOf(split[1]);
		int endMin = (startMin+lengthI)%60;
		int hoursGained = (int)(startMin+lengthI)/60;
		int endHour = startHour+hoursGained;
		
		
		return String.format("%02d:%02d", endHour, endMin);
	}catch(Exception e) {
		
		e.printStackTrace();
		
	}
	return start;
	
	
}

static String getDayOfMonthSuffix(Date date) {
	Calendar c = Calendar.getInstance();
	c.setTime(date);
	int n = c.get(Calendar.DAY_OF_MONTH);
    if (n >= 11 && n <= 13) {
        return n + "th";
    }
    switch (n % 10) {
        case 1:  return n + "st";
        case 2:  return n + "nd";
        case 3:  return n + "rd";
        default: return n+ "th";
    }
}


	public static void addApps(List<String> dayApps, GridBagConstraints c, Container contentPane1, JPanel contentPane, int day, Date appDate, Date currentDate, String user) throws Exception {
		
		try{
			JButton time;
			int totalDay = dayApps.size();
			System.out.println(totalDay/9);
			System.out.println("Length of list");
			for (int i=0;i<(totalDay/9);i++){
				String[] split = dayApps.get((9*i)+1).split(":");
				int appHour = Integer.valueOf(split[0]);
				int appMin = Integer.valueOf(split[1]);
				appMin = appMin / 15;
				appHour = appHour - 8;

				JPanel appointmentInfo = new JPanel();
				appointmentInfo.setLayout(new GridLayout(7,1));
				Border paddingBorder = BorderFactory.createEmptyBorder(15,15,15,15);
				Border outside = BorderFactory.createBevelBorder(1);
				appointmentInfo.setBorder(BorderFactory.createCompoundBorder(outside, paddingBorder));
				

				appointmentInfo.add(new JLabel("Patient Name: " + dayApps.get((9*i)+6) + " " + dayApps.get((9*i)+7)));
				
				appointmentInfo.add(new JLabel("Patient Contact No: " +  dayApps.get((9*i)+8)) );
				appointmentInfo.add(new JLabel("Appointment Type: " +  (dayApps.get(9*i) ) ) );
				appointmentInfo.add(new JLabel("Cost: " +  (dayApps.get((9*i)+3) ) ) );
				appointmentInfo.add(new JLabel("Partner Performing Procedure: " +  (dayApps.get((9*i)+4) )) );
				appointmentInfo.add(new JLabel("Time of Appointment: " +  split[0] + ":" +split[1] + "-" + getEnd(dayApps.get((9*i)+1), dayApps.get((9*i)+2)) ) );
				JButton returnButton = new JButton("RETURN TO CALENDAR");
				returnButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						contentPane1.removeAll();
						try {
							addAppForm(contentPane1, currentDate, user);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						contentPane1.add(contentPane);
						contentPane.setVisible(true);
						contentPane.revalidate();
						contentPane1.repaint();
					}
				});
				
				JButton deleteButton = new JButton("DELETE APPOINTMENT");
				String timeApp = dayApps.get((9*i)+1);
				String partnerApp = dayApps.get((9*i)+4);
				deleteButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						try {
							SqlCreation.deleteAppointment(appDate, timeApp, partnerApp);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						contentPane1.removeAll();
						try {
							createTimetable(contentPane1, currentDate, user);
							addAppForm(contentPane1, currentDate, user);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						contentPane1.revalidate();
						contentPane1.repaint();
					}
				});
				
				JPanel buttons = new JPanel();
				buttons.setLayout(new FlowLayout());
				buttons.add(returnButton);
				buttons.add(deleteButton);
				appointmentInfo.add(buttons);
				
				time = new JButton(dayApps.get((9*i)+5) + " " + dayApps.get((9*i)+7).charAt(0) + ": "+split[0] + ":" +split[1] + "-" + getEnd(dayApps.get((9*i)+1), dayApps.get((9*i)+2)) );
				time.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						contentPane1.removeAll();
						contentPane1.add(appointmentInfo);
						appointmentInfo.setVisible(true);
						appointmentInfo.revalidate();
						contentPane1.repaint();
					}
				});
				
				c.fill = GridBagConstraints.NONE;
				c.weightx = 0.5;
				c.gridheight = (int) ((Float.valueOf(dayApps.get((9*i)+2)))/15);
				System.out.println("HEIGHT IS - " + (Float.valueOf(dayApps.get((9*i)+2)))/15);
				c.ipady = (Integer.valueOf(dayApps.get((9*i)+2))/3);
				c.ipadx = 0;
				c.gridx = day;
				c.gridy = (appHour*4) + appMin;
				contentPane.add(time, c);
			}
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		
	}

}
    
    

