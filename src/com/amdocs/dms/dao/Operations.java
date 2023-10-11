package com.amdocs.dms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.amdocs.dms.model.Doctor;

public class Operations {
	
	Connection con = null;
	
	public int addDoctor(Doctor doctor) {
		
		try {
			con = DBConnection.getConnection();
            String query = "INSERT INTO tblDoctor(doctorid, doctorname, mobilenumber, specialization, availableshift, fees) VALUES (sequence1.nextval, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            
            statement.setString(1, doctor.getDoctorName());
            statement.setString(2, doctor.getMobileNumber());
            statement.setString(3, doctor.getSpecialization());
            statement.setString(4, doctor.getAvailableShift());
            statement.setDouble(5, doctor.getFees());

            int rs = statement.executeUpdate();
            return rs;
            
        } catch(SQLException sql) {
        	
        	System.out.print(sql.toString());
        	return -1;
        }
	
		catch (Exception e) {
        	
            System.out.println(e.getMessage());
            return -1; 
        }
		finally {
			
			 DBConnection.closeConnection(con);
		}
		
	}
	
	public int deleteDoctor(int doctorId) {
		
		
		try {
			
			con = DBConnection.getConnection();
            String query = "DELETE FROM tblDoctor WHERE doctorid = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, doctorId);

            int rs = statement.executeUpdate();
            return rs;
            
        } catch (Exception e) {
            e.printStackTrace();
            return -1; 
        }
		
		finally {
			
			 DBConnection.closeConnection(con);
		}
	}
	
	public boolean updateDoctorFees(int doctorId, double newFees) {
       
			boolean status=false;
			
		try {
        	
        	con = DBConnection.getConnection();
            String query = "UPDATE tblDoctor SET fees = ? WHERE doctorid = ?";
            PreparedStatement statement = con.prepareStatement(query);
            
            statement.setDouble(1, (float)newFees);
            statement.setInt(2, doctorId);
            int rowsUpdated = statement.executeUpdate();
            
            if(rowsUpdated==1) {
            	status = true;
            }
                        
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
       
        finally {
			
			 DBConnection.closeConnection(con);
		}
        
        return status;
    }
	
	public List<Doctor> showAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try {
        	
        	con = DBConnection.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tblDoctor");

            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(resultSet.getInt("doctorId"));
                doctor.setDoctorName(resultSet.getString("doctorName"));
                doctor.setMobileNumber(resultSet.getString("mobileNumber"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctor.setAvailableShift(resultSet.getString("availableShift"));
                doctor.setFees(resultSet.getDouble("fees"));
                doctors.add(doctor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        finally {
			
			 DBConnection.closeConnection(con);
		}
        
        return doctors;
    }
	
	public List<Doctor> searchBySpecialization(String specialization) {
        List<Doctor> doctors = new ArrayList<>();
        try {
        	
        	con = DBConnection.getConnection();
            String query = "SELECT * FROM tblDoctor WHERE specialization = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, specialization);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(resultSet.getInt("doctorId"));
                doctor.setDoctorName(resultSet.getString("doctorName"));
                doctor.setMobileNumber(resultSet.getString("mobileNumber"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctor.setAvailableShift(resultSet.getString("availableShift"));
                doctor.setFees(resultSet.getDouble("fees"));
                doctors.add(doctor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        finally {
			
			 DBConnection.closeConnection(con);
		}
        
        return doctors;
    }
	
	public List<Doctor> searchByFeesAndShift(String shift) {
        List<Doctor> doctors = new ArrayList<>();
        try {
        	con = DBConnection.getConnection();
            String query = "SELECT * FROM tblDoctor WHERE fees = ( SELECT MIN(fees) FROM tblDoctor WHERE availableShift = ? ) AND availableShift = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, shift);
            statement.setString(2, shift);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(resultSet.getInt("doctorId"));
                doctor.setDoctorName(resultSet.getString("doctorName"));
                doctor.setMobileNumber(resultSet.getString("mobileNumber"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctor.setAvailableShift(resultSet.getString("availableShift"));
                doctor.setFees(resultSet.getDouble("fees"));
                doctors.add(doctor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        finally {
			
			 DBConnection.closeConnection(con);
		}
        
        return doctors;
    }
	
	public int countDoctorsByShift(String shift) {
        try {
        	con = DBConnection.getConnection();
            String query = "SELECT COUNT(*) FROM tblDoctor WHERE availableshift = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, shift);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        finally {
			
			 DBConnection.closeConnection(con);
		}
        return -1;
    }
	
}
