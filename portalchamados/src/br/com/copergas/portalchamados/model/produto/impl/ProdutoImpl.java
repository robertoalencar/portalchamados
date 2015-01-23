package br.com.copergas.portalchamados.model.produto.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.copergas.portalchamados.model.comum.negocio.impl.EntidadeNegocioImpl;
import br.com.copergas.portalchamados.model.produto.Produto;
import br.com.copergas.portalchamados.model.produto.ProdutoCategoria;

/**
 * @author Roberto Alencar
 * 
 */
@Entity
@Table(name = "PRODUTO")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "PRODUTO_SEQ", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class ProdutoImpl extends EntidadeNegocioImpl implements Produto {

    @ManyToOne(fetch = FetchType.EAGER, targetEntity=ProdutoCategoriaImpl.class) // Já quando o atributo fetch recebe FetchType .EAGER indica que sempre que o objeto pai for buscado na base de dados, seu conteúdo também será trazido, se no lugar de FetchType.EAGER for colocado FetchType.LAZY, o conteúdo do objeto pai só será trazido a primeira vez que o objeto pai for trazido da base de dados. É necessário utilizar o atributo targetEntity quando se trabalha com interface.
    @Fetch(FetchMode.JOIN) //A anotação fetch com FetchMode.JOIN, indica que as consultas vão ser feitas através de um join, se no lugar de FetchMode.JOIN for colocado FetchMode.SELECT a consulta vai ser feita por dois selects.
    @Cascade(CascadeType.SAVE_UPDATE) //O atributo cascade indica como as alterações na entidade serão refletidas na entidade relacionada no banco de dados,
    @JoinColumn(name="PRODUTO_CATEGORIA_ID", insertable=true, updatable=true) //JoinColumn é usado para especificar a coluna que contem a chave estrangeira de cada relacionamento.
    private ProdutoCategoria produtoCategoria;
    
    @Column(name = "CODIGO")
    private String codigo;
    
    @Column(name = "DESCRICAO")
    private String descricao;
    
    @Column(name = "DATA")
    private Date data;
    
    @Column(name = "VALOR")
    private Double valor;

    
    public ProdutoCategoria getProdutoCategoria() {
        return produtoCategoria;
    }

    public void setProdutoCategoria(ProdutoCategoria produtoCategoria) {
        this.produtoCategoria = produtoCategoria;
    }

    public String getCodigo() {
	return codigo;
    }

    public void setCodigo(String codigo) {
	this.codigo = codigo;
    }

    public Date getData() {
	return data;
    }

    public void setData(Date data) {
	this.data = data;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public Double getValor() {
	return valor;
    }

    public void setValor(Double valor) {
	this.valor = valor;
    }

    public String toString() {
	return "[" + codigo + "] - " + descricao;
    }

}