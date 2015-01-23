package br.com.copergas.portalchamados.model.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Roberto Alencar
 * 
 */
public class BeanFactory {

    private static ClassPathXmlApplicationContext ctx;

    static {
	ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
    }

    private BeanFactory() {

    }

    public static Object getBean(String beanName) {
	return ctx.getBean(beanName);
    }

    public static Object getBean(String beanName, Class classe) {
	return ctx.getBean(beanName, classe);
    }

    public static Class<?> getClassPorID(String beanName) {
	return ctx.getType(beanName);
    }

}