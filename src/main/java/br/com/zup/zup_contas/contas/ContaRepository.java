package br.com.zup.zup_contas.contas;

import br.com.zup.zup_contas.contas.enuns.Status;
import br.com.zup.zup_contas.contas.enuns.Tipo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends CrudRepository<Conta, Integer> {

    Optional<Conta> findById(int id);

    List<Conta> findAllByStatus (Status status);

    List<Conta> findAllByTipo(Tipo tipo);

    @Query(value = "SELECT * FROM gerenciador_contas WHERE VALOR BETWEEN :valor-10 AND :valor+10", nativeQuery = true)
    List<Conta> findAllByValor(Double valor);

}
