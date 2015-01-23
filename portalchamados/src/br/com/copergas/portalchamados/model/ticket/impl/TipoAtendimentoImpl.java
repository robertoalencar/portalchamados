package br.com.copergas.portalchamados.model.ticket.impl;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.copergas.portalchamados.model.comum.negocio.impl.EntidadeRelacionamentoImpl;
import br.com.copergas.portalchamados.model.ticket.TipoAtendimento;

/**
 * @author roberto.alencar
 *
 */
@Entity
@Table(name = "TIPO_ATENDIMENTO")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "TIPO_ATENDIMENTO_SEQ", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class TipoAtendimentoImpl extends EntidadeRelacionamentoImpl implements TipoAtendimento {

}