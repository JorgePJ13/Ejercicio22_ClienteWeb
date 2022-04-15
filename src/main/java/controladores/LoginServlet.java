package controladores;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.gson.Gson;

import modelo.entidad.Usuario;
import modelo.negocio.GestorUsuario;
import modelo.persistencia.DaoUsuarioMySQL;
import modelo.persistencia.interfaces.DaoUsuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = {"/usuarios/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/JSON;charset=UTF-8");
		PrintWriter write = response.getWriter();
		JSONObject json = new JSONObject();
		
		HttpSession session = request.getSession();
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		DaoUsuario userDao = new DaoUsuarioMySQL();
		userDao.setConnection(conn);

		String Usuario = request.getParameter("fName");
		String Contrasenha = request.getParameter("password");
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		Usuario user = userDao.get(Usuario); // Busca al usuario en la base de datos

		if (user != null) {
			if (Contrasenha.equals(user.getPassword())) {
				session.setAttribute("usuario", user);
				//response.sendRedirect("index.jsp");
				
				json.put("valido", true);
				response.getWriter().write(json.toString());
				
			} else {
				session.setAttribute("errorInicio", "Password incorrecta");
				//request.getRequestDispatcher("login.jsp").forward(request, response);
				
				json.put("valido", false);
				response.getWriter().write(json.toString());
			}
		} else {
			session.setAttribute("errorInicio", "El usuario no existe");
			//request.getRequestDispatcher("login.jsp").forward(request, response);
			
			json.put("valido", false);
			response.getWriter().write(json.toString());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

}
