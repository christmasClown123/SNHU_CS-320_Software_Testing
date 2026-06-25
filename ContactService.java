package projectOne;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ContactService {
	// creates a map for tasks that uses id as the key for information
	private Map<String, Contact> contactList;
	
	// ContactService constructor that initializes a new map
	public ContactService() {
		this.contactList = new HashMap<>();
	}
	
	// add a new contact with a unique id
	public void addContact(Contact contact) {
		// checks for null
		if (contact == null) {
			throw new IllegalArgumentException("Cannot add a null contact.");
		}
		// checks for duplicate
		if (contactList.containsKey(contact.getContactId())) {
			throw new IllegalArgumentException("Contact with ID " + contact.getContactId() + " already exists.");
		}
		contactList.put(contact.getContactId(), contact);
	}
	
	public void deleteContact(String contactId) {
		// checks for null or non-existent
		if (!contactList.containsKey(contactId)) {
			throw new IllegalArgumentException("Cannot delete null or non-existent contact.");
		}
		contactList.remove(contactId);
	}
	
	public void updateFirstName(String contactId, String newFirstName) {
		Contact contact = contactList.get(contactId);
		if (contact == null) {
			throw new IllegalArgumentException("Cannot update null or non-existent contact.");
		}
		contact.setFirstName(newFirstName);
	}
	
	public void updateLastName(String contactId, String newLastName) {
		Contact contact = contactList.get(contactId);
		if (contact == null) {
			throw new IllegalArgumentException("Cannot update null or non-existent contact.");
		}
		contact.setLastName(newLastName);
	}
	
	public void updateAddress(String contactId, String newAddress) {
		Contact contact = contactList.get(contactId);
		if (contact == null) {
			throw new IllegalArgumentException("Cannot update null or non-existent contact.");
		}
		contact.setAddress(newAddress);
	}
	
	public void updatePhone(String contactId, String newPhone) {
		Contact contact = contactList.get(contactId);
		if (contact == null) {
			throw new IllegalArgumentException("Cannot update null or non-existent contact.");
		}
		contact.setPhone(newPhone);
	}
	
	// getter for contact id in contactService
	public Contact getContact(String contactId) {
		return contactList.get(contactId);
	}
	
	// retrieves user-input to create a new contact
	public void addContactHelper(Scanner scanner) {
		try {
			System.out.print("Enter Contact ID (Max 10 characters): ");
			String contactId = scanner.nextLine();
			
			System.out.print("Enter First Name (Max 10 characters): ");
			String firstName = scanner.nextLine();
			
			System.out.print("Enter Last Name (Max 10 characters): ");
			String lastName = scanner.nextLine();
			
			System.out.print("Enter Address (Max 30 characters): ");
			String address = scanner.nextLine();
			
			System.out.print("Enter Phone Number (Max 10 digits): ");
			String phone = scanner.nextLine();
			
			Contact contact = new Contact(contactId, firstName, lastName, address, phone);
			addContact(contact);
			System.out.println("Contact has been added successfully!");
		} catch (IllegalArgumentException e) {
			System.out.println("Error adding contact: " + e.getMessage());
		}
	}
	
	// delete contact helper and interface
	public void deleteContactHelper(Scanner scanner) {
		System.out.print("Enter Contact ID to delete: ");
		String id = scanner.nextLine();
				
		try {
			deleteContact(id);
			System.out.println("Contact has been deleted successfully!");
		} catch (IllegalArgumentException e) {
			System.out.println("Error deleting contact: " + e.getMessage());
		}
	}
	
	// retrieves user-input to update a contact
	public void updateContactHelper(Scanner scanner) {
		System.out.print("Enter Contact ID to update: ");
		String contactId = scanner.nextLine();
		
		if (getContact(contactId) == null) {
			System.out.println("Contact is not found in tracker.");
			return;
		}
		
		try {
			System.out.println("\nChoose an update option:");
			System.out.println("1. Update First Name");
			System.out.println("2. Update Last Name");
			System.out.println("3. Update Address");
			System.out.println("4. Update Phone number");
			System.out.println("5. Update all information");
			
			int updateChoice = scanner.nextInt();
			scanner.nextLine();
			
			switch (updateChoice) {
			case 1:
				System.out.print("Enter new First Name (Max 10 character): ");
				String firstName = scanner.nextLine();
				updateFirstName(contactId, firstName);
				System.out.println("First Name has been successfully updated!");
				break;
			case 2:
				System.out.print("Enter new Last Name (Max 10 characters): ");
				String lastName = scanner.nextLine();
				updateLastName(contactId, lastName);
				System.out.println("Last Name has been successfully update!");
				break;
			case 3:
				System.out.print("Enter new Address (Max 50 characters): ");
				String address = scanner.nextLine();
				updateAddress(contactId, address);
				System.out.println("Address has been successfully updated!");
				break;
			case 4:
				System.out.print("Enter new Phone (Max 10 digits): ");
				String phone = scanner.nextLine();
				updatePhone(contactId, phone);
				System.out.println("Phone has been successfully updated!");
			case 5:
				System.out.print("Enter new First Name (Max 10 character): ");
				String newFirstName = scanner.nextLine();
				updateFirstName(contactId, newFirstName);
				System.out.print("Enter new Last Name (Max 10 characters): ");
				String newLastName = scanner.nextLine();
				updateLastName(contactId, newLastName);
				System.out.print("Enter new Address (Max 50 characters): ");
				String newAddress = scanner.nextLine();
				updateAddress(contactId, newAddress);
				System.out.print("Enter new Phone (Max 10 digits): ");
				String newPhone = scanner.nextLine();
				updatePhone(contactId, newPhone);
				System.out.println("Contact has been successfully updated!");
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Error: Please enter a valid number (1-5).");
			scanner.nextLine();
		} catch (IllegalArgumentException e) {
			System.out.println("Error updating contact: " + e.getMessage());
		}
	}
	
	// view a specific contact
	public void viewContactHelper(Scanner scanner) {
		System.out.print("Enter Contact ID to view: ");
		String contactId = scanner.nextLine();
		
		Contact contact = getContact(contactId);
		if (contact == null) {
			System.out.println("Contact has not been found.");
		} else {
			System.out.println("\n*** Contact Details ***");
			System.out.println("ID: " + contact.getContactId());
			System.out.println("Name: " + contact.getFirstName() + " " + contact.getLastName());
			System.out.println("Address: " + contact.getAddress());
			System.out.println("Phone: " + contact.getPhone());
			System.out.println("**********************");
		}
	}
	
	// view all contacts in contactList
	public void displayAllContacts() {
		if (contactList.isEmpty()) {
			System.out.println("No contacts found.");
			return;
		}
		
		System.out.println("\n*** All Contacts ***");
		for (Contact contact : contactList.values()) {
			System.out.println("ID: " + contact.getContactId());
			System.out.println("Name: " + contact.getFirstName() + " " + contact.getLastName());
			System.out.println("Address: " + contact.getAddress());
			System.out.println("Phone: " + contact.getPhone());
			System.out.println("**********************");
		}
	}
	
	public void contactMenuHelper() {
		Scanner scanner = new Scanner(System.in);
		boolean contactSession = true;
		
		System.out.println("\nContact Management System");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
		
		while (contactSession) {
			System.out.println("\nChoose an option:");
			System.out.println("1. Add Contact");
			System.out.println("2. Delete Contact");
			System.out.println("3. Update Contact");
			System.out.println("4. View Contact");
			System.out.println("5. View All Contacts");
			System.out.println("6. Main Menu");
			System.out.print("Enter your choice: ");
			
			try {
				int choice = scanner.nextInt();
				scanner.nextLine(); // clears scanner for next input
				
				switch (choice) {
				case 1:
					addContactHelper(scanner);
					break;
				case 2:
					deleteContactHelper(scanner);
					break;
				case 3:
					updateContactHelper(scanner);
					break;
				case 4:
					viewContactHelper(scanner);
					break;
				case 5: 
					displayAllContacts();
					break;
				case 6:
					contactSession = false;
					System.out.println("Thank you for using the Task Mangement System!");
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
