package projectOne;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactServiceTest {
	
	// private instance for testing
	private ContactService service;
	
	@BeforeEach
	void setUp() {
		service = new ContactService();
	}
	
	// test case
	private Contact testContact(String id) {
		return new Contact(id, "John", "Doe", "101 Main St", "5551234567");
	}
	
	// ================
	// addContact tests
	// ================
	
	@Test
	void testAddContact_Success() {
		Contact c = testContact("abc123");
		service.addContact(c);
		assertNotNull(service.getContact("abc123"));
		assertEquals("John", service.getContact("abc123").getFirstName());
	}
	
	@Test
	void testAddContact_MultipleSuccessiveUpdates() {
		service.addContact(testContact("abc123"));
		service.addContact(testContact("abc234"));
		service.addContact(testContact("abc345"));
		
		assertNotNull(service.getContact("abc123"));
		assertNotNull(service.getContact("abc234"));
		assertNotNull(service.getContact("abc345"));
	}
	
	@Test
	void testAddContact_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.addContact(testContact(null)));
	}
	
	@Test
	void testAddContact_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.addContact(testContact("")));
	}
	
	@Test
	void testAddContact_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.addContact(testContact("     ")));
	}
	
	@Test
	void testAddContact_DuplicateId_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.addContact(testContact("abc123")));
	}
	
	// ===================
	// deleteContact tests
	// ===================
	
	@Test
	void testDeleteContact_Success() {
		service.addContact(testContact("abc123"));
		service.deleteContact("abc123");
		assertNull(service.getContact("abc123"));
	}
	
	@Test
	void testDeleteContact_NonExistentId_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteContact("BLANK"));
	}
	
	@Test
	void testDeleteContact_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteContact(null));
	}
	
	@Test
	void testDeleteContact_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteContact(""));
	}
	
	@Test
	void testDeleteContact_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteContact("     "));
	}
	
	@Test
	void testDeleteContact_DoubleNegative_ThrowsException() {
		service.addContact(testContact("abc123"));
		service.deleteContact("abc123");
		assertThrows(IllegalArgumentException.class, () -> service.deleteContact("abc123"));
	}
	
	@Test
	void testDeleteContact_DoesNotAffectOtherTasks() {
		service.addContact(testContact("abc123"));
		service.addContact(testContact("123abc"));
		service.deleteContact("abc123");
		assertNotNull(service.getContact("123abc"));
		assertNull(service.getContact("abc123"));
	}
	
	// =====================
	// updateFirstName tests
	// =====================
	
	@Test
	void testUpdateFirstName_Success() {
		service.addContact(testContact("abc123"));
		service.updateFirstName("abc123", "Jon");
		assertEquals("Jon", service.getContact("abc123").getFirstName());
	}
	
	@Test
	void testUpdateFirstName_MultipleSuccessiveUpdates() {
		service.addContact(testContact("abc123"));
		service.updateFirstName("abc123", "Jon");
		service.updateFirstName("abc123", "Johnny");
		assertEquals("Johnny", service.getContact("abc123").getFirstName());
	}
	
	@Test
	void testUpdateFirstName_NonExistentId_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("BLANK", "Jon"));
	}
	
	@Test
	void testUpdateFirstName_Null_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("abc123", null));
	}
	
	@Test
	void testUpdateFirstName_IsEmpty_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("abc123", ""));
	}
	
	@Test
	void testUpdateFirstName_WhitespaceOnly_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("abc123", "     "));
	}
	
	@Test
	void testUpdateFirstName_TooLong_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("abc123", "12345678901"));
	}
	
	@Test
	void testUpdateFirstName_PreservedAfterNullUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("abc123", null));
		assertEquals("John", service.getContact("abc123").getFirstName());
	}
	
	@Test
	void testUpdateFirstName_PreservedAfterIsEmptyUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("abc123", ""));
		assertEquals("John", service.getContact("abc123").getFirstName());
	}
	
	@Test
	void testUpdateFirstName_PreservedAfterWhitespaceOnlyUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("abc123", "     "));
		assertEquals("John", service.getContact("abc123").getFirstName());
	}
	
	@Test
	void testUpdateFirstName_PreservedAfterTooLongUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("abc123", "12345678901"));
		assertEquals("John", service.getContact("abc123").getFirstName());
	}
	
	// ====================
	// updateLastName tests
	// ====================
	
	@Test
	void testUpdateLastName_Success() {
		service.addContact(testContact("abc123"));
		service.updateLastName("abc123", "Doh");
		assertEquals("Doh", service.getContact("abc123").getLastName());
	}
	
	@Test
	void testUpdateLasttName_MultipleSuccessiveUpdates() {
		service.addContact(testContact("abc123"));
		service.updateLastName("abc123", "Doh");
		service.updateLastName("abc123", "Dough");
		assertEquals("Dough", service.getContact("abc123").getLastName());
	}
	
	@Test
	void testUpdateLastName_NonExistentId_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateLastName("BLANK", "Doh"));
	}
	
	@Test
	void testUpdateLastName_Null_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateLastName("abc123", null));
	}
	
	@Test
	void testUpdateLastName_IsEmpty_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateLastName("abc123", ""));
	}
	
	@Test
	void testUpdateLastName_WhitespaceOnly_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateLastName("abc123", "     "));
	}
	
	@Test
	void testUpdateLastName_TooLong_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateLastName("abc123", "12345678901"));
	}
	
	@Test
	void testUpdateLastName_PreservedAfterNullUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateLastName("abc123", null));
		assertEquals("Doe", service.getContact("abc123").getLastName());
	}
	
	@Test
	void testUpdateLastName_PreservedAfterIsEmptyUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateLastName("abc123", ""));
		assertEquals("Doe", service.getContact("abc123").getLastName());
	}
	
	@Test
	void testUpdateLastName_PreservedAfterWhitespaceOnlyUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateLastName("abc123", "     "));
		assertEquals("Doe", service.getContact("abc123").getLastName());
	}
	
	@Test
	void testUpdateLastName_PreservedAfterTooLongUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateLastName("abc123", "12345678901"));
		assertEquals("Doe", service.getContact("abc123").getLastName());
	}
	
	// ===================
	// updateAddress tests
	// ===================
	
	@Test
	void testUpdateAddress_Success() {
		service.addContact(testContact("abc123"));
		service.updateAddress("abc123", "222 Second Ave");
		assertEquals("222 Second Ave", service.getContact("abc123").getAddress());
	}
	
	@Test
	void testUpdateAddress_MultipleSuccessiveUpdates() {
		service.addContact(testContact("abc123"));
		service.updateAddress("abc123", "222 Second Ave");
		service.updateAddress("abc123", "333 Third Loop");
		assertEquals("333 Third Loop", service.getContact("abc123").getAddress());
	}
	
	@Test
	void testUpdateAddress_NonExistentId_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateAddress("BLANK", "222 Second Ave"));
	}
	
	@Test
	void testUpdateAddress_Null_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateAddress("abc123", null));
	}
	
	@Test
	void testUpdateAddress_IsEmpty_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateAddress("abc123", ""));
	}
	
	@Test
	void testUpdateAddress_WhitespaceOnly_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateAddress("abc123", "     "));
	}
	
	@Test
	void testUpdateAddress_TooLong_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateAddress("abc123", "1234567890123456789012345678901"));
	}
	
	@Test
	void testUpdateAddress_PreservedAfterNullUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateAddress("abc123", null));
		assertEquals("101 Main St", service.getContact("abc123").getAddress());
	}
	
	@Test
	void testUpdateAddress_PreservedAfterIsEmptyUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateAddress("abc123", ""));
		assertEquals("101 Main St", service.getContact("abc123").getAddress());
	}
	
	@Test
	void testUpdateAddress_PreservedAfterWhitespaceOnlyUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateAddress("abc123", "     "));
		assertEquals("101 Main St", service.getContact("abc123").getAddress());
	}
	
	@Test
	void testUpdateAddress_PreservedAfterTooLongUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateAddress("abc123", "1234567890123456789012345678901"));
		assertEquals("101 Main St", service.getContact("abc123").getAddress());
	}
	
	// =================
	// updatePhone tests
	// =================
	
	@Test
	void testPhone_Success() {
		service.addContact(testContact("abc123"));
		service.updatePhone("abc123", "1234567890");
		assertEquals("1234567890", service.getContact("abc123").getPhone());
	}
	
	@Test
	void testUpdatePhone_MultipleSuccessiveUpdates() {
		service.addContact(testContact("abc123"));
		service.updatePhone("abc123", "1234567890");
		service.updatePhone("abc123", "0987654321");
		assertEquals("0987654321", service.getContact("abc123").getPhone());
	}
	
	@Test
	void testUpdatePhone_NonExistentId_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updatePhone("BLANK", "1234567890"));
	}
	
	@Test
	void testUpdatePhone_Null_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updatePhone("abc123", null));
	}
	
	@Test
	void testUpdatePhone_IsEmpty_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updatePhone("abc123", ""));
	}
	
	@Test
	void testUpdatePhone_WhitespaceOnly_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updatePhone("abc123", "     "));
	}
	
	@Test
	void testUpdatePhone_TooLong_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updatePhone("abc123", "12345678901"));
	}
	
	@Test
	void testUpdatePhone_TooShort_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updatePhone("abc123", "123456789"));
	}
	
	@Test
	void testUpdatePhone_ContainsLetters_ThrowsException() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updatePhone("abc123", "TenDigitss"));
	}
	
	@Test
	void testUpdatePhone_PreservedAfterNullUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updatePhone("abc123", null));
		assertEquals("5551234567", service.getContact("abc123").getPhone());
	}
	
	@Test
	void testUpdatePhone_PreservedAfterIsEmptyUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updatePhone("abc123", ""));
		assertEquals("5551234567", service.getContact("abc123").getPhone());
	}
	
	@Test
	void testUpdatePhone_PreservedAfterWhitespaceOnlyUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updatePhone("abc123", "     "));
		assertEquals("5551234567", service.getContact("abc123").getPhone());
	}
	
	@Test
	void testUpdatePhone_PreservedAfterTooLongUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updatePhone("abc123", "12345678901"));
		assertEquals("5551234567", service.getContact("abc123").getPhone());
	}
	
	@Test
	void testUpdatePhone_PreservedAfterTooShortUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updatePhone("abc123", "123456789"));
		assertEquals("5551234567", service.getContact("abc123").getPhone());
	}
	
	@Test
	void testUpdatePhone_PreservedAfterContainsLettersUpdate() {
		service.addContact(testContact("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updatePhone("abc123", "TenDigitss"));
		assertEquals("5551234567", service.getContact("abc123").getPhone());
	}
}
