package com.firstspring;

public class Result {
    // Some default error messages
    public static final String ID_EXISTS = "ID van deze entiteit bestaat al.";


    private String message;

    public Result() {}

    public Result(String code) {
        setMessageFromCode(code);
    }

    public void setMessageFromCode(String code){
        // Search code in message resources.
        message = code;
    }
    public String getMessage() {
        return message;
    }
}
