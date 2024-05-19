package tech.ada.banco.controller;

import tech.ada.banco.dto.ClientePFDTO;
import tech.ada.banco.exception.ValorInvalidoException;
import tech.ada.banco.service.ClientePFService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientesPF")
@RequiredArgsConstructor
public class ClientePFController {

    private final ClientePFService service;
    private final ModelMapper modelMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).FUNCIONARIO.name())")
    public List<ClientePFDTO> listarTodos(){
        return service.listarClientes().stream().collect(Collectors.toList());
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).FUNCIONARIO.name())")
    public ResponseEntity<ClientePFDTO> buscarPorUuid(@PathVariable("uuid")UUID uuid){
        var cliente = service.buscarPorUuid(uuid);
        return cliente.map(ClientePFDTO -> ResponseEntity.ok(modelMapper.map(ClientePFDTO, ClientePFDTO.class))).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).FUNCIONARIO.name())")
    public ResponseEntity excluir(@PathVariable("uuid") UUID uuid){
        service.excluir(uuid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{uuid}")
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).FUNCIONARIO.name())")
    public ResponseEntity<ClientePFDTO> atualizar(@PathVariable("uuid") UUID uuid, @RequestBody ClientePFDTO clientePFDTO){
        clientePFDTO.setUuid(uuid);
        return new ResponseEntity(service.atualizar(clientePFDTO), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).FUNCIONARIO.name())")
    public ResponseEntity<ClientePFDTO> inserir(@Valid @RequestBody ClientePFDTO clientePFDTO){
        try {
            return new ResponseEntity(service.salvar(clientePFDTO), HttpStatus.OK);
        } catch (ValorInvalidoException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
