package com.amdocs.dms.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.amdocs.dms.model.Doctor;
import com.amdocs.dms.dao.Operations;
import com.amdocs.dms.exceptions.*;
import com.amdocs.dms.exceptions.DMSExceptions.*;




public class Menu {
	
	static DMSExceptions ex = new DMSExceptions();
	static Operations operations = new Operations();
	static Scanner sc = new Scanner(System.in);
	
	private static void addDoctor() {
		
		try {
        System.out.println("Enter doctor details:");
        
        int doctorId=0;

        System.out.print("Doctor Name: ");
        sc.nextLine();
        String doctorName = sc.nextLine();

        System.out.print("Mobile Number: ");
        String mobileNumber = sc.nextLine();
        
        if(mobileNumber.length() != 10) {
        	throw new InvalidNumberException(" Please enter a valid number.");
        }

        System.out.print("Specialization: ");
        String specialization = sc.nextLine();

        System.out.print("Available Shift (Morning/Afternoon/Evening): ");
        String availableShift = sc.nextLine();
        
        if(!(availableShift.equals("Morning") || availableShift.equals("Afternoon") || availableShift.equals("Evening"))) {
        	
        	throw new UndefinedShiftException(" Please enter correct shift.");
        }
        
        System.out.print("Fees: ");
        double fees = sc.nextDouble();
        
        sc.nextLine(); 
        

        if(doctorName.isBlank() || mobileNumber.isBlank() || specialization.isBlank() || availableShift.isBlank() ) {
        	throw new EmptyFieldException(" Please enter all the values.");
        }
        
        Doctor doctor = new Doctor(doctorId, doctorName, mobileNumber, specialization, availableShift, fees);
        int result = operations.addDoctor(doctor);

        if (result > 0) {
            System.out.println("Doctor added successfully.");
        } else {
            throw new DoctorNotAddedException("Doctor not added");
        }
        
    } catch(InputMismatchException e) {
    	System.out.println(" Please enter correct values.");
    } catch(Exception e) {
    	System.out.println("Error adding doctor" + e.getMessage());
    }
} 
	
	private void updateDoctorFees() {
		
		try {
			
			boolean idFound=false;
        System.out.print("Enter doctor ID: ");
        int doctorId = sc.nextInt();
        
        List<Doctor> doctors = operations.showAllDoctors();
        for (Doctor doctor : doctors) {
        	if (doctor.getDoctorId() == doctorId) {
                idFound=true;
                break;
            }
        }
        
        if(idFound==false) {
        	throw new DoctorIdException("Doctor with ID " + doctorId + " doesn't exists.");
        }
        
        System.out.print("Enter new fees: ");
        double newFees = sc.nextDouble();
        
        boolean success = operations.updateDoctorFees(doctorId, newFees);

        if (success) {
            System.out.println("Doctor fees updated successfully.");
        } else {
        	throw new DoctorNotUpdatedException("Doctor fees not updated.");
        }
        
		} catch(InputMismatchException e) {
			System.out.println("Please enter correct values.");
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
    }
	
	private static void deleteDoctor() {
		
		
		try {
			
			boolean idFound=false;
        System.out.print("Enter doctor ID: ");
        int doctorId = sc.nextInt();
        
        List<Doctor> doctors = operations.showAllDoctors();
        for (Doctor doctor : doctors) {
        	if (doctor.getDoctorId() == doctorId) {
                idFound=true;
                break;
            }
        }
        
        if(idFound==false) {
        	throw new DoctorIdException("Doctor with ID " + doctorId + " doesn't exists.");
        }

        int result = operations.deleteDoctor(doctorId);

        if (result > 0) {
            System.out.println("Doctor deleted successfully.");
        } else {
        	throw new DoctorNotDeletedException("Doctor not deleted.");
        }
        } catch(InputMismatchException e) {
			System.out.println("Please enter correct values.");
			
		} catch(Exception e) {
        	System.out.println(e.getMessage());
        }
    }
	
	private static void viewAllDoctors() {
		try {
        List<Doctor> doctors = operations.showAllDoctors();

        if (doctors.isEmpty()) {
        	throw new DoctorsNotFoundException("Doctors not found.");
        } else {
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }
        }
        } catch(InputMismatchException e) {
			System.out.println("Please enter correct values.");
			
		} catch(Exception e) {
        	System.out.println(e.getMessage());
        }
    }
	
	private static void findDoctorBySpecialization() {
     
		try {
		System.out.print("Enter specialization: ");
		sc.nextLine();
        String specialization = sc.nextLine();

        List<Doctor> doctors = operations.searchBySpecialization(specialization);

        if (!doctors.isEmpty()) {
        	for (Doctor doctor : doctors) 
                System.out.println(doctor);
        } else {
        	throw new NoDoctorFoundException("No doctors found with this specialization.");
            }
        }catch(InputMismatchException e) {
			System.out.println("Please enter correct values.");
			
		}catch(Exception e) {
        	System.out.println(e.getMessage());
        }
		} 
    
	
	private static void findDoctorsWithLessFees() {
		
		try {
        System.out.print("Enter shift (Morning/Afternoon/Evening): ");
        sc.nextLine();
        String shift = sc.nextLine();
        
        if(!(shift.equals("Morning") || shift.equals("Afternoon") || shift.equals("Evening"))) {
        	
        	throw new UndefinedShiftException(" Please enter correct shift.");
        }

        List<Doctor> doctors = operations.searchByFeesAndShift(shift);

        if (doctors.isEmpty()) {
            throw new NoDoctorFoundException("No doctors found with least fees in the "+shift+" shift.");
        } else {
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }
        }
        
		}catch(InputMismatchException e) {
			System.out.println("Please enter correct values.");
			
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			
		}
    }
	
	private static void countDoctorsByShift() {
		
		try {
        System.out.print("Enter shift (Morning/Afternoon/Evening): ");
        sc.nextLine();
        String shift = sc.nextLine();
        
        if(!(shift.equals("Morning") || shift.equals("Afternoon") || shift.equals("Evening"))) {
        	
        	throw new UndefinedShiftException(" Please enter correct shift.");
        }

        int count = operations.countDoctorsByShift(shift);

        if (count >= 0) {
            System.out.println("Number of doctors in " + shift + "shift: " + count+ ".");
        } else {
           throw new NoCountException("Error in couting doctors.");
        }
        
		}catch(InputMismatchException e) {
			System.out.println("Please enter correct values.");
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
    }
	
	private static void exit() {
		sc.close();
        System.exit(0);
    }
	
		
	public void menu(){
	
		try {
		int option; 
		
		while(true) {
			
		System.out.println("\n***********************************************************************************");
		System.out.println("Please enter the desired operation: \n 1. ADD NEW DOCTOR \n 2. UPDATE DOCTOR FEES"
				+ "\n 3. DELETE DOCTOR \n 4. VIEW ALL DOCTORS \n 5. FIND DOCTOR BY SPECIALIZATION "
				+ "\n 6. FIND DOCTORS WHO'S FEES IS LESS THAN ALL  DOCTOR OF GIVEN SHIFT \n 7. COUNT NUMBER OF DOCTOS BY SHIFT "
				+ "\n 8. EXIT" );
		System.out.println("***********************************************************************************");
		
		
		option= sc.nextInt();
		
		switch (option) {
        case 1:
            addDoctor();
            break;
        case 2:
            updateDoctorFees();
            break;
        case 3:
            deleteDoctor();
            break;
        case 4:
            viewAllDoctors();
            break;
        case 5:
            findDoctorBySpecialization();
            break;
        case 6:
            findDoctorsWithLessFees();
            break;
        case 7:
            countDoctorsByShift();
            break;
        case 8:
            exit();
            break;
        default:
            System.out.println("Invalid choice. Please try again. /n");
   }
	}
  } catch(InputMismatchException e) {
	  System.out.println("Please enter correct values.");
  }
 }

}
