package pe.edu.unu.evaluacion.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class MySessionListener implements HttpSessionListener {	 

	private static Logger logger = Logger.getLogger(MySessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		String msgTX ="[sessionCreated] ";
		logger.info(msgTX + "Inicio");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		String msgTX ="[sessionDestroyed] ";
		logger.info(msgTX + "Inicio");
		
	}

}
