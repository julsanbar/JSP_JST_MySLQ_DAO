package exception_validations;

public class RecetaExisteException extends Exception{

	public RecetaExisteException(String msg) {
		super(msg);
	}
	
	public RecetaExisteException() {
		super();
	}
	
}
