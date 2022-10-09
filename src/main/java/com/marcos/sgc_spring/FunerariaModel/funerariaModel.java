package com.marcos.sgc_spring.FunerariaModel;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sgcfunerarias")
@Data
public class funerariaModel {

    @Id
    int funcodigo;
    String fundescricao;
    String funcidade;
    String funendereco;
    int funnumero;
}
