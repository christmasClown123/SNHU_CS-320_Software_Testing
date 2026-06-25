package projectOne;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TaskTest {

	// isolate instance for testing
	private Task testTask() {
		return new Task("abc123", "Drink", "Plenty of water");
	}
	
	// success instance for taskId, name, and description
	@Test
	void testTaskCreationSuccess() {
		Task t = testTask();
		assertEquals("abc123", t.getTaskId());
		assertEquals("Drink", t.getTaskName());
		assertEquals("Plenty of water", t.getTaskDesc());
	}
	
	// ============
	// taskId tests
	// ============
	
	@Test
	void testTaskId_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Task(null, "Drink", "Plenty of water"));
	}
	
	@Test
	void testTaskId_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Task("", "Drink", "Plenty of water"));
	}
	
	@Test
	void testTaskId_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Task("     ", "Drink", "Plenty of water"));
	}
	
	@Test
	void testTaskId_TooLong_ThrowsArguments() {
		assertThrows(IllegalArgumentException.class, () -> new Task("12345678901", "Drink", "Plenty of water"));
	}
	
	@Test
	void testTaskId_MaxBoundary() {
		Task t = new Task("1234567890", "Drink", "Plenty of Water");
		assertEquals("1234567890", t.getTaskId());
	}
	
	@Test
	void testTaskId_MinBoundary() {
		Task t = new Task("1", "Drink", "Plenty of Water");
		assertEquals("1", t.getTaskId());
	}
	
	// ===============
	// task name tests
	// ===============
	
	@Test
	void testTaskName_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Task("abc123", null, "Plenty of water"));
	}
	
	@Test
	void testTaskName_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Task("abc123", "", "Plenty of water"));
	}
	
	@Test
	void testTaskName_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Task("abc123", "     ", "Plenty of water"));
	}

	@Test
	void testTaskName_TooLong_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Task("abc123", "123456789012345678901", "Plenty of water"));
	}
	
	@Test
	void testTaskName_MaxBoundary() {
		Task t = new Task("abc123", "12345678901234567890", "Plenty of water");
		assertEquals("12345678901234567890", t.getTaskName());
	}
	
	@Test
	void testTaskName_MinBoundary() {
		Task t = new Task("abc123", "1", "Plenty of water");
		assertEquals("1", t.getTaskName());
	}
	
	@Test
	void testSetTaskName_Success() {
		Task t = testTask();
		t.setTaskName("Hydrate");
		assertEquals("Hydrate", t.getTaskName());
	}
	
	@Test
	void testSetTaskName_MutipleSuccessiveUpdates() {
		Task t = testTask();
		t.setTaskName("Hydrate");
		t.setTaskName("Chug");
		assertEquals("Chug", t.getTaskName());
	}
	
	@Test
	void testSetTaskName_Null_ThrowsException() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskName(null));
	}
	
	@Test
	void testSetTaskName_IsEmpty_ThrowsException() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskName(""));
	}
	
	@Test
	void testSetTaskName_WhitespaceOnly_ThrowsException() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskName("     "));
	}
	
	@Test
	void testSetTaskName_TooLong_ThrowsException() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskName("123456789012345678901"));
	}
	
	@Test
	void testSetTaskName_PreservedAfterNullUpdate() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskName(null));
		assertEquals("Drink", t.getTaskName());
	}
	
	@Test
	void testSetTaskName_PreservedAfterIsEmptyUpdate() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskName(""));
		assertEquals("Drink", t.getTaskName());
	}
	
	@Test
	void testSetTaskName_PreservedAfterWhitespaceOnlyUpdate() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskName("     "));
		assertEquals("Drink", t.getTaskName());
	}
	
	@Test
	void testSetTaskName_PreservedAfterTooLongUpdate() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskName("123456789012345678901"));
		assertEquals("Drink", t.getTaskName());
	}
	
	@Test
	void testSetTaskName_MaxBoundary() {
		Task t = testTask();
		t.setTaskName("12345678901234567890");
		assertEquals("12345678901234567890", t.getTaskName());
	}
	
	@Test
	void testSetTaskName_MinBoundary() {
		Task t = testTask();
		t.setTaskName("1");
		assertEquals("1", t.getTaskName());
	}
	
	// ======================
	// task description tests
	// ======================
	
	@Test
	void testTaskDesc_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Task("abc123", "Drink", null));
	}
	
	@Test
	void testTaskDesc_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Task("abc123", "Drink", ""));
	}
	
	@Test
	void testTaskDesc_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Task("abc123", "Drink", "     "));
	}
	
	@Test 
	void testTaskDesc_TooLong_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Task("abc123", "Drink", "Plenty of water is vitally important to a healthy lifestyle."));
	}
	
	@Test
	void testTaskDesc_MaxBoundary() {
		Task t = new Task("abc123", "Drink", "Plenty of water is important for healthy lifestyle");
		assertEquals("Plenty of water is important for healthy lifestyle", t.getTaskDesc());
	}
	
	@Test
	void testTaskDesc_MinBoundary() {
		Task t = new Task("abc123", "Drink", "w");
		assertEquals("w", t.getTaskDesc());
	}
	
	@Test
	void testSetTaskDesc_Success() {
		Task t = testTask();
		t.setTaskDesc("Plenty of H2O");
		assertEquals("Plenty of H2O", t.getTaskDesc());
	}
	
	@Test
	void testSetTaskDesc_MultipleSuccessiveUpdates() {
		Task t = testTask();
		t.setTaskDesc("Plenty of H2O");
		t.setTaskDesc("Still, sparkling, or tap");
		assertEquals("Still, sparkling, or tap", t.getTaskDesc());
	}
	
	@Test
	void testSetTaskDesc_Null_ThrowsException() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskDesc(null));
	}
	
	@Test
	void testSetTaskDesc_IsEmpty_ThrowsException() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskDesc(""));
	}
	
	@Test
	void testSetTaskDesc_WhitespaceOnly_ThrowsException() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskDesc("     "));
	}
	
	@Test
	void testSetTaskDesc_TooLong_ThrowsException() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskDesc("Plenty of water is vitally important to a healthy lifestyle."));
	}
	
	@Test
	void testSetTaskDesc_PreservedAfterNullUpdate() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskDesc(null));
		assertEquals("Plenty of water", t.getTaskDesc());
	}
	
	@Test
	void testSetTaskDesc_PreservedAfterIsEmptyUpdate() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskDesc(""));
		assertEquals("Plenty of water", t.getTaskDesc());
	}
	
	@Test
	void testSetTaskDesc_PreservedAfterWhitespaceOnlyUpdate() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskDesc("     "));
		assertEquals("Plenty of water", t.getTaskDesc());
	}
	
	@Test
	void testSetTaskDesc_PreservedAfterTooLongUpdate() {
		Task t = testTask();
		assertThrows(IllegalArgumentException.class, () -> t.setTaskDesc("Plenty of water is vitally important to a healthy lifestyle."));
		assertEquals("Plenty of water", t.getTaskDesc());
	}
	
	@Test
	void testSetTaskDesc_MaxBoundary() {
		Task t = testTask();
		t.setTaskDesc("Plenty of water is important for healthy lifestyle");
		assertEquals("Plenty of water is important for healthy lifestyle", t.getTaskDesc());
	}
	
	@Test
	void testSetTaskDesc_MinBoundary() {
		Task t = testTask();
		t.setTaskDesc("1");
		assertEquals("1", t.getTaskDesc());
	}
}
