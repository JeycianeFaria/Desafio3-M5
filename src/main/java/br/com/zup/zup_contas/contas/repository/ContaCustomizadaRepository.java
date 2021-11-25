package br.com.zup.zup_contas.contas.repository;

import br.com.zup.zup_contas.contas.Conta;
import br.com.zup.zup_contas.contas.enuns.Filtros;
import br.com.zup.zup_contas.contas.enuns.Status;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Repository
public class ContaCustomizadaRepository {

    private final EntityManager entityManager;

    public ContaCustomizadaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Conta> buscarContaPorFiltro(Map<String, String> filtrosRecebidos) {
        String query = "SELECT * FROM gerenciador_contas";

        boolean primeiroFiltro = true;

        for (Map.Entry<String, String> referencia : filtrosRecebidos.entrySet()) {
            query += Filtros.valueOf(referencia.getKey().toUpperCase()).addFilter(primeiroFiltro);
            primeiroFiltro = false;
        }

        var q = entityManager.createNativeQuery(query, Conta.class);

        for (Map.Entry<String, String> referencia : filtrosRecebidos.entrySet()) {
            Filtros.valueOf(referencia.getKey().toUpperCase()).setValues(q, referencia.getValue());
        }

        return q.getResultList();
    }

}
