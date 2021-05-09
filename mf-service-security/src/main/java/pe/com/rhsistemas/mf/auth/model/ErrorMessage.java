package pe.com.rhsistemas.mf.auth.model;

public class ErrorMessage {
    private String field;
    private String message;
    
    public ErrorMessage(String field, String message) {
		this.field = field;
		this.message = message;
	}
    
	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}
	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
