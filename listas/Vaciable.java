package listas;

/**
 * Interfaz que incluye métodos para determinar el tamaño de un objeto y si está
 * vacío o no.
 * 
 * @author LTO
 */
public interface Vaciable {
	/**
	 * Informa si una lista está vacía o no.
	 * 
	 * @return true si está vacia
	 */
	public boolean esVacia();

	/**
	 * Devolvemos el tamaño de la lista.
	 * 
	 * @return Entero representando el tamaño.
	 */
	public int longitud();
}