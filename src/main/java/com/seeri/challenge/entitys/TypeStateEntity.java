package com.seeri.challenge.entitys;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "type_state")
@Getter
@Setter
public class TypeStateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_state_id")
    private long typeStateId;
    @NonNull
    private String name;
    @Column(name = "update_date")
    private Date updateDate;
    @Column(name = "create_date", updatable = false)
    private Date createDate;
    private boolean state;
}

