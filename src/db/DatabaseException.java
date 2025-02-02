package db;

public class DatabaseException extends RuntimeException {

    private static final Long SerialId = 1L;

    public DatabaseException(String message) {
        super(message);
    }

}
