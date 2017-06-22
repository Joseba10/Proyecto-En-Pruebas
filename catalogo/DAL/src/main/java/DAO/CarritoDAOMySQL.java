package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.ipartek.TIPOS.Carrito;

public class CarritoDAOMySQL extends IpartekDAOMySQL implements carritoDAO {

	private final static String FIND_ID = "Select * from compras where username=?";
	private final static String INSERT = "Insert into compras(username,productos,cantidad)Values(?,?,?)";
	private final static String Update = "Update compras Set username=?,productos=?,cantidad=? where username=?";
	private final static String Delete = "Delete from compras where username=?";
	private PreparedStatement psFindById, psInsert, psUpdate, psDelete;
	public ResultSet rs = null;

	// LOG4J
	private static Logger log = Logger.getLogger(ProductoDAOMySQL.class);

	public CarritoDAOMySQL() {

	}

	@Override
	public int insert(Carrito carrito) {

		ResultSet generatedKeys = null;

		try {
			psInsert = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			psInsert.setInt(1, carrito.getId_usuario());
			psInsert.setInt(2, carrito.getProductos());
			psInsert.setInt(3, carrito.getCantidad());

			int res = psInsert.executeUpdate();

			if (res != 1)
				throw new DAOException("La insercion ha devuelto un valor " + res);

			generatedKeys = psInsert.getGeneratedKeys();

			if (generatedKeys.next())
				return generatedKeys.getInt(1);

			else
				throw new DAOException("No se ha recibido la clave generada");

		} catch (Exception e) {
			throw new DAOException("Error en Insert", e);
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

}
