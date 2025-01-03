package exceptions;

public class InvalidArrayPositionException extends RuntimeException  {
	private static final long serialVersionUID = 1L;

	public InvalidArrayPositionException(String message) {
        super(message);
    }
}
