package com.ultimate.ecommerce.repository.server.response.base;

public class ResponseState {
    Boolean isSuccessful;
    String message;

    public static ResponseState successState(){
        return new ResponseState();
    }

    public static ResponseState failureState(String message){
        return new ResponseState(message);
    }


    private ResponseState() {
        this.isSuccessful = true;
        this.message = "DONE";
    }

    private ResponseState(String message) {
        this.isSuccessful = false;
        this.message = message;
    }

    public Boolean isSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}