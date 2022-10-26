package com.marcos.sgc_spring.PessoaModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@Table(name = "SGCFALECIDOS")
public class pessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int falcodigo;
    String falnome;
    @Column(unique = true)
    String falcpf;
    String falsexo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate falnascimento;
    String falnaturalidade;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate falfalecimento;
    String falmedresp;


}
