package br.com.copergas.portalchamados.model.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * @author Roberto Alencar
 * 
 */
public final class Util {
    
    /**
     * Tipos de Severity:
     * 
     * FacesMessage.SEVERITY_INFO
     * FacesMessage.SEVERITY_WARN
     * FacesMessage.SEVERITY_ERROR
     * FacesMessage.SEVERITY_FATAL
     */
    public static void exibeMensagemTela(Severity severity, String chaveMensagem) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, 
		MensagemUtil.obterMensagem(Constantes.RESOURCE_BUNDLE, chaveMensagem), null));
    }

    public static void exibeMensagemTela(Severity severity, String chaveMensagem, Object argumento) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, 
		MensagemUtil.obterMensagem(Constantes.RESOURCE_BUNDLE, chaveMensagem, argumento), null));
    }

    public static void exibeMensagemTela(Severity severity, String chaveMensagem, Object argumentos[]) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, 
		MensagemUtil.obterMensagem(Constantes.RESOURCE_BUNDLE, chaveMensagem, argumentos), null));
    }

    /**
     * Método responsável por substituir os argumentos em um texto, ex: Olá {0}.
     * 
     * @param texo O texto que será processado
     * @param numeroDeArgumentos O Número do arqumento
     * @param valorDoArgumento O valor do argumento
     * @return Um texto com os argumentos subtituídos
     */
    public static String substituirArgumentosDoTexto(String texo, int numeroDeArgumentos, Object valorDoArgumento) {
	StringBuffer sb = new StringBuffer(texo.length() + 10);
	String chave = "{" + numeroDeArgumentos + "}";
	int i = texo.indexOf(chave);
	if (i >= 0) {
	    sb.append(texo.substring(0, i));
	    sb.append(valorDoArgumento);
	    sb.append(texo.substring(i + chave.length()));
	} else {
	    sb.append(texo);
	}
	return sb.toString();
    }
    
    public static int obterSLA(String chave) {
	
	return Integer.parseInt(Constantes.ARQUIVO_SLA.getString(chave));
    }

}