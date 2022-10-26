package com.marcos.sgc_spring.SepulturaRepositorio;

import com.marcos.sgc_spring.SepulturaModel.sepulturaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface sepulturaRepositorio extends JpaRepository<sepulturaModel,Integer> {

    @Query("select MAX (sep.sepcodigo) from sepulturaModel sep ")
    int findCod();

    sepulturaModel findBySepdescricao(String sepdescricao);
}
