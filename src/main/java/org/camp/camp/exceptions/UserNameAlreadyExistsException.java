package org.camp.camp.exceptions;

public class UserNameAlreadyExistsException extends RuntimeException {
    public UserNameAlreadyExistsException(String userName) {
        super("User Name already registered: " + userName);
    }
}
