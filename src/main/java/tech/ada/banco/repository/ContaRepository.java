package tech.ada.banco.repository;

import tech.ada.banco.model.Cliente;
import tech.ada.banco.model.ClientePF;
import tech.ada.banco.model.Conta;
import tech.ada.banco.model.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContaRepository<T extends Cliente, U extends Conta> extends JpaRepository<U, Long> {

    public Optional<U> findByUuid(UUID uuid);
    public List<U> findByCliente(Optional<T> cliente);
}
