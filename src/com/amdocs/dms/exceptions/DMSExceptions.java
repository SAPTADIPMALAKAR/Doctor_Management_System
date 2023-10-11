package com.amdocs.dms.exceptions;

public class DMSExceptions {

	public static class DoctorNotAddedException extends Exception{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DoctorNotAddedException(String args) {
			super();
		}
	}
	
	public static class DoctorNotDeletedException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DoctorNotDeletedException(String args) {
			super();
		}
	}
	
	public static class DoctorNotUpdatedException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DoctorNotUpdatedException(String args) {
			super(args);
			
		}

	}
	
	public static class DoctorsNotFoundException extends Exception {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DoctorsNotFoundException(String args) {
			super();
		}

	}
	
	public static class NoDoctorFoundException extends Exception {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NoDoctorFoundException(String args) {
			super();
		}
	}
	
	public static class NoCountException extends Exception {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NoCountException(String args) {
			super();
		}
	}
	
	

}
