package com.marcos.sgc_spring.SepultamentoRepositorio;

import com.marcos.sgc_spring.SepultamentoModel.sepultamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface sepultamentoRepositorio extends JpaRepository<sepultamentoModel, Integer> {



    @Query("select MAX(sep.sepulcodigo) from sepultamentoModel sep")
    int lasCod();

    @Query("select (fun.fundescricao) from funerariaModel fun")
    List<String> nameFun();

    @Query("select (cem.undnome)from cemiterioModel cem ")
    List<String> nameCem();

    @Query(value = "select * from sgcsepultamentos s where s.sepulcodigo = :cod",nativeQuery = true)
    List<sepultamentoModel> filterParam(
            @Param("cod") int cod
    );

}
