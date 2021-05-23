package controlador.control_Recetario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import DAO.RecetasDAO;
import exception_validations.CantidadHarinaExpception;
import exception_validations.CantidadIngrediente;
import exception_validations.CantidadLiquidoException;
import exception_validations.HarinaException;
import exception_validations.LiquidosException;
import exception_validations.PreparacionException;
import exception_validations.RecetaExisteException;
import exception_validations.TituloException;
import modelo.Recetas;

/**
 * Servlet implementation class Controlador_Recetas
 */
@WebServlet("/Controlador_Recetas")
public class Controlador_Recetas extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Resource(lookup = "jdbc/recetario")
	private DataSource miPool;
	private RecetasDAO recetasDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlador_Recetas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String opcion = request.getParameter("opcion");
		
		if(opcion == null) {
			mostrar_titulos_bd(request, response);
		}
		
		switch (opcion) {
		case "mostrar":

			mostrar_tabla(request, response);
			
			break;
			
		case "inserta":
			
			insertar_bd(request, response);
			
			break;

		default:
			
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void mostrar_titulos_bd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			recetasDAO =new RecetasDAO(miPool);
		}catch (Exception e){
			throw new ServletException();
		}
		
		request.setAttribute("titulosBD", (ArrayList<Recetas>) recetasDAO.readAll());
		request.getRequestDispatcher("vista/verTitulosBD.jsp").forward(request, response);
		
	}
	
	public void insertar_bd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			recetasDAO =new RecetasDAO(miPool);
		}catch (Exception e){
			throw new ServletException();
		}
		
		try {
			
			Recetas recetabd = (Recetas) request.getSession().getAttribute("receta");
			
			ArrayList<Recetas> recetasBDS = (ArrayList<Recetas>) recetasDAO.readAll();
			
			for(int i=0;i<recetasBDS.size();i++) {
				
				if(recetasBDS.get(i).getTitulo().equals(recetabd.getTitulo())) {
					
					throw new RecetaExisteException("La receta ya existe en la base de datos");
					
				}
				
			}
			
			
			recetasDAO.create(recetabd);
			request.setAttribute("tituloinsertado", recetabd.getTitulo());
			request.getRequestDispatcher("vista/verReceta.jsp").forward(request, response);
			
		}catch(SQLException e) {
			e.getMessage();
			e.getErrorCode();
		}catch(RecetaExisteException r) {
			request.setAttribute("errorInserta", r.getMessage());
			request.getRequestDispatcher("vista/verReceta.jsp").forward(request, response);
			r.printStackTrace();
		}
		
		
	}
	
	public void mostrar_tabla(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			recetasDAO =new RecetasDAO(miPool);
		}catch (Exception e){
			throw new ServletException();
		}
		
		//TODO REALIZAR ARRAYLIST Y CREAR UN ATRIBUTO REQUEST DONDE ESTE ESE ARRAY, UNA VEZ REALIZADO REENVIAR A FORMULARIO Y SACAR CADA VALOR
		
		try {
			
			String titulo = request.getParameter("titulo");
			
			if(titulo.isEmpty() || titulo == null) {
				throw new TituloException("Debe de indicar un título");
			}
			
			String harina = request.getParameter("harina");
			
			if(harina == null || harina.equals("null")) {
				throw new HarinaException("Debe de indicar el tipo de harina");
			}
			
			Double cantidadHarina = Double.valueOf(request.getParameter("cantidadHarina"));
			
			if(cantidadHarina == null || cantidadHarina < 0) {
				throw new CantidadHarinaExpception("Debe de indicar la cantidad de harina");
			}
			
			String[] liquidos = request.getParameterValues("liquidos");
			
			if(liquidos == null) {
				throw new LiquidosException("Debe de indicar al menos un líquido");
			}
				
			
			String liquidos_seleccionados = "";
			
			for(int i=0;i<liquidos.length;i++) {
				
				switch(liquidos[i]) {
				
					case "agua":
						
						Double cantAgua = Double.valueOf(request.getParameter("aguaMl"));
						
						if(cantAgua != null) {
							
							liquidos_seleccionados += liquidos[i]+" "+cantAgua+"ml,";
							
						}else {
							
							throw new CantidadLiquidoException("Debe de indicar la cantidad de ml para el agua");
							
						}
						
					break;
					
					case "leche":
				
						Double cantLeche = Double.valueOf(request.getParameter("lecheMl"));
						
						if(cantLeche != null) {
							
							liquidos_seleccionados += liquidos[i]+" "+cantLeche+"ml,";					
							
						}else {
							
							throw new CantidadLiquidoException("Debe de indicar la cantidad de ml para la leche");
							
						}
						
					break;
					
					case "aceite":
						
						Double cantAceite = Double.valueOf(request.getParameter("aceiteMl"));
						
						if(cantAceite != null) {
							
							liquidos_seleccionados += liquidos[i]+" "+cantAceite+"ml,";
							
						}else {
							
							throw new CantidadLiquidoException("Debe de indicar la cantidad de ml para el aceite");
							
						}
						
					break;
					
					default:
				
				}
				
			}
			
			String liquidos_seleccionados_retocado = "";
			
			for(int i=0;i<liquidos_seleccionados.length();i++) {
			
				if(i != liquidos_seleccionados.length()-1) {
					
					liquidos_seleccionados_retocado += liquidos_seleccionados.charAt(i);
					
				}
				
			}
			
			Double cantidadLevadura = Double.valueOf(request.getParameter("levadura"));
			
			if(cantidadLevadura == null || cantidadLevadura < 0) {
				throw new CantidadIngrediente("Debe indicar la cantidad de levadura");
			}
			
			Double cantidadAzucar = Double.valueOf(request.getParameter("azucar"));
			
			if(cantidadAzucar == null || cantidadAzucar < 0) {
				throw new CantidadIngrediente("Debe indicar la cantidad de azúcar");
			}
			
			Double cantidadSal = Double.valueOf(request.getParameter("sal"));
			
			if(cantidadSal == null || cantidadSal < 0) {
				throw new CantidadIngrediente("Debe indicar la cantidad de sal");
			}
			
			String preparacion = request.getParameter("preparacion");
			
			if(preparacion == null) {
				throw new PreparacionException("Debe indicar la preparación");
			}
			
			Recetas receta = new Recetas(titulo,harina,cantidadHarina,liquidos_seleccionados_retocado,cantidadLevadura,cantidadAzucar,cantidadSal,preparacion);
			
			List<String> listaTitulos = (List<String>) request.getSession().getAttribute("listaTitulos");
			
			if(listaTitulos == null) {
				
				listaTitulos = new ArrayList<String>();
				listaTitulos.add(titulo);
				
			}else {
				
				if(listaTitulos.indexOf(titulo) == -1) {
					
					listaTitulos.add(titulo);
					
				}
				
			}
			
			request.getSession().setAttribute("listaTitulos", listaTitulos);
			request.getSession().setAttribute("receta", receta);
			request.setAttribute("controlador", titulo);
			request.getRequestDispatcher("vista/verReceta.jsp").forward(request, response);
			
		}catch(TituloException | HarinaException | CantidadHarinaExpception | LiquidosException | CantidadLiquidoException | CantidadIngrediente | PreparacionException e) {
			request.setAttribute("errores",e.getMessage());
			request.getRequestDispatcher("vista/formuIngredientes.jsp").forward(request, response);
			e.printStackTrace();
		}catch(NumberFormatException n) {
			request.setAttribute("errores","Debe de ser númerico");
			request.getRequestDispatcher("vista/formuIngredientes.jsp").forward(request, response);
			n.printStackTrace();
		}catch(Exception x) {
			x.printStackTrace();
		}
		
	}

}
