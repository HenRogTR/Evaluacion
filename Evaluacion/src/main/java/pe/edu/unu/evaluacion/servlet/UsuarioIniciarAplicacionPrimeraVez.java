package pe.edu.unu.evaluacion.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pe.edu.unu.evaluacion.bean.AjaxBean;
import pe.edu.unu.evaluacion.bean.TransaccionBean;
import pe.edu.unu.evaluacion.ejb.UsuarioEjb;
import pe.edu.unu.evaluacion.ejb.UsuarioEjbImpl;
import pe.edu.unu.evaluacion.exception.UsuarioException;
import pe.edu.unu.evaluacion.util.Ajax;
import pe.edu.unu.evaluacion.util.Utilitarios;

/**
 * Servlet implementation class UsuarioIniciarAplicacionPrimeraVez
 */
@WebServlet("/UsuarioIniciarAplicacionPrimeraVez")
public class UsuarioIniciarAplicacionPrimeraVez extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Logger logger = Logger.getLogger(UsuarioIniciarAplicacionPrimeraVez.class);
	private UsuarioEjb usuarioEjb;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioIniciarAplicacionPrimeraVez() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("No tiene permisos para ver esta página...");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String idTx = Utilitarios.getIdTransaccion(null);
		String msgTx = "[IniciarAplicacionPrimeraVez idTx=" + idTx + "] ";
		String respuesta = "";
		
		logger.info(msgTx + "Inicio - Registrando datos primera vez uso");
		usuarioEjb = new UsuarioEjbImpl();
		try{
			usuarioEjb.setPrimeraVez(new TransaccionBean(idTx, msgTx));
			respuesta = Ajax.jsonFormato(
					new AjaxBean("idTransaccion", idTx),
					new AjaxBean("codigoRespuesta", "0"),
					new AjaxBean("mensajeRespuesta", "Operación exitosa.")
					);
			logger.warn(msgTx + "Éxito al registrar datos por primera vez.");
		}catch(UsuarioException ue){
			logger.warn(msgTx + "Error al registrar datos por primera vez: " + ue.getMessage());
			respuesta = Ajax.jsonFormato(
					new AjaxBean("idTransaccion", idTx),
					new AjaxBean("codigoRespuesta", ue.getCode()),
					new AjaxBean("mensajeRespuesta", ue.getMessage())
					);
		}finally{
			logger.info(msgTx + "Fin - Registrando datos primera vez uso");
			out.print(String.format("[%s]", respuesta));
		}		
	}

}
