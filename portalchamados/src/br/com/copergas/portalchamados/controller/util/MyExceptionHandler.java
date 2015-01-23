package br.com.copergas.portalchamados.controller.util;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.log4j.Logger;

import br.com.copergas.portalchamados.model.util.Constantes;
import br.com.copergas.portalchamados.model.util.MensagemUtil;

/**
 * @author roberto.alencar
 * 
 */
public class MyExceptionHandler extends ExceptionHandlerWrapper {

    private static final Logger log = Logger.getLogger(MyExceptionHandler.class.getCanonicalName());
    private ExceptionHandler wrapped;

    MyExceptionHandler(ExceptionHandler exception) {
	this.wrapped = exception;
    }

    @Override
    public ExceptionHandler getWrapped() {
	return wrapped;
    }

    @Override
    public void handle() throws FacesException {

	final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
	while (i.hasNext()) {
	    ExceptionQueuedEvent event = i.next();
	    ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

	    // get the exception from context
	    Throwable t = context.getException();

	    final FacesContext fc = FacesContext.getCurrentInstance();
	    final Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
	    final NavigationHandler nav = fc.getApplication().getNavigationHandler();

	    // here you do what ever you want with exception
	    try {

		log.error("Critical Exception!", t);

		// redirect error page
		requestMap.put("exceptionMessage", t.getMessage());
		nav.handleNavigation(fc, null, "/error");
		fc.renderResponse();

		String mensagem = null;
		if (t.getMessage() != null) {
		    if (t.getMessage().indexOf('&') != -1) {
			mensagem = t.getMessage().substring(t.getMessage().indexOf('&')+1, t.getMessage().length());
		    } else {
			mensagem = MensagemUtil.obterMensagem(Constantes.RESOURCE_BUNDLE, Constantes.PARAMETRO_MSG_ERRO_INESPERADO); 
		    }
		}
		
		FacesContext.getCurrentInstance().addMessage(null,
			new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null));

	    } finally {
		i.remove();
	    }
	}

	getWrapped().handle();
    }

}