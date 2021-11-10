package br.com.zup.zup_contas.contas;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContaRepository extends CrudRepository<Conta, String> {
    Optional<Conta> findById(int id);
}
