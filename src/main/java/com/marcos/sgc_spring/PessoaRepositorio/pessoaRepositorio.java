package com.marcos.sgc_spring.PessoaRepositorio;

import com.marcos.sgc_spring.PessoaModel.pessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface pessoaRepositorio extends JpaRepository<pessoaModel, Integer> {
    @Query("select MAX(pessoa.falcodigo) from pessoaModel pessoa")
    int lastCod();

//    @Query(value = "select * from sgcfalecidos f where f.falnome like %:falnome%", nativeQuery = true)
//    List<pessoaModel> nameFal(
//            @Param("falnome") String falnome
//    );
//
//    @Query("select f.falnome from pessoaModel f where f.falnome like %:falnome%")
//    List<String> nameFal3(
//            @Param("falnome") String falnome
//    );

    List<pessoaModel> findByFalnomeContains(String name);


}
