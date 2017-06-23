package controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProductoDAO;

import com.ipartek.TIPOS.Producto;
import com.ipartek.TIPOS.Usuario;

@WebServlet("/Zonadecompra")
public class Zonadecompra extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String op = request.getParameter("op");

		ServletContext application = request.getServletContext();

		ProductoDAO productodao = (ProductoDAO) application.getAttribute("productoDAO");
		String[] productos = request.getParameterValues("productos");

		ArrayList<Producto> productosarray = new ArrayList<>();

		// Recoger las opciones que esten seleccionadas

		if (productos != null) {
			for (String producto : productos) {

				int cantidad = Integer.parseInt(request.getParameter("cantidad-" + producto));

				System.out.println("ID: " + producto + ", cantidad: " + cantidad);
			}
		}
		System.out.println("productosarray: " + productosarray);
		// Recojo la sesion por lo que puedo coger toda la informacion que contenga
		HttpSession session = request.getSession();
		session.setAttribute("listaproductos", productos);

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (op == null) {

			System.out.println(productos.toString());

		} else {

			switch (op) { // La primera vez que entre ira a la zona del formulario
			case "primeravez":

				request.getRequestDispatcher("/WEB-INF/vistas/carrito.jsp").forward(request, response);
				break;

			case "completado":
				productodao.abrir();
				for (String nombre : productos) {

					// El array se llena de productos

					Producto p = productodao.findByName(nombre);
					productosarray.add(p);

				}
				productodao.cerrar();

				session.setAttribute("productosarray", productosarray);
				request.getRequestDispatcher("/WEB-INF/vistas/factura.jsp").forward(request, response);
				break;
			default:
				request.getRequestDispatcher("/WEB-INF/vistas/factura.jsp").forward(request, response);

				break;
			}
		}
	}
}
