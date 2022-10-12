package com.marcos.sgc_spring.SepulturaRepositorio;

import com.marcos.sgc_spring.SepulturaModel.sepulturaModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class sepulturaRepositorioCustom {

    private EntityManager entityManager;

    public sepulturaRepositorioCustom(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<sepulturaModel> findSepCustom(String codigo,String descricao, String cemiterio){
        String query="select S from sepulturaModel as S where 1=1";

        Map<String,String> paramMap=new HashMap<>();
        paramMap.put(codigo," and S.sepcodigo = :codigo");
        paramMap.put(descricao," and (S.sepdescricao) like :descricao");
        paramMap.put(cemiterio," and S.sepcemiterio= :cemiterio");

        for(Map.Entry<String,String> entry: paramMap.entrySet()){
            if(entry.getKey() !=null){
                query+=entry.getValue();
            }
        }

        var queryMontada = entityManager.createQuery(query,sepulturaModel.class);

        if(codigo!=null){
            queryMontada.setParameter("codigo",Integer.parseInt(codigo));
        }
        if (descricao != null){
            queryMontada.setParameter("descricao","%"+descricao+"%");
        }
        if (cemiterio != null){
            queryMontada.setParameter("cemiterio",cemiterio);
        }

        return queryMontada.getResultList();

    }
}
