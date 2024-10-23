package br.ba.fvc.beans;

import br.ba.fvc.mapeamento.Aluguel;
import br.ba.fvc.rn.AluguelRN;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "faturamentoBean")
@SessionScoped
public class FaturamentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    AluguelRN aluguelRN;
    private Date dataInicio;
    private Date dataFim;
    private BigDecimal totalFaturamento;
    private List<Aluguel> listaAlugueis;

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getTotalFaturamento() {
        return totalFaturamento;
    }

    public void setTotalFaturamento(BigDecimal totalFaturamento) {
        this.totalFaturamento = totalFaturamento;
    }

    public List<Aluguel> getListaAlugueis() {
        return listaAlugueis;
    }

    public void setListaAlugueis(List<Aluguel> listaAlugueis) {
        this.listaAlugueis = listaAlugueis;
    }

    public void calcularFaturamento() {
        aluguelRN = new AluguelRN();

        listaAlugueis = aluguelRN.pesquisarAlugueisPorPeriodo(dataInicio, dataFim);
        totalFaturamento = BigDecimal.ZERO;

        for (Aluguel aluguel : listaAlugueis) {
            totalFaturamento = totalFaturamento.add(aluguel.getValorPago());
        }

    }
    
    public void limparCampos(){
        dataInicio = null;
        dataFim = null;
        totalFaturamento = null;
    }

}
