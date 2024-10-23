package br.ba.fvc.mapeamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "aluguel")
public class Aluguel implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAluguel")
    private int idAluguel;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataAluguel")
    private Date dataAluguel;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataEntrega")
    private Date dataEntrega;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "entregue", nullable = false)
    private String entregue = "N";

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "valorPago")
    private BigDecimal valorPago;

    public Aluguel() {
    }

    public Aluguel(int idAluguel) {
        this.idAluguel = idAluguel;
    }

    public Aluguel(int idAluguel, Veiculo veiculo, Date dataAluguel, Date dataEntrega, Cliente cliente, String entregue, String observacao, BigDecimal valoPago) {
        this.idAluguel = idAluguel;
        this.veiculo = veiculo;
        this.dataAluguel = dataAluguel;
        this.dataEntrega = dataEntrega;
        this.cliente = cliente;
        this.entregue = entregue;
        this.observacao = observacao;
        this.valorPago = valoPago;
    }

    public int getIdAluguel() {
        return idAluguel;
    }

    public void setIdAluguel(int idAluguel) {
        this.idAluguel = idAluguel;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Date getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(Date dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEntregue() {
        return entregue;
    }

    public void setEntregue(String entregue) {
        this.entregue = entregue;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    @Override
    public String toString() {
        return "Aluguel{" + "idAluguel=" + idAluguel + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idAluguel;
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
        final Aluguel other = (Aluguel) obj;
        if (this.idAluguel != other.idAluguel) {
            return false;
        }
        return true;
    }

}
