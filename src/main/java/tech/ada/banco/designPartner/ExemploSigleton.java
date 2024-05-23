package tech.ada.banco.designPartner;

import java.math.BigDecimal;

class ExemploSigleton {
    public static void main(String[] args) {
        Conta conta = Conta.getInstance(1, BigDecimal.valueOf(100));
        Conta conta2 = Conta.getInstance(2,BigDecimal.valueOf(1000));
        System.out.println(conta.getNumero());
        System.out.println(conta2.getNumero());
    }
}
class Conta{


    private Integer numero;
    private BigDecimal saldo;

    public Integer getNumero() {
        return numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    private static Conta instance;

    private Conta(Integer numero, BigDecimal saldo){
        this.numero = numero;
        this.saldo = saldo;
    }

    public static Conta getInstance(Integer numero, BigDecimal saldo){
        if(instance==null){
            instance = new Conta(numero, saldo);
        }
        return instance;
    }
}

