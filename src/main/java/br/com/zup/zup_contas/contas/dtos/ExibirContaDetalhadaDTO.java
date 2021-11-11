package br.com.zup.zup_contas.contas.dtos;

import br.com.zup.zup_contas.contas.enuns.Status;
import br.com.zup.zup_contas.contas.enuns.Tipo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ExibirContaDetalhadaDTO {

    private int id;
    private String nome;
    private double valor;
    private Tipo tipo;
    private LocalDate dataDeVencimento;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataDePagamento;
    private LocalDate dataCadastro;
    private Status status;

}
