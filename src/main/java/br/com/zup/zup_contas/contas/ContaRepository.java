package br.com.zup.zup_contas.contas;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends CrudRepository<Conta, Integer> {

    Optional<Conta> findById(int id);

    @Query(value = "SELECT * FROM gerenciador_contas WHERE status = :status", nativeQuery = true)
    List<Conta> status(String status);

    @Query(value = "SELECT * FROM gerenciador_contas WHERE tipo = :tipo", nativeQuery = true)
    List<Conta> tipo(String tipo);

    @Query(value = "SELECT * FROM gerenciador_contas WHERE VALOR BETWEEN :valor-10 AND :valor+10", nativeQuery = true)
    List<Conta> valor(Double valor);

    @Query(value = "SELECT * FROM gerenciador_contas WHERE status = :status AND tipo = :tipo AND valor BETWEEN :valor-10 AND :valor+10", nativeQuery = true)
    List<Conta> todosFiltros(String status, String tipo, Double valor);

    @Query(value = "SELECT * FROM gerenciador_contas WHERE status = :status AND tipo = :tipo", nativeQuery = true)
    List<Conta> statusTipo(String status, String tipo);

    @Query(value = "SELECT * FROM gerenciador_contas WHERE status = :status AND valor BETWEEN :valor-10 AND :valor+10", nativeQuery = true)
    List<Conta> statusValor(String status, Double valor);

    @Query(value = "SELECT * FROM gerenciador_contas WHERE  tipo = :tipo AND valor BETWEEN :valor-10 AND :valor+10", nativeQuery = true)
    List<Conta> tipoValor(String tipo, Double valor);


}
