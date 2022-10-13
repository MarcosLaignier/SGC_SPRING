package com.marcos.sgc_spring.FunerariaRepositorio;

import com.marcos.sgc_spring.FunerariaModel.funerariaModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class funerariaRepositorioCustom {

    private EntityManager entityManager;

    public funerariaRepositorioCustom(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<funerariaModel>findFunCustom(String codigo, String nome, String cidade){
        String query = "select F from funerariaModel AS F where 1=1";
        Map<String,String> ParamMap = new HashMap<>();
        ParamMap.put(codigo," and F.funcodigo = :codigo");
        ParamMap.put(nome," and F.fundescricao like :nome");
        ParamMap.put(cidade," and F.funcidade = :cidade");
        for(Map.Entry<String, String> entry : ParamMap.entrySet()){
            if (entry.getKey()!=null){
                query+=entry.getValue();
            }
        }

        var queryMontada = entityManager.createQuery(query+ " order by F.funcodigo",funerariaModel.class);

        if (codigo !=null){
            queryMontada.setParameter("codigo",Integer.parseInt(codigo));
        }
        if (nome !=null){
            queryMontada.setParameter("nome","%"+nome+"%");
        }
        if (cidade!=null){
            queryMontada.setParameter("cidade",cidade);
        }
        return queryMontada.getResultList();
    }
}
