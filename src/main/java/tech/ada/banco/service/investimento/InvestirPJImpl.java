package tech.ada.banco.service.investimento;

import tech.ada.banco.exception.SaldoInsuficienteException;
import tech.ada.banco.exception.ValorInvalidoException;
import tech.ada.banco.model.ClientePJ;
import tech.ada.banco.model.ContaCorrente;
import tech.ada.banco.model.ContaInvestimento;

import java.math.BigDecimal;

public interface InvestirPJImpl extends Investir<ClientePJ> {
    BigDecimal RENDIMENTO = BigDecimal.valueOf(0.02);

    @Override
    default ContaInvestimento investir(ClientePJ cliente, ContaInvestimento conta, BigDecimal valor) throws ValorInvalidoException, SaldoInsuficienteException {
        if(valor.compareTo(BigDecimal.ZERO)<1){
            throw new ValorInvalidoException("Valor menor que zero ou igual a zero");
        }
        if(conta.getSaldo().compareTo(valor)<0) {
            throw new SaldoInsuficienteException();
        }

        conta.setSaldo(conta.getSaldo().add(valor).add(valor.multiply(RENDIMENTO)));
        return conta;
    }


}
