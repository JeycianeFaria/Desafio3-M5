package br.com.zup.zup_contas.contas;

import br.com.zup.zup_contas.contas.dtos.AtualizarContaDTO;
import br.com.zup.zup_contas.contas.dtos.CadastroContaDTO;
import br.com.zup.zup_contas.contas.dtos.ExibirContaDTO;
import br.com.zup.zup_contas.contas.dtos.SaidaContaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;
    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaidaContaDTO cadastrarConta(@RequestBody CadastroContaDTO cadastroConta){
        Conta contaRecibida = modelMapper.map(cadastroConta, Conta.class);
        contaService.salvarConta(contaRecibida);

        return modelMapper.map(contaRecibida, SaidaContaDTO.class);
    }

    @GetMapping
    public List<ExibirContaDTO> exibirListaContas(){
        List<ExibirContaDTO> listaContas = new ArrayList<>();

        for (Conta referencia: contaService.exibirContasSalvas()) {
            ExibirContaDTO exibirContaDTO = modelMapper.map(referencia,ExibirContaDTO.class);
            listaContas.add(exibirContaDTO);
        }

        return listaContas;
    }

    @PutMapping("/{id}")
    public SaidaContaDTO atualizarPagamento(@PathVariable int id, @RequestBody AtualizarContaDTO atualizarConta){
        Conta contaAtualizada = contaService.atulizarStatusConta(id);

        return modelMapper.map(contaAtualizada, SaidaContaDTO.class);
    }

}
