package com.marcos.sgc_spring.CemiterioRepositorio;

import com.marcos.sgc_spring.CemiterioModel.cemiterioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface cemiterioRepositorio extends JpaRepository<cemiterioModel,Integer> {

    @Query("SELECT MAX (u.undcodigo) FROM cemiterioModel u ")
    int findcod();

    @Query("SELECT (cem.undnome) from cemiterioModel cem")
    List<String> findnameCemiterio();

    @Query("SELECT (sep.sepcemiterio) from sepulturaModel sep where sep.sepcodigo = :name and sep.sepdescricao = :desc")
    String num(
            @Param("name") int name
    );

    cemiterioModel findByUndnome(String nome) ;
}

