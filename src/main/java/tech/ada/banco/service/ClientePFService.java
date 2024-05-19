package tech.ada.banco.service;

import tech.ada.banco.dto.ClientePFDTO;
import tech.ada.banco.enums.StatusEnum;
import tech.ada.banco.exception.ValorInvalidoException;
import tech.ada.banco.model.ClientePF;
import tech.ada.banco.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientePFService {

    private final ClienteRepository<ClientePF> clienteRepository;

    private final ModelMapper modelMapper;

    private ClientePFDTO convertDto(ClientePF cliente){
        return modelMapper.map(cliente, ClientePFDTO.class);
    }

    private ClientePF convertFromDto(ClientePFDTO clienteDTO){
        return modelMapper.map(clienteDTO, ClientePF.class);
    }

    public List<ClientePFDTO> listarClientes(){
        return clienteRepository.findAll().stream().map(this::convertDto).collect(Collectors.toList());
    }

    public ClientePFDTO salvar(ClientePFDTO clienteDTO) throws ValorInvalidoException {
       if(clienteDTO.getDataNascimento().isAfter(LocalDate.now().minusYears(18))){
            throw new ValorInvalidoException("Cliente não pode ser menor de 18 anos");
        }

        var cliente = convertFromDto(clienteDTO);
        cliente.setUuid(UUID.randomUUID());
        cliente.setDataCadastro(LocalDate.now());
        cliente.setStatus(StatusEnum.ATIVO);
        return convertDto(clienteRepository.save(cliente));
    }

    public Optional<ClientePFDTO> buscarPorUuid(UUID uuid){
        return clienteRepository.findByUuid(uuid).map(this::convertDto);
    }

    public void excluir(UUID uuid){
        clienteRepository.delete(clienteRepository.findByUuid(uuid).orElseThrow());
    }

    public ClientePFDTO atualizar(ClientePFDTO clienteDTO){
        var cliente = clienteRepository.findByUuid(clienteDTO.getUuid()).orElseThrow();
        cliente.setNome(clienteDTO.getNome());
        return convertDto(clienteRepository.save(cliente));
    }
}
