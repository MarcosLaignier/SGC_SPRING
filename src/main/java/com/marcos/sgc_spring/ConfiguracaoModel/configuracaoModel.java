package com.marcos.sgc_spring.ConfiguracaoModel;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SGCCONFIGURACOES")
@Data
public class configuracaoModel {
    @Id
    int CODCLIENTE;
    String SGCMUNICIPIO;
    String SGCPATHLOGO;
    String SGCPATHIMG;
    String SGCTIPOMUNICIPIO;

}
