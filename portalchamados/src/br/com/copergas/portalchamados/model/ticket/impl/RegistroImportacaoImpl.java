package br.com.copergas.portalchamados.model.ticket.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.copergas.portalchamados.model.comum.negocio.impl.EntidadeNegocioImpl;
import br.com.copergas.portalchamados.model.ticket.RegistroImportacao;

/**
 * @author roberto.alencar
 *
 */
@Entity
@Table(name = "REGISTRO_IMPORTACAO")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "REGISTRO_IMPORTACAO_SEQ", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class RegistroImportacaoImpl extends EntidadeNegocioImpl implements RegistroImportacao {

    @Column(name = "QTD_TICKET_INSERIDOS")
    private Integer qtdTicketInseridos;
    
    @Column(name = "QTD_TICKET_ALTERADOS")
    private Integer qtdTicketAlterados;
    
    @Column(name = "QTD_TICKET_PROCESSADOS")
    private Integer qtdTicketProcessados;
    
    @Column(name = "DT_ULTIMA_IMPORTACAO")
    private Date dataUltimaImportacao;

    
    public Integer getQtdTicketInseridos() {
        return qtdTicketInseridos;
    }

    public void setQtdTicketInseridos(Integer qtdTicketInseridos) {
        this.qtdTicketInseridos = qtdTicketInseridos;
    }

    public Integer getQtdTicketAlterados() {
        return qtdTicketAlterados;
    }

    public void setQtdTicketAlterados(Integer qtdTicketAlterados) {
        this.qtdTicketAlterados = qtdTicketAlterados;
    }

    public Integer getQtdTicketProcessados() {
        return qtdTicketProcessados;
    }

    public void setQtdTicketProcessados(Integer qtdTicketProcessados) {
        this.qtdTicketProcessados = qtdTicketProcessados;
    }

    public Date getDataUltimaImportacao() {
        return dataUltimaImportacao;
    }

    public void setDataUltimaImportacao(Date dataUltimaImportacao) {
        this.dataUltimaImportacao = dataUltimaImportacao;
    }
    
}