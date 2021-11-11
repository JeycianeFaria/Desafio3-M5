package br.com.zup.zup_contas.config;

import br.com.zup.zup_contas.contas.exceptions.ContaNaoEncontrada;
import br.com.zup.zup_contas.contas.exceptions.NaoExisteCadastro;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ContaNaoEncontrada.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemErroExcecao manipularContaNaoEncontrada(ContaNaoEncontrada exception){
        return new MensagemErroExcecao(exception.getMessage());
    }

    @ExceptionHandler(NaoExisteCadastro.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemErroExcecao manipularNaoExisteCadastro(NaoExisteCadastro exception){
        return new MensagemErroExcecao(exception.getMessage());
    }

}
