package dentist;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqlCreation {

	public static void main(String[] args) throws Exception {
		
		getConnection();
		Connection conn = getConnection();
		deleteData();
		PreparedStatement delete2 = conn.prepareStatement("DROP TABLE freeTreatments;");
		delete2.executeUpdate();
		PreparedStatement delete3 = conn.prepareStatement("DROP TABLE appointments;");
		delete3.executeUpdate();
		PreparedStatement delete4 = conn.prepareStatement("DROP TABLE patients;");
		delete4.executeUpdate();
		PreparedStatement delete5 = conn.prepareStatement("DROP TABLE plans;");
		delete5.executeUpdate();
		PreparedStatement delete6 = conn.prepareStatement("DROP TABLE addresses;");
		delete6.executeUpdate();
		createTable();
		initialisingPlans();
		Date dateOB = Date.valueOf("1998-01-12");
		Date dateOfApp = Date.valueOf("2017-11-21");
		Date dateOfApp2 = Date.valueOf("2017-11-22");
		Date dateOfApp3 = Date.valueOf("2017-11-23");
		Date dateOfApp4 = Date.valueOf("2017-11-24");
		Date dateOfApp5 = Date.valueOf("2017-11-25");
		Date dateOfApp6 = Date.valueOf("2017-11-26");
		Date dateOfApp7 = Date.valueOf("2017-11-27");
		Time appTime = Time.valueOf("10:00:00");
		Time appTime2 = Time.valueOf("12:00:00");
		Time appTime3 = Time.valueOf("12:45:00");
		Time appTime4 = Time.valueOf("15:30:00");
		Time appTime5 = Time.valueOf("16:00:00");
		Time appTime6 = Time.valueOf("14:15:00");
		Time appTime7 = Time.valueOf("9:00:00");
		registerPatient("BLANK", "BLANK", "BLANK", "BLANK", 0, 0, "NOT", "NOT", "AVAILABLE", dateOB, "0000");
		registerPatient("DN370RX", "Church Lane", "Lincoln", "Grimsby", 1, 2, "Mr", "Harry", "Williams", dateOB, "7484659713");
		registerPatient("DN370QW", "Main Road", "Lincoln", "Grimsby", 4, 2, "Mr", "Harold", "Williams", dateOB, "7484659713");
		registerPatient("DN370RX", "Church Lane", "Lincoln", "Grimsby", 1, 0, "Mrs", "Janice", "Williams", dateOB, "7484659713");
		insertAppointment(2, "hygeine", dateOfApp, appTime, 60, 45.00, "Hygenist");
		insertAppointment(4, "repair", dateOfApp, appTime2, 45, 15.00, "Hygenist");
		insertAppointment(3, "check up", dateOfApp, appTime3, 75, 20.00, "Hygenist");
		insertAppointment(2, "check up", dateOfApp2, appTime4, 75, 20.00, "Dentist");
		insertAppointment(3, "hygeine", dateOfApp3, appTime7, 30, 45.00, "Dentist");
		insertAppointment(4, "hygeine", dateOfApp4, appTime2, 60, 45.00, "Hygenist");
		insertAppointment(3, "hygeine", dateOfApp5, appTime6, 60, 45.00, "Hygenist");
		insertAppointment(2, "hygeine", dateOfApp6, appTime, 30, 45.00, "Hygenist");
		insertAppointment(4, "hygeine", dateOfApp7, appTime5, 45, 45.00, "Dentist");
		getAppsOnDate(Date.valueOf("2017-11-21"), "Hygenist");
		
		}
		
	
	public static void createTable() throws Exception {
		
		try{
			Connection conn = getConnection();
			PreparedStatement createAddresses = conn.prepareStatement("CREATE TABLE IF NOT EXISTS addresses (address_id INT NOT NULL UNIQUE AUTO_INCREMENT, postcode VARCHAR(10), street VARCHAR(20), district VARCHAR(20), city VARCHAR(20), house_no INT, PRIMARY KEY ( address_id ) );");
			createAddresses.executeUpdate();
			System.out.println("success address");
			
			PreparedStatement createPlan = conn.prepareStatement("CREATE TABLE IF NOT EXISTS  plans (plan_id INT NOT NULL UNIQUE, name VARCHAR(20), monthly_cost DECIMAL(10, 2), PRIMARY KEY ( plan_id ) );");
			createPlan.executeUpdate();
			System.out.println("success plan");
			
			PreparedStatement createPatients = conn.prepareStatement("CREATE TABLE IF NOT EXISTS patients ( patient_id INT NOT NULL UNIQUE AUTO_INCREMENT, address_id INT NOT NULL, plan_id INT DEFAULT 0, title VARCHAR(4), forename VARCHAR(20), surname VARCHAR(20), birth DATE, contact_number VARCHAR(15), PRIMARY KEY ( patient_id ), FOREIGN KEY (address_id) REFERENCES addresses (address_id), FOREIGN KEY (plan_id) REFERENCES plans (plan_id) );");
			createPatients.executeUpdate();
			System.out.println("success patients");
			
			PreparedStatement createAppointments = conn.prepareStatement("CREATE TABLE IF NOT EXISTS appointments ( appointment_id INT NOT NULL UNIQUE AUTO_INCREMENT, patient_id INT NOT NULL, appointment_type VARCHAR(20), date DATE, start_time TIME, length_mins INT, cost DECIMAL(10, 2), partner VARCHAR(20), PRIMARY KEY ( appointment_id ), FOREIGN KEY (patient_id) REFERENCES patients(patient_id) );");
			createAppointments.executeUpdate();
			System.out.println("success appointments");
			
			PreparedStatement createFreeTreatments = conn.prepareStatement("CREATE TABLE IF NOT EXISTS freeTreatments ( free_id INT NOT NULL UNIQUE AUTO_INCREMENT, patient_id INT NOT NULL, plan_id INT NOT NULL, check_ups TINYINT(1), hygiene TINYINT(1), repairs TINYINT(1), PRIMARY KEY ( free_id ), FOREIGN KEY (patient_id) REFERENCES patients(patient_id), FOREIGN KEY (plan_id) REFERENCES plans(plan_id) );");
			createFreeTreatments.executeUpdate();
			System.out.println("success free treatments");
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
public static List<String> getAppsOnDate(Date date, String user) throws Exception {
		
		try{
			Connection conn = getConnection();
			System.out.println(date);
			PreparedStatement getAppointments;
			if(user.equals("Secretary")){
				getAppointments = conn.prepareStatement("SELECT appointment_type, start_time, length_mins, cost, partner, title, forename, surname, contact_number FROM appointments INNER JOIN patients ON appointments.patient_id = patients.patient_id WHERE appointments.date = '"+date+"';");
			}
			else {
				getAppointments = conn.prepareStatement("SELECT appointment_type, start_time, length_mins, cost, partner, title, forename, surname, contact_number FROM appointments INNER JOIN patients ON appointments.patient_id = patients.patient_id WHERE appointments.date = '"+date+"' AND appointments.partner = '"+user+"';");
			}
			
			ResultSet apps = getAppointments.executeQuery();
			
			List<String> appointments = new ArrayList<String>();
			
			
			while(apps.next()){
				appointments.add(apps.getString(1));
				System.out.println(apps.getString(1));
				appointments.add(apps.getString(2));
				appointments.add(apps.getString(3));
				appointments.add(apps.getString(4));
				appointments.add(apps.getString(5));
				appointments.add(apps.getString(6));
				appointments.add(apps.getString(7));
				appointments.add(apps.getString(8));
				appointments.add(apps.getString(9));
			}
			System.out.println(appointments);
			
			return appointments;
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		return null;
		
	}
	
	
	
	
public static void deleteData() throws Exception {
		
		try{
			Connection conn = getConnection();
			PreparedStatement deleteFree = conn.prepareStatement("DELETE FROM freeTreatments;");
			deleteFree.executeUpdate();
			System.out.println("deleted free treatments");
			
			PreparedStatement deleteAppointments = conn.prepareStatement("DELETE FROM appointments;");
			deleteAppointments.executeUpdate();
			System.out.println("deleted appointments");
			
			PreparedStatement deletePatients = conn.prepareStatement("DELETE FROM patients;");
			deletePatients.executeUpdate();
			System.out.println("deleted patients");
			
			PreparedStatement deletePlans = conn.prepareStatement("DELETE FROM plans;");
			deletePlans.executeUpdate();
			System.out.println("deleted plans");
			
			PreparedStatement deleteAddresses = conn.prepareStatement("DELETE FROM addresses;");
			deleteAddresses.executeUpdate();
			System.out.println("deleted addresses");
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	
public static void insertAppointment(int id, String appType, Date date, Time start, int length, double cost, String partner) throws Exception {
		
		try{
			
			Connection conn = getConnection();
			
			PreparedStatement insertApp = conn.prepareStatement("INSERT IGNORE INTO appointments (patient_id, appointment_type, date, start_time, length_mins, cost, partner) VALUES (" + id + ", '" + appType + "', '" + date + "', '" + start + "', " + length + ", " + cost + ", '" + partner + "');");
			insertApp.executeUpdate();

			System.out.println("APPOINTMENT INSERTED");
			
			
			
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		
	}

public static void deleteAppointment(Date date, String time, String partner) throws Exception {
	
	try{
		
		Connection conn = getConnection();
		System.out.println(date.toString());
		System.out.println(time);
		System.out.println(partner);
		PreparedStatement deleteApp = conn.prepareStatement("DELETE FROM appointments WHERE date = '" + date.toString() + "' AND start_time = '" + time + "' AND partner = '" + partner + "';");
		deleteApp.executeUpdate();

		System.out.println("APPOINTMENT DELETED");
		
		
		
		
		
	}catch(Exception e){
		
		e.printStackTrace();
	}
	
	
}
	
	public static void initialisingPlans() throws Exception {
		
		try{
			
			Connection conn = getConnection();
			
			PreparedStatement initNone = conn.prepareStatement("INSERT IGNORE INTO plans (plan_id, name, monthly_cost) VALUES (0, 'No Dental Health Plan', 0.00);");
			initNone.executeUpdate();
			
			PreparedStatement initFreePlan = conn.prepareStatement("INSERT IGNORE INTO plans (plan_id, name, monthly_cost) VALUES (1, 'NHS Free Plan', 0.00);");
			initFreePlan.executeUpdate();
			
			PreparedStatement initMaintenance = conn.prepareStatement("INSERT IGNORE INTO plans (plan_id, name, monthly_cost) VALUES (2, 'Maintenance Plan', 15.00);");
			initMaintenance.executeUpdate();
					
			PreparedStatement initOral = conn.prepareStatement("INSERT IGNORE INTO plans (plan_id, name, monthly_cost) VALUES (3, 'Oral Health Plan', 21.00);");
			initOral.executeUpdate();
							
			PreparedStatement initRepair = conn.prepareStatement("INSERT IGNORE INTO plans (plan_id, name, monthly_cost) VALUES (4, 'Dental Repair Plan', 36.00);");
			initRepair.executeUpdate();
			
			
			
			System.out.println("All plans inserted");
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		
	}
	
	
public static int getPatientId(String forename, String surname, String dOb, String contactNo) throws Exception {
		
		try{
			Connection conn = getConnection();
			
			PreparedStatement getPatient = conn.prepareStatement("SELECT patient_id FROM patients WHERE forename = '" + forename + "' AND surname = '" + surname + "' AND birth = '" + dOb + "'  AND contact_number = '" + contactNo + "';");
			ResultSet patient_id = getPatient.executeQuery();
			
			int id = 1;
			
			if(patient_id.next()){
				id = Integer.valueOf( patient_id.getString(1)  );
			}
			System.out.println("GOT PATIENT ID, IT IS - " + id);
			return id;
		} catch(Exception e){
			
			e.printStackTrace();
		}
		return 1;
}
	
	
	
	public static void registerPatient(String postCode, String street, String district, String city, int houseNumber, int plan, String title, String forename, String surname, Date birth, String contact ) throws Exception {
		
		try{
			
			Connection conn = getConnection();
			
			PreparedStatement registerAddress = conn.prepareStatement("INSERT IGNORE INTO addresses (postcode, street, district, city, house_no) VALUES ('" + postCode + "', '" + street + "', '" + district + "', '" + city + "', " + houseNumber + ");");
			registerAddress.executeUpdate();
			
			System.out.println("ADDRESS REGISTERED");
			
			PreparedStatement getAddressID = conn.prepareStatement("SELECT address_id FROM addresses WHERE postcode = '" + postCode + "' AND house_no = " + houseNumber + ";");
			ResultSet result = getAddressID.executeQuery();
			
			String address = null;
			
			if(result.next()){
				address = result.getString(1);
				System.out.println(address);
			}
			System.out.println(address);
			
			PreparedStatement registerPatient = conn.prepareStatement("INSERT IGNORE INTO patients (address_id, plan_id, title, forename, surname, birth, contact_number) VALUES (" + address + ", " + plan + ", '" + title + "', '" + forename + "', '" + surname + "', '" + birth + "', " + contact + ");");
			registerPatient.executeUpdate();
			
			System.out.println("PATIENT REGISTERED");
			
			PreparedStatement getPatient = conn.prepareStatement("SELECT patient_id FROM patients WHERE forename = '" + forename + "' AND surname = '" + surname + "' AND address_id = " + result.getString(1) + ";");
			ResultSet patient_id = getPatient.executeQuery();
			
			String id = null;
			
			if(patient_id.next()){
				id = patient_id.getString(1);
				System.out.println(id);
			}
			System.out.println("GOT PATIENT ID, IT IS - " + id);
			
			int checkUps, hygiene, repairs;
			
			if (plan == 0){ checkUps = 0; hygiene = 0; repairs = 0; }
			else if (plan == 1){ checkUps = 2; hygiene = 2; repairs = 6; }
			else if (plan == 2){ checkUps = 2; hygiene = 2; repairs = 0; }
			else if (plan == 3){ checkUps = 2; hygiene = 4; repairs = 0; }
			else { checkUps = 2; hygiene = 2; repairs = 2; }
			
			PreparedStatement getFree = conn.prepareStatement("INSERT IGNORE INTO freeTreatments ( patient_id, plan_id, check_ups, hygiene, repairs) VALUES (" + patient_id.getString(1) + ", " + plan + ", '" + checkUps + "', '" + hygiene + "', '" + repairs + "');");
			getFree.executeUpdate();
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
public static void registerPatient(int patient_id, int plan_id) throws Exception {
		
		try{
			
			Connection conn = getConnection();
			
			PreparedStatement updateRemaining = conn.prepareStatement("UPDATE patients SET check_ups_remaining='', hygiene_remaining='', repairs_remaining WHERE patient_id = ;");
			updateRemaining.executeUpdate();
			
			System.out.println("UPDATED FREE DENTIST APPOINTMENTS REMAINING");
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		
	}

public static List<String> getNextApp(String partner, String date, String time) throws Exception {
	
	try{
		
		Connection conn = getConnection();
		System.out.println(date);
		PreparedStatement getAppointments = null;
		String closestTime = "23:59:00";
		int closestHour = 23;
		int closestMin = 59;
		String currentTime = time;
		String[] splitCurrent = (currentTime.split(":"));
		int currentHour = Integer.valueOf(splitCurrent[0]);
		int currentMin = Integer.valueOf(splitCurrent[1]);
		
		if(partner.equals("Secretary")){
			PreparedStatement getTodayTimes = conn.prepareStatement("SELECT start_time FROM appointments WHERE date = '"+date+"';");
			ResultSet timesResult = getTodayTimes.executeQuery();
			List<Integer> times = new ArrayList<Integer>();
			while(timesResult.next()){
				String[] split = (timesResult.getString(1)).split(":");
				times.add(Integer.valueOf(split[0]));
				times.add(Integer.valueOf(split[1]));
			}
			for (int i=0; i<=(times.size()/2); i++){
				if ( times.get(i) < closestHour && times.get(i) > currentHour){
					closestHour = times.get(i);
					closestMin = times.get(i+1);
				}
				else if ( times.get(i) < closestHour && times.get(i) == currentHour){
					if ( times.get(i+1) > currentMin){
						closestHour = times.get(i);
						closestMin = times.get(i+1);
					}
				}
				else if ( times.get(i) == closestHour && times.get(i) == currentHour){
					if ( times.get(i+1) < closestMin && times.get(i+1) > currentMin){
						closestHour = times.get(i);
						closestMin = times.get(i+1);
					}
				}
				else if ( times.get(i) == closestHour && times.get(i) > currentHour){
					if ( times.get(i+1) < closestMin){
						closestHour = times.get(i);
						closestMin = times.get(i+1);
					}
				}
			}
			
			closestTime = (String.valueOf(closestHour)+":"+String.valueOf(closestMin)+":00");
				
			PreparedStatement getClosestApp = conn.prepareStatement("SELECT appointment_type, start_time, length_mins, cost, partner, title, forename, surname, contact_number FROM appointments INNER JOIN patients ON appointments.patient_id = patients.patient_id WHERE start_time = '"+closestTime+"';");
			ResultSet app = getClosestApp.executeQuery();
			
			List<String> appInfo = new ArrayList<String>();
			
			while(app.next()){
				appInfo.add(app.getString(1));
				appInfo.add(app.getString(2));
				appInfo.add(app.getString(3));
				appInfo.add(app.getString(4));
				appInfo.add(app.getString(5));
				appInfo.add(app.getString(6));
				appInfo.add(app.getString(7));
				appInfo.add(app.getString(8));
				appInfo.add(app.getString(9));
			}
			
			return appInfo;
		
		}
		else {
			PreparedStatement getTodayTimes = conn.prepareStatement("SELECT start_time FROM appointments WHERE date = '"+date+"' AND partner='"+partner+"';");
			ResultSet timesResult = getTodayTimes.executeQuery();
			List<Integer> times = new ArrayList<Integer>();
			while(timesResult.next()){
				String[] split = (timesResult.getString(1)).split(":");
				times.add(Integer.valueOf(split[0]));
				times.add(Integer.valueOf(split[1]));
			}
			for (int i=0; i<=(times.size()/2); i++){
				if ( times.get(i) < closestHour && times.get(i) > currentHour){
					closestHour = times.get(i);
					closestMin = times.get(i+1);
				}
				else if ( times.get(i) < closestHour && times.get(i) == currentHour){
					if ( times.get(i+1) > currentMin){
						closestHour = times.get(i);
						closestMin = times.get(i+1);
					}
				}
				else if ( times.get(i) == closestHour && times.get(i) == currentHour){
					if ( times.get(i+1) < closestMin && times.get(i+1) > currentMin){
						closestHour = times.get(i);
						closestMin = times.get(i+1);
					}
				}
				else if ( times.get(i) == closestHour && times.get(i) > currentHour){
					if ( times.get(i+1) < closestMin){
						closestHour = times.get(i);
						closestMin = times.get(i+1);
					}
				}
			}
			
			closestTime = (String.valueOf(closestHour)+":"+String.valueOf(closestMin)+":00");
				
			PreparedStatement getClosestApp = conn.prepareStatement("SELECT appointment_type, start_time, length_mins, cost, partner, title, forename, surname, contact_number FROM appointments INNER JOIN patients ON appointments.patient_id = patients.patient_id WHERE start_time = '"+closestTime+"';");
			ResultSet app = getClosestApp.executeQuery();
			
			List<String> appInfo = new ArrayList<String>();
			
			while(app.next()){
				appInfo.add(app.getString(1));
				appInfo.add(app.getString(2));
				appInfo.add(app.getString(3));
				appInfo.add(app.getString(4));
				appInfo.add(app.getString(5));
				appInfo.add(app.getString(6));
				appInfo.add(app.getString(7));
				appInfo.add(app.getString(8));
				appInfo.add(app.getString(9));
			}
			
			return appInfo;
		
		}
		
	}catch(Exception e) {
		
		e.printStackTrace();
		
	}
	return null;
}
	
	
	
	public static List<String> getPatientAppointments(int patient) throws Exception {

		Connection conn = getConnection();
		
		PreparedStatement getPatientApps = conn.prepareStatement("SELECT appointment_type, start_time, length_mins, date, cost, partner, title, forename, surname, contact_number FROM appointments INNER JOIN patients ON appointments.patient_id = patients.patient_id WHERE patient_id = "+patient+";");
		ResultSet patientAppResult = getPatientApps.executeQuery();
		
		List<String> patientApps = new ArrayList<String>();
		
		while(patientAppResult.next()){
			patientApps.add(patientAppResult.getString(1));
			patientApps.add(patientAppResult.getString(2));
			patientApps.add(patientAppResult.getString(3));
			patientApps.add(patientAppResult.getString(4));
			patientApps.add(patientAppResult.getString(5));
			patientApps.add(patientAppResult.getString(6));
			patientApps.add(patientAppResult.getString(7));
			patientApps.add(patientAppResult.getString(8));
			patientApps.add(patientAppResult.getString(9));
			patientApps.add(patientAppResult.getString(10));
		}
		
		return patientApps;

	}
	
	public static Connection getConnection() throws Exception {
		try{
			
			//String driver = "com.mysql.jdbc.Driver";
			//Class.forName(driver);
			
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			
			String DB="jdbc:mysql://stusql.dcs.shef.ac.uk/team027?user=team027&password=2c773769";
			
			Connection conn = DriverManager.getConnection(DB);
			System.out.println("connected");
			return conn;
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		return null;
		
		
		
	}
	
	
	
}
