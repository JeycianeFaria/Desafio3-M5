package br.com.zup.zup_contas.contas.dtos;

import br.com.zup.zup_contas.contas.enuns.Status;
import br.com.zup.zup_contas.contas.enuns.Tipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class SaidaContaDTO {

    private int id;
    private String nome;
    private double valor;
    private Tipo tipo;
    private LocalDate dataDeVencimento;
    private LocalDateTime dataDePagamento;
    private Status status;

}
