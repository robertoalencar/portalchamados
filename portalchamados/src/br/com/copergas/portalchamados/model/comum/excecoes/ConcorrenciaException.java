package br.com.copergas.portalchamados.model.comum.excecoes;

/**
 * @author Roberto Alencar
 * 
 */
public class ConcorrenciaException extends ProjetoException {

    private static final long serialVersionUID = 534568108794551141L;

    public ConcorrenciaException() {
	super();
    }

    /**
     * @param chave
     * @param rootCause
     */
    public ConcorrenciaException(String chave, Exception rootCause) {
	super(chave, rootCause);
    }

    /**
     * @param message
     * @param cause
     */
    public ConcorrenciaException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * @param message
     */
    public ConcorrenciaException(String message) {
	super(message);
    }

    /**
     * @param cause
     */
    public ConcorrenciaException(Throwable cause) {
	super(cause);
    }

    /**
     * @param chave
     * @param param
     */
    public ConcorrenciaException(String chave, Object param) {
	super(chave, param);
    }
}