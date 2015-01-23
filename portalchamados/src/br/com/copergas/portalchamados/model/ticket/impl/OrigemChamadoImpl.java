package br.com.copergas.portalchamados.model.ticket.impl;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.copergas.portalchamados.model.comum.negocio.impl.EntidadeRelacionamentoImpl;
import br.com.copergas.portalchamados.model.ticket.OrigemChamado;

/**
 * @author roberto.alencar
 * 
 */
@Entity
@Table(name = "ORIGEM_CHAMADO")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "ORIGEM_CHAMADO_SEQ", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class OrigemChamadoImpl extends EntidadeRelacionamentoImpl implements OrigemChamado {

}