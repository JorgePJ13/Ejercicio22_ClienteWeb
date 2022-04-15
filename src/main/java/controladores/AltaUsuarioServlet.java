package controladores;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.entidad.Usuario;
import modelo.persistencia.DaoUsuarioMySQL;
import modelo.persistencia.interfaces.DaoUsuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class AltaUsuarioServlet
 */
@WebServlet(urlPatterns = {"/AltaUsuarioServlet"}, name = "AltaUsuarioServlet")
public class AltaUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AltaUsuarioServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		DaoUsuario userDao = new DaoUsuarioMySQL();
		userDao.setConnection(conn);

		String nomUsuario = request.getParameter("username");
		String Contrasenha = request.getParameter("password");
		String Conf_Contrasenha = request.getParameter("conf_password");

		// Comprobacion de que contraseña y confirmar contraseña coinciden
		if (Contrasenha.equals(Conf_Contrasenha)) {
			Usuario user = userDao.get(nomUsuario); // busca al usuario en la base de datos

			if (user == null) {
				long idUsuario;
				user = new Usuario();
				user.setNombre(nomUsuario);
				user.setPassword(Contrasenha);
				idUsuario = userDao.add(user);
				user.setId(idUsuario);

				// Redireccionamos a index.jsp con el nuevo usuario dado de alta
				session.setAttribute("usuario", user);
				response.sendRedirect("index.jsp");
			} else {
				session.setAttribute("errorAlta", "El usuario con ese nombre ya existe");
				request.getRequestDispatcher("registro.jsp").forward(request, response);
			}
		} else {
			session.setAttribute("errorAlta", "No coinciden las contraseñas");
			request.getRequestDispatcher("registro.jsp").forward(request, response);
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
