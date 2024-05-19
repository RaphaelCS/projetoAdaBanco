package tech.ada.banco.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tech.ada.banco.dto.ClientePJDTO;
import tech.ada.banco.enums.StatusEnum;
import tech.ada.banco.exception.ValorInvalidoException;
import tech.ada.banco.model.ClientePJ;
import tech.ada.banco.repository.ClienteRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientePJService {
    
    private final ClienteRepository<ClientePJ> clienteRepository;

    private final ModelMapper modelMapper;

    private ClientePJDTO convertDto(ClientePJ cliente){
        return modelMapper.map(cliente, ClientePJDTO.class);
    }

    private ClientePJ convertFromDto(ClientePJDTO clienteDTO){
        return modelMapper.map(clienteDTO, ClientePJ.class);
    }

    public List<ClientePJDTO> listarClientes(){
        return clienteRepository.findAll().stream().map(this::convertDto).collect(Collectors.toList());
    }

    public ClientePJDTO salvar(ClientePJDTO clienteDTO) {
        var cliente = convertFromDto(clienteDTO);
        cliente.setUuid(UUID.randomUUID());
        cliente.setDataCadastro(LocalDate.now());
        cliente.setStatus(StatusEnum.ATIVO);
        return convertDto(clienteRepository.save(cliente));
    }

    public Optional<ClientePJDTO> buscarPorUuid(UUID uuid){
        return clienteRepository.findByUuid(uuid).map(this::convertDto);
    }

    public void excluir(UUID uuid){
        clienteRepository.delete(clienteRepository.findByUuid(uuid).orElseThrow());
    }

    public ClientePJDTO atualizar(ClientePJDTO clienteDTO){
        var cliente = clienteRepository.findByUuid(clienteDTO.getUuid()).orElseThrow();
        cliente.setNome(clienteDTO.getNome());
        return convertDto(clienteRepository.save(cliente));
    }
}
