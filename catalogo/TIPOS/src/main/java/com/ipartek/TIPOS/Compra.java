package com.ipartek.TIPOS;

public class Compra {

	private String username;
	private String productos;
	private int cantidad;
	private int preciototal;

	public Compra() {

	}

	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + preciototal;
		result = prime * result + ((productos == null) ? 0 : productos.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		if (cantidad != other.cantidad)
			return false;
		if (preciototal != other.preciototal)
			return false;
		if (productos == null) {
			if (other.productos != null)
				return false;
		} else if (!productos.equals(other.productos))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProductos() {
		return productos;
	}

	public void setProductos(String productos) {
		this.productos = productos;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getPreciototal() {
		return preciototal;
	}

	public void setPreciototal(int preciototal) {
		this.preciototal = preciototal;
	}

}
