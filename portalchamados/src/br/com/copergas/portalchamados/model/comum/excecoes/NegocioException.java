package br.com.copergas.portalchamados.model.comum.excecoes;

import java.util.Map;

/**
 * @author Roberto Alencar
 * 
 */
public class NegocioException extends ProjetoException {

    private static final long serialVersionUID = -1510635336746319789L;

    private Map<String, Object> erros;

    public NegocioException() {
	super();
    }

    public NegocioException(String message, Throwable cause) {
	super(message, cause);
    }

    public NegocioException(String message) {
	super(message);
    }

    public NegocioException(Throwable cause) {
	super(cause);
    }

    public NegocioException(Map<String, Object> erros) {
	super();
	this.erros = erros;
    }

    public NegocioException(String chave, Exception rootCause) {
	super(chave, rootCause);
    }

    public NegocioException(String chave, Object param) {
	super(chave, param);
    }

    public NegocioException(String chave, boolean key) {
	super(chave, chave);
    }

    public NegocioException(String chave, Object[] param) {
	super(chave, param);
    }

    public Map<String, Object> getErros() {
	return erros;
    }

    public void setErros(Map<String, Object> erros) {
	this.erros = erros;
    }

}