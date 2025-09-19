package lk.ijse.elite_driving_school_orm.bo.exception;

public class InUseException extends RuntimeException {
    public InUseException(String message) {
        super(message);
    }
}
