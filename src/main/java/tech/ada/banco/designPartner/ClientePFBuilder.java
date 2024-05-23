package tech.ada.banco.designPartner;

import org.springframework.format.annotation.DateTimeFormat;
import tech.ada.banco.enums.StatusEnum;
import tech.ada.banco.model.ClientePF;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.UUID;

public class ClientePFBuilder {

    private Long id;

    private UUID uuid;

    private String nome;

    private LocalDate dataCadastro;

    private StatusEnum status;

    private String cpf;

    private LocalDate dataNascimento;

    public ClientePFBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ClientePFBuilder uuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public ClientePFBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClientePFBuilder dataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
        return this;
    }

    public ClientePFBuilder status(StatusEnum status) {
        this.status = status;
        return this;
    }

    public ClientePFBuilder cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public ClientePFBuilder dataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public ClientePF build(){
        return new ClientePF(cpf,dataNascimento,status);
    }
}

class ExemploBuilder{
    public static void main(String[] args) {
        ClientePF clientePF = ClientePF.builder().cpf("111111")
                .dataNascimento(LocalDate.now())
                .status(StatusEnum.ATIVO)
                .nome("Raphael")
                .build();
        System.out.println(clientePF);
    }
}
