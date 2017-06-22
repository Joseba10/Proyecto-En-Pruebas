package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.TIPOS.Usuario;

@WebServlet("/Zonadecompra")
public class Zonadecompra extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String op = request.getParameter("op");

		String[] productos = request.getParameterValues("productos");

		// Recoger las opciones que esten seleccionadas
		for (String producto : productos) {

			int cantidad = Integer.parseInt(request.getParameter("cantidad-" + producto));

			System.out.println("ID: " + producto + ", cantidad: " + cantidad);
		}

		// Recojo la sesion por lo que puedo coger toda la informacion que contenga
		HttpSession session = request.getSession();

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		switch (op) { // La primera vez que entre ira a la zona del formulario
		case "primeravez":

			request.getRequestDispatcher("/WEB-INF/vistas/carrito.jsp").forward(request, response);
			break;

		default:
			request.getRequestDispatcher("/WEB-INF/vistas/factura.jsp").forward(request, response);

			break;
		}
	}
}
