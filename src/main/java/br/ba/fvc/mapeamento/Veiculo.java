package br.ba.fvc.mapeamento;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private Integer numero;

    @Column(name = "placa")
    private String placa;

    @Column(name = "fabricante")
    private String fabricante;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "anoModelo")
    private int anoModelo;

    @Column(name = "qtdPortas")
    private int qtdPortas;

    @Column(name = "acessorios")
    private String acessorios;

    public Veiculo() {
    }

    public Veiculo(Integer numero) {
        this.numero = numero;
    }

    public Veiculo(Integer numero, String placa, String fabricante, String modelo, int anoModelo, int qtdPortas, String acessorios) {
        this.numero = numero;
        this.placa = placa;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.anoModelo = anoModelo;
        this.qtdPortas = qtdPortas;
        this.acessorios = acessorios;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public int getQtdPortas() {
        return qtdPortas;
    }

    public void setQtdPortas(int qtdPortas) {
        this.qtdPortas = qtdPortas;
    }

    public String getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(String acessorios) {
        this.acessorios = acessorios;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "placa=" + placa + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.numero);
        hash = 83 * hash + Objects.hashCode(this.placa);
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
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }

}
