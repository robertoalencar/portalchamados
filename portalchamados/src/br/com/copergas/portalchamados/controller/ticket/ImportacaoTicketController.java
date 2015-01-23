package br.com.copergas.portalchamados.controller.ticket;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.UploadedFile;

import br.com.copergas.portalchamados.controller.util.ComumController;
import br.com.copergas.portalchamados.model.comum.excecoes.ProjetoException;
import br.com.copergas.portalchamados.model.ticket.RegistroImportacao;
import br.com.copergas.portalchamados.model.util.Constantes;
import br.com.copergas.portalchamados.model.util.Util;

/**
 * @author Roberto Alencar
 * 
 */
@ManagedBean
@RequestScoped
public class ImportacaoTicketController extends ComumController {
    
    private RegistroImportacao registroImportacao;
    private UploadedFile file;
    
    
    public ImportacaoTicketController() throws ProjetoException {
	setRegistroImportacao(fachada.obterRegistroImportacao(RegistroImportacao.VALOR_UNICO_REGISTRO_IMPORTACAO));
    }

    
    // GETs e SETs
    
    public RegistroImportacao getRegistroImportacao() {
        return registroImportacao;
    }
    public void setRegistroImportacao(RegistroImportacao registroImportacao) {
        this.registroImportacao = registroImportacao;
    }
    public UploadedFile getFile() {
        return file;
    }
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    
    // Métodos de controle

    public void importarTicketsSpiceworks() throws ProjetoException, IOException {

	if (file != null) {
            fachada.importarTicketsSpiceworks(file.getInputstream());
            Util.exibeMensagemTela(FacesMessage.SEVERITY_INFO, Constantes.PARAMETRO_MSG_SUCESSO_IMPORTACAO);
            setRegistroImportacao(fachada.obterRegistroImportacao(RegistroImportacao.VALOR_UNICO_REGISTRO_IMPORTACAO));
        }
    }

}