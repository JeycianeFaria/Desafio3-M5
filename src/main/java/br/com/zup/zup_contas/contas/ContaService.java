package br.com.zup.zup_contas.contas;

import br.com.zup.zup_contas.contas.enuns.Status;
import br.com.zup.zup_contas.contas.exceptions.ContaNaoEncontrada;
import br.com.zup.zup_contas.contas.exceptions.NaoExisteCadastro;
import br.com.zup.zup_contas.contas.repository.QuerysCustomizadaRepository;
import br.com.zup.zup_contas.contas.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private QuerysCustomizadaRepository contaCustomizadaRepository;

    public void verificarData(Conta conta) {
        if (conta.getDataDeVencimento().isBefore(conta.getDataCadastro())) {
            conta.setStatus(Status.VENCIDA);
            return;
        }

        conta.setStatus(Status.AGUARDANDO);

    }

    public Conta salvarConta(Conta conta) {

        conta.setDataCadastro(LocalDate.now());
        verificarData(conta);
        contaRepository.save(conta);

        return conta;
    }

    public List<Conta> exibirContasSalvas(Map<String, String> filtros) {
        List<Conta> contasSalvas = (List<Conta>) contaRepository.findAll();

        if (contasSalvas.isEmpty()) {
            throw new NaoExisteCadastro("Nenhuma conta cadastrada!");
        }

        return retornarBusca(contasSalvas, filtros);
    }

    public List<Conta> retornarBusca(List<Conta> contaSalvas, Map<String, String> filtros) {

        //estrutura condicional para verificar filtros
        if (filtros.get("status") != null || filtros.get("tipo") != null || (filtros.get("valor") != null)) {
            return contaCustomizadaRepository.buscar(filtros.get("status"), filtros.get("tipo"),
                    Double.parseDouble(filtros.get("valor")));
        }

        return contaSalvas;
    }

    public Conta buscarContaId(int id) {
        Optional<Conta> contaId = contaRepository.findById(id);

        if (contaId.isEmpty()) {
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

    public void excluirConta(int id) {

        if (contaRepository.existsById(id)) {
            contaRepository.deleteById(id);
            return;
        }

        throw new ContaNaoEncontrada("Conta não encontrada");
    }

}
