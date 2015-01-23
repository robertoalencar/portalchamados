package br.com.copergas.portalchamados.model.comum.negocio.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeNegocio;

/**
 * @author Roberto Alencar
 * 
 */
@MappedSuperclass
public abstract class EntidadeNegocioImpl implements EntidadeNegocio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_GENERATOR")
    @Column(name = "ID", unique = true, nullable = false)
    private long chavePrimaria;

    @Column(name = "NR_VERSAO")
    private int versao;

    @Column(name = "DT_ULTMA_ALTERACAO")
    private Date ultimaAlteracao;

    @Column(name = "IN_USO")
    private boolean habilitado;

    
    
    public long getChavePrimaria() {
	return chavePrimaria;
    }
    public void setChavePrimaria(long chavePrimaria) {
	this.chavePrimaria = chavePrimaria;
    }
    public int getVersao() {
	return versao;
    }
    public void setVersao(int versao) {
	this.versao = versao;
    }
    public Date getUltimaAlteracao() {
	return ultimaAlteracao;
    }
    public void setUltimaAlteracao(Date ultimaAlteracao) {
	this.ultimaAlteracao = ultimaAlteracao;
    }
    public boolean isHabilitado() {
	return habilitado;
    }
    public void setHabilitado(boolean habilitado) {
	this.habilitado = habilitado;
    }
    public void incrementarVersao() {
	this.versao++;
    }
    
    

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (this == obj)
	    return true;
	if (obj instanceof EntidadeNegocioImpl == false) {
	    return false;
	}
	if (this.getChavePrimaria() > 0) {
	    EntidadeNegocioImpl that = (EntidadeNegocioImpl) obj;
	    return new EqualsBuilder().append(this.getChavePrimaria(), that.getChavePrimaria()).isEquals();
	} else {
	    return super.equals(obj);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
	if (this.getChavePrimaria() > 0) {
	    return new HashCodeBuilder(3, 5).append(this.getChavePrimaria()).toHashCode();
	} else {
	    return super.hashCode();
	}
    }

}