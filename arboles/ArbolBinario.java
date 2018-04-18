package arboles;

import listas.Lista;
 
/**
 * Interfaz de árboles binarios con operaciones típicas.
 * 
 * @author LTO
 * 
 * @param <T>
 */
public interface ArbolBinario<T extends Comparable<? super T>> {
	/**
	 * Comprueba si un árbol está vacío.
	 */
	boolean vacio();

	/**
	 * Devuelve el número de elementos.
	 */
	int numElementos();

	/**
	 * Devuelve la altura de un árbol. Un árbol vacío tiene altura 0. La altura
	 * de un árbol no vacío es igual a 1 más el máximo de las alturas de sus
	 * hijos.
	 * 
	 * @return altura de árbol.
	 */
	int altura();

	/**
	 * Comprueba si el elemento está en el árbol.
	 * 
	 * @param elem
	 *            elemento a buscar.
	 * @return true si está, false si no.
	 */
	boolean pertenece(T elem);

	/**
	 * Devuelve una lista con el recorrido en inorden de un árbol.
	 * 
	 * @return recorrido en inorden
	 */
	Lista<T> inOrden();

	/**
	 * Devuelve la lista con el recorrido desde el nodo raíz hasta el elemento
	 * pasado como argumento.
	 * 
	 * @param elem
	 *            es el elemento hasta el que se pide el camino.
	 * @return camino hasta el elemento.
	 * @throws ABBException
	 *             lanza una excepción del tipo ABBException si el elemento no
	 *             esta en el árbol.
	 */
	Lista<T> camino(T elem) throws ABBException;

	/**
	 * Comprueba si el árbol está ordenado.
	 * 
	 * @return true si está ordenado, o false si no.
	 */
	boolean ordenado();

	/**
	 * Comprueba si el árbol está equilibrado en altura.
	 * 
	 * @return true si está equilibrado, o false si no.
	 */ 
	boolean equilibrado();

	/**
	 * inserta un elemento en el árbol
	 * 
	 * @param elem
	 *            elemento a insertar
	 */
	void inserta(T elem);

	/**
	 * elimina un elemento en el árbol
	 * 
	 * @param elem
	 *            elemento a eliminar
	 */
	void elimina(T elem);
}
