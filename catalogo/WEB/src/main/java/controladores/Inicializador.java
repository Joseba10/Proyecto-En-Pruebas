package controladores;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import DAO.ProductoDAO;
import DAO.ProductoDAOFactory;
import DAO.UsuarioDAO;
import DAO.UsuarioDAOMySQL;

import com.ipartek.TIPOS.Producto;
import com.ipartek.TIPOS.Usuario;

public class Inicializador implements ServletContextListener {

	private static Logger log = Logger.getLogger(Inicializador.class);

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {

		// Configurar LOG4J

		PropertyConfigurator.configure(Inicializador.class.getClassLoader().getResource("log4j.properties"));

		// Meter cosas de inicio
		UsuarioDAO usuarioDAO = new UsuarioDAOMySQL();
		ServletContext application = arg0.getServletContext();
		application.setAttribute("usuarioDAO", usuarioDAO);

		usuarioDAO.abrir();

		if (usuarioDAO.findAll().length != 0) {

			usuarioDAO.deleteTableUsuarios();
		}

		log.info("Abierta la base de datos en el Inicializador");
		usuarioDAO.insert(new Usuario(2, "Andoni", "q", "Andoni1"));
		usuarioDAO.insert(new Usuario(1, "admin", "admin", "admin"));
		usuarioDAO.insert(new Usuario(2, "hodei2", "pass2", "j"));
		usuarioDAO.insert(new Usuario(2, "joseba", "joseba", "joseba"));
		log.info("Añadidos 3 usuarios");
		usuarioDAO.cerrar();

		ProductoDAO productosDAO = ProductoDAOFactory.getProductoDAO();
		productosDAO.abrir();
		if (productosDAO.findAll().length != 0) {

			productosDAO.deleteTableProductos();

		}

		productosDAO.insert(new Producto("Manzana", 2.0, "Manzana de Asturias", 0, 2));
		productosDAO.insert(new Producto("Tomate", 2.0, "Tomate de Asturias", 1, 3));
		log.info("Añadidos 2 productos");
		productosDAO.cerrar();
		application.setAttribute("productoDAO", productosDAO);

		productosDAO.abrir();
		Producto[] listaproductos = productosDAO.findAll();

		productosDAO.cerrar();

		application.setAttribute("listaproductos", listaproductos);
		usuarioDAO.cerrar();
	}
}
