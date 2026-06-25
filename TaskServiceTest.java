package projectOne;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskServiceTest {

	// private instance for testing
	private TaskService service;
	
	@BeforeEach
	void setUp() {
		service = new TaskService();
	}
	
	// test case
	private Task testTask(String id) {
		return new Task(id, "Drink", "Plenty of water");
	}

	// =============
	// addTask tests
	// =============
	
	@Test
	void testAddTask_Success() {
		Task t = testTask("abc123");
		service.addTask(t);
		assertNotNull(service.getTask("abc123"));
		assertEquals("Drink", service.getTask("abc123").getTaskName());
	}
	
	@Test
	void testAddTask_MultipleSuccessiveUpdates() {
		service.addTask(testTask("abc123"));
		service.addTask(testTask("abc234"));
		service.addTask(testTask("abc345"));
		
		assertNotNull(service.getTask("abc123"));
		assertNotNull(service.getTask("abc234"));
		assertNotNull(service.getTask("abc345"));
	}
	
	@Test
	void testAddTask_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.addTask(testTask(null)));
	}
	
	@Test
	void testAddTask_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.addTask(testTask("")));
	}
	
	@Test
	void testAddTask_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.addTask(testTask("     ")));
	}
	
	@Test
	void testAddTask_DuplicateId_ThrowsException() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.addTask(testTask("abc123")));
	}
	
	// ================
	// deleteTask tests
	// ================
	
	@Test
	void testDeleteTask_Success() {
		service.addTask(testTask("abc123"));
		service.deleteTask("abc123");
		assertNull(service.getTask("abc123"));
	}
	
	@Test
	void testDeleteTask_NonExistentId_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteTask("BLANK"));
	}
	
	@Test
	void testDeleteTask_Null_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteTask(null));
	}

	@Test
	void testDeleteTask_IsEmpty_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteTask(""));
	}
	
	@Test
	void testDeleteTask_WhitespaceOnly_ThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteTask("     "));
	}
	
	@Test
	void testDeleteTask_DoubleNegative_ThrowsException() {
		service.addTask(testTask("abc123"));
		service.deleteTask("abc123");
		assertThrows(IllegalArgumentException.class, () -> service.deleteTask("abc123"));
	}

	@Test
	void testDeleteTask_DoesNotAffectOtherTasks() {
		service.addTask(testTask("abc123"));
		service.addTask(testTask("123abc"));
		service.deleteTask("abc123");
		assertNotNull(service.getTask("123abc"));
		assertNull(service.getTask("abc123"));
	}
	
	// ====================
	// updateTaskName tests
	// ====================
	
	@Test
	void testUpdateTaskName_Success() {
		service.addTask(testTask("abc123"));
		service.updateTaskName("abc123", "Hydrate");
		assertEquals("Hydrate", service.getTask("abc123").getTaskName());
	}
	
	@Test
	void testUpdateTaskName_MultipleSuccessiveUpdates() {
		service.addTask(testTask("abc123"));
		service.updateTaskName("abc123", "Chug");
		service.updateTaskName("abc123", "Swig");
		assertEquals("Swig", service.getTask("abc123").getTaskName());
	}
	
	@Test
	void testUpdateTaskName_NonExistentId_ThrowsException() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskName("BLANK", "Hydrate"));
	}
	
	@Test
	void testUpdateTaskName_Null_ThrowsException() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskName("abc123", null));
	}
	
	@Test
	void testUpdateTaskName_IsEmpty_ThrowsException() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskName("abc123", ""));
	}
	
	@Test
	void testUpdateTaskName_WhitespaceOnly_ThrowsException() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskName("abc123", "     "));
	}
	
	@Test
	void testUpdateTaskName_TooLong_ThrowsException() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskName("abc123", "123456789012345678901"));
	}
	
	@Test
	void testUpdateTaskName_PreservedAfterNullUpdate() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskName("abc123", null));
		assertEquals("Drink", service.getTask("abc123").getTaskName());
	}
	
	@Test
	void testUpdateTaskName_PreservedAfterIsEmptyUpdate() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskName("abc123", ""));
		assertEquals("Drink", service.getTask("abc123").getTaskName());
	}
	
	@Test
	void testUpdateTaskName_PreservedAfterWhitespaceOnlyUpdate() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskName("abc123", "     "));
		assertEquals("Drink", service.getTask("abc123").getTaskName());
	}
	
	@Test
	void testUpdateTaskName_PreservedAfterTooLongUpdate() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskName("abc123", "123456789012345678901"));
		assertEquals("Drink", service.getTask("abc123").getTaskName());
	}
	
	// ===========================
	// updateTaskDescription tests
	// ===========================
	
	@Test
	void testUpdateTaskDescription_Success() {
		service.addTask(testTask("abc123"));
		service.updateTaskDescription("abc123", "Plenty of H2O");
		assertEquals("Plenty of H2O", service.getTask("abc123").getTaskDesc());
	}
	
	@Test
	void testUpdateTaskDescription_MultipleSuccessiveUpdates() {
		service.addTask(testTask("abc123"));
		service.updateTaskDescription("abc123", "Plenty of H2O");
		service.updateTaskDescription("abc123", "A lot of water");
		assertEquals("A lot of water", service.getTask("abc123").getTaskDesc());
	}
	
	@Test
	void testUpdateTaskDescription_NonExistentId_ThrowsException() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskDescription("BLANK", "Plenty of H2O"));
	}
	
	@Test
	void testUpdateTaskDescription_Null_ThrowsException() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskDescription("abc123", null));
	}
	
	@Test
	void testUpdateTaskDescription_IsEmpty_ThrowsException() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskDescription("abc123", ""));
	}
	
	@Test
	void testUpdateTaskDescription_WhitespaceOnly_ThrowsException() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskDescription("abc123", "      "));
	}
	
	@Test
	void testUpdateTaskDescription_TooLong_ThrowsException() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskDescription("abc123", "Plenty of water is vitally important to a healthy lifestyle."));
	}
	
	@Test
	void testUpdateTaskDescription_PreservedAfterNullUpdate() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskDescription("abc123", null));
		assertEquals("Plenty of water", service.getTask("abc123").getTaskDesc());
	}

	@Test
	void testUpdateTaskDescription_PreservedAfterIsEmptyUpdate() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskDescription("abc123", ""));
		assertEquals("Plenty of water", service.getTask("abc123").getTaskDesc());
	}
	
	@Test
	void testUpdateTaskDescription_PreservedAfterWhitespaceOnlyUpdate() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskDescription("abc123", "     "));
		assertEquals("Plenty of water", service.getTask("abc123").getTaskDesc());
	}
	
	@Test
	void testUpdateTaskDescription_PreservedAfterTooLongUpdate() {
		service.addTask(testTask("abc123"));
		assertThrows(IllegalArgumentException.class, () -> service.updateTaskDescription("abc123", "Plenty of water is vitally important to a healthy lifestyle."));
		assertEquals("Plenty of water", service.getTask("abc123").getTaskDesc());
	}
}
