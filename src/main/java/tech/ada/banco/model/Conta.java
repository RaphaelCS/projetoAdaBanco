package tech.ada.banco.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta" , discriminatorType = DiscriminatorType.INTEGER)
@NoArgsConstructor
public abstract class Conta{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(columnDefinition = "UUID", nullable = false)
    private UUID uuid;

    @Column(unique = true)
    private Integer numero;

    @Column
    private BigDecimal saldo;

    @Column
    private LocalDate dataCriacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Conta(Conta origem){
        if(origem!= null){
            this.cliente = origem.cliente;
            this.saldo = origem.saldo;
            this.numero = origem.numero;
        }
    }

    public abstract Conta clone();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(numero, conta.numero) && Objects.equals(saldo, conta.saldo) && Objects.equals(cliente, conta.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, saldo, cliente);
    }


/*
    public Conta(Cliente cliente)  {
        this.numero = BancoDadosService.getNumeroConta();
        saldo = BigDecimal.ZERO;
        dataCriacao = LocalDate.now();
        uuid = UUID.randomUUID();
        this.cliente = cliente;
        this.cliente.setConta(this);
        BancoDadosService.incluirConta(this);
    }
*/
}




