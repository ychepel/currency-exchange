package UI;

import DL.ErrorLogHandler;

public class ApplicationException extends RuntimeException{

    public ApplicationException() {
        System.err.println("Unfortunately the operation cannot be performed due to a temporary malfunction. Please come back tomorrow.");
    }
}
