package pe.edu.unu.evaluacion.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.edu.unu.evaluacion.bean.AjaxBean;
import pe.edu.unu.evaluacion.ejb.EscuelaEjb;
import pe.edu.unu.evaluacion.ejb.EscuelaEjbImpl;
import pe.edu.unu.evaluacion.exception.EscuelaException;
import pe.edu.unu.evaluacion.pojo.Escuela;
import pe.edu.unu.evaluacion.pojo.Usuario;
import pe.edu.unu.evaluacion.util.Ajax;
import pe.edu.unu.evaluacion.util.UtilRegistro;
import pe.edu.unu.evaluacion.util.UtilSesion;
import pe.edu.unu.evaluacion.util.UtilValidaciones;
import pe.edu.unu.evaluacion.util.Utilitarios;

/**
 * Servlet implementation class EscuelaServlet
 */
@WebServlet("/EscuelaServlet")
public class EscuelaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final Logger wlLogger = LoggerFactory.getLogger(this.getClass().getName());
	private EscuelaEjb escuelaEjb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EscuelaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String accion = request.getParameter("accion");
		
		String idTx = Utilitarios.getIdTransaccion(
				request.getParameter("idTransaccion"));
		
		if("crear".equals(accion)){
			crear(idTx, request, out);
		}
		
		if("leerTodos".equals(accion)){
			leerTodos(idTx, request, out);
		}
		
		if("actualizar".equals(accion)){
			actualizar(idTx, request, out);
		}
		
	}
	
	private void crear(String idTx, HttpServletRequest request, 
			PrintWriter out){
		
		String msjTx = "[leerTodos idTx=" + idTx + "] ";
		
		StringBuilder respuestaJson = new StringBuilder();
		StringBuilder auditoriaJS = new StringBuilder();
		
		escuelaEjb = new EscuelaEjbImpl(msjTx);		
		
		try{			
			wlLogger.info(msjTx 
					+ "===================== Inicio acción crear =====================");
			Usuario usuario = UtilSesion.obtenerUsuarioCache(msjTx, request.getSession().getAttribute("usuarioSesion").toString());
			
			String idEscuela2 = request.getParameter("txt_idEscuela2_crear");
			String nombreEscuela = request.getParameter("txt_nombreEscuela_crear");
			String abreviatura = request.getParameter("txt_abreviaturaEscuela_crear");
			String descripcion = request.getParameter("txt_descripcion_crear");
			String registro = UtilRegistro.getRegistro("1", idTx, usuario.getIdUsuario().toString());
			String idFacultad = request.getParameter("sel_idFacultad_crear");
			
			escuelaEjb.getEscuela().setIdEscuela2(idEscuela2);
			escuelaEjb.getEscuela().setNombreEscuela(nombreEscuela);
			escuelaEjb.getEscuela().setAbreviatura(abreviatura);
			escuelaEjb.getEscuela().setDescripcion(descripcion);
			escuelaEjb.getEscuela().getFacultad().setIdFacultad(Integer.parseInt(idFacultad));
			escuelaEjb.getEscuela().setRegistro(registro);
			
			escuelaEjb.crear();
			
			auditoriaJS.append(
					Ajax.jsonFormato(
							new AjaxBean("idTx", idTx),
							new AjaxBean("codRes", "0"),
							new AjaxBean("msjRes", "Operación exitosa.")
					));
		}catch(Exception e){
			wlLogger.info(msjTx 
					+ "Error no controlado: " + e.getMessage(), e);
			auditoriaJS.append(
					Ajax.jsonFormato(
							new AjaxBean("idTx", idTx),
							new AjaxBean("codRes", "-1"),
							new AjaxBean(
									"msjRes", 
									"Error no contraldo, indicar al administrador")
					));
		}finally{
			
			respuestaJson.append(Ajax.jsonFormato(
					new AjaxBean("auditoria", auditoriaJS, "55")
					)) ;
			
			wlLogger.info(msjTx + "respuestaJson = " + respuestaJson);
			
			wlLogger.info(msjTx 
					+ "===================== Fin acción crear =====================");
			
			out.print(respuestaJson);
		}
	}
	
	private void leerTodos(String idTx, HttpServletRequest request, 
			PrintWriter out){
		
		String msjTx = "[leerTodos idTx=" + idTx + "] ";
		
		StringBuilder respuestaJson = new StringBuilder();
		StringBuilder auditoriaJS = new StringBuilder();
		StringBuilder escuelaListaJS = new StringBuilder();
		
		escuelaEjb = new EscuelaEjbImpl(msjTx);
		
		try{
			wlLogger.info(msjTx 
					+ "===================== Inicio acción leerTodos =====================");
			
			escuelaEjb.leerTodos();
			
			int cont = 0;
			
			for(Escuela escuela: escuelaEjb.getEscuelaLista()){
				if(cont > 0){
					escuelaListaJS.append(", ");
				}
				
				escuelaListaJS.append(
						Ajax.jsonFormato(
								new AjaxBean("idEscuela", escuela.getIdEscuela()),
								new AjaxBean("idEscuela2", escuela.getIdEscuela2()),
								new AjaxBean("nombreEscuela", Utilitarios.escapeCaracteres(escuela.getNombreEscuela())),
								new AjaxBean("descripcion", escuela.getDescripcion()),
								new AjaxBean("abreviatura", escuela.getAbreviatura()),
								new AjaxBean("registro", escuela.getRegistro()),
								new AjaxBean("idFacultad", escuela.getFacultad().getIdFacultad()),
								new AjaxBean("nombreFacultad", escuela.getFacultad().getNombreFacultad())
						));
				cont++;
			}
			
			auditoriaJS.append(
					Ajax.jsonFormato(
							new AjaxBean("idTx", idTx),
							new AjaxBean("codRes", "0"),
							new AjaxBean("msjRes", "Operación exitosa.")
					));
		}catch(EscuelaException ee){
			wlLogger.info(msjTx 
					+ "No se ha obtenido las escuelas: " + ee.getMessage());
			auditoriaJS.append(
					Ajax.jsonFormato(
							new AjaxBean("idTx", idTx),
							new AjaxBean("codRes", ee.getCode()),
							new AjaxBean("msjRes", ee.getMessage())
					));
		}catch(Exception e){
			wlLogger.info(msjTx 
					+ "Error no controlado: " + e.getMessage(), e);
			auditoriaJS.append(
					Ajax.jsonFormato(
							new AjaxBean("idTx", idTx),
							new AjaxBean("codRes", "-1"),
							new AjaxBean(
									"msjRes", 
									"Error no contraldo, indicar al administrador")
					));
		}finally{
			respuestaJson.append(
					Ajax.jsonFormato(
							new AjaxBean("auditoria", auditoriaJS.toString(), "55"),
							new AjaxBean("escuelaLista", escuelaListaJS.toString(), "12")
					) );
			
			wlLogger.info(msjTx + "respuestaJson = " + respuestaJson);
			
			wlLogger.info(msjTx 
					+ "===================== Fin acción leerTodos =====================");
			
			out.print(respuestaJson);
		}
	}

	private void actualizar(String idTx, HttpServletRequest request, 
			PrintWriter out){
		
		String msjTx = "[actualizar idTx=" + idTx + "] ";
		
		StringBuilder respuestaJson = new StringBuilder();
		StringBuilder auditoriaJS = new StringBuilder();
		
		escuelaEjb = new EscuelaEjbImpl(msjTx);
		
		try {
			Usuario usuario = UtilSesion.obtenerUsuarioCache(msjTx, request.getSession().getAttribute("usuarioSesion").toString());
			
			wlLogger.info(msjTx 
					+ "===================== Inicio acción actualizar =====================");
			
			String idEscuela = request.getParameter("txt_idEscuela_actualizar");
			String idEscuela2 = request.getParameter("txt_idEscuela2_actualizar");
			String nombreEscuela = request.getParameter("txt_nombreEscuela_actualizar");
			String abreviatura = request.getParameter("txt_abreviaturaEscuela_actualizar");
			String descripcion = request.getParameter("txt_descripcion_actualizar");
			String registro = UtilRegistro.getRegistro("1", idTx, usuario.getIdUsuario().toString());
			String idFacultad = request.getParameter("sel_idFacultad_actualizar");
			
			wlLogger.info(msjTx + "Validando datos requeridos");
			if(!UtilValidaciones.numero(idEscuela)){
				throw new EscuelaException("1", "idEscuela inválido");
			}else if(!UtilValidaciones.valido(nombreEscuela)){
				throw new EscuelaException("1", "Ingrese <b>nombre de escuela</b>");
			}else if(!UtilValidaciones.numero(idFacultad)){
				throw new EscuelaException("1", "Seleccione <b>facultad</b>");
			}else if(!UtilValidaciones.valido(abreviatura)){
				throw new EscuelaException("1", "Ingrese <b>abreviatura</b>");
			}		
			wlLogger.info(msjTx + "Validación correcta");
			
			escuelaEjb.actualizar(Integer.parseInt(idEscuela), idEscuela2, 
					nombreEscuela, abreviatura, descripcion, registro, 
					Integer.parseInt(idFacultad));
			
			auditoriaJS.append(
					Ajax.jsonFormato(
							new AjaxBean("idTx", idTx),
							new AjaxBean("codRes", "0"),
							new AjaxBean("msjRes", "Operación exitosa.")
					));
			
		} catch (EscuelaException ee) {
			wlLogger.info(msjTx 
					+ "No se ha actualizado las facultades: " + ee.getMessage());
			auditoriaJS.append(
					Ajax.jsonFormato(new AjaxBean("idTx", idTx),
					new AjaxBean("codRes", ee.getCode()), new AjaxBean(
							"msjRes", ee.getMessage())));
		} catch (Exception e) {
			wlLogger.info(msjTx + "Error no controlado: " + e.getMessage(), e);
			auditoriaJS.append(Ajax.jsonFormato(new AjaxBean("idTx", idTx),
					new AjaxBean("codRes", "-1"), new AjaxBean("msjRes",
							"Error no controlado, indicar al administrador.")));
		} finally {
			respuestaJson.append(Ajax.jsonFormato(new AjaxBean("auditoria",
					auditoriaJS, "55")));

			wlLogger.info(msjTx + "respuestaJson = {}", respuestaJson);

			wlLogger.info(msjTx
					+ "===================== Fin acción actualizar =====================");

			out.print(respuestaJson);

		}
	}
}
