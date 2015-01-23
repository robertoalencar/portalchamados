package br.com.copergas.portalchamados.model.usuario.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.copergas.portalchamados.model.comum.negocio.impl.EntidadeNegocioImpl;
import br.com.copergas.portalchamados.model.usuario.Usuario;

/**
 * @author Roberto Alencar
 *
 */
@Entity
@Table(name = "USUARIO")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "USUARIO_SEQ", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UsuarioImpl extends EntidadeNegocioImpl implements Usuario {

    @Column(name = "NOME")
    private String nome;
    
    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
	return nome;
    }
}