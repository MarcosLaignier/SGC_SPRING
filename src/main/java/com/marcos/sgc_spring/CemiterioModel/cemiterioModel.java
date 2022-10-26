package com.marcos.sgc_spring.CemiterioModel;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sgcunidades")
public class cemiterioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int undcodigo;
    @Column(unique = true)
    String undnome;
    String undendereco;
    int undnumero;
    String undcidade;
    String undestado;
    String undresponsavel;
    boolean status;
}
