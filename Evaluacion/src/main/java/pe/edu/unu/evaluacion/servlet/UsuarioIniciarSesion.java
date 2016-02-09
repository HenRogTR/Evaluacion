package pe.edu.unu.evaluacion.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import pe.edu.unu.evaluacion.bean.AjaxBean;
import pe.edu.unu.evaluacion.bean.TransaccionBean;
import pe.edu.unu.evaluacion.ejb.UsuarioEjb;
import pe.edu.unu.evaluacion.ejb.UsuarioEjbImpl;
import pe.edu.unu.evaluacion.exception.UsuarioException;
import pe.edu.unu.evaluacion.util.Ajax;
import pe.edu.unu.evaluacion.util.UtilSesion;
import pe.edu.unu.evaluacion.util.Utilitarios;

/**
 * Servlet implementation class UsuarioIniciarSesion
 */
@WebServlet("/UsuarioIniciarSesion")
public class UsuarioIniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Logger logger = Logger.getLogger(UsuarioIniciarSesion.class);
    private UsuarioEjb usuarioEjb;
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioIniciarSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String idTx = Utilitarios.getIdTransaccion(null);
		String msgTx = "[UsuarioIniciarSesion idTx=" + idTx + "] ";		
		String respuesta = "";
		
		logger.info(msgTx + "Inicio validación de inicio de sesión");
		
		try{
			usuarioEjb = new UsuarioEjbImpl();
			usuarioEjb.getUsuario().setUsuario(request.getParameter("txtUsuario"));
			usuarioEjb.getUsuario().setContrasenia(request.getParameter("txtContrasenia"));
			
			usuarioEjb.getIniciarSesion(new TransaccionBean(idTx, msgTx));
			
			logger.info(msgTx + "Valdiación correcta de inicio de sesión.");
			respuesta = Ajax.jsonFormato(
					new AjaxBean("idTransaccion", idTx),
					new AjaxBean("codigoRespuesta", "0"),
					new AjaxBean("mensajeRespuesta", "Operación exitosa.")
					);		
			logger.info(msgTx + "Verificando roles.");
			
			
			String rolRespuesta = Utilitarios.rolValido(usuarioEjb.getUsuario().getRol());
			
			respuesta += ", {\"rol\":\"" + rolRespuesta + "\"}";
			
			session.setAttribute("usuarioSesion",usuarioEjb.getUsuario().getUsuario());
			session.setAttribute("perfilListaSesion", rolRespuesta);
			
			UtilSesion.setSesion(msgTx, usuarioEjb.getUsuario());
			
		}catch(UsuarioException ue){
			logger.warn(msgTx + "Error al iniciar sesión: "+ue.getMessage());
			respuesta = Ajax.jsonFormato(
					new AjaxBean("idTransaccion", idTx),
					new AjaxBean("codigoRespuesta", ue.getCode()),
					new AjaxBean("mensajeRespuesta", ue.getMessage())
					);
		}finally{
			logger.info(msgTx + "Fin validación de inicio de sesión");
			out.print(String.format("[%s]", respuesta));
		}
		
	}

}
