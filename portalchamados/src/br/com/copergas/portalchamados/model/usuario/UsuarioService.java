package br.com.copergas.portalchamados.model.usuario;

import java.util.List;
import java.util.Map;

import br.com.copergas.portalchamados.model.comum.excecoes.NegocioException;
import br.com.copergas.portalchamados.model.comum.negocio.Service;

/**
 * @author Roberto Alencar
 *
 */
public interface UsuarioService extends Service {

    String BEAN_NAME = "usuarioService";
 
    List<Usuario> listarUsuario(Map<String, Object> filtro) throws NegocioException;
    
}