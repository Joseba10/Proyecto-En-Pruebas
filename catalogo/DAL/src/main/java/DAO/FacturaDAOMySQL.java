package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ipartek.TIPOS.Carrito;
import com.ipartek.TIPOS.Factura;
import com.ipartek.TIPOS.Producto;

public class FacturaDAOMySQL extends IpartekDAOMySQL implements FacturaDAO {

	private final static String FIND_ID = "Select * from facturas where id=?";
	private final static String INSERT = "Insert into facturas(numero_factura,id_usuarios,fecha)Values(?,?,?)";
	private final static String Update = "Update facturas Set numero_facturas=?,id_usuarios=?,fecha=? where id=?";
	private final static String Delete = "Delete from facturas where id=?";
	private final static String GET_MAX_ID = "SELECT MAX(ID) FROM facturas";
	private final static String Find_Produc_By_Factura = "Select * from productos ,facturas_productos where facturas_productos.id_facturas=? and productos.id=facturas_productos.id_productos";
	private final static String Registrar_Productos = "Insert into facturas_productos(id_facturas,id_productos,cantidad) values(?,?,?)";

	private PreparedStatement psFindById, psInsert, psUpdate, psDelete, psBuscarfactura, psRegistrar_Productos, psGetMaxId;
	public ResultSet rs = null;

	// LOG4J
	private static Logger log = Logger.getLogger(ProductoDAOMySQL.class);

	public FacturaDAOMySQL() {

	}

	public int insert(Factura factura) {
		ResultSet generatedKeys = null;
		try {
			psInsert = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			psInsert.setInt(1, factura.getNumero_factura());
			psInsert.setInt(2, factura.getId_usuarios());
			psInsert.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
			int res = psInsert.executeUpdate();
			if (res != 1) {
				throw new DAOException("La inserción ha devuelto un valor " + res);
			}
			generatedKeys = psInsert.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			} else {
				throw new DAOException("No se ha recibido la clave generada");
			}
		} catch (SQLException e) {
			throw new DAOException("Error en el insert", e);
		} finally {
			cerrar(psInsert, generatedKeys);
		}
	}

	@Override
	public int delete(int id_username) {
		try {
			psDelete = con.prepareStatement(Delete);
			psDelete.setInt(1, id_username);
			int res = psDelete.executeUpdate();
			if (res != 1)
				throw new DAOException("La actualizacion ha devuelto un valor " + res);
		} catch (Exception e) {

			throw new DAOException("Error en el delete ", e);
		}
		return id_username;

	}

	public int getMaxId() {

		ResultSet rs;
		int maxId = 0;
		try {
			psGetMaxId = con.prepareStatement(GET_MAX_ID);

			rs = psGetMaxId.executeQuery();

			while (rs.next()) {
				maxId = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException("Error al obtener maxId", e);
		} finally {
			cerrar(psGetMaxId);
		}

		return maxId;
	}

	@Override
	public void update(Carrito carrito) {
		try {
			psUpdate = con.prepareStatement(Update);
			psUpdate.setInt(1, carrito.getId_usuario());
			psUpdate.setInt(2, carrito.getProductos());
			psUpdate.setInt(3, carrito.getCantidad());

			log.info(carrito.getId_usuario());
			log.info(carrito.getProductos());
			log.info(carrito.getCantidad());

			int res = psUpdate.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualizacion ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error al actualizar", e);
		}
	}

	private void cerrar(PreparedStatement ps) {

		cerrar(ps, null);

	}

	private void cerrar(PreparedStatement ps, ResultSet rs) {

		try {
			if (rs != null)
				rs.close();

		} catch (Exception e) {

			throw new DAOException("Error en el cierre de ps o rs", e);

		}
	}

	// Adaptar

	public Producto[] findProductoByFacturaId(int id) {
		ArrayList<Producto> productos = new ArrayList<>();
		ResultSet rs = null;

		Producto producto;

		try {
			psBuscarfactura = con.prepareStatement(Find_Produc_By_Factura);
			psBuscarfactura.setInt(1, id);

			rs = psBuscarfactura.executeQuery();

			while (rs.next()) {
				producto = new Producto();

				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setImagen(rs.getInt("imagen"));
				producto.setCantidad(rs.getInt("cantidad"));

				productos.add(producto);
			}
		} catch (Exception e) {
			throw new DAOException("Error al buscar los productos de la factura", e);
		} finally {
			cerrar(psBuscarfactura);
		}

		return productos.toArray(new Producto[productos.size()]);
	}

	public int insertFacturaProducto(int id_factura, int id_producto, int cantidad) {
		try {
			psRegistrar_Productos = con.prepareStatement(Registrar_Productos);
			psRegistrar_Productos.setInt(1, id_factura);
			psRegistrar_Productos.setInt(2, id_producto);
			psRegistrar_Productos.setInt(3, cantidad);
			int res = psRegistrar_Productos.executeUpdate();
			if (res != 1) {
				throw new DAOException("La inserción ha devuelto un valor " + res);
			}
		} catch (SQLException e) {
			throw new DAOException("Error en al insertar producto en la factura", e);
		} finally {
			cerrar(psRegistrar_Productos);
		}
		return 1;
	}
}
