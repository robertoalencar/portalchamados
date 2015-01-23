package br.com.copergas.portalchamados.model.ticket.impl;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.copergas.portalchamados.model.comum.negocio.impl.EntidadeRelacionamentoImpl;
import br.com.copergas.portalchamados.model.ticket.TicketCategoria;
import br.com.copergas.portalchamados.model.ticket.TicketSubCategoria;

/**
 * @author Roberto Alencar
 * 
 */
@Entity
@Table(name = "TICKET_SUB_CATEGORIA")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "TICKET_SUB_CATEGORIA_SEQ", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class TicketSubCategoriaImpl extends EntidadeRelacionamentoImpl implements TicketSubCategoria {

    @ManyToOne(fetch = FetchType.EAGER, targetEntity=TicketCategoriaImpl.class) 
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="TICKET_CATEGORIA_ID", insertable=true, updatable=true) 
    private TicketCategoria ticketCategoria;


    public TicketCategoria getTicketCategoria() {
        return ticketCategoria;
    }

    public void setTicketCategoria(TicketCategoria ticketCategoria) {
        this.ticketCategoria = ticketCategoria;
    }
}