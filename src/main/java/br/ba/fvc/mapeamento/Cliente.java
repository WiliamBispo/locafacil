package br.ba.fvc.mapeamento;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cpf", length = 14)
    private String cpf;

    @NotEmpty
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotEmpty
    @Column(name = "telefone", nullable = false, length = 15)
    private String telefone;

    @NotEmpty
    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @NotEmpty
    @Column(name = "endereco", nullable = false, length = 200)
    private String endereco;

    @NotEmpty
    @Column(name = "uf", nullable = false, length = 2)
    private String uf;

    public Cliente() {
    }

    public Cliente(String cpf) {
        this.cpf = cpf;
    }

    public Cliente(String cpf, String nome, String telefone, String email, String endereco, String uf) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.uf = uf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Cliente{" + "cpf=" + cpf + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }

}
