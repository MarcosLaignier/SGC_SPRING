package com.marcos.sgc_spring.SepultamentoModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marcos.sgc_spring.PessoaModel.pessoaModel;
import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "sgcsepultamentos")
public class sepultamentoModel {
    @Id
    int sepulcodigo;
    String sepulfalecido;
    String sepulcpffal;
    String sepulfuneraria;
    String sepulcemiterio;
    String sepulsepultura;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date sepdatasepultamento;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date sepdatafalecimento;

}
