package br.com.zup.zup_contas.contas.repository;

import br.com.zup.zup_contas.contas.Conta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends CrudRepository<Conta, Integer> {

    Optional<Conta> findById(int id);

}
