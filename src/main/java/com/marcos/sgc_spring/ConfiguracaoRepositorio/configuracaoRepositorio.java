package com.marcos.sgc_spring.ConfiguracaoRepositorio;

import com.marcos.sgc_spring.ConfiguracaoModel.configuracaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface configuracaoRepositorio extends JpaRepository<configuracaoModel,Integer> {

    @Query("select C.SGCMUNICIPIO FROM configuracaoModel C")
    public String findName();

    configuracaoModel findByCODCLIENTE(int CODCLIENTE);
}
