package org.example.exception;

public class DAOException extends RuntimeException {
    public DAOException(Throwable e) {
        super(e);
    }
}
