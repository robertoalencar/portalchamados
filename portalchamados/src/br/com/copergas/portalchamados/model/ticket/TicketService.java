package br.com.copergas.portalchamados.model.ticket;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import br.com.copergas.portalchamados.model.comum.excecoes.ConcorrenciaException;
import br.com.copergas.portalchamados.model.comum.excecoes.NegocioException;
import br.com.copergas.portalchamados.model.comum.negocio.EntidadeNegocio;
import br.com.copergas.portalchamados.model.comum.negocio.Service;

/**
 * @author Roberto Alencar
 * 
 */
public interface TicketService extends Service {

    String BEAN_NAME = "ticketService";

    EntidadeNegocio criarTicketCategoria();

    Class<?> getClasseEntidadeTicketCategoria();
    
    EntidadeNegocio criarTicketSubCategoria();

    Class<?> getClasseEntidadeTicketSubCategoria();

    EntidadeNegocio criarSetor();

    Class<?> getClasseEntidadeSetor();
    
    EntidadeNegocio criarTipoChamado();

    Class<?> getClasseEntidadeTipoChamado();
    
    EntidadeNegocio criarOrigemChamado();

    Class<?> getClasseEntidadeOrigemChamado();
    
    EntidadeNegocio criarSistema();

    Class<?> getClasseEntidadeSistema();
    
    EntidadeNegocio criarStatus();

    Class<?> getClasseEntidadeStatus();
    
    EntidadeNegocio criarPrioridade();

    Class<?> getClasseEntidadePrioridade();
    
    EntidadeNegocio criarTipoAtendimento();

    Class<?> getClasseEntidadeTipoAtendimento();
 
    EntidadeNegocio criarStatusCopergas();
    
    Class<?> getClasseEntidadeStatusCopergas();
    
    EntidadeNegocio criarStatusFornecedor();
    
    Class<?> getClasseEntidadeStatusFornecedor();
 
    EntidadeNegocio criarRegistroImportacao();
    
    Class<?> getClasseEntidadeRegistroImportacao();
    
    List<Ticket> listarTicket(Map<String, Object> filtro) throws NegocioException;
    
    List<Ticket> listarTicketIndicadores(Map<String, Object> filtro) throws NegocioException;
    
    void importarTicketsSpiceworks(InputStream arquivo) throws NegocioException, ConcorrenciaException;

}