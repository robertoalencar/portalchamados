package br.com.copergas.portalchamados.model.produto.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.copergas.portalchamados.model.comum.negocio.impl.EntidadeNegocioImpl;
import br.com.copergas.portalchamados.model.produto.ProdutoCategoria;

/**
 * @author Roberto Alencar
 * 
 */
@Entity
@Table(name = "PRODUTO_CATEGORIA")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "PRODUTO_CATEGORIA_SEQ", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class ProdutoCategoriaImpl extends EntidadeNegocioImpl implements ProdutoCategoria {

    @Column(name = "DESCRICAO")
    private String descricao;

    
    @Override
    public String getDescricao() {
	return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    
    public String toString() {
	return descricao;
    }
}