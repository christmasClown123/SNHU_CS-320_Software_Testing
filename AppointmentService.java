package projectOne;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class AppointmentService {
	// sets date format for interface
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		
	// creates a map for tasks that uses id as the key for information
	private Map<String, Appointment> apptTracker;
		
	// AppointmentService constructor that initializes a new map
	public AppointmentService() {
		this.apptTracker = new HashMap<>();
	}
		
	// add a new appointment with a unique id
	public void addAppt(Appointment appt) {
		if (appt == null) {
			throw new IllegalArgumentException("Cannot add null appointment.");
		}
		if (apptTracker.containsKey(appt.getApptId())) {
			throw new IllegalArgumentException("Appointment with ID " + appt.getApptId() + " already exists.");
		}
		apptTracker.put(appt.getApptId(), appt);
	}
		
	// delete an appointment with a unique id
	public void deleteAppt(String apptId) {
		// checks for null or non-existent
		if (!apptTracker.containsKey(apptId)) {
			throw new IllegalArgumentException("Cannot delete null or non-existent appointment.");
		}
		apptTracker.remove(apptId);
	}
		
	// update for appointment date
	public void updateApptDate(String apptId, Date apptDate) {
		Appointment appt = apptTracker.get(apptId);
		// checks for null or non-existent
		if (appt == null) {
			throw new IllegalArgumentException("Cannot update null or non-existent appointment.");
		}
		appt.setApptDate(apptDate);
	}
		
	// update for appointment description
	public void updateApptDesc(String apptId, String apptDesc) {
		Appointment appt = apptTracker.get(apptId);
		// checks for null or non-existent
		if (appt == null) {
			throw new IllegalArgumentException("Cannot update null or non-existent appointment.");
		}
		appt.setApptDesc(apptDesc);
	}
		
	// getter for task id in appointmentService
	public Appointment getAppt(String apptId) {
		return apptTracker.get(apptId);
	}
		
	// add appointment helper and interface
	public void addApptHelper(Scanner scanner) {
		try {
			System.out.print("Enter Appointment ID (Max 10 characters): ");
			String id = scanner.nextLine();
				
			System.out.print("Enter Date (" + DATE_FORMAT + "): ");
			String dateInput = scanner.nextLine();
			// converts user input to Date
			Date date = sdf.parse(dateInput);
				
			System.out.print("Enter the Appointment description (Max 50 characters): ");
			String desc = scanner.nextLine();
				
			Appointment appt = new Appointment(id, date, desc);
			addAppt(appt);
			System.out.println("Appointment has been added successfully!");
		} catch (IllegalArgumentException e) {
			System.out.println("Error adding appointment: " + e.getMessage());
		} catch (ParseException e) {
			System.out.println("Please use " + DATE_FORMAT + " format when adding a date.");
		}
	}
		
	// delete appointment helper and interface
	public void deleteApptHelper(Scanner scanner) {
		System.out.print("Enter Appointment ID to delete: ");
		String apptId = scanner.nextLine();
				
		try {
			deleteAppt(apptId);
			System.out.println("Appointment has been deleted successfully!");
		} catch (IllegalArgumentException e) {
			System.out.println("Error deleting appointment: " + e.getMessage());
		}
	}
		
	// update appointment helper and interface
	public void updateApptHelper(Scanner scanner) {
		System.out.print("Enter Appointment ID to update: ");
		String apptId = scanner.nextLine();
			
		if (getAppt(apptId) == null) {
			System.out.println("Appointment is not found in tracker.");
			return;
		}
			
		try {
			System.out.println("\nChoose an update option:");
			System.out.println("1. Update Appointment date");
			System.out.println("2. Update Appointment description");
			System.out.println("3. Update Appointment date & description");
				
			int updateChoice = scanner.nextInt();
			scanner.nextLine();
				
			switch (updateChoice) {
			case 1:
				System.out.print("Enter new Appointment date (" + DATE_FORMAT + "): ");
				String dateInput = scanner.nextLine();
				// converts user input to Date
				Date date = sdf.parse(dateInput);
				updateApptDate(apptId, date);
				System.out.println("Appointment date has been successfully updated!");
				break;
			case 2:
				System.out.print("Enter new Appointment description (Max 50 characters): ");
				String description = scanner.nextLine();
				updateApptDesc(apptId, description);
				System.out.println("Appointment description has been successfully update!");
				break;
			case 3:
				System.out.print("Enter new Appointment date (" + DATE_FORMAT + "): ");
				String newDateInput = scanner.nextLine();
				// converts user input to Date
				Date newDate = sdf.parse(newDateInput);
				System.out.print("Enter new Appointment description (Max 50 characters): ");
				String newDesc = scanner.nextLine();
				updateApptDate(apptId, newDate);
				updateApptDesc(apptId, newDesc);
				System.out.println("Appointment has been successfully updated!");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Error: Please enter a valid number (1-3).");
			scanner.nextLine();
		} catch (IllegalArgumentException e) {
			System.out.println("Error updating appointment: " + e.getMessage());
		} catch (ParseException e) {
			System.out.println("Please use " + DATE_FORMAT + " format when adding a date.");
		}
	}
		
	// view a specific task
	public void viewApptHelper(Scanner scanner) {
		System.out.print("Enter Appointment ID to view: ");
		String apptId = scanner.nextLine();
			
		Appointment appt = getAppt(apptId);
		if (appt == null) {
			System.out.println("Appointment has not been found.");
		} else {
			System.out.println("\n*** Appointment Details ***");
			System.out.println("ID: " + appt.getApptId());
			System.out.println("Date: " + sdf.format(appt.getApptDate()));
			System.out.println("Desc: " + appt.getApptDesc());
			System.out.println("**********************");
		}
	}
		
	// view all tasks in tracker
	public void displayAllAppts() {
		if (apptTracker.isEmpty()) {
			System.out.println("No appointments found.");
			return;
		}
				
		System.out.println("\n*** All Appointments ***");
		for (Appointment appt : apptTracker.values()) {
			System.out.println("ID: " + appt.getApptId());
			System.out.println("Date: " + sdf.format(appt.getApptDate()));
			System.out.println("Desc: " + appt.getApptDesc());
			System.out.println("**********************");
		}
	}
	
	public void apptMenuHelper() {
		Scanner scanner = new Scanner(System.in);
		boolean apptSession = true;
		
		System.out.println("\nAppointment Management System");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
		
		while (apptSession) {
			System.out.println("\nChoose an option:");
			System.out.println("1. Add Appointment");
			System.out.println("2. Delete Appointment");
			System.out.println("3. Update Appointment");
			System.out.println("4. View Appointment");
			System.out.println("5. View All Appointments");
			System.out.println("6. Main Menu");
			System.out.print("Enter your choice: ");
			
			try {
				int choice = scanner.nextInt();
				scanner.nextLine(); // clears scanner for next input
				
				switch (choice) {
				case 1:
					addApptHelper(scanner);
					break;
				case 2:
					deleteApptHelper(scanner);
					break;
				case 3:
					updateApptHelper(scanner);
					break;
				case 4:
					viewApptHelper(scanner);
					break;
				case 5: 
					displayAllAppts();
					break;
				case 6:
					apptSession = false;
					System.out.println("Thank you for using the Appointment Mangement System!");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Please enter a valid number (1-6).");
				scanner.nextLine();
			} catch (Exception e) {
				System.out.println("Error in menu: " + e.getMessage());
			}
		}
	}
}
