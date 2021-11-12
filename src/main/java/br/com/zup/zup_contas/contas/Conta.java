package br.com.zup.zup_contas.contas;

import br.com.zup.zup_contas.contas.enuns.Status;
import br.com.zup.zup_contas.contas.enuns.Tipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "gerenciador_contas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    @Column(nullable = false)
    private String nome;
    @Setter
    @Column(nullable = false, precision = 10, scale = 3)
    private double valor;
    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @Setter
    @Column(nullable = false)
    private LocalDate dataDeVencimento;
    @Setter
    @Column(nullable = true)
    private LocalDateTime dataDePagamento;
    @Setter
    private LocalDate dataCadastro;
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

}
