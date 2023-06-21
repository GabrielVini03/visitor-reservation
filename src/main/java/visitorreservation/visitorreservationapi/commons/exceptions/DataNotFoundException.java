package visitorreservation.visitorreservationapi.commons.exceptions;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String msg) {
        super(msg);
    }

    public DataNotFoundException() {
        super("Object Not found");
    }
}
