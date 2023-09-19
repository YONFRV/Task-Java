package com.seeri.challenge.models;

import lombok.Data;

@Data
public class DataTaskModel
{
    private long id;
    private String titulo;
    private String state;
    private String createDate;
    private String updateDate;
}
