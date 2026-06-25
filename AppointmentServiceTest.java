package projectOne;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

class AppointmentServiceTest {
	
	// private test instance
	private AppointmentService service;
	
	// instance of future date always safe for testing
	private Date testDate = new Date(System.currentTimeMillis() + 60000);
	private Date futureDate = new Date(System.currentTimeMillis() + 86400000);

	@BeforeEach
	void setUp() {
		service = new AppointmentService();
	}

	// test case
	private Appointment testAppt(String id) {
		return new Appointment(id, testDate, "future appointment");
	}
	
	// =============
	// addAppt tests
	// =============
	
	@Test
	void testAddAppt_Success() {
		Appointment a = testAppt("abc123");
		service.addAppt(a);
		assertNotNull(service.getAppt("abc123"));
		assertEquals("future appointment", service.getAppt("abc123").getApptDesc());
	}
	
	@Test
	void testAddAppt_MultipleSuccessiveUpdates() {
		service.addAppt(testAppt("abc123"));
		service.addAppt(testAppt("abc234"));
		service.addAppt(testAppt("abc345"));
		
		assertNotNull(service.getAppt("abc123"));
		assertNotNull(service.getAppt("abc234"));
		assertNotNull(service.getAppt("abc345"));
	}
		
	@Test
	void testAddAppt_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.addAppt(testAppt(null)));
	}
	
	@Test
	void testAddAppt_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.addAppt(testAppt("")));
	}
	
	@Test
	void testAddAppt_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.addAppt(testAppt("     ")));
	}
		
	@Test
	void testAddAppt_DuplicateId_ThrowsException() {
		service.addAppt(testAppt("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.addAppt(testAppt("abc123")));
	}
	
	// ================
	// deleteAppt tests
	// ================
	
	@Test
	void testDeleteAppt_Success() {
		service.addAppt(testAppt("abc123"));
		service.deleteAppt("abc123");
		assertNull(service.getAppt("abc123"));
	}
		
	@Test
	void testDeleteAppt_NonExistentId_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteAppt("BLANK"));
	}
		
	@Test
	void testDeleteAppt_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteAppt(null));
	}

	@Test
	void testDeleteAppt_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteAppt(""));
	}
	
	@Test
	void testDeleteAppt_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteAppt("     "));
	}
	
	@Test
	void testDeleteAppt_DoubleNegative_ThrowsException() {
		service.addAppt(testAppt("abc123"));
		service.deleteAppt("abc123");
		assertThrows(IllegalArgumentException.class, () -> service.deleteAppt("abc123"));
	}

	@Test
	void testDeleteAppt_DoesNotAffectOtherTasks() {
		service.addAppt(testAppt("abc123"));
		service.addAppt(testAppt("123abc"));
		service.deleteAppt("abc123");
		assertNotNull(service.getAppt("123abc"));
		assertNull(service.getAppt("abc123"));
	}
	
	// ====================
	// updateApptDate tests
	// ====================
	
	@Test
	void testUpdateApptDate_Success() {
		service.addAppt(testAppt("abc123"));
		service.updateApptDate("abc123", futureDate);
		assertEquals(futureDate, service.getAppt("abc123").getApptDate());
	}
	
	@Test
	void testUpdateApptDate_MultipleSuccessiveUpdates() {
		service.addAppt(testAppt("abc123"));
		service.updateApptDate("abc123", futureDate);
		Date testFutureDate = new Date(System.currentTimeMillis() + 86400000 * 2);
		service.updateApptDate("abc123", testFutureDate);
		assertEquals(testFutureDate, service.getAppt("abc123").getApptDate());
	}
	
	@Test
	void testUpdateApptDate_NonExistentId_ThrowsException() {
		service.addAppt(testAppt("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateApptDate("BLANK", futureDate));
	}
	
	@Test
	void testUpdateApptDate_Null_ThrowsException() {
		service.addAppt(testAppt("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateApptDate("abc123", null));
	}
	
	@Test
	void testUpdateApptDate_PastDate_ThrowsException() {
		service.addAppt(testAppt("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateApptDate("abc123", new Date(System.currentTimeMillis() - 8640000)));
	}
	
	@Test
	void testUpdateApptDate_PreservedAfterNullUpdate() {
		service.addAppt(testAppt("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateApptDate("abc123", null));
		assertEquals(testDate, service.getAppt("abc123").getApptDate());
	}
	
	@Test
	void testUpdateApptDate_PreservedAfterPastDateUpdate() {
		service.addAppt(testAppt("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateApptDate("abc123", new Date(System.currentTimeMillis() - 8640000)));
		assertEquals(testDate, service.getAppt("abc123").getApptDate());
	}

	// ====================
	// updateApptDesc tests
	// ====================
	
	@Test
	void testUpdateApptDesc_Success() {
		service.addAppt(testAppt("abc123"));
		service.updateApptDesc("abc123", "another future appointment");
		assertEquals("another future appointment", service.getAppt("abc123").getApptDesc());
	}
	
	@Test
	void testUpdateApptDesc_MultipleSuccessiveUpdates() {
		service.addAppt(testAppt("abc123"));
		service.updateApptDesc("abc123", "another future appointment");
		service.updateApptDesc("abc123", "appointment from the future");
		assertEquals("appointment from the future", service.getAppt("abc123").getApptDesc());
	}
		
	@Test
	void testUpdateApptDesc_NonExistentId_ThrowsException() {
		service.addAppt(testAppt("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateApptDesc("BLANK", "another future appointment"));
	}
		
	@Test
	void testUpdateApptDesc_Null_ThrowsException() {
		service.addAppt(testAppt("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateApptDesc("abc123", null));
	}
		
	@Test
	void testUpdateApptDesc_IsEmpty_ThrowsException() {
		service.addAppt(testAppt("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateApptDesc("abc123", ""));
	}
	
	@Test
	void testUpdateApptDesc_WhitespaceOnly_ThrowsException() {
		service.addAppt(testAppt("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateApptDesc("abc123", "     "));
	}
		
	@Test
	void testUpdateApptDesc_TooLong_ThrowsException() {
		service.addAppt(testAppt("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateApptDesc("abc123", "This appointment being generated is well over the character limit of 50."));
	}
	
	@Test
	void testUpdateApptDesc_PreservedAfterNullUpdate() {
		service.addAppt(testAppt("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateApptDesc("abc123", null));
		assertEquals("future appointment", service.getAppt("abc123").getApptDesc());
	}
	
	@Test
	void testUpdateApptDesc_PreservedAfterIsEmptyUpdate() {
		service.addAppt(testAppt("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateApptDesc("abc123", ""));
		assertEquals("future appointment", service.getAppt("abc123").getApptDesc());
	}
	
	@Test
	void testUpdateApptDesc_PreservedAfterWhitespaceOnlyUpdate() {
		service.addAppt(testAppt("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateApptDesc("abc123", "     "));
		assertEquals("future appointment", service.getAppt("abc123").getApptDesc());
	}
	
	@Test
	void testUpdateApptDesc_PreservedAfterTooLongUpdate() {
		service.addAppt(testAppt("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateApptDesc("abc123", "This appointment being generated is well over the character limit of 50."));
		assertEquals("future appointment", service.getAppt("abc123").getApptDesc());
	}
}
