package projectOne;

public class Contact {
	// immutable lengths to compare objects
	private static final int CONTACT_ID_MAX = 10;
	private static final int FIRST_NAME_MAX = 10;
	private static final int LAST_NAME_MAX = 10;
	private static final int ADDRESS_MAX = 30;
	private static final String PHONE_MAX = "\\d{10}";
		
	// objects to be accessed only in Contact class
	private final String contactId;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
		
	// Contact constructor
	public Contact(String contactId, String firstName, String lastName, String address, String phone) {
		// object validation
		validateContactId(contactId);
		validateFirstName(firstName);
		validateLastName(lastName);
		validateAddress(address);
		validatePhone(phone);
			
		// establish objects
		this.contactId = contactId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}
		
	//object getters
	public String getContactId() {
		return contactId;
	}
		
	public String getFirstName() {
		return firstName;
	}
		
	public String getLastName() {
		return lastName;
	}
		
	public String getAddress() {
		return address;
	}
		
	public String getPhone() {
		return phone;
	}
		
	//object setters, no setter for id since it is key
	public void setFirstName(String firstName) {
		validateFirstName(firstName);
		this.firstName = firstName;
	}
		
	public void setLastName(String lastName) {
		validateLastName(lastName);
		this.lastName = lastName;
	}
		
	public void setAddress(String address) {
		validateAddress(address);
		this.address = address;
	}
		
	public void setPhone(String phone) {
		validatePhone(phone);
		this.phone = phone;
	}
		
	// object validators
	private void validateContactId(String contactId) {
		if (contactId == null || contactId.trim().isEmpty()) {
			throw new IllegalArgumentException("Contact ID cannot be null or empty.");
		}
		if (contactId.length() > CONTACT_ID_MAX) {
			throw new IllegalArgumentException("Contact ID cannot be longer than " + CONTACT_ID_MAX + " characters.");
		}
	}
		
	private void validateFirstName(String firstName) {
		if (firstName == null || firstName.trim().isEmpty()) {
			throw new IllegalArgumentException("First Name cannot be null or empty.");
		}
		if (firstName.length() > FIRST_NAME_MAX) {
			throw new IllegalArgumentException("First Name cannot be longer than " + FIRST_NAME_MAX + " characters.");
		}
	}
		
	private void validateLastName(String lastName) {
		if (lastName == null || lastName.trim().isEmpty()) {
			throw new IllegalArgumentException("Last Name cannot be null or empty.");
		}
		if (lastName.length() > LAST_NAME_MAX) {
			throw new IllegalArgumentException("Last Name cannot be longer than " + LAST_NAME_MAX + " characters.");
		}
	}
		
	private void validateAddress(String address) {
		if (address == null || address.trim().isEmpty()) {
			throw new IllegalArgumentException("Address cannot be null or empty.");
		}
		if (address.length() > ADDRESS_MAX) {
			throw new IllegalArgumentException("Address cannot be longer than " + ADDRESS_MAX + " characters.");
		}
	}
		
	private void validatePhone(String phone) {
		if (phone == null || phone.trim().isEmpty()) {
			throw new IllegalArgumentException("Phone cannot be null or empty.");
		}
		if (!phone.matches(PHONE_MAX)) {
			throw new IllegalArgumentException("Phone must be 10 digits.");
		}
	}
}
