package com.seeri.challenge.entitys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task")
@Getter
@Setter
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long id;
    @Column( nullable = false)
    private String titulo;
    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;
    @ManyToOne // Relación ManyToOne: una tarea tiene un estado
    @JoinColumn(name = "type_state_id") // Nombre de la columna que almacena la clave foránea
    private TypeStateEntity state;
    @Column(name = "update_date")
    private Date updateDate;
    @Column(name = "create_date", updatable = false)
    private Date createDate;
}
