package com.seeri.challenge.models;

import lombok.Data;

@Data
public class ResponseControllerModel {
    private int status;
    private ResponseModel<String> response;
}
