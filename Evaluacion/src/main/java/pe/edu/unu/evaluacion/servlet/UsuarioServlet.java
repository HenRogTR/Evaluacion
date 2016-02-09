package pe.edu.unu.evaluacion.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import pe.edu.unu.evaluacion.pojo.Usuario;
import pe.edu.unu.evaluacion.util.Ajax;
import pe.edu.unu.evaluacion.util.UtilSesion;
import pe.edu.unu.evaluacion.util.Utilitarios;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(UsuarioServlet.class);
	private UsuarioEjb usuarioEjb;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String accion = request.getParameter("accion");
		
		String idTx = Utilitarios.getIdTransaccion(
				request.getParameter("idTransaccion"));

		if ("obtenerDatosPorUsuario".equals(accion)) {
			obtenerDatosPorUsuario(idTx, request, out);
			return;
		}
		
		if("establecerPerfil".equals(accion)){			
			establecerPerfil(idTx, request, out);
			return;
		}
		
		if("usuarioCargarTodos".equals(accion)){
			usuarioCargarTodos(idTx, request, out);
			return;
		}

	}	

	private void obtenerDatosPorUsuario(String idTx, HttpServletRequest request, 
			PrintWriter out){

		String msgTx = "[obtenerDatosPorUsuario idTx=" + idTx + "] ";
		String respuestaJson = "";
		
		Usuario usuarioObj = null;
		usuarioEjb = new UsuarioEjbImpl();
		
		String usuario = request.getParameter("usuario");

		logger.info(msgTx 
				+ " ===================== Inicio acción obtenerDatosPorUsuario =====================");			
		logger.info(msgTx + "Dato de entrada: usuario=" + usuario);
		
		try{				
			
			usuarioObj = UtilSesion.obtenerUsuarioCache(msgTx, usuario);
			
			if(usuarioObj == null){
				usuarioObj = usuarioEjb.leerPorUsuario(
						new TransaccionBean(idTx, msgTx), 
						usuario);
			}
			
			respuestaJson = Ajax.jsonFormato(
					new AjaxBean("idTransaccion", idTx),
					new AjaxBean("codigoRespuesta", "0"),
					new AjaxBean(
							"mensajeRespuesta", "Operación exitosa.")
					);
			
			logger.info(msgTx + "Verificando roles.");
			
			String rolRespuesta = Utilitarios.rolValido(usuarioObj.getRol());
			
			respuestaJson += Ajax.jsonFormato(
					new AjaxBean("rol", rolRespuesta)
					);
			
		}catch(UsuarioException ue){
			respuestaJson = Ajax.jsonFormato(
					new AjaxBean("idTransaccion", idTx),
					new AjaxBean("codigoRespuesta", ue.getCode()),
					new AjaxBean(
							"mensajeRespuesta", ue.getMessage())
					);
		}finally{
			
			respuestaJson = String.format(
					"[%s]", respuestaJson.replace("}{", "}, {"));
			
			logger.info(msgTx + "respuestaJson = " + respuestaJson);
			logger.info(msgTx 
					+ "===================== Fin acción obtenerDatosPorUsuario =====================");
			
			out.print(respuestaJson);
		}	
	}
	
	private void establecerPerfil(String idTx, HttpServletRequest request, 
			PrintWriter out){
		String msgTx = "[establecerPerfil idTx=" + idTx + "] ";
		String respuestaJson = "";		

		HttpSession session = request.getSession();
		String perfil = request.getParameter("perfil");
		
		logger.info(msgTx + " Estableciendo en sesión perfil=" + perfil);
		session.setAttribute("perfilSesion", perfil);
		
		respuestaJson = Ajax.jsonFormato(
				new AjaxBean("idTransaccion", idTx),
				new AjaxBean("codigoRespuesta", "0"),
				new AjaxBean(
						"mensajeRespuesta", "Operación exitosa.")
				);
		
		respuestaJson = String.format(
				"[%s]", respuestaJson.replace("}{", "}, {"));			
		logger.info(msgTx + "respuestaJson = " + respuestaJson);

		out.print(respuestaJson);
	}

	private void usuarioCargarTodos(String idTx, HttpServletRequest request,
			PrintWriter out) {
		
		String msgTx = "[obtenerDatosPorUsuario idTx=" + idTx + "] ";
		String respuestaJson = "";
		String auditoriaJS = "";
		String usuarioJS = "";
		
		List<Usuario> usuarioLista = null;
		usuarioEjb = new UsuarioEjbImpl();
		try{
			usuarioLista = usuarioEjb.leerTodos(
					new TransaccionBean(idTx, msgTx));
		
			auditoriaJS = Ajax.jsonFormato(
					new AjaxBean("idTx", idTx),
					new AjaxBean("codRes", "0"),
					new AjaxBean("msjRes", "Operación exitosa.")
					);
			
			StringBuilder usuarioStr = new StringBuilder();
			for(Usuario usuarioObj: usuarioLista){
				
				String perfil = "";
				String registro = "";
				
				usuarioStr.append(Ajax.jsonFormato(
						new AjaxBean(
								"idUsuario", 
								usuarioObj.getIdUsuario().toString()),
						new AjaxBean("idUsuario2", usuarioObj.getIdUsuario2()),
						new AjaxBean("perfil", perfil),
						new AjaxBean("usuario", usuarioObj.getUsuario()),
						new AjaxBean("contrasenia", "*********"),
						new AjaxBean("llave", usuarioObj.getLlave()),
						new AjaxBean("registro", registro)
						));
			}
			
			usuarioJS = usuarioStr.toString();
			
		}catch(UsuarioException ue){
			logger.info(msgTx 
					+ "No se ha obtenido los usuarios: " + ue.getMessage());
			auditoriaJS = Ajax.jsonFormato(
					new AjaxBean("idTx", idTx),
					new AjaxBean("codRes", ue.getCode()),
					new AjaxBean("msjRes", ue.getMessage())
					);
		}catch(Exception e){
			logger.error(msgTx + "Ocurrió un error a leer todos los usuarios: " + e.getMessage(), e);
			auditoriaJS = Ajax.jsonFormato(
					new AjaxBean("idTx", idTx),
					new AjaxBean("codRes", "-1"),
					new AjaxBean("msjRes", e.getMessage())
					);
		}finally{
			
			respuestaJson = Ajax.jsonFormato(
					new AjaxBean("auditoria", auditoriaJS, "55"),
					new AjaxBean("usuarioLista", usuarioJS, "12")
					) ;
			
			logger.info(msgTx + "respuestaJson = " + respuestaJson);
		}
		out.print(respuestaJson);
	}
}
