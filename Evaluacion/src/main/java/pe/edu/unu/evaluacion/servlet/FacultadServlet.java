package pe.edu.unu.evaluacion.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pe.edu.unu.evaluacion.bean.AjaxBean;
import pe.edu.unu.evaluacion.ejb.FacultadEjb;
import pe.edu.unu.evaluacion.ejb.FacultadEjbImpl;
import pe.edu.unu.evaluacion.exception.FacultadException;
import pe.edu.unu.evaluacion.pojo.Facultad;
import pe.edu.unu.evaluacion.pojo.Usuario;
import pe.edu.unu.evaluacion.util.Ajax;
import pe.edu.unu.evaluacion.util.UtilRegistro;
import pe.edu.unu.evaluacion.util.UtilSesion;
import pe.edu.unu.evaluacion.util.Utilitarios;
import pe.edu.unu.evaluacion.util.UtilValidaciones;

/**
 * Servlet implementation class FacultadServlet
 */
@WebServlet("/FacultadServlet")
public class FacultadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger logger = Logger.getLogger(FacultadServlet.class);
	private FacultadEjb facultadEjb;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacultadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse 
	 * response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse 
	 * response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String accion = request.getParameter("accion");
		
		String idTx = Utilitarios.getIdTransaccion(
				request.getParameter("idTransaccion"));
		
		if("leerTodos".equals(accion)){
			leerTodos(idTx, request, out);
		}else if("crear".equals(accion)){
			crear(idTx, request, out);
		}else if("actualizar".equals(accion)){
			actualizar(idTx, request, out);
		}
	}

	private void leerTodos(String idTx, HttpServletRequest request, 
			PrintWriter out){
		
		String msjTx = "[leerTodos idTx=" + idTx + "] ";
		
		StringBuilder respuestaJson = new StringBuilder();
		StringBuilder auditoriaJS = new StringBuilder();
		StringBuilder facultadListaJS = new StringBuilder();
		
		facultadEjb = new FacultadEjbImpl(msjTx);
		
		try{
			logger.info(msjTx 
					+ "===================== Inicio acción leerTodos =====================");
			
			List<Facultad> facultadLista = facultadEjb.leerTodos();
			int cont = 0;
			for(Facultad facultad: facultadLista){
				if(cont > 0){
					facultadListaJS.append(", ");
				}
				facultadListaJS.append(
						Ajax.jsonFormato(
								new AjaxBean("idFacultad", 
										facultad.getIdFacultad()),
								new AjaxBean("idFacultad2", 
										facultad.getIdFacultad2()),
								new AjaxBean("nombreFacultad",
										Utilitarios.escapeCaracteres(
												facultad.getNombreFacultad()
										)),
								new AjaxBean("abreviatura", 
										facultad.getAbreviatura()),
								new AjaxBean("descripcion", 
										facultad.getDescripcion()),
								new AjaxBean("registro",
										facultad.getRegistro())
						));
				cont ++;
			}
			
			auditoriaJS.append(
					Ajax.jsonFormato(
							new AjaxBean("idTx", idTx),
							new AjaxBean("codRes", "0"),
							new AjaxBean("msjRes", "Operación exitosa.")
					));
		}catch(FacultadException fe){
			logger.info(msjTx 
					+ "No se ha obtenido las facultades: " + fe.getMessage());
			auditoriaJS.append(
					Ajax.jsonFormato(
							new AjaxBean("idTx", idTx),
							new AjaxBean("codRes", fe.getCode()),
							new AjaxBean("msjRes", fe.getMessage())
					));
		}catch(Exception e){
			logger.info(msjTx 
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
							new AjaxBean("facultadLista", facultadListaJS.toString(), "12")
					) );
			
			logger.info(msjTx + "respuestaJson = " + respuestaJson);
			
			logger.info(msjTx 
					+ "===================== Fin acción leerTodos =====================");
			
			out.print(respuestaJson);
		}
		
	}

	private void crear(String idTx, HttpServletRequest request, 
			PrintWriter out){
		
		String msjTx = "[crear idTx=" + idTx + "] ";
		
		StringBuilder respuestaJson = new StringBuilder();
		StringBuilder auditoriaJS = new StringBuilder();
		
		facultadEjb = new FacultadEjbImpl(msjTx);
		
		Usuario usuario = UtilSesion.obtenerUsuarioCache(msjTx, request.getSession().getAttribute("usuarioSesion").toString());
		
		try{
			logger.info(msjTx 
					+ "===================== Inicio acción crear =====================");
			
			String idFacultad2 = request.getParameter("idFacultad2");
			String nombreFacultad = request.getParameter("nombreFacultad");
			String abreviatura = request.getParameter("abreviatura");
			String descripcion = request.getParameter("descripcion");
			String registro = UtilRegistro.getRegistro("1", idTx, usuario.getIdUsuario().toString());
			
			logger.info(msjTx + "Validando datos requeridos");
			if(!UtilValidaciones.valido(nombreFacultad)){
				throw new FacultadException("1", "Ingrese <b>nombre de facultad</b>");
			}else if(!UtilValidaciones.valido(abreviatura)){
				throw new FacultadException("1", "Ingrese <b>abreviatura de facultad</b>");
			}
			logger.info(msjTx + "Validación correcta");
			
			facultadEjb.getFacultad().setIdFacultad2(idFacultad2);
			facultadEjb.getFacultad().setNombreFacultad(nombreFacultad);
			facultadEjb.getFacultad().setAbreviatura(abreviatura);
			facultadEjb.getFacultad().setDescripcion(descripcion);
			facultadEjb.getFacultad().setRegistro(registro);
			
			facultadEjb.crear();
			
			auditoriaJS.append(
					Ajax.jsonFormato(
							new AjaxBean("idTx", idTx),
							new AjaxBean("codRes", "0"),
							new AjaxBean("msjRes", "Operación exitosa.")
					));
			
		}catch(Exception e){
			logger.info(msjTx 
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
			
			logger.info(msjTx + "respuestaJson = " + respuestaJson);
			
			logger.info(msjTx 
					+ "===================== Fin acción crear =====================");
			
			out.print(respuestaJson);
		}
	}

	private void actualizar(String idTx, HttpServletRequest request,
			PrintWriter out) {
		
		String msjTx = "[actualizar idTx=" + idTx + "] ";
		
		StringBuilder respuestaJson = new StringBuilder();
		StringBuilder auditoriaJS = new StringBuilder();
		
		facultadEjb = new FacultadEjbImpl(msjTx);
		Usuario usuario = UtilSesion.obtenerUsuarioCache(msjTx, request.getSession().getAttribute("usuarioSesion").toString());
		
		try{
			logger.info(msjTx 
					+ "===================== Inicio acción actualizar =====================");
			
			String idFacultad = request.getParameter("txt_idFacultad_actualizar");
			String idFacultad2 = request.getParameter("txt_idFacultad2_actualizar");
			String nombreFacultad = request.getParameter("txt_nombreFacultad_actualizar");
			String abreviatura = request.getParameter("txt_abreviaturaFacultad_actualizar");
			String descripcion = request.getParameter("txt_descripcion_actualizar");
			String registro = "";
			
			logger.info(msjTx + "Validando datos requeridos");
			if(!UtilValidaciones.numero(idFacultad)){
				throw new FacultadException("1", "idFacultad inválido");
			}else if(!UtilValidaciones.valido(nombreFacultad)){
				throw new FacultadException("1", "Ingrese <b>nombre de facultad</b>");
			}else if(!UtilValidaciones.valido(abreviatura)){
				throw new FacultadException("1", "Ingrese <b>abreviatura de facultad</b>");
			}
			
			logger.info(msjTx + "Validación correcta");
			
			facultadEjb.leerPorId(Integer.parseInt(idFacultad));
			
			registro = UtilRegistro.getRegistro("1", idTx, usuario.getIdUsuario().toString()) + "_" + facultadEjb.getFacultad().getRegistro();
			
			facultadEjb.getFacultad().setIdFacultad2(idFacultad2);
			facultadEjb.getFacultad().setNombreFacultad(nombreFacultad);
			facultadEjb.getFacultad().setAbreviatura(abreviatura);
			facultadEjb.getFacultad().setDescripcion(descripcion);
			facultadEjb.getFacultad().setRegistro(registro);			
			
			facultadEjb.actualizar();
			
			auditoriaJS.append(
					Ajax.jsonFormato(
							new AjaxBean("idTx", idTx),
							new AjaxBean("codRes", "0"),
							new AjaxBean("msjRes", "Operación exitosa.")
					));
		}catch(FacultadException fe){
				logger.info(msjTx 
						+ "No se ha actualizado las facultades: " + fe.getMessage());
				auditoriaJS.append(
						Ajax.jsonFormato(
								new AjaxBean("idTx", idTx),
								new AjaxBean("codRes", fe.getCode()),
								new AjaxBean("msjRes", fe.getMessage())
						));
		}catch(Exception e){
			logger.info(msjTx 
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
			
			logger.info(msjTx + "respuestaJson = " + respuestaJson);
			
			logger.info(msjTx 
					+ "===================== Fin acción actualizar =====================");
			
			out.print(respuestaJson);
		}
		
	}
}
