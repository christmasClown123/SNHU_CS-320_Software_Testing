package projectOne;

// Courtney Jenkins
// SNHU
// CS 320
// 14 Jun 26

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

	public static void main(String[] args) {
		ContactService serviceC = new ContactService();
		TaskService serviceT = new TaskService();
		AppointmentService serviceA = new AppointmentService();
		Scanner scanner = new Scanner(System.in);
		boolean activeSession = true;
		
		System.out.println("Personal Management System");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		while (activeSession) {
			System.out.println("\nChoose an option:");
			System.out.println("1. Contact Management");
			System.out.println("2. Task Management");
			System.out.println("3. Appointment Management");
			System.out.println("4. Exit");
			
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();
				
				switch (choice) {
				case 1:
					serviceC.contactMenuHelper();
					break;
				case 2:
					serviceT.taskMenuHelper();
					break;
				case 3:
					serviceA.apptMenuHelper();
					break;
				case 4:
					activeSession = false;
					System.out.println("Thank you for using Personal Management System!");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Please enter a valid number (1-3).");
				scanner.nextLine();
			} catch (Exception e) {
				System.out.println("Error in menu: " + e.getMessage());
			}
		}
		scanner.close();
	}
}
