package br.com.copergas.portalchamados.controller.ticket;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.copergas.portalchamados.model.comum.excecoes.ProjetoException;
import br.com.copergas.portalchamados.model.ticket.TicketCategoria;
import br.com.copergas.portalchamados.model.util.Constantes;
import br.com.copergas.portalchamados.model.util.Fachada;
import br.com.copergas.portalchamados.model.util.MensagemUtil;

/**
 * @author roberto.alencar
 * 
 */
@FacesConverter(value="ticketCategoriaConverter", forClass = TicketCategoria.class)
public class TicketCategoriaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	
	if (value == null || value.equals("")) {
	    return null;  
	} else {
	    try {
		Fachada fachada = Fachada.getInstancia();
		return fachada.consultarTicketCategoriaPorDescricao(value);
	    } catch (ProjetoException e) {
		Object argumentos[] = {value, component.getId()};
		throw new ConverterException(MensagemUtil.obterMensagem(Constantes.RESOURCE_BUNDLE,
			Constantes.PARAMETRO_MSG_ERRO_CONVERSAO, argumentos));
	    }
	}
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {

	if (object == null || object.equals("")) {
	    return "";
	} else {
	    return String.valueOf(object);
	}
    }

}