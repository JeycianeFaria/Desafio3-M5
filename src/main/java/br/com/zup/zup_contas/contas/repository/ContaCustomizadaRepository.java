package br.com.zup.zup_contas.contas.repository;

import br.com.zup.zup_contas.contas.Conta;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ContaCustomizadaRepository {

    private final EntityManager entityManager;

    public ContaCustomizadaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Conta> buscar(String status, String tipo, Double valor){

        String query = "SELECT * FROM gerenciador_contas";
        String condicao = " WHERE";

        if (status != null){
            query += condicao +  " status = :status";
            condicao = " AND";
        }

        if (tipo != null){
            query += condicao + " tipo = :tipo";
            condicao = " AND";
        }

        if (valor != null){
            query += condicao + " valor BETWEEN :valor-10 AND :valor+10";
        }

        var q = entityManager.createNativeQuery(query, Conta.class);

        if (status != null){
           q.setParameter("status", status);
        }

        if (tipo != null){
            q.setParameter("tipo", tipo);
        }

        if (valor != null){
            q.setParameter("valor", valor);
        }

        return q.getResultList();
    }

}
