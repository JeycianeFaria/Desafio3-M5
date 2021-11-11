package br.com.zup.zup_contas.contas;

import br.com.zup.zup_contas.contas.enuns.Status;
import br.com.zup.zup_contas.contas.exceptions.ContaNaoEncontrada;
import br.com.zup.zup_contas.contas.exceptions.NaoExisteCadastro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta verificarData(Conta conta) {
        if (conta.getDataDeVencimento().isBefore(conta.getDataCadastro())) {
            conta.setStatus(Status.VENCIDA);
        }
        conta.setStatus(Status.AGUARDANDO);

        return conta;
    }

    public Conta salvarConta(Conta conta) {
        conta.setDataCadastro(LocalDate.now());
        verificarData(conta);
        contaRepository.save(conta);

        return conta;
    }

    public List<Conta> exibirContasSalvas() {
        List<Conta> contasSalvas = (List<Conta>) contaRepository.findAll();
        if (contasSalvas.isEmpty()){
            throw new NaoExisteCadastro("Nenhuma conta cadastrada!");
        }

        return contasSalvas;
    }

    public Conta buscarContaId(int id) {
        Optional<Conta> contaId = contaRepository.findById(id);

        if (contaId.isEmpty()){
            throw new ContaNaoEncontrada("Conta não encontrada");
        }

        return contaId.get();
    }

    public Conta atualizarStatusConta(int id) {
        Conta contaAtualizar = buscarContaId(id);
        contaAtualizar.setStatus(Status.PAGO);
        contaAtualizar.setDataDePagamento(LocalDateTime.now());
        contaRepository.save(contaAtualizar);

        return contaAtualizar;
    }

}
