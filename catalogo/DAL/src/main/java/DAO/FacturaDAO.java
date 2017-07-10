package DAO;

import com.ipartek.TIPOS.Carrito;
import com.ipartek.TIPOS.Factura;

public interface FacturaDAO extends IpartekDAO {

	public int insert(Factura factura);

	public int delete(int id_username);

	public void update(Carrito carrito);

	public int getMaxId();

	public int insertFacturaProducto(int idfactura, int idproductos, int cantidad);

}
