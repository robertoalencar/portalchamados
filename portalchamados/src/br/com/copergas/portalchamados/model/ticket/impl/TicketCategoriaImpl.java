package br.com.copergas.portalchamados.model.ticket.impl;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.copergas.portalchamados.model.comum.negocio.impl.EntidadeRelacionamentoImpl;
import br.com.copergas.portalchamados.model.ticket.TicketCategoria;

/**
 * @author Roberto Alencar
 * 
 */
@Entity
@Table(name = "TICKET_CATEGORIA")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "TICKET_CATEGORIA_SEQ", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class TicketCategoriaImpl extends EntidadeRelacionamentoImpl implements TicketCategoria {

}