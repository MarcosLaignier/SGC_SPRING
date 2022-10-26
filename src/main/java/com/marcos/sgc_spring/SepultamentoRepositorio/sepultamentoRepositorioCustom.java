package com.marcos.sgc_spring.SepultamentoRepositorio;

import com.marcos.sgc_spring.SepultamentoModel.sepultamentoModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class sepultamentoRepositorioCustom {

    private final EntityManager entityManager;

    public sepultamentoRepositorioCustom(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<sepultamentoModel> find(String pessoa, String cpf, String Cemiterio){

        String query = "select P from sepultamentoModel AS P where 1=1 ";
//        String condicaoInicial = "where ";

        Map<String,String> map = new HashMap<>();

        map.put(pessoa,"and P.sepulfalecido like :pessoa");
        map.put(cpf," and P.sepulcpffal = :cpf");
        map.put(Cemiterio," and P.sepulcemiterio = :Cemiterio");
        for( Map.Entry<String,String> entry : map.entrySet()){
            if (entry.getKey() !=null){
                query+=entry.getValue();
            }
        }
        var q = entityManager.createQuery(query+" order by P.sepulcodigo",sepultamentoModel.class);


        if(pessoa !=null ){
            q.setParameter("pessoa","%"+pessoa+"%");
        }

        if (cpf!=null ){
            q.setParameter("cpf",cpf);

        }

        if (Cemiterio!=null ){
            q.setParameter("Cemiterio",Cemiterio);
        }


        return q.getResultList();
    }
}
