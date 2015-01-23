package br.com.copergas.portalchamados.model.usuario;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeNegocio;

/**
 * @author Roberto Alencar
 *
 */
public interface Usuario extends EntidadeNegocio {

    String BEAN_NAME = "usuario";

    String ATRIBUTO_NOME = "nome";
    String LABEL_NOME = "Nome";
    Long VALOR_USUARIO_UNASSIGNED = 1L;

    
    String getNome();

    void setNome(String nome);
    
}