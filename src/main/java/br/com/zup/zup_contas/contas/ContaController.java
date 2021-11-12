package br.com.zup.zup_contas.contas;

import br.com.zup.zup_contas.contas.dtos.*;
import br.com.zup.zup_contas.contas.enuns.Status;
import br.com.zup.zup_contas.contas.exceptions.StatusPagamentoIncorreto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;
    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaidaContaDTO cadastrarConta(@RequestBody @Valid CadastroContaDTO cadastroConta) {
        Conta contaRecibida = modelMapper.map(cadastroConta, Conta.class);
        contaService.salvarConta(contaRecibida);

        return modelMapper.map(contaRecibida, SaidaContaDTO.class);
    }

    @GetMapping
    public List<ExibirContaDTO> exibirListaContas(@RequestParam(required = false) Map<String, String> filtros) {
        List<ExibirContaDTO> listaContas = new ArrayList<>();

        for (Conta referencia : contaService.exibirContasSalvas(filtros)) {
            ExibirContaDTO exibirContaDTO = modelMapper.map(referencia, ExibirContaDTO.class);
            listaContas.add(exibirContaDTO);
        }

        return listaContas;
    }

    @GetMapping("/{id}")
    public ExibirContaDetalhadaDTO buscarContaID(@PathVariable int id) {
        return modelMapper.map(contaService.buscarContaId(id), ExibirContaDetalhadaDTO.class);
    }

    @PutMapping("/{id}")
    public SaidaContaDTO atualizarPagamento(@PathVariable int id, @RequestBody AtualizarContaDTO atualizarConta) {
        Conta contaAtualizada = contaService.atualizarStatusConta(id);

        if (atualizarConta.getStatus() == Status.PAGO) {
            return modelMapper.map(contaAtualizada, SaidaContaDTO.class);
        }

        throw new StatusPagamentoIncorreto("Status para pagamento inválido!");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarConta(@PathVariable int id) {
        contaService.excluirConta(id);
    }

}
