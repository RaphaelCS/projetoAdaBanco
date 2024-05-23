package tech.ada.banco.service;

import tech.ada.banco.dto.ContaDTO;
import tech.ada.banco.exception.SaldoExistenteException;
import tech.ada.banco.exception.SaldoInsuficienteException;
import tech.ada.banco.exception.ValorInvalidoException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContaServiceInterface {

    List<ContaDTO> listarContas(UUID clienteUuid);

    ContaDTO salvar(ContaDTO contaDTO);

    Optional<ContaDTO> buscarPorUuid(UUID uuid);

    void excluir(UUID uuid) throws SaldoExistenteException;

    ContaDTO sacar(ContaDTO contaDTO) throws SaldoInsuficienteException, ValorInvalidoException ;

    ContaDTO deposito(ContaDTO contaDTO) throws ValorInvalidoException;

    ContaDTO consutarSaldo(UUID uuid);

    ContaDTO transferir(ContaDTO contaDTO) throws ValorInvalidoException, SaldoInsuficienteException;

    void validarUsuario(String CpfCnpj, ContaDTO contaDTO);
}
