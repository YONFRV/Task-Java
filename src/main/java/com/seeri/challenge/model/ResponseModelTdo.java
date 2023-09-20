package com.seeri.challenge.model;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseModelTdo {
    private long typeStateId;
    private String name;
    private Date updateDate;
    private Date createDate;
    private boolean state;
}
