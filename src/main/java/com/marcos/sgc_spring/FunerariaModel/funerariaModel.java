package com.marcos.sgc_spring.FunerariaModel;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sgcfunerarias")
@Data
public class funerariaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int funcodigo;
    @Column(unique = true)
    String fundescricao;
    String funcidade;
    String funendereco;
    int funnumero;
}
