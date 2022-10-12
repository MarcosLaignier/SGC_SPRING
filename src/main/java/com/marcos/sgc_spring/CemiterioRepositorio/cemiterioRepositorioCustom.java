package com.marcos.sgc_spring.CemiterioRepositorio;

import com.marcos.sgc_spring.CemiterioModel.cemiterioModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class cemiterioRepositorioCustom {
    private EntityManager entityManager;

    public cemiterioRepositorioCustom(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<cemiterioModel> findUndCustom(String codigo, String nome, String responsavel) {
        String query="select U from cemiterioModel as U where 1=1";

        Map<String,String> paramMap = new HashMap<>();
        paramMap.put(codigo," and undcodigo =:codigo");
        paramMap.put(nome," and undnome like :nome ");
        paramMap.put(responsavel," and undresponsavel like :responsavel");
        for (Map.Entry<String,String> entry:paramMap.entrySet()){
            if(entry.getKey()!=null){
                query+=entry.getValue();
            }
        }

        var queryMontada = entityManager.createQuery(query);

        if(codigo != null){
            queryMontada.setParameter("codigo",Integer.parseInt(codigo));
        }

        if (nome != null){
            queryMontada.setParameter("nome","%"+nome+"%");
        }

        if (responsavel != null){
            queryMontada.setParameter("responsavel","%"+responsavel+"%");
        }

        return queryMontada.getResultList();
    }
}
