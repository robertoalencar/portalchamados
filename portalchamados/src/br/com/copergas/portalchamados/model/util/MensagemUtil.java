package br.com.copergas.portalchamados.model.util;

import java.util.ResourceBundle;

/**
 * @author Roberto Alencar
 * 
 */
public class MensagemUtil {

    /**
     * Método responsável por obter a mensagem do arquivo de propriedades.
     * 
     * @param rb O Resource Bundle
     * @param chave A chave
     * @param argumentos Os argumentos da mensagem
     * @return String A Mensagem
     */
    public static String obterMensagem(ResourceBundle rb, String chave, Object argumentos[]) {
	String mensagem = obterMensagem(rb, chave);
	for (int i = 0; i < argumentos.length; i++) {
	    mensagem = Util.substituirArgumentosDoTexto(mensagem, i, argumentos[i]);
	}
	return mensagem;
    }

    /**
     * Método responsável por obter a mensagem do arquivo de propriedades.
     * 
     * @param rb O Resource Bundle
     * @param chave A chave
     * @param argumento Os argumentos da mensagem
     * @return String A Mensagem
     */
    public static String obterMensagem(ResourceBundle rb, String chave, Object argumento) {
	return obterMensagem(rb, chave, new Object[] { argumento });
    }

    /**
     * Método responsável por obter a mensagem do arquivo de propriedades.
     * 
     * @param rb O Resource Bundle
     * @param chave A chave
     * @return String A mensagem
     */
    public static String obterMensagem(ResourceBundle rb, String chave) {
	return rb.getString(chave);
    }

}