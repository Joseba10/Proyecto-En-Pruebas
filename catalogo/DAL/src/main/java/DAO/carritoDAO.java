package DAO;

import com.ipartek.TIPOS.Carrito;

public interface carritoDAO extends IpartekDAO {

	public int insert(Carrito carrito);

	public int delete(int id_username);

	public void update(Carrito carrito);

}
