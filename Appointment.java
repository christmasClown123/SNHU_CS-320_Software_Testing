package projectOne;

import java.util.Date;

public class Appointment {
	// immutable length for each field defined by the appointment class requirements
	private static final int APPT_ID_LENGTH = 10;
	private static final int APPT_DESC_LENGTH = 50;
		
	// private variables for appointment object
	private final String apptId;
	private Date apptDate;
	private String apptDesc;
		
	// appointment constructor with object validation
	public Appointment(String apptId, Date apptDate, String apptDesc) {
		// object validation when creating an appointment
		validateApptId(apptId);
		validateApptDate(apptDate);
		validateApptDesc(apptDesc);
			
		this.apptId = apptId;
		this.apptDate = apptDate;
		this.apptDesc = apptDesc;
	}
		
	// object getters
	public String getApptId() {
		return apptId;
	}
		
	public Date getApptDate() {
		return new Date(apptDate.getTime());
	}
		
	public String getApptDesc() {
		return apptDesc;
	}
		
	// object setters
	public void setApptDate(Date apptDate) {
		validateApptDate(apptDate);
		this.apptDate = new Date(apptDate.getTime());
	}
		
	public void setApptDesc(String apptDesc) {
		validateApptDesc(apptDesc);
		this.apptDesc = apptDesc;
	}
		
	// object validators
	private void validateApptId(String apptId) {
		if (apptId == null || apptId.trim().isEmpty()) {
			throw new IllegalArgumentException("Appointment ID cannot be null or empty.");
		}
		if (apptId.length() > APPT_ID_LENGTH) {
			throw new IllegalArgumentException("Appointment ID cannot be longer than " + APPT_ID_LENGTH + " characters.");
		}
	}
		
	private void validateApptDate(Date apptDate) {
		if (apptDate == null) {
			throw new IllegalArgumentException("Appointment Date cannot be null.");
		}
		
		Date now = new Date();
		
		if (apptDate.before(now)) {
			throw new IllegalArgumentException("Appointment Date cannot be in the past.");
		}
	}
		
	private void validateApptDesc(String apptDesc) {
		if (apptDesc == null || apptDesc.trim().isEmpty()) {
			throw new IllegalArgumentException("Appointment Description cannot be null or empty.");
		}
		if (apptDesc.length() > APPT_DESC_LENGTH) {
			throw new IllegalArgumentException("Appointment Description cannot be longer than " + APPT_DESC_LENGTH + " characters.");
		}
	}
}
