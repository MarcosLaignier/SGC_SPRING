package com.marcos.sgc_spring.PessoaRepositorio;

import com.marcos.sgc_spring.PessoaModel.pessoaModel;
import com.marcos.sgc_spring.SepultamentoModel.sepultamentoModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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

    //    public List<pessoaModel> findCustom(String nome , Date dtNasc , String sexo){
    public List<pessoaModel> findCustom(String nome, String sexo) {
        String query = "select P from pessoaModel  as P where 1=1 ";

        Map<String, String> dados = new HashMap<>();
        dados.put(nome, " and P.falnome=:nome ");
//        dados.put(dtNasc,"and P.falnascimento=:dtNasc");
        dados.put(sexo, " and P.falsexo=:sexo ");
        for (Map.Entry<String, String> entry : dados.entrySet()) {
            if (entry.getKey() != null) {
                query += entry.getValue();
            }
        }

        var queryMontada = entityManager.createQuery(query, pessoaModel.class);

        if (nome != null) {
            queryMontada.setParameter("nome", nome);
        }

        if (sexo != null) {
            queryMontada.setParameter("sexo", sexo);

        }


        return queryMontada.getResultList();
    }
}
