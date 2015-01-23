package br.com.copergas.portalchamados.model.comum.negocio.impl;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeRelacionamento;

/**
 * @author Roberto Alencar
 * 
 */
@MappedSuperclass
public abstract class EntidadeRelacionamentoImpl extends EntidadeNegocioImpl implements EntidadeRelacionamento {

    @Column(name = "DESCRICAO")
    private String descricao;

    
    public String getDescricao() {
	return descricao;
    }
    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public String toString() {
	return descricao;
    }
}