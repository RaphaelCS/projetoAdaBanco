package tech.ada.banco.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@DiscriminatorValue("1")
@Entity
@Getter
@Setter
@AllArgsConstructor
public class ContaCorrente extends Conta {

/*
    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

 */
}
