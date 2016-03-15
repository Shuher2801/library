package it.by.library.services.exception;



public class ServiceException extends Exception{
	
	private static final long serialVersionUID = -2584864147342698812L;
		private Exception exception;

	    public ServiceException(String message, Exception e) {
	        this.exception = e;
	    }

	    public Exception getException() {
	        return exception;
	    }

	    public void setException(Exception exception) {
	        this.exception = exception;
	    }
	}

