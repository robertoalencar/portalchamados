package br.com.copergas.portalchamados.controller.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.copergas.portalchamados.model.util.Fachada;

/**
 * @author Roberto Alencar
 * 
 */
public class ComumController {

    protected static final String ATRIBUTO_SESSAO_ENTIDADE_SELECIONADA = "entidadeSelecionada";
    protected static final String ATRIBUTO_SESSAO_HABILITA_EDICAO = "habilitaEdicao";
    protected static final String ATRIBUTO_SESSAO_EXIBE_BOTOES_ALTERACAO = "exibeBotoesAlteracao";
    protected Fachada fachada = Fachada.getInstancia();
    protected boolean habilitaEdicao = Boolean.FALSE;
    protected boolean exibeBotoesAlteracao = Boolean.FALSE;
    
    
    public boolean isHabilitaEdicao() {
        return habilitaEdicao;
    }
    public void setHabilitaEdicao(Boolean habilitaEdicao) {
        this.habilitaEdicao = habilitaEdicao;
    }
    public boolean isExibeBotoesAlteracao() {
        return exibeBotoesAlteracao;
    }
    public void setExibeBotoesAlteracao(Boolean exibeBotoesAlteracao) {
        this.exibeBotoesAlteracao = exibeBotoesAlteracao;
    }
    
    
    protected Object getAttributeSession(String id) {

	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	return session.getAttribute(id);
    }
    protected void setAttributeSession(String id, Object param) {
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	session.setAttribute(id, param);
    }

}