package projectOne;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ContactTest {
	
	// isolate instance for testing
	private Contact testContact() {
		return new Contact("abc123", "John", "Doe", "101 Main St", "5551234567");
	}
	
	// success instance for contact
	@Test
	void testContactCreationSuccess() {
		Contact c = testContact();
		assertEquals("abc123", c.getContactId());
		assertEquals("John", c.getFirstName());
		assertEquals("Doe", c.getLastName());
		assertEquals("101 Main St", c.getAddress());
		assertEquals("5551234567", c.getPhone());
	}
	
	// ===============
	// contactId tests
	// ===============
	
	@Test
	void testContactId_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact(null, "John", "Doe", "101 Main St", "5551234567"));
	}
	
	@Test
	void testContactId_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("", "John", "Doe", "101 Main St", "5551234567"));
	}
	
	@Test
	void testContactId_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("     ", "John", "Doe", "101 Main St", "5551234567"));
	}
	
	@Test
	void testContactId_TooLong_ThrowsArguments() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("12345678901", "John", "Doe", "101 Main St", "5551234567"));
	}
	
	@Test
	void testContactId_MaxBoundary_Success() {
		Contact c = new Contact("1234567890", "John", "Doe", "101 Main St", "5551234567");
		assertEquals("1234567890", c.getContactId());
	}
	
	@Test
	void testContactId_MinBoundary_Success() {
		Contact c = new Contact("1", "John", "Doe", "101 Main St", "5551234567");
		assertEquals("1", c.getContactId());
	}
	
	// ========================
	// contact first name tests
	// ========================
	
	@Test
	void testFirstName_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", null, "Doe", "101 Main St", "5551234567"));
	}
	
	@Test
	void testFirstName_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "", "Doe", "101 Main St", "5551234567"));
	}
	
	@Test
	void testFirstName_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "     ", "Doe", "101 Main St", "5551234567"));
	}
	
	@Test
	void testFirstName_TooLong_ThrowsArguments() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "JohnJacobJingleHeimerSchmidt", "Doe", "101 Main St", "5551234567"));
	}
	
	@Test
	void testFirstName_MaxBoundary() {
		Contact c = new Contact("abc123", "JohnJohnJo", "Doe", "101 Main St", "5551234567");
		assertEquals("JohnJohnJo", c.getFirstName());
	}
	
	@Test
	void testFirstName_MinBoundary() {
		Contact c = new Contact("abc123", "J", "Doe", "101 Main St", "5551234567");
		assertEquals("J", c.getFirstName());
	}

	@Test
	void testSetFirstNameSuccess() {
		Contact c = testContact();
		c.setFirstName("Jon");
		assertEquals("Jon", c.getFirstName());
	}
	
	@Test
	void testSetFirstName_MultipleSuccessiveUpdates() {
		Contact c = testContact();
		c.setFirstName("Jon");
		c.setFirstName("Johnny");
		assertEquals("Johnny", c.getFirstName());
	}
	
	@Test
	void testSetFirstName_Null_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
	}
	
	@Test
	void testSetFirstName_IsEmpty_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setFirstName(""));
	}
	
	@Test
	void testSetFirstName_WhitespaceOnly_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setFirstName("     "));
	}
	
	@Test
	void testSetFirstName_TooLong_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setFirstName("JohnJacobJingleHeimerSchmidt"));
	}
	
	@Test
	void testSetFirstName_PreservedAfterNullUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
		assertEquals("John", c.getFirstName());
	}
	
	@Test
	void testSetFirstName_PreservedAfterIsEmptyUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setFirstName(""));
		assertEquals("John", c.getFirstName());
	}
	
	@Test
	void testSetFirstName_PreservedAfterWhitespaceOnlyUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setFirstName("     "));
		assertEquals("John", c.getFirstName());
	}
	
	@Test
	void testSetFirstName_PreservedAfterTooLongUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setFirstName("JohnJacobJingleHeimerSchmidt"));
		assertEquals("John", c.getFirstName());
	}
	
	@Test
	void testSetFirstName_MaxBoundary() {
		Contact c = testContact();
		c.setFirstName("JohnJohnJo");
		assertEquals("JohnJohnJo", c.getFirstName());
	}
	
	@Test
	void testSetFirstName_MinBoundary() {
		Contact c = testContact();
		c.setFirstName("J");
		assertEquals("J", c.getFirstName());
	}
	
	// =================
	// contact last name
	// =================
	
	@Test
	void testLastName_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "John", null, "101 Main St", "5551234567"));
	}
	
	@Test
	void testLastName_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "John", "", "101 Main St", "5551234567"));
	}
	
	@Test
	void testLastName_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "John", "     ", "101 Main St", "5551234567"));
	}
	
	@Test
	void testLastName_TooLong_ThrowsArguments() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "John", "HisNameIsMyNameToo", "101 Main St", "5551234567"));
	}
	
	@Test
	void testLastName_MaxBoundary() {
		Contact c = new Contact("abc123", "John", "DoeDoeDoeD", "101 Main St", "5551234567");
		assertEquals("DoeDoeDoeD", c.getLastName());
	}
	
	@Test
	void testLastName_MinBoundary() {
		Contact c = new Contact("abc123", "John", "D", "101 Main St", "5551234567");
		assertEquals("D", c.getLastName());
	}

	@Test
	void testSetLastNameSuccess() {
		Contact c = testContact();
		c.setLastName("Doh");
		assertEquals("Doh", c.getLastName());
	}
	
	@Test
	void testSetLastName_MultipleSuccessiveUpdates() {
		Contact c = testContact();
		c.setLastName("Doh");
		c.setLastName("Dough");
		assertEquals("Dough", c.getLastName());
	}
	
	@Test
	void testSetLastName_Null_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setLastName(null));
	}
	
	@Test
	void testSetLastName_IsEmpty_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setLastName(""));
	}
	
	@Test
	void testSetLastName_WhitespaceOnly_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setLastName("     "));
	}
	
	@Test
	void testSetLastName_TooLong_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setLastName("HisNameIsMyNameToo"));
	}
	
	@Test
	void testSetLastName_PreservedAfterNullUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setLastName(null));
		assertEquals("Doe", c.getLastName());
	}
	
	@Test
	void testSetLastName_PreservedAfterIsEmptyUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setLastName(""));
		assertEquals("Doe", c.getLastName());
	}
	
	@Test
	void testSetLastName_PreservedAfterWhitespaceOnlyUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setLastName("     "));
		assertEquals("Doe", c.getLastName());
	}
	
	@Test
	void testSetLastName_PreservedAfterTooLongUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setLastName("HisNameIsMyNameToo"));
		assertEquals("Doe", c.getLastName());
	}
	
	@Test
	void testSetLastName_MaxBoundary() {
		Contact c = testContact();
		c.setLastName("DoeDoeDoeD");
		assertEquals("DoeDoeDoeD", c.getLastName());
	}
	
	@Test
	void testSetLastName_MinBoundary() {
		Contact c = testContact();
		c.setLastName("D");
		assertEquals("D", c.getLastName());
	}
	
	// =====================
	// contact address tests
	// =====================
	
	@Test
	void testAddress_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "John", "Doe", null, "5551234567"));
	}
	
	@Test
	void testAddress_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "John", "Doe", "", "5551234567"));
	}
	
	@Test
	void testAddress_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "John", "Doe", "     ", "5551234567"));
	}
	
	@Test
	void testAddress_TooLong_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "John", "Doe", "420 Error Ave, Debugger City, CS 00320", "5551234567"));
	}
	
	@Test
	void testAddress_MaxBoundary() {
		Contact c = new Contact("abc123", "John", "Doe", "~~Exactly Thirty Characters ~~", "5551234567");
		assertEquals("~~Exactly Thirty Characters ~~", c.getAddress());
	}
	
	@Test
	void testAddress_MinBoundary() {
		Contact c = new Contact("abc123", "John", "Doe", "~", "5551234567");
		assertEquals("~", c.getAddress());
	}
	
	@Test
	void testSetAddress_Success() {
		Contact c = testContact();
		c.setAddress("222 Second Ave");
		assertEquals("222 Second Ave", c.getAddress());
	}
	
	@Test
	void testSetAddress_MultipleSuccessiveUpdates() {
		Contact c = testContact();
		c.setAddress("222 Second Ave");
		c.setAddress("333 Third Loop");
		assertEquals("333 Third Loop", c.getAddress());
	}
	
	@Test
	void testSetAddress_Null_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
	}
	
	@Test
	void testSetAddress_IsEmpty_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setAddress(""));
	}
	
	@Test
	void testSetAddress_WhitespaceOnly_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setAddress("     "));
	}
	
	@Test
	void testSetAddress_TooLong_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setAddress("420 Error Ave, Debugger City, CS 00320"));
	}
	
	@Test
	void testSetAddress_PreservedAfterNullUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
		assertEquals("101 Main St", c.getAddress());
	}
	
	@Test
	void testSetAddress_PreservedAfterIsEmptyUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setAddress(""));
		assertEquals("101 Main St", c.getAddress());
	}
	
	@Test
	void testSetAddress_PreservedAfterWhitespaceOnlyUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setAddress("     "));
		assertEquals("101 Main St", c.getAddress());
	}
	
	@Test
	void testSetAddress_PreservedAfterTooLongUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setAddress("420 Error Ave, Debugger City, CS 00320"));
		assertEquals("101 Main St", c.getAddress());
	}
	
	@Test
	void testSetAddress_MaxBoundary() {
		Contact c = testContact();
		c.setAddress("~~Exactly Thirty Characters ~~");
		assertEquals("~~Exactly Thirty Characters ~~", c.getAddress());
	}
	
	@Test
	void testSetAddress_MinBoundary() {
		Contact c = testContact();
		c.setAddress("~");
		assertEquals("~", c.getAddress());
	}
	
	// ===================
	// contact phone tests
	// ===================
	
	@Test
	void testPhone_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "John", "Doe", "101 Main St", null));
	}
	
	@Test
	void testPhone_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "John", "Doe", "101 Main St", ""));
	}
	
	@Test
	void testPhone_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "John", "Doe", "101 Main St", "     "));
	}
	
	@Test
	void testPhone_TooLong_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "John", "Doe", "101 Main St", "12345678901"));
	}
	
	@Test
	void testPhone_TooShort_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "John", "Doe", "101 Main St", "123456789"));
	}
	
	@Test
	void testPhone_ContainsLetters_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Contact("abc123", "John", "Doe", "101 Main St", "TenDigitss"));
	}
	
	@Test
	void testSetPhone_Success() {
		Contact c = testContact();
		c.setPhone("1234567890");
		assertEquals("1234567890", c.getPhone());
	}
	
	@Test
	void testSetPhone_MultipleSuccessiveUpdates() {
		Contact c = testContact();
		c.setPhone("1234567890");
		c.setPhone("0987654321");
		assertEquals("0987654321", c.getPhone());
	}
	
	@Test
	void testSetPhone_Null_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setPhone(null));
	}
	
	@Test
	void testSetPhone_IsEmpty_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setPhone(""));
	}
	
	@Test
	void testSetPhone_WhitespaceOnly_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setPhone("     "));
	}
	
	@Test
	void testSetPhone_TooLong_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setPhone("12345678901"));
	}
	
	@Test
	void testSetPhone_TooShort_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setPhone("123456789"));
	}
	
	@Test
	void testSetPhone_ContainsLetters_ThrowsException() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setPhone("TenDigitss"));
	}
	
	@Test
	void testPhone_PreservedAfterNullUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setPhone(null));
		assertEquals("5551234567", c.getPhone());
	}
	
	@Test
	void testPhone_PreservedAfterIsEmptyUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setPhone(""));
		assertEquals("5551234567", c.getPhone());
	}
	
	@Test
	void testPhone_PreservedAfterWhitespaceOnlyUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setPhone("     "));
		assertEquals("5551234567", c.getPhone());
	}
	
	@Test
	void testPhone_PreservedAfterTooLongUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setPhone("12345678901"));
		assertEquals("5551234567", c.getPhone());
	}
	
	@Test
	void testPhone_PreservedAfterTooShortUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setPhone("123456789"));
		assertEquals("5551234567", c.getPhone());
	}
	
	@Test
	void testPhone_PreservedAfterContainsLettersUpdate() {
		Contact c = testContact();
		assertThrows(IllegalArgumentException.class, () -> c.setPhone("TenDigitss"));
		assertEquals("5551234567", c.getPhone());
	}
}