package com.marcos.sgc_spring.SepulturaModel;

import com.marcos.sgc_spring.CemiterioModel.cemiterioModel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sgcsepulturas")
public class sepulturaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int sepcodigo;
    @Column(unique = true)
    String sepdescricao;
    String sepcemiterio;
    @ManyToOne(optional = false)
    cemiterioModel cemiterio;
}
