package br.com.copergas.portalchamados.model.comum.negocio;

import java.util.List;

import br.com.copergas.portalchamados.model.comum.excecoes.ConcorrenciaException;
import br.com.copergas.portalchamados.model.comum.excecoes.NegocioException;

/**
 * @author Roberto Alencar
 * 
 */
public interface Service {

    EntidadeNegocio criar();

    Class<?> getClasseEntidade();

    EntidadeNegocio obter(long chavePrimaria) throws NegocioException;

    EntidadeNegocio obter(long chavePrimaria, Class<?> classe) throws NegocioException;
    
    EntidadeNegocio consultarPorDescricao(String descricao, Class<?> classe) throws NegocioException;

    List<EntidadeRelacionamento> listarTodasEntidadeRelacionamento(Class<?> classe, boolean habilitado);
    
    List<? extends EntidadeNegocio> listarTodasEntidadeNegocio(Class<?> classe, boolean habilitados); 
    
    List<? extends EntidadeNegocio> carregarCombobox(Boolean habilitado, Class<?> classe) throws NegocioException;
    
    long inserir(EntidadeNegocio entidadeNegocio) throws NegocioException;

    void atualizar(EntidadeNegocio entidadeNegocio) throws ConcorrenciaException, NegocioException;

    void atualizar(EntidadeNegocio entidadeNegocio, Class<?> classe) throws ConcorrenciaException, NegocioException;

    void remover(EntidadeNegocio entidadeNegocio) throws NegocioException;

    void remover(EntidadeNegocio entidadeNegocio, boolean fisicamente) throws NegocioException;

}