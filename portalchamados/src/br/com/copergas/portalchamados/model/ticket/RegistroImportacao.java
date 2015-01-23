package br.com.copergas.portalchamados.model.ticket;

import java.util.Date;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeNegocio;

public interface RegistroImportacao extends EntidadeNegocio {
    
    String BEAN_NAME = "registroImportacao";
    
    Long VALOR_UNICO_REGISTRO_IMPORTACAO = 1L;
    
    String ATRIBUTO_QTD_TICKET_INSERIDOS = "qtdTicketInseridos";
    String ATRIBUTO_QTD_TICKET_ALTERADOS = "qtdTicketAlterados";
    String ATRIBUTO_DATA_ULTIMA_IMPORTACAO = "dataUltimaImportacao";
    
    String LABEL_QTD_TICKET_INSERIDOS = "Quantidade de Tickets Inseridos";
    String LABEL_QTD_TICKET_ALTERADOS = "Quantidade de Tickets Alterados";
    String LABEL_DATA_ULTIMA_IMPORTACAO = "Data da Última Importação";
    

    Integer getQtdTicketInseridos();

    void setQtdTicketInseridos(Integer qtdTicketInseridos);

    Integer getQtdTicketAlterados();

    void setQtdTicketAlterados(Integer qtdTicketAlterados);

    Integer getQtdTicketProcessados();

    void setQtdTicketProcessados(Integer qtdTicketProcessados);
    
    Date getDataUltimaImportacao();

    void setDataUltimaImportacao(Date dataUltimaImportacao);

}