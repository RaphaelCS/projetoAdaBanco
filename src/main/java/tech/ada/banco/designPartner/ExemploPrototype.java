package tech.ada.banco.designPartner;

import tech.ada.banco.model.ClientePF;
import tech.ada.banco.model.Conta;
import tech.ada.banco.model.ContaCorrente;

import java.math.BigDecimal;

public class ExemploPrototype {

    public static void main(String[] args) {

        Conta conta = new ContaCorrente();
        conta.setNumero(1);
        conta.setCliente(new ClientePF());
        conta.setSaldo(BigDecimal.ZERO);

        Conta conta2 = conta.clone();

        System.out.println(conta.equals(conta2));
        System.out.println(conta==conta2);
    }

}
