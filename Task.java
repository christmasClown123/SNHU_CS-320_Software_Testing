package projectOne;

public class Task {
	// immutable length for each field defined by the task class requirements
	private static final int TASK_ID_LENGTH = 10;
	private static final int TASK_NAME_LENGTH = 20;
	private static final int TASK_DESC_LENGTH = 50;
		
	// private variables for task object
	private final String taskId;
	private String taskName;
	private String taskDesc;
		
	// task constructor with object validation
	public Task(String taskId, String taskName, String taskDesc) {
		//object validation when creating a task
		validateTaskId(taskId);
		validateTaskName(taskName);
		validateTaskDesc(taskDesc);
			
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskDesc = taskDesc;
	}
		
	// object getters
	public String getTaskId() {
		return taskId;
	}
		
	public String getTaskName() {
		return taskName;
	}
		
	public String getTaskDesc() {
		return taskDesc;
	}
		
	// object setters
	public void setTaskName(String taskName) {
		validateTaskName(taskName);
		this.taskName = taskName;
	}
		
	public void setTaskDesc(String taskDesc) {
		validateTaskDesc(taskDesc);
		this.taskDesc = taskDesc;
	}
		
	// object validators
	private void validateTaskId(String taskId) {
		if (taskId == null || taskId.trim().isEmpty()) {
			throw new IllegalArgumentException("Task ID cannot be null or empty.");
		}
		if (taskId.length() > TASK_ID_LENGTH) {
			throw new IllegalArgumentException("Task ID cannot be longer than " + TASK_ID_LENGTH + " characters.");
		}
	}
		
	private void validateTaskName(String taskName) {
		if (taskName == null || taskName.trim().isEmpty()) {
			throw new IllegalArgumentException("Task name cannot be null or empty.");
		}
		if (taskName.length() > TASK_NAME_LENGTH) {
			throw new IllegalArgumentException("Task name cannot be longer than " + TASK_NAME_LENGTH + " characters.");
		}
	}
		
	private void validateTaskDesc(String taskDesc) {
		if (taskDesc == null || taskDesc.trim().isEmpty()) {
			throw new IllegalArgumentException("Task description cannot be null or empty.");
		}
		if (taskDesc.length() > TASK_DESC_LENGTH) {
			throw new IllegalArgumentException("Task description cannot be longer than " + TASK_DESC_LENGTH + " characters.");
		}
	}
}
