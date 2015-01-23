package br.com.copergas.portalchamados.model.comum.excecoes;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author Roberto Alencar
 * 
 */
public class ProjetoException extends Exception {

    private static final long serialVersionUID = 2966639560663027575L;

    private Exception rootCause;
    private String chaveErro;
    private Object[] parametrosErro;

    /**
     * Construtor padrão.
     */
    public ProjetoException() {
	super();
    }

    /**
     * @param message
     */
    public ProjetoException(String message) {
	super(message);
    }

    /**
     * @param cause
     */
    public ProjetoException(Throwable cause) {
	super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ProjetoException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * @param chave
     * @param param
     */
    public ProjetoException(String chave, Object param) {
	this.chaveErro = chave;
	this.parametrosErro = new Object[] { param };
    }

    /**
     * @param chave
     * @param param
     */
    public ProjetoException(String chave, Object[] param) {
	this.chaveErro = chave;
	this.parametrosErro = param;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
     */
    public void printStackTrace(PrintWriter s) {
	super.printStackTrace(s);
	if (getRootCause() != null) {
	    getRootCause().printStackTrace(s);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
     */
    public void printStackTrace(PrintStream s) {
	super.printStackTrace(s);
	if (getRootCause() != null) {
	    getRootCause().printStackTrace(s);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#printStackTrace()
     */
    public void printStackTrace() {
	super.printStackTrace();
	if (getRootCause() != null) {
	    getRootCause().printStackTrace();
	}
    }

    /**
     * @return Retorna o atributo rootCause.
     */
    public Exception getRootCause() {
	return rootCause;
    }

    /**
     * @param rootCause
     *            O valor a ser atribuído ao atributo rootCause.
     */
    public void setRootCause(Exception rootCause) {
	this.rootCause = rootCause;
    }

    /**
     * @return Retorna o atributo chaveErro.
     */
    public String getChaveErro() {
	return chaveErro;
    }

    /**
     * @param chaveErro
     *            O valor a ser atribuído ao atributo chaveErro.
     */
    public void setChaveErro(String chaveErro) {
	this.chaveErro = chaveErro;
    }

    /**
     * @return Retorna o atributo parametrosErro.
     */
    public Object[] getParametrosErro() {
	return parametrosErro;
    }

    /**
     * @param parametrosErro
     *            O valor a ser atribuído ao atributo parametrosErro.
     */
    public void setParametrosErro(Object[] parametrosErro) {
	this.parametrosErro = parametrosErro;
    }

}