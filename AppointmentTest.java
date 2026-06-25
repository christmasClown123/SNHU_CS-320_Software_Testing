package projectOne;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Date;

class AppointmentTest {

	// instance of future date always safe for testing
	private Date futureDate = new Date(System.currentTimeMillis() + 86400000);
	
	// isolate instance for testing
	private Appointment testAppt() {
		return new Appointment("abc123", futureDate, "future appointment");
	}

	// success instance for apptId, apptDate, and apptDesc
	@Test
	void testAppointmentCreationSuccess() {
		Appointment a = testAppt();
		assertEquals("abc123", a.getApptId());
		assertEquals(futureDate, a.getApptDate());
		assertEquals("future appointment", a.getApptDesc());
	}
	
	// ============
	// apptId tests
	// ============
	
	@Test
	void testApptId_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Appointment(null, futureDate, "future appointment"));
	}
		
	@Test
	void testApptId_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Appointment("", futureDate, "future appointment"));
	}
	
	@Test
	void testApptId_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Appointment("     ", futureDate, "future appointment"));
	}
		
	@Test
	void testApptId_TooLong_ThrowsArguments() {
		assertThrows(IllegalArgumentException.class, () -> new Appointment("12345678901", futureDate, "future appointment"));
	}
		
	@Test
	void testApptId_MaxBoundary() {
		Appointment a = new Appointment("1234567890", futureDate, "future appointment");
		assertEquals("1234567890", a.getApptId());
	}
	
	@Test
	void testApptId_MinBoundary() {
		Appointment a = new Appointment("1", futureDate, "future appointment");
		assertEquals("1", a.getApptId());
	}
	
	// ======================
	// appointment date tests
	// ======================
	
	@Test
	void testApptDate_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Appointment("abc123", null, "future appointment"));
	}
	
	@Test
	void testApptDate_PastDate_ThrowsException() {
		Date pastDate = new Date(System.currentTimeMillis() - 86400000);
		assertThrows(IllegalArgumentException.class, () -> new Appointment("abc123", pastDate, "future appointment"));
	}
	
	@Test
	void testSetApptDate_Success() {
		Appointment a = testAppt();
		Date testDate = new Date(System.currentTimeMillis() + 86400000);
		a.setApptDate(testDate);
		assertEquals(testDate, a.getApptDate());
	}
	
	@Test
	void testSetApptDate_MultipleSuccessiveUpdates() {
		Appointment a = testAppt();
		Date testDate = new Date(System.currentTimeMillis() + 86400000);
		a.setApptDate(testDate);
		Date futureTestDate = new Date(System.currentTimeMillis() + 86400000 * 2);
		a.setApptDate(futureTestDate);
		assertEquals(futureTestDate, a.getApptDate());
	}
	
	@Test
	void testSetApptDate_Null_ThrowsException() {
		Appointment a = testAppt();
		assertThrows(IllegalArgumentException.class, () -> a.setApptDate(null));
	}
	
	@Test
	void testSetApptDate_PastDate_ThrowsException() {
		Appointment a = testAppt();
		Date pastDate = new Date(System.currentTimeMillis() - 86400000);
		assertThrows(IllegalArgumentException.class, () -> a.setApptDate(pastDate));
	}
	
	@Test
	void testSetApptDate_PreservedAfterNullUpdate() {
		Appointment a = testAppt();
		assertThrows(IllegalArgumentException.class, () -> a.setApptDate(null));
		assertEquals(futureDate, a.getApptDate());
	}
	
	@Test
	void testSetApptDate_PreservedAfterPastDateUpdate() {
		Appointment a = testAppt();
		Date pastDate = new Date(System.currentTimeMillis() - 86400000);
		assertThrows(IllegalArgumentException.class, () -> new Appointment("abc123", pastDate, "future appointment"));
		assertEquals(futureDate, a.getApptDate());
	}
	
	// =============================
	// appointment description tests
	// =============================
		
	@Test
	void testApptDesc_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Appointment("abc123", futureDate, null));
	}
		
	@Test
	void testApptDesc_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Appointment("abc123", futureDate, ""));
	}
	
	@Test
	void testApptDesc_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Appointment("abc123", futureDate, "     "));
	}
		
	@Test 
	void testApptDesc_TooLong_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Appointment("abc123", futureDate, "This appointment being generated is well over the character limit of 50."));
	}
		
	@Test
	void testApptDesc_MaxBoundary() {
		Appointment a = new Appointment("abc123", futureDate, "An appointment description of exactly fifty chars.");
		assertEquals("An appointment description of exactly fifty chars.", a.getApptDesc());
	}
	
	@Test
	void testApptDesc_MinBoundary() {
		Appointment a = new Appointment("abc123", futureDate, "1");
		assertEquals("1", a.getApptDesc());
	}
		
	@Test
	void testSetApptDesc_Success() {
		Appointment a = testAppt();
		a.setApptDesc("a new future appointment");
		assertEquals("a new future appointment", a.getApptDesc());
	}
	
	@Test
	void testSetApptDesc_MultipleSuccessiveUpdates() {
		Appointment a = testAppt();
		a.setApptDesc("a new future appointment");
		a.setApptDesc("another appointment");
		assertEquals("another appointment", a.getApptDesc());
	}
		
	@Test
	void testSetApptDesc_Null_ThrowsException() {
		Appointment a = testAppt();
		assertThrows(IllegalArgumentException.class, () -> a.setApptDesc(null));
	}
		
	@Test
	void testSetApptDesc_IsEmpty_ThrowsException() {
		Appointment a = testAppt();
		assertThrows(IllegalArgumentException.class, () -> a.setApptDesc(""));
	}
	
	@Test
	void testSetApptDesc_WhitespaceOnly_ThrowsException() {
		Appointment a = testAppt();
		assertThrows(IllegalArgumentException.class, () -> a.setApptDesc("     "));
	}
		
	@Test
	void testSetApptDesc_TooLong_ThrowsException() {
		Appointment a = testAppt();
		assertThrows(IllegalArgumentException.class, () -> a.setApptDesc("This appointment being generated is well over the character limit of 50."));
	}
	
	@Test
	void testSetApptDesc_PreservedAfterNullUpdate() {
		Appointment a = testAppt();
		assertThrows(IllegalArgumentException.class, () -> a.setApptDesc(null));
		assertEquals("future appointment", a.getApptDesc());
	}
	
	@Test
	void testSetApptDesc_PreservedAfterIsEmptyUpdate() {
		Appointment a = testAppt();
		assertThrows(IllegalArgumentException.class, () -> a.setApptDesc(""));
		assertEquals("future appointment", a.getApptDesc());
	}
	
	@Test
	void testSetApptDesc_PreservedAfterWhitespaceOnlyUpdate() {
		Appointment a = testAppt();
		assertThrows(IllegalArgumentException.class, () -> a.setApptDesc("     "));
		assertEquals("future appointment", a.getApptDesc());
	}
	
	@Test
	void testSetApptDesc_PreservedAfterTooLongUpdate() {
		Appointment a = testAppt();
		assertThrows(IllegalArgumentException.class, () -> a.setApptDesc("This appointment being generated is well over the character limit of 50."));
		assertEquals("future appointment", a.getApptDesc());
	}
	
	@Test
	void testSetApptDesc_MaxBoundary() {
		Appointment a = testAppt();
		a.setApptDesc("An appointment description of exactly fifty chars.");
		assertEquals("An appointment description of exactly fifty chars.", a.getApptDesc());
	}
	
	@Test
	void testSetApptDesc_MinBoundary() {
		Appointment a = testAppt();
		a.setApptDesc("w");
		assertEquals("w", a.getApptDesc());
	}
}
