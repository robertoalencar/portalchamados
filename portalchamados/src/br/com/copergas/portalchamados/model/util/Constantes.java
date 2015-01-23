package br.com.copergas.portalchamados.model.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Roberto Alencar
 * 
 */
public final class Constantes {
    
    /** Constantes criadas para o arquivos de propriedades das mensagens */
    public static Locale LOCALE_PADRAO = new Locale("pt", "BR");
    public static final String ARQUIVO_MENSAGENS = "messages";
    public static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(ARQUIVO_MENSAGENS, LOCALE_PADRAO);
    public static ResourceBundle ARQUIVO_SLA = ResourceBundle.getBundle("tempoSLA", LOCALE_PADRAO);

    
    /** Chaves para as mensagens localizadas no arquivo de mensagens.properties */
    public static final String ERRO_ENTIDADE_NAO_ENCONTRADA = "ERRO_ENTIDADE_NAO_ENCONTRADA";
    public static final String ERRO_ENTIDADE_VERSAO_DESATUALIZADA = "ERRO_ENTIDADE_VERSAO_DESATUALIZADA";
    public static final String PARAMETRO_MSG_SUCESSO_INCLUSAO = "PARAMETRO_MSG_SUCESSO_INCLUSAO";
    public static final String PARAMETRO_MSG_SUCESSO_ALTERACAO = "PARAMETRO_MSG_SUCESSO_ALTERACAO";
    public static final String PARAMETRO_MSG_SUCESSO_EXCLUSAO = "PARAMETRO_MSG_SUCESSO_EXCLUSAO";
    public static final String PARAMETRO_MSG_SUCESSO_IMPORTACAO = "PARAMETRO_MSG_SUCESSO_IMPORTACAO";
    public static final String PARAMETRO_MSG_OBJ_DUPLICADO = "PARAMETRO_MSG_OBJ_DUPLICADO";
    public static final String PARAMETRO_MSG_ERRO_CONVERSAO = "PARAMETRO_MSG_ERRO_CONVERSAO";
    public static final String PARAMETRO_MSG_CAMPOS_OBRIGATORIOS = "PARAMETRO_MSG_CAMPOS_OBRIGATORIOS";
    public static final String PARAMETRO_MSG_ERRO_INESPERADO = "PARAMETRO_MSG_ERRO_INESPERADO";
    
    
    /** Atributos para serem utilizados em filtros de consulta */
    public static final String FILTRO_DATA_INICIAL = "dataInicial";
    public static final String FILTRO_DATA_FINAL = "dataFinal";
    public static final String FILTRO_DATA_INICIAL_VENCIMENTO = "dataInicialVencimento";
    public static final String FILTRO_DATA_FINAL_VENCIMENTO = "dataFinalVencimento";
    public static final String FILTRO_DATA_INICIAL_FECHAMENTO = "dataInicialFechamento";
    public static final String FILTRO_DATA_FINAL_FECHAMENTO = "dataFinalFechamento";

    
    /** Nome dos arquivos das telas .xhtml */
    public static final String ARQUIVO_XHTML_PESQUISAR_PRODUTO = "pesquisarProduto";
    public static final String ARQUIVO_XHTML_SALVAR_PRODUTO = "salvarProduto";
    public static final String ARQUIVO_XHTML_PESQUISAR_TICKET = "pesquisarTicket";
    public static final String ARQUIVO_XHTML_SALVAR_TICKET = "salvarTicket";
    public static final String ARQUIVO_XHTML_PESQUISAR_CLIENTE = "pesquisarCliente";
    public static final String ARQUIVO_XHTML_SALVAR_CLIENTE = "salvarCliente";
    
}