package br.com.zup.zup_contas.contas;

import br.com.zup.zup_contas.contas.enuns.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

}
