package br.com.zup.zup_contas.contas;

import br.com.zup.zup_contas.contas.enuns.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta verificarData(Conta conta){
        if (conta.getDataDeVencimento().isBefore(conta.getDataCadastro())){
            conta.setStatus(Status.VENCIDA);
        }
        conta.setStatus(Status.AGUARDANDO);

        return conta;
    }

    public Conta salvarConta(Conta conta){
        conta.setDataCadastro(LocalDate.now());
        verificarData(conta);
        contaRepository.save(conta);

        return conta;
    }

    public List<Conta> exibirContasSalvas(){
        List<Conta> contasSalvas = (List<Conta>) contaRepository.findAll();

        return contasSalvas;
    }

    public Conta buscarContaId(int id){
        Optional<Conta> contaId = contaRepository.findById(id);

        return contaId.get();
    }

    public Conta atulizarStatusConta(Conta conta){
        Optional<Conta> contaAtualizar = buscarContaId(conta.getId());


    }

}
