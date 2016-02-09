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

import pe.edu.unu.evaluacion.ejb.SemestreEjb;
import pe.edu.unu.evaluacion.util.Utilitarios;

/**
 * Servlet implementation class SemestreServlet
 */
@WebServlet("/SemestreServlet")
public class SemestreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final Logger wlLogger = LoggerFactory.getLogger(this.getClass().getName());
	private SemestreEjb semestreEjb;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SemestreServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		
	}
	
	private void leerTodos(String idTx, HttpServletRequest request, 
			PrintWriter out){
		
	}

	private void actualizar(String idTx, HttpServletRequest request, 
			PrintWriter out){
		
	}
}
