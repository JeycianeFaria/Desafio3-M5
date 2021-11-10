package br.com.zup.zup_contas.contas;

import br.com.zup.zup_contas.contas.dtos.CadastroContaDTO;
import br.com.zup.zup_contas.contas.dtos.SaidaContaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
