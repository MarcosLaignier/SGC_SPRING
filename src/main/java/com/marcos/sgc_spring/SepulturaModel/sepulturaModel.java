package com.marcos.sgc_spring.SepulturaModel;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sgcsepulturas")
public class sepulturaModel {

    @Id
    int sepcodigo;
//    int SEPIDCEMITERIO;
    String sepdescricao;
    String sepcemiterio;
}
