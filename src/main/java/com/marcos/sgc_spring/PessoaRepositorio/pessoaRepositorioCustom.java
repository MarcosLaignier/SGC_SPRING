package com.marcos.sgc_spring.PessoaRepositorio;

import com.marcos.sgc_spring.PessoaModel.pessoaModel;
import com.marcos.sgc_spring.SepultamentoModel.sepultamentoModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class pessoaRepositorioCustom {

    private final EntityManager entityManager;

    public pessoaRepositorioCustom(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<pessoaModel> findCustom(String nome , LocalDate dtNasc , String sexo){
        String query = "select P from pessoaModel  as P where 1=1 ";

        Map<String, String> dados = new HashMap<>();
        dados.put(nome, " and P.falnome like :nome ");
        dados.put(sexo, " and P.falsexo=:sexo ");
        for (Map.Entry<String, String> entry : dados.entrySet()) {
            if (entry.getKey() != null) {
                query += entry.getValue();
            }
        }
        if(dtNasc != null){
            query +=" and P.falnascimento=:dtNasc ";
        }

        var queryMontada = entityManager.createQuery(query+" order by P.falcodigo", pessoaModel.class);

        if (nome != null) {
            queryMontada.setParameter("nome","%"+nome+"%");
        }

        if (sexo != null) {
            queryMontada.setParameter("sexo", sexo);

        }
        if(dtNasc != null){
            queryMontada.setParameter("dtNasc",dtNasc);
        }


        return queryMontada.getResultList();
    }
}
