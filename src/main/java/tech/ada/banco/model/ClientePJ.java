package tech.ada.banco.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@DiscriminatorValue("2")
@Builder
public class ClientePJ extends Cliente{

    @Column(unique = true)
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }
/*
    public ClientePJ(String nome, String cpnj) {
        super(nome);
        this.cnpj = cpnj;
        BancoDadosService.incluirCliente(this);
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientePJ clientePJ = (ClientePJ) o;
        return Objects.equals(cnpj, clientePJ.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }
}
