package tech.ada.banco.designPartner;

import tech.ada.banco.dto.ClientePFDTO;
import tech.ada.banco.dto.ContaDTO;
import tech.ada.banco.exception.SaldoExistenteException;
import tech.ada.banco.exception.SaldoInsuficienteException;
import tech.ada.banco.exception.ValorInvalidoException;
import tech.ada.banco.model.Conta;
import tech.ada.banco.model.ContaCorrente;
import tech.ada.banco.service.ContaCorrentePFService;
import tech.ada.banco.service.ContaServiceInterface;
import tech.ada.banco.service.consultaSaldo.ConsultaSaldo;
import tech.ada.banco.service.deposito.Deposito;
import tech.ada.banco.service.saque.SaquePFImpl;
import tech.ada.banco.service.transferencia.TransferenciaPFImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

    interface ContaService{
        void sacar(Conta conta, BigDecimal valor);
        void depositar(Conta conta, BigDecimal valor);
        BigDecimal consultarSaldo(Conta conta);
        void transferir(Conta conta, BigDecimal valor, Conta contaDestino);
    }

    class ContaCorrentePF implements ContaService {

        @Override
        public void sacar(Conta conta, BigDecimal valor) {
            conta.setSaldo(conta.getSaldo().subtract(valor));
        }

        @Override
        public void depositar(Conta conta, BigDecimal valor) {
            conta.setSaldo(conta.getSaldo().add(valor));
        }

        @Override
        public BigDecimal consultarSaldo(Conta conta) {
            return conta.getSaldo();
        }

        @Override
        public void transferir(Conta conta, BigDecimal valor, Conta contaDestino) {
            conta.setSaldo(conta.getSaldo().subtract(valor));
            contaDestino.setSaldo(contaDestino.getSaldo().subtract(valor));
        }
    }

    class ContaCorrentePJ implements ContaService {

        @Override
        public void sacar(Conta conta, BigDecimal valor) {
            BigDecimal taxa = valor.multiply(BigDecimal.valueOf(0.005));
            conta.setSaldo(conta.getSaldo().subtract(valor).subtract(taxa));
        }

        @Override
        public void depositar(Conta conta, BigDecimal valor) {
            conta.setSaldo(conta.getSaldo().add(valor));
        }

        @Override
        public BigDecimal consultarSaldo(Conta conta) {
            return conta.getSaldo();
        }

        @Override
        public void transferir(Conta conta, BigDecimal valor, Conta contaDestino) {
            conta.setSaldo(conta.getSaldo().subtract(valor));
            contaDestino.setSaldo(contaDestino.getSaldo().subtract(valor));
        }
    }

class ExemploBridge {

    private ContaService service;

    public void setService(ContaService service) {
        this.service = service;
    }

    ExemploBridge(ContaService service){
        this.service = service;
    }

    public void sacar(Conta conta, BigDecimal valor){
        service.sacar(conta,valor);
    }
    public void depositar(Conta conta, BigDecimal valor){
        service.depositar(conta,valor);
    }
    public BigDecimal consultarSaldo(Conta conta){
        return service.consultarSaldo(conta);
    }
    public void transferir(Conta conta, BigDecimal valor, Conta contaDestino){
        service.transferir(conta,valor,contaDestino);
    }


    public static void main(String[] args) {
        Conta conta = new ContaCorrente();
        conta.setNumero(1);
        conta.setSaldo(BigDecimal.ZERO);

        ExemploBridge bridge = new ExemploBridge(new ContaCorrentePF());
        bridge.depositar(conta,BigDecimal.valueOf(100));
        bridge.sacar(conta,BigDecimal.valueOf(50));
        System.out.println(bridge.consultarSaldo(conta));

        bridge.setService(new ContaCorrentePJ());
        bridge.sacar(conta, BigDecimal.valueOf(10));
        System.out.println(bridge.consultarSaldo(conta));
    }
}
