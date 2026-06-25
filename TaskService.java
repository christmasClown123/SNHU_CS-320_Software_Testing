package projectOne;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class TaskService {
	// creates a map for tasks that uses id as the key for information
	private Map<String, Task> taskTracker;
	
	// TaskService constructor that initializes a new map
	public TaskService() {
		this.taskTracker = new HashMap<>();
	}
	
	// add a new task with a unique id
	public void addTask(Task task) {
		// checks for null
		if (task == null) {
			throw new IllegalArgumentException("Cannot add a null task.");
		}
		// checks for duplicate
		if (taskTracker.containsKey(task.getTaskId())) {
			throw new IllegalArgumentException("Task with ID " + task.getTaskId() + " already exists.");
		}
		taskTracker.put(task.getTaskId(), task);
	}
	
	// deletes a task from the listing based on id
	public void deleteTask(String taskId) {
		// checks for null or non-existent
		if (!taskTracker.containsKey(taskId)) {
			throw new IllegalArgumentException("Cannot delete null or non-existent task.");
		}
		taskTracker.remove(taskId);
	}
	
	// allows user to update task name based on pulling id as key from listing
	public void updateTaskName(String taskId, String newName) {
		Task task = taskTracker.get(taskId);
		// checks for null or non-existent
		if (task == null) {
			throw new IllegalArgumentException("Cannot update a null or non-existent task.");
		}
		task.setTaskName(newName);
	}
	
	// allows user to update task description based on pulling id as key from listing
	public void updateTaskDescription(String taskId, String newDescription) {
		Task task = taskTracker.get(taskId);
		// checks for null
		if (task == null) {
			throw new IllegalArgumentException("Cannot update a null or non-existent task.");
		}
		task.setTaskDesc(newDescription);
	}
	
	// getter for task id in taskService
	public Task getTask(String taskId) {
		return taskTracker.get(taskId);
	}
	
	// retrieves user-input to create a new task
	public void addTaskHelper(Scanner scanner) {
		try {
			System.out.print("Enter Task ID (Max 10 characters): ");
			String id = scanner.nextLine();
			
			System.out.print("Enter a Task name (Max 20 characters): ");
			String name = scanner.nextLine();
			
			System.out.print("Enter a Task description (Max 50 characters): ");
			String description = scanner.nextLine();
			
			Task task = new Task(id, name, description);
			addTask(task);
			System.out.println("Task has been added successfully!");
		} catch (IllegalArgumentException e) {
			System.out.println("Error adding task: " + e.getMessage());
		}

	}
	
	// retrieves user-input to delete a task
	public void deleteTaskHelper(Scanner scanner) {
		System.out.print("Enter Task ID to delete: ");
		String taskId = scanner.nextLine();
		
		try {
			deleteTask(taskId);
			System.out.println("Task has been deleted successfully!");
		} catch (IllegalArgumentException e) {
			System.out.println("Error deleting task: " + e.getMessage());
		}
	}
	
	// retrieves user-input to update a task
	public void updateTaskHelper(Scanner scanner) {
		System.out.print("Enter Task ID to update: ");
		String taskId = scanner.nextLine();
		
		if (getTask(taskId) == null) {
			System.out.println("Task is not found in tracker.");
			return;
		}
		
		try {
			System.out.println("\nChoose an update option:");
			System.out.println("1. Update Task name");
			System.out.println("2. Update Task description");
			System.out.println("3. Update Task name & description");
			
			int updateChoice = scanner.nextInt();
			scanner.nextLine();
			
			switch (updateChoice) {
			case 1:
				System.out.print("Enter new Task name (Max 20 character): ");
				String name = scanner.nextLine();
				updateTaskName(taskId, name);
				System.out.println("Task name has been successfully updated!");
				break;
			case 2:
				System.out.print("Enter new Task description (Max 50 characters): ");
				String description = scanner.nextLine();
				updateTaskDescription(taskId, description);
				System.out.println("Task description has been successfully update!");
				break;
			case 3:
				System.out.print("Enter new Task name (Max 20 characters): ");
				String newName = scanner.nextLine();
				System.out.print("Enter new Task description (Max 50 characters): ");
				String newDesc = scanner.nextLine();
				updateTaskName(taskId, newName);
				updateTaskDescription(taskId, newDesc);
				System.out.println("Task has been successfully updated!");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Error: Please enter a valid number (1-3).");
			scanner.nextLine();
		} catch (IllegalArgumentException e) {
			System.out.println("Error updating task: " + e.getMessage());
		}
	}
	
	// view a specific task
	public void viewTaskHelper(Scanner scanner) {
		System.out.print("Enter Task ID to view: ");
		String taskId = scanner.nextLine();
		
		Task task = getTask(taskId);
		if (task == null) {
			System.out.println("Task has not been found.");
		} else {
			System.out.println("\n*** Task Details ***");
			System.out.println("ID: " + task.getTaskId());
			System.out.println("Name: " + task.getTaskName());
			System.out.println("Desc: " + task.getTaskDesc());
			System.out.println("**********************");
		}
	}
	
	// view all tasks in tracker
	public void displayAllTasks() {
		if (taskTracker.isEmpty()) {
			System.out.println("No tasks found.");
			return;
		}
		
		System.out.println("\n*** All Tasks ***");
		for (Task task : taskTracker.values()) {
			System.out.println("ID: " + task.getTaskId());
			System.out.println("Name: " + task.getTaskName());
			System.out.println("Desc: " + task.getTaskDesc());
			System.out.println("**********************");
		}
	}
	
	public void taskMenuHelper() {
		Scanner scanner = new Scanner(System.in);
		boolean taskSession = true;
		
		System.out.println("\nTask Management System");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
		
		while (taskSession) {
			System.out.println("\nChoose an option:");
			System.out.println("1. Add Task");
			System.out.println("2. Delete Task");
			System.out.println("3. Update Task");
			System.out.println("4. View Task");
			System.out.println("5. View All Tasks");
			System.out.println("6. Main Menu");
			System.out.print("Enter your choice: ");
			
			try {
				int choice = scanner.nextInt();
				scanner.nextLine(); // clears scanner for next input
				
				switch (choice) {
				case 1:
					addTaskHelper(scanner);
					break;
				case 2:
					deleteTaskHelper(scanner);
					break;
				case 3:
					updateTaskHelper(scanner);
					break;
				case 4:
					viewTaskHelper(scanner);
					break;
				case 5: 
					displayAllTasks();
					break;
				case 6:
					taskSession = false;
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