package com.seeri.challenge.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class RequestDataTaskModel {
    private long id;
    @NonNull
    private String titulo;
    private String descripcion;
    @NonNull
    private String state;
}
