package com.amdocs.dms.model;

public class Doctor {
	
	private int doctorId;
	private String doctorName;
	private String mobileNumber;
	private String specialization;
	private String availableShift;
	private double fees;
	
	public Doctor(int doctorid, String doctorName, String mobileNumber, String specialization, String availableShift,
			double fees) {
		super();
		this.doctorId = doctorid;
		this.doctorName = doctorName;
		this.mobileNumber = mobileNumber;
		this.specialization = specialization;
		this.availableShift = availableShift;
		this.fees = fees;
	}

	public Doctor() {
		// TODO Auto-generated constructor stub
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorid) {
		this.doctorId = doctorid;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getAvailableShift() {
		return availableShift;
	}

	public void setAvailableShift(String availableShift) {
		this.availableShift = availableShift;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	@Override
	public String toString() {
	    return String.format("| %-10d | %-20s | %-15s | %-20s | %-15s | %-10.2f |\n", 
	                         doctorId, doctorName, mobileNumber, specialization, availableShift, fees);
	}


	

	
}
