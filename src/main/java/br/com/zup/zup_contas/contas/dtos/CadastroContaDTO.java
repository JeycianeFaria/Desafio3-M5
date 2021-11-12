package br.com.zup.zup_contas.contas.dtos;

import br.com.zup.zup_contas.contas.enuns.Tipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CadastroContaDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, message = "O nome deve conter no minimo 3 caracteres")
    private String nome;
    @Min(value = 1, message = "O valor é obrigatório e deve ser maior ou igual a 1.00")
    private double valor;
    @NotNull(message = "Tipo é obrigatório")
    private Tipo tipo;
    @NotNull(message = "A data de vencimento é obrigatória")
    private LocalDate dataDeVencimento;

}
