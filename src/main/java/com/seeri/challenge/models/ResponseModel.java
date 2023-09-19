package com.seeri.challenge.models;


import lombok.Data;

@Data
public class ResponseModel<T> {
    private T Data;
    private boolean success;
    private boolean warning;
    private String message = "";

    @Override
    public String toString() {
        return "Response{" +
                "Data=" + Data +
                ", IsSuccess=" + success +
                ", IsWarning=" + warning +
                ", Message='" + message + '\'' +
                '}';
    }

}
