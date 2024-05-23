package tech.ada.banco.model;

import lombok.*;
import tech.ada.banco.designPartner.ClientePFBuilder;
import tech.ada.banco.enums.StatusEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("1")
public class ClientePF extends Cliente{

    @Column(unique = true)
    private String cpf;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientePF clientePF = (ClientePF) o;
        return Objects.equals(cpf, clientePF.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    public static ClientePFBuilder builder(){
        return new ClientePFBuilder();
    }
}
