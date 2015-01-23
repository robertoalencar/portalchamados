package br.com.copergas.portalchamados.model.ticket;

import java.util.Date;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeNegocio;
import br.com.copergas.portalchamados.model.usuario.Usuario;

public interface Ticket extends EntidadeNegocio {

    
    String BEAN_NAME = "ticket";
    
    String ATRIBUTO_NUMERO = "numero";
    String ATRIBUTO_MAQUINA_ORIGEM = "maquinaOrigem";
    String ATRIBUTO_USUARIO = "usuario";
    String ATRIBUTO_CATEGORIA = "categoria";
    String ATRIBUTO_SUB_CATEGORIA = "subCategoria";
    String ATRIBUTO_DATA_CRIACAO = "dataCriacao";
    String ATRIBUTO_DATA_VENCIMENTO = "dataVencimento";
    String ATRIBUTO_DATA_FECHAMENTO = "dataFechamento";
    String ATRIBUTO_TITULO = "titulo";
    String ATRIBUTO_DESCRICAO = "descricao";
    String ATRIBUTO_FECHAMENTO_ENCAMINHADO = "fechamentoEncaminhado";
    String ATRIBUTO_SETOR = "setor";
    String ATRIBUTO_SOLICITANTE = "solicitante";
    String ATRIBUTO_TIPO_CHAMADO = "tipoChamado";
    String ATRIBUTO_ORIGEM_CHAMADO = "origemChamado";
    String ATRIBUTO_SISTEMA = "sistema";
    String ATRIBUTO_STATUS_COPERGAS = "statusCopergas";
    String ATRIBUTO_STATUS_FORNECEDOR = "statusFornecedor";
    String ATRIBUTO_STATUS = "status";
    String ATRIBUTO_PRIORIDADE = "prioridade";
    String ATRIBUTO_FOI_REABERTO = "foiReaberto";
    String ATRIBUTO_TIPO_ATENDIMENTO = "tipoAtendimento";
    
    String LABEL_NUMERO = "Número";
    String LABEL_MAQUINA_ORIGEM = "Máquina Origem";
    String LABEL_USUARIO = "Usuário";
    String LABEL_CATEGORIA = "Categoria";
    String LABEL_SUB_CATEGORIA = "Subcategoria";
    String LABEL_DATA_CRIACAO = "Data Criação";
    String LABEL_DATA_VENCIMENTO = "Data Vencimento";
    String LABEL_DATA_FECHAMENTO = "Data Fechamento";
    String LABEL_TITULO = "Título";
    String LABEL_DESCRICAO = "Descrição";
    String LABEL_FECHAMENTO_ENCAMINHADO = "Fechamento Encaminhado";
    String LABEL_SETOR = "Setor";
    String LABEL_SOLICITANTE = "Solicitante";
    String LABEL_TIPO_CHAMADO = "Tipo Chamado";
    String LABEL_ORIGEM_CHAMADO = "Origem Chamado";
    String LABEL_SISTEMA = "Sistema";
    String LABEL_STATUS_COPERGAS = "Status Copergas";
    String LABEL_STATUS_FORNECEDOR = "Status Fornecedor";
    String LABEL_STATUS = "Status";
    String LABEL_PRIORIDADE = "Prioridade";
    String LABEL_FOI_REABERTO = "Foi Reaberto";
    String LABEL_TIPO_ATENDIMENTO = "Tipo Atendimento";
    

    String getNumero();

    void setNumero(String numero);

    String getMaquinaOrigem();

    void setMaquinaOrigem(String maquinaOrigem);
    
    Usuario getUsuario();

    void setUsuario(Usuario usuario);

    TicketCategoria getCategoria();

    void setCategoria(TicketCategoria categoria);

    TicketSubCategoria getSubCategoria();

    void setSubCategoria(TicketSubCategoria subCategoria);

    Date getDataCriacao();

    void setDataCriacao(Date dataCriacao);

    Date getDataVencimento();

    void setDataVencimento(Date dataVencimento);

    Date getDataFechamento();

    void setDataFechamento(Date dataFechamento);

    String getTitulo();

    void setTitulo(String titulo);

    String getDescricao();

    void setDescricao(String descricao);

    Boolean getFechamentoEncaminhado();

    void setFechamentoEncaminhado(Boolean fechamentoEncaminhado);

    Setor getSetor();

    void setSetor(Setor setor);

    String getSolicitante();

    void setSolicitante(String solicitante);

    TipoChamado getTipoChamado();

    void setTipoChamado(TipoChamado tipoChamado);

    OrigemChamado getOrigemChamado();

    void setOrigemChamado(OrigemChamado origemChamado);

    Sistema getSistema();

    void setSistema(Sistema sistema);

    Status getStatus();

    void setStatus(Status status);

    Prioridade getPrioridade();

    void setPrioridade(Prioridade prioridade);

    Boolean getFoiReaberto();

    void setFoiReaberto(Boolean foiReaberto);

    TipoAtendimento getTipoAtendimento();

    void setTipoAtendimento(TipoAtendimento tipoAtendimento);

    StatusCopergas getStatusCopergas();

    void setStatusCopergas(StatusCopergas statusCopergas);
    
    StatusFornecedor getStatusFornecedor();

    void setStatusFornecedor(StatusFornecedor statusFornecedor);
 
    Integer getQtdDiasAbertos();

    void setQtdDiasAbertos(Integer qtdDiasAbertos);

    Integer getQtdDiasParaVencimento();

    void setQtdDiasParaVencimento(Integer qtdDiasParaVencimento);
 
    Boolean getUltrapassouSLA();

    void setUltrapassouSLA(Boolean ultrapassouSLA);
    
}