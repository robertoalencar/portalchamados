package br.com.copergas.portalchamados.controller.produto;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.copergas.portalchamados.controller.util.ComumController;
import br.com.copergas.portalchamados.model.comum.excecoes.ProjetoException;
import br.com.copergas.portalchamados.model.produto.Produto;
import br.com.copergas.portalchamados.model.produto.ProdutoCategoria;
import br.com.copergas.portalchamados.model.ticket.Ticket;
import br.com.copergas.portalchamados.model.util.Constantes;
import br.com.copergas.portalchamados.model.util.Util;

/**
 * @author Roberto Alencar
 * 
 */
@ManagedBean
@ViewScoped
public class ProdutoController extends ComumController {

    private Produto produtoSelecionado;
    private Produto produto;
    private List<Produto> listaProduto;
    private List<ProdutoCategoria> listaCategoriaProduto;
    private Date dataInicial;
    private Date dataFinal;
    
    
    public ProdutoController() throws ProjetoException {
	
	setListaCategoriaProduto(fachada.listarProdutoCategoria(Boolean.TRUE));
	this.carregaCamposTelaAlteracao();
	this.setListaProduto(fachada.listarProduto(new HashMap<String, Object>()));
    }
    
    
    //Getters and Setters
    
    
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }
    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }
    public List<Produto> getListaProduto() {
        return listaProduto;
    }
    public void setListaProduto(List<Produto> listaProduto) {
        this.listaProduto = listaProduto;
    }
    public List<ProdutoCategoria> getListaCategoriaProduto() {
        return listaCategoriaProduto;
    }
    public void setListaCategoriaProduto(List<ProdutoCategoria> listaCategoriaProduto) {
        this.listaCategoriaProduto = listaCategoriaProduto;
    }
    public Date getDataInicial() {
        return dataInicial;
    }
    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }
    public Date getDataFinal() {
        return dataFinal;
    }
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    
    //Métodos de controle
    

    public void pesquisar() throws ProjetoException {

	Map<String, Object> filtro = new HashMap<String, Object>();
	filtro.put(Produto.ATRIBUTO_CODIGO, this.getProduto().getCodigo());
	filtro.put(Produto.ATRIBUTO_DESCRICAO, this.getProduto().getDescricao());
	filtro.put(Produto.ATRIBUTO_PRODUTO_CATEGORIA, this.getProduto().getProdutoCategoria());
	filtro.put(Constantes.FILTRO_DATA_INICIAL, this.getDataInicial());
	filtro.put(Constantes.FILTRO_DATA_FINAL, this.getDataFinal());
	this.setListaProduto(fachada.listarProduto(filtro));
    }

    public String inserirProdutoPesquisar() throws ProjetoException {

	this.inserirProduto();
	this.pesquisar();
	setDataInicial(this.getProduto().getData()); //apenas para exibir no filtro da pesquisa
	setDataFinal(this.getProduto().getData()); //apenas para exibir no filtro da pesquisa
	return Constantes.ARQUIVO_XHTML_PESQUISAR_PRODUTO;
    }
    
    public void inserirProduto() throws ProjetoException {
	
	Produto novoProduto = montarObjeto(fachada.criarProduto());
	fachada.inserirProduto(novoProduto);
	Util.exibeMensagemTela(FacesMessage.SEVERITY_INFO, Constantes.PARAMETRO_MSG_SUCESSO_INCLUSAO,
		novoProduto.toString());
    }
    
    public void limpar() throws ProjetoException {
	
	this.setProduto(fachada.criarProduto());
	setDataInicial(null);
	setDataFinal(null);
	this.setListaProduto(fachada.listarProduto(new HashMap<String, Object>()));
    }
    
    public String cancelar() throws ProjetoException {

	this.limpar();
	this.pesquisar();
	setAttributeSession(ATRIBUTO_SESSAO_ENTIDADE_SELECIONADA, null);
	setAttributeSession(ATRIBUTO_SESSAO_HABILITA_EDICAO, null);
	setAttributeSession(ATRIBUTO_SESSAO_EXIBE_BOTOES_ALTERACAO, null);
	return Constantes.ARQUIVO_XHTML_PESQUISAR_PRODUTO;
    }
    
    public String exibirTelaAtualizacao() throws ProjetoException {
	
	setAttributeSession(ATRIBUTO_SESSAO_ENTIDADE_SELECIONADA, produtoSelecionado);
	setAttributeSession(ATRIBUTO_SESSAO_HABILITA_EDICAO, Boolean.FALSE);
	setAttributeSession(ATRIBUTO_SESSAO_EXIBE_BOTOES_ALTERACAO, Boolean.TRUE);
	return Constantes.ARQUIVO_XHTML_SALVAR_PRODUTO;
    }
    
    public String atualizarProdutoPesquisar() throws ProjetoException {

	this.atualizarProduto();
	this.pesquisar();
	setDataInicial(this.getProduto().getData()); //apenas para exibir no filtro da pesquisa
	setDataFinal(this.getProduto().getData()); //apenas para exibir no filtro da pesquisa
	return Constantes.ARQUIVO_XHTML_PESQUISAR_PRODUTO;
    }
    
    public void atualizarProduto() throws ProjetoException {
	
	fachada.atualizarProduto(produto);
	Util.exibeMensagemTela(FacesMessage.SEVERITY_INFO, Constantes.PARAMETRO_MSG_SUCESSO_ALTERACAO,
		produto.toString());
	setAttributeSession(ATRIBUTO_SESSAO_ENTIDADE_SELECIONADA, null);
	setAttributeSession(ATRIBUTO_SESSAO_HABILITA_EDICAO, null);
	setAttributeSession(ATRIBUTO_SESSAO_EXIBE_BOTOES_ALTERACAO, null);
    }
    
    public void removerProduto() throws ProjetoException {

	Produto produto = (Produto) fachada.obterProduto(this.produtoSelecionado.getChavePrimaria());
	fachada.removerProduto(produto);
	this.pesquisar();
	Util.exibeMensagemTela(FacesMessage.SEVERITY_INFO, Constantes.PARAMETRO_MSG_SUCESSO_EXCLUSAO,
		this.produtoSelecionado.toString());
    }
    
    
    public void habilitaEdicao() throws ProjetoException {
	
	setAttributeSession(ATRIBUTO_SESSAO_HABILITA_EDICAO, Boolean.TRUE);
	setHabilitaEdicao(Boolean.TRUE);
    }
    
    public void preencherDataAtual() throws ProjetoException {
	
	Calendar c = GregorianCalendar.getInstance();
	dataInicial = c.getTime();
	dataFinal = c.getTime();
    }
    
    public void preencherSemanaAtual() throws ProjetoException {
	
	Calendar c = GregorianCalendar.getInstance();
	
	c.setFirstDayOfWeek(Calendar.SUNDAY);  
        int diaSemana = c.get(Calendar.DAY_OF_WEEK);  
        c.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - diaSemana);  
	dataInicial = c.getTime();
	
	c.add(Calendar.DAY_OF_MONTH, c.getFirstDayOfWeek()+6);
	dataFinal = c.getTime();
    }
    
    public void preencherMesAtual() throws ProjetoException {
	
	Calendar c = GregorianCalendar.getInstance();
	
	c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
	dataInicial = c.getTime();
	
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));  
	dataFinal = c.getTime();
    }
    
    private void carregaCamposTelaAlteracao() throws ProjetoException {

	if (getAttributeSession(ATRIBUTO_SESSAO_HABILITA_EDICAO) != null) {
	    setHabilitaEdicao((boolean) getAttributeSession(ATRIBUTO_SESSAO_HABILITA_EDICAO));
	} else {
	    setHabilitaEdicao(Boolean.TRUE);
	}
	
	if (getAttributeSession(ATRIBUTO_SESSAO_EXIBE_BOTOES_ALTERACAO) != null) {
	    setExibeBotoesAlteracao((boolean) getAttributeSession(ATRIBUTO_SESSAO_EXIBE_BOTOES_ALTERACAO));
	}
	
	if (getAttributeSession(ATRIBUTO_SESSAO_ENTIDADE_SELECIONADA) != null) {
	    if (getAttributeSession(ATRIBUTO_SESSAO_ENTIDADE_SELECIONADA) instanceof Produto) {
		this.produtoSelecionado = (Produto) getAttributeSession(ATRIBUTO_SESSAO_ENTIDADE_SELECIONADA);
		this.setProduto(produtoSelecionado);
	    }
	} else {
	    this.setProduto(fachada.criarProduto());
	}
    }
    
    private Produto montarObjeto(Produto obj) {
	
	obj.setCodigo(this.produto.getCodigo());
	obj.setProdutoCategoria(this.produto.getProdutoCategoria());
	obj.setDescricao(this.produto.getDescricao());
	obj.setData(this.produto.getData());
	obj.setValor(this.produto.getValor());
	return obj;
    }
    
}