package com.example.Common;

public class BaseException extends Exception {
    String mess = "";
    public BaseException(String mess){
        this.mess = mess;
    }
}
