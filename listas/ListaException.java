package listas;

/**
 * Clase que gestionará las excepciones de las listas. Se trata de excepciones no comprobadas.
 * 
 * @author LTO
 */
public class ListaException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8135130214524626482L;

	/**
	 * 
	 */
	public ListaException() {
		super();
	}

	public ListaException(String mensaje) {
		super(mensaje);
	}
}
    