package com.seeri.challenge.model;

import lombok.Data;

@Data
public class ResponseControllerModel {
    private int status;
    private ResponseModel<String> response;
}
