package br.com.zup.zup_contas.config;

import br.com.zup.zup_contas.contas.exceptions.ContaNaoEncontrada;
import br.com.zup.zup_contas.contas.exceptions.NaoExisteCadastro;
import br.com.zup.zup_contas.contas.exceptions.StatusPagamentoIncorreto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<MensagemErroValidacao> manipularErrosValidacao(MethodArgumentNotValidException exception) {
        List<MensagemErroValidacao> erros = new ArrayList<>();

        for (FieldError referencia : exception.getFieldErrors()) {
            MensagemErroValidacao erroValidacao = new MensagemErroValidacao(referencia.getField(),
                    referencia.getDefaultMessage());
            erros.add(erroValidacao);
        }

        return erros;

    }

    @ExceptionHandler(ContaNaoEncontrada.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemErroExcecao manipularContaNaoEncontrada(ContaNaoEncontrada exception) {
        return new MensagemErroExcecao(exception.getMessage());
    }

    @ExceptionHandler(NaoExisteCadastro.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemErroExcecao manipularNaoExisteCadastro(NaoExisteCadastro exception) {
        return new MensagemErroExcecao(exception.getMessage());
    }

    @ExceptionHandler(StatusPagamentoIncorreto.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MensagemErroExcecao manipularStatusPagamentoIncorreto(StatusPagamentoIncorreto exception) {
        return new MensagemErroExcecao(exception.getMessage());
    }

}
