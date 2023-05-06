package personserver;


public class PersonLoadException extends Exception {

    public PersonLoadException() {
    }

    public PersonLoadException(String message) {
        super(message);
    }

    public PersonLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonLoadException(Throwable cause) {
        super(cause);
    }

    public PersonLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
