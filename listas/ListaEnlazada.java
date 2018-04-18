package listas;

import java.util.*;

/**
 * Clase que implementa la interfaz lista a través de una estructura basada en
 * nodos enlazados.
 * 
 * @author LTO
 * 
 * @param <T>
 *            Tipo de los objetos que se almacenarán en la lista enlazada.
 */
public class ListaEnlazada<T> implements Lista<T> {

	/**
	 * Número de elementos que contiene la lista enlazada
	 */
	private int numElementos;

	/**
	 * Primer nodo de la lista.
	 */
	private Nodo<T> primero;

	/**
	 * Último nodo de la lista.
	 */
	private Nodo<T> ultimo;

	/**
	 * Clase estática y anidada que representa un nodo de la lista. Cada nodo
	 * contiene el valor almacenado y una referencia a otro nodo.
	 * 
	 * @param <S>
	 *            Tipo del valor que contiene el nodo.
	 */
	static class Nodo<S> {

		/**
		 * Dato que contiene el nodo.
		 */
		protected S valor;

		/**
		 * Referencia al nodo siguiente.
		 */
		protected Nodo<S> siguiente;

		/**
		 * Constructor que crea un nodo con el valor del parámetro y sin ninguna
		 * relación con otro nodo.
		 * 
		 * @param v
		 *            Valor a almacenar en el nodo.
		 */
		protected Nodo(S v) {
			valor = v;
			siguiente = null;
		}

		/**
		 * Constructor que crea un nodo con el valor pasado como parámetro y
		 * unido a otro nodo (siguiente).
		 * 
		 * @param v
		 *            Valor que contiene el nodo.
		 * @param sig
		 *            Nodo con el que se relaciona.
		 */
		protected Nodo(S v, Nodo<S> sig) {
			valor = v;
			siguiente = sig;
		}
	}

	/**
	 * Constructor que genera una lista enlazada vacía.
	 */
	public ListaEnlazada() {
		numElementos = 0;
		primero = null;
		ultimo = null;
	}

	/**
	 * Constructor que genera una lista enlazada con los elementos de la lista
	 * que se le pasa como argumento.
	 */
	public ListaEnlazada(Lista<T> m) {
		this();
		for (T e : m) {
			agregar(e);
		}
	}

	public Lista<T> especimen() {
		return new ListaEnlazada<T>();
	}

	public int longitud() {
		return numElementos;
	}

	public void insertar(int pos, T elem) {
		// Si se intenta insertar el elemento en una posición fuera de rango se
		// lanza una excepción.
		if (pos < 0 || pos > numElementos) {
			throw new ListaException("Índice fuera de rango");
		}
		// En caso de insertar en una posición correcta, distinguimos tres
		// casos:
		if (pos == numElementos) {
			// Insertamos al final, añadiendo el elemento de forma eficiente.
			agregar(elem);
		} else {
			if (pos == 0) {
				// Insertamos al principio
				Nodo<T> nuevoNodo = new Nodo<T>(elem, primero);
				primero = nuevoNodo;
			} else {
				// Insertamos en una posición intermedia accediendo
				// secuencialmente a la posición anterior.
				Nodo<T> aux = primero;
				for (int i = 0; i < pos - 1; i++) {
					aux = aux.siguiente;
				}
				Nodo<T> nuevoNodo = new Nodo<T>(elem, aux.siguiente);
				aux.siguiente = nuevoNodo;
			}
			// No es necesario actualizar el último en ninguno de estos casos,
			// pues esto sólo sería necesario si en la lista no hay elementos,
			// caso que es tratado en la rama anterior (pos == numElementos).
			numElementos++;
		}
	}

	public boolean esVacia() {
		return numElementos == 0;
	}

	public void agregar(T elem) {
		// El elemento se añade al final utilizando la referencia al último
		// nodo.
		Nodo<T> nuevoNodo = new Nodo<T>(elem, null);
		if (numElementos != 0) {
			ultimo.siguiente = nuevoNodo;
		} else {
			primero = nuevoNodo;
		}
		ultimo = nuevoNodo;
		numElementos++;
	}

	public void eliminar(int pos) {
		// Si la posición está fuera de rango se lanza una excepción.
		if (pos < 0 || pos >= numElementos) {
			throw new ListaException("Índice fuera de rango");
		}
		// En caso de que la posición sea correcta, se distinguen tres casos:
		if (pos == 0) {
			// Se elimina el primer elemento.
			primero = primero.siguiente;
			// Si, además, era el único elemento, se actualiza el último.
			if (primero == null) {
				ultimo = null;
			}
		} else {
			// Se avanza hasta el nodo anterior al que se desea eliminar.
			Nodo<T> aux = primero;
			for (int i = 0; i < pos - 1; i++) {
				aux = aux.siguiente;
			}
			if (pos == (numElementos - 1)) {
				// Se elimina el último elemento, que no es el único (caso
				// anterior).
				ultimo = aux;
				ultimo.siguiente = null;
			} else {
				// Se elimina un elemento intermedio.
				aux.siguiente = aux.siguiente.siguiente;
			}
		}
		numElementos--;
	}

	public T elemento(int pos) {
		// Si la posición está fuera de rango se lanza una excepción.
		if ((pos < 0) || (pos >= numElementos)) {
			throw new ListaException("Índice fuera de rango");
		}
		T res = null;
		if (pos == numElementos - 1) {
			/*
			 * Si se desea consultar el último elemento accedemos a él a través
			 * de la referencia al último nodo. Este caso podría ser tratado
			 * como un caso particular del caso general, aunque sería mucho más
			 * ineficiente.
			 */
			res = ultimo.valor;
		} else {
			// acceso a un elemento intermedio, incluido el primero.
			Nodo<T> aux = primero;
			for (int i = 0; i < pos; i++) {
				aux = aux.siguiente;
			}
			res = aux.valor;
		}
		return res;
	}

	public String toString() {
		/*
		 * Representación textual de la lista como una secuencia de elementos
		 * separados por comas y encerrados entre corchetes.
		 */
		String res = "[";
		Nodo<T> aux = primero;
		while (aux != null) {
			res += aux.valor;
			if (aux != ultimo) {
				res += ", ";
			}
			aux = aux.siguiente;
		}
		res += "]";
		return res;
	}

	public int posicion(T elem) {
		int res = 0;
		Nodo<T> aux = primero;
		while (res < numElementos && !aux.valor.equals(elem)) {
			aux = aux.siguiente;
			res++;
		}
		return (res < numElementos) ? res : -1;
	}

	public void invertir() {
		if (primero != null) {
			// Mantenemos dos referencias a nodos consecutivos
			Nodo<T> anterior = primero;
			Nodo<T> posterior = primero.siguiente;
			primero.siguiente = null;
			while (posterior != null) {
				// Creamos una referencia auxiliar al nodo posterior
				Nodo<T> aux = posterior;
				// Avanzamos el nodo posterior
				posterior = posterior.siguiente;
				// Enlazamos hacia atrás el nodo auxiliar
				aux.siguiente = anterior;
				// Avanzamos el nodo anterior
				anterior = aux;
			}
			// Actualiamos las referencias al primero y último nodos.
			ultimo = primero;
			primero = anterior;
		}
	}

	public Lista<T> sublista(int inicio, int fin) {
		/*
		 * Se devuelve la sublista con los elementos que están entre inicio
		 * (inclusive) y fin (exclusive). Implementación eficiente evitando
		 * invocar el método elemento(int).
		 */
		Lista<T> res = new ListaEnlazada<T>();
		int min = Math.max(0, inicio);
		int max = Math.min(fin, numElementos);
		Nodo<T> aux = primero;
		for (int i = 0; i < min; i++) {
			aux = aux.siguiente;
		}
		for (int i = min; i < max; i++) {
			res.agregar(aux.valor);
			aux = aux.siguiente;
		}
		// Si min>=max se devuelve una lista enlazada vacía.
		return res;
	}

	/**
	 * Implementación de la interfaz Iterable<T>
	 */
	public Iterator<T> iterator() {
		return new IterLista();
	}

	public void encadenar(Lista<? extends T> m) {
		for (int i = 0; i < m.longitud(); i++) {
			this.agregar(m.elemento(i));
		}
	}

	public boolean equals(Object o) {
		if (!(o instanceof ListaEnlazada)) {
			return false;
		}
		ListaEnlazada<T> l = (ListaEnlazada) o;
		if (this.longitud() != l.longitud()) {
			return false;
		}
		for (int i = 0; i < this.longitud(); i++) {
			if (!this.elemento(i).equals(l.elemento(i))) {
				return false;
			}
		}
		return true;
	}

	public int hashCode() {
		int h = 17;
		for (T t : this) {
			h += h * t.hashCode();
		}
		return h;
	}

	public void agregarTodos(Lista<T> l) {
		for (T e : l) {
			agregar(e);
		}
	}

	public void insertarTodos(int i, Lista<T> l) {
		int j = 0;
		for (T e : l) {
			insertar(i + j, e);
			j++;
		}
	}

	/**
	 * Clase anidada que implementa la interfaz Iterator<T> para las listas,
	 * independientemente de su implementación. (Estáticas o dinámicas)
	 * 
	 * @param <T>
	 */
	class IterLista implements Iterator<T> {
		/**
		 * Nodo de la lista por la que vamos iterando. El valor que devolverá
		 * next() será proximo.valor.
		 */
		Nodo<T> anterior, actual, proximo;

		/**
		 * Creamos el iterador sobre la lista. El constructor no tiene
		 * argumentos porque se itera sobre ListaEnlazada.this
		 */
		public IterLista() {
			anterior = null;
			actual = null;
			proximo = primero;
		}

		/**
		 * Devuelve true si existe algún elemento más en la lista.
		 */
		public boolean hasNext() {
			return proximo != null;
		}

		/**
		 * Devuelve un elemento de la lista y apuntamos al siguiente.
		 */
		public T next() {
			if (proximo == null) {
				throw new NoSuchElementException("No existe el elemento");
			}
			if (actual != null) {
				anterior = actual;
			}
			actual = proximo;
			proximo = proximo.siguiente;
			return actual.valor;
		}

		/**
		 * Borra el último elemento devuelto por next()
		 */
		public void remove() {
			if (actual == null) {
				throw new IllegalStateException(
						"No se ha hecho un next antes o dos llamadas consecutivas a remove()");
			}
			actual = null;
			if (anterior == null) {
				primero = proximo;
			} else {
				anterior.siguiente = proximo;
			}
			if (proximo == null) {
				ultimo = anterior;
			}
		}
	}
}