package br.com.copergas.portalchamados.model.ticket;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeRelacionamento;

/**
 * @author Roberto Alencar
 * 
 */
public interface TicketSubCategoria extends EntidadeRelacionamento {

    String BEAN_NAME = "ticketSubCategoria";
    String ATRIBUTO_TICKET_CATEGORIA = "ticketCategoria";

    TicketCategoria getTicketCategoria();
    void setTicketCategoria(TicketCategoria ticketCategoria);

}