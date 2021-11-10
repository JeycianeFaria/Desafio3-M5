package br.com.zup.zup_contas.contas.dtos;

import br.com.zup.zup_contas.contas.enuns.Tipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class CadastroContaDTO {

    private String nome;
    private double valor;
    private Tipo tipo;
    private LocalDate dataDeVencimento;

}
