package br.com.copergas.portalchamados.model.ticket.impl;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.copergas.portalchamados.model.comum.negocio.impl.EntidadeRelacionamentoImpl;
import br.com.copergas.portalchamados.model.ticket.StatusFornecedor;

/**
 * @author roberto.alencar
 *
 */
@Entity
@Table(name = "STATUS_FORNECEDOR")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "STATUS_FORNECEDOR_SEQ", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class StatusFornecedorImpl extends EntidadeRelacionamentoImpl implements StatusFornecedor {

}