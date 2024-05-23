package tech.ada.banco.model;

import lombok.*;

import javax.persistence.*;

@DiscriminatorValue("1")
@Entity
@Data
@Builder
public class ContaCorrente extends Conta {

    public ContaCorrente(){super();}

    public ContaCorrente(ContaCorrente origem){
        super(origem);
    }

    @Override
    public Conta clone() {
        return new ContaCorrente(this);
    }

/*
    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

 */
}
