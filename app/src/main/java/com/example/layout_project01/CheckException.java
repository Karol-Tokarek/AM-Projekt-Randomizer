package com.example.layout_project01;

public class CheckException extends Exception {
    public CheckException(){}
    public CheckException(String message)
    {
        super(message);
    }
    public CheckException(Throwable cause)
    {
        super(cause);
    }
    public CheckException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
