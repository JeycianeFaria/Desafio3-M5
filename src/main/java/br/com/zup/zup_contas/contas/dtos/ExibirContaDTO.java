package br.com.zup.zup_contas.contas.dtos;

import br.com.zup.zup_contas.contas.enuns.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ExibirContaDTO {

    private int id;
    private String nome;
    private double valor;
    private Status status;

}
