package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CompraDAO;
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

		CompraDAO compraDAO = (CompraDAO) application.getAttribute("compraDAO");

		ProductoDAO productodao = (ProductoDAO) application.getAttribute("productoDAO");
		String[] productos = request.getParameterValues("productos");

		String[] cantidades = request.getParameterValues("cantidad");

		ArrayList<Producto> productosarray = new ArrayList<>();

		// Recoger las opciones que esten seleccionadas
		// int cantidad = 1;
		// if (productos != null) {
		// for (String producto : productos) {
		//
		// if (request.getParameter("cantidad-" + producto) == "") {
		//
		// cantidad = 1;
		// }
		//
		// else {
		// cantidad = Integer.parseInt(request.getParameter("cantidad"));
		//
		// System.out.println("ID: " + producto + ", cantidad: " + cantidad);
		// }
		// }
		// }
		System.out.println("productosarray: " + productosarray);
		// Recojo la sesion por lo que puedo coger toda la informacion que contenga
		HttpSession session = request.getSession();
		session.setAttribute("listaproductos", productos);

		productosarray = (ArrayList<Producto>) session.getAttribute("productosarray");

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (op == null) {

			System.out.println(productos.toString());

		} else {

			switch (op) { // La primera vez que entre ira a la zona del formulario
			case "primeravez":

				productodao.abrir();
				Producto[] arrayproductos = productodao.findAll();
				productodao.cerrar();
				request.setAttribute("arrayproductos", arrayproductos);
				request.getRequestDispatcher("/WEB-INF/vistas/carrito.jsp").forward(request, response);
				break;

			case "completado":

				Enumeration<String> nombres = request.getParameterNames();

				productodao.abrir();

				TreeMap<Integer, Producto> productosCarrito = (TreeMap<Integer, Producto>) session.getAttribute("productosCarrito");

				if (productosCarrito == null) {

					productosCarrito = new TreeMap<Integer, Producto>();
				}

				while (nombres.hasMoreElements()) {
					String idStr = nombres.nextElement();
					int cantidad;
					try {

						cantidad = Integer.parseInt(request.getParameter(idStr));
					} catch (Exception e) {

						cantidad = 0;
					}
					System.out.println(idStr);
					System.out.println(request.getParameter(idStr));
					if (cantidad != 0) {

						int id = Integer.parseInt(idStr);

						Producto p = productodao.findById(id);

						int cantidadRepetida = 0;
						if (productosCarrito.containsKey(p.getId())) {

							/*
							 * Hacer TreeMap para sustituirlo de esta manera podria coger
							 * los atributos,en este caso cantidad
							 */
							cantidadRepetida = productosCarrito.get(p.getId()).getCantidad();

							// for (Producto productoRepetido : productosCarrito.values())
							// {
							//
							// if (productoRepetido.getId() == p.getId()) {
							// cantidadRepetida = productoRepetido.getCantidad();
							//
							// }
							//
							// }

							p.setCantidad(cantidad + cantidadRepetida);

						} else {
							p.setCantidad(cantidad);
						}

						if (cantidad <= 0) {

							productosCarrito.remove(p.getId(), p);
						} else {

							productosCarrito.put(p.getId(), p);
						}
					}
				}
				productodao.cerrar();

				System.out.println(productosCarrito);
				session.setAttribute("productosCarrito", productosCarrito);

				request.getRequestDispatcher("/WEB-INF/vistas/factura.jsp").forward(request, response);
				break;

			case "confirmado": {

				compraDAO.abrir();

				compraDAO.cerrar();

			}

			default:
				request.getRequestDispatcher("/WEB-INF/vistas/factura.jsp").forward(request, response);

				break;
			}
		}
	}
}
