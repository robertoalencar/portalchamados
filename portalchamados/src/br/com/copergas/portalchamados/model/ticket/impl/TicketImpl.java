package br.com.copergas.portalchamados.model.ticket.impl;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.copergas.portalchamados.model.comum.negocio.impl.EntidadeNegocioImpl;
import br.com.copergas.portalchamados.model.ticket.OrigemChamado;
import br.com.copergas.portalchamados.model.ticket.Prioridade;
import br.com.copergas.portalchamados.model.ticket.Setor;
import br.com.copergas.portalchamados.model.ticket.Sistema;
import br.com.copergas.portalchamados.model.ticket.Status;
import br.com.copergas.portalchamados.model.ticket.StatusCopergas;
import br.com.copergas.portalchamados.model.ticket.StatusFornecedor;
import br.com.copergas.portalchamados.model.ticket.Ticket;
import br.com.copergas.portalchamados.model.ticket.TicketCategoria;
import br.com.copergas.portalchamados.model.ticket.TicketSubCategoria;
import br.com.copergas.portalchamados.model.ticket.TipoAtendimento;
import br.com.copergas.portalchamados.model.ticket.TipoChamado;
import br.com.copergas.portalchamados.model.usuario.Usuario;
import br.com.copergas.portalchamados.model.usuario.impl.UsuarioImpl;

/**
 * 
 * @author Roberto Alencar
 */
@Entity
@Table(name = "TICKET")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "TICKET_SEQ", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class TicketImpl extends EntidadeNegocioImpl implements Ticket  {

    @Column(name = "NUMERO")
    private String numero;
    
    @Column(name = "MAQUINA_ORIGEM")
    private String maquinaOrigem;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity=UsuarioImpl.class) 
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="USUARIO_ID", insertable=true, updatable=true)
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity=TicketCategoriaImpl.class) 
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="CATEGORIA_ID", insertable=true, updatable=true)
    private TicketCategoria categoria;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity=TicketSubCategoriaImpl.class) 
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="SUB_CATEGORIA_ID", insertable=true, updatable=true)
    private TicketSubCategoria subCategoria;
    
    @Column(name = "DT_CRIACAO")
    private Date dataCriacao;
    
    @Column(name = "DT_VENCIMENTO")
    private Date dataVencimento;
    
    @Column(name = "DT_FECHAMENTO")
    private Date dataFechamento;
    
    @Column(name = "TITULO")
    private String titulo;
    
    @Column(name = "DESCRICAO")
    private String descricao;
    
    @Column(name = "FECHAMENTO_ENCAMINHADO")
    private Boolean fechamentoEncaminhado;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity=SetorImpl.class) 
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="SETOR_ID", insertable=true, updatable=true)
    private Setor setor;
    
    @Column(name = "SOLICITANTE")
    private String solicitante;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity=TipoChamadoImpl.class) 
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="TIPO_CHAMADO_ID", insertable=true, updatable=true)
    private TipoChamado tipoChamado;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity=OrigemChamadoImpl.class) 
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="ORIGEM_CHAMADO_ID", insertable=true, updatable=true)
    private OrigemChamado origemChamado;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity=SistemaImpl.class) 
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="SISTEMA_ID", insertable=true, updatable=true)
    private Sistema sistema;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity=StatusCopergasImpl.class) 
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="STATUS_COPERGAS_ID", insertable=true, updatable=true)
    private StatusCopergas statusCopergas;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity=StatusFornecedorImpl.class) 
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="STATUS_FORNECEDOR_ID", insertable=true, updatable=true)
    private StatusFornecedor statusFornecedor;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity=StatusImpl.class) 
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="STATUS_ID", insertable=true, updatable=true)
    private Status status;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity=PrioridadeImpl.class) 
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="PRIORIDADE_ID", insertable=true, updatable=true)
    private Prioridade prioridade;
    
    @Column(name = "FOI_REABERTO")
    private Boolean foiReaberto;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity=TipoAtendimentoImpl.class) 
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="TIPO_ATENDIMENTO_ID", insertable=true, updatable=true)
    private TipoAtendimento tipoAtendimento;
    
    @Transient
    private Integer qtdDiasAbertos;
    @Transient
    private Integer qtdDiasParaVencimento;
    @Transient
    private Boolean ultrapassouSLA;
    
    @Override
    public String getNumero() {
        return numero;
    }

    @Override
    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String getMaquinaOrigem() {
        return maquinaOrigem;
    }

    @Override
    public void setMaquinaOrigem(String maquinaOrigem) {
        this.maquinaOrigem = maquinaOrigem;
    }

    @Override
    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public TicketCategoria getCategoria() {
        return categoria;
    }

    @Override
    public void setCategoria(TicketCategoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public TicketSubCategoria getSubCategoria() {
        return subCategoria;
    }

    @Override
    public void setSubCategoria(TicketSubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }

    @Override
    public Date getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public Date getDataVencimento() {
        return dataVencimento;
    }

    @Override
    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    @Override
    public Date getDataFechamento() {
        return dataFechamento;
    }

    @Override
    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public Boolean getFechamentoEncaminhado() {
        return fechamentoEncaminhado;
    }

    @Override
    public void setFechamentoEncaminhado(Boolean fechamentoEncaminhado) {
        this.fechamentoEncaminhado = fechamentoEncaminhado;
    }

    @Override
    public Setor getSetor() {
        return setor;
    }

    @Override
    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    @Override
    public String getSolicitante() {
        return solicitante;
    }

    @Override
    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    @Override
    public TipoChamado getTipoChamado() {
        return tipoChamado;
    }

    @Override
    public void setTipoChamado(TipoChamado tipoChamado) {
        this.tipoChamado = tipoChamado;
    }

    @Override
    public OrigemChamado getOrigemChamado() {
        return origemChamado;
    }

    @Override
    public void setOrigemChamado(OrigemChamado origemChamado) {
        this.origemChamado = origemChamado;
    }

    @Override
    public Sistema getSistema() {
        return sistema;
    }

    @Override
    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Prioridade getPrioridade() {
        return prioridade;
    }

    @Override
    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public Boolean getFoiReaberto() {
        return foiReaberto;
    }

    @Override
    public void setFoiReaberto(Boolean foiReaberto) {
        this.foiReaberto = foiReaberto;
    }

    @Override
    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    @Override
    public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }
    
    @Override
    public StatusCopergas getStatusCopergas() {
        return statusCopergas;
    }

    @Override
    public void setStatusCopergas(StatusCopergas statusCopergas) {
        this.statusCopergas = statusCopergas;
    }

    @Override
    public StatusFornecedor getStatusFornecedor() {
        return statusFornecedor;
    }

    @Override
    public void setStatusFornecedor(StatusFornecedor statusFornecedor) {
        this.statusFornecedor = statusFornecedor;
    }

    public Integer getQtdDiasAbertos() {
	
	if (Status.VALOR_STATUS_OPEN.equals(this.status.getChavePrimaria())) {

	    Calendar dataAtual = Calendar.getInstance();
	    Calendar dataCadastro = Calendar.getInstance();
	    dataCadastro.setTime(this.dataCriacao);

	    int diferencaAno = dataAtual.get(Calendar.YEAR) - dataCadastro.get(Calendar.YEAR);
	    if (diferencaAno == 0) {

		qtdDiasAbertos = dataAtual.get(Calendar.DAY_OF_YEAR) - dataCadastro.get(Calendar.DAY_OF_YEAR);

	    } else if (diferencaAno == 1) {

		int totAnoAnterior = 365 - dataCadastro.get(Calendar.DAY_OF_YEAR);
		qtdDiasAbertos = dataAtual.get(Calendar.DAY_OF_YEAR) + totAnoAnterior;

	    } else if (diferencaAno == 2) {

		int totAnoAnterior = 365 - dataCadastro.get(Calendar.DAY_OF_YEAR);
		qtdDiasAbertos = dataAtual.get(Calendar.DAY_OF_YEAR) + totAnoAnterior + 365;

	    } else if (diferencaAno == 3) {

		int totAnoAnterior = 365 - dataCadastro.get(Calendar.DAY_OF_YEAR);
		qtdDiasAbertos = dataAtual.get(Calendar.DAY_OF_YEAR) + totAnoAnterior + 730;
	    }
	}
	
        return qtdDiasAbertos;
    }

    public void setQtdDiasAbertos(Integer qtdDiasAbertos) {
        this.qtdDiasAbertos = qtdDiasAbertos;
    }

    public Integer getQtdDiasParaVencimento() {
	
	if (Status.VALOR_STATUS_OPEN.equals(this.status.getChavePrimaria())) {

	    if (dataVencimento != null) {

		Calendar dataAtual = Calendar.getInstance();
		Calendar dataVencimento = Calendar.getInstance();
		dataVencimento.setTime(this.dataVencimento);

		qtdDiasParaVencimento = dataVencimento.get(Calendar.DAY_OF_YEAR) - dataAtual.get(Calendar.DAY_OF_YEAR);
	    }
	}
	
        return qtdDiasParaVencimento;
    }

    public void setQtdDiasParaVencimento(Integer qtdDiasParaVencimento) {
        this.qtdDiasParaVencimento = qtdDiasParaVencimento;
    }

    public Boolean getUltrapassouSLA() {
        return ultrapassouSLA;
    }

    public void setUltrapassouSLA(Boolean ultrapassouSLA) {
        this.ultrapassouSLA = ultrapassouSLA;
    }

    public String toString() {
	return "[" + numero + "] - " + titulo;
    }
}
