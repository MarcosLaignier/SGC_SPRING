package com.marcos.sgc_spring.SepultamentoModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marcos.sgc_spring.CemiterioModel.cemiterioModel;
import com.marcos.sgc_spring.FunerariaModel.funerariaModel;
import com.marcos.sgc_spring.PessoaModel.pessoaModel;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "sgcsepultamentos")
public class sepultamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int sepulcodigo;
    @Column(unique = true)
    String sepulfalecido;
    String sepulcpffal;
    String sepulfuneraria;
    String sepulcemiterio;
    String sepulsepultura;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date sepdatasepultamento;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date sepdatafalecimento;
    @OneToOne
    pessoaModel pessoa;
    @OneToOne
    cemiterioModel cemiterio;
    @OneToOne
    funerariaModel funeraria;

}
