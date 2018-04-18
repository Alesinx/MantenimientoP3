package Pruebas;

import arboles.*;
import listas.Lista;

public class Util {

	public static <T extends Comparable<? super T>> ABB <T> ArbolArray(T[] valores){
		ABB<T> arbol = new ABB<T>();
		
		for(T elem: valores) {
			arbol.inserta(elem);
		}
		return arbol;
	}//end arbolArray
	
	public static <T extends Comparable<? super T>> AVL <T> AVLArray(T[] valores){
		AVL<T> arbol = new AVL<T>();
		
		for(T elem: valores) {
			arbol.inserta(elem);
		}
		return arbol;
	}//end AVLarray
	
	public static <T> boolean equals(T[] valores, Lista<T> lista) {
		boolean certeza = true;
		if (valores.length == lista.longitud()) {
			int i = 0;
			for (T elem : lista) {
				if (valores[i] != elem) {
					return false;
				}
				i++;
			} 
			return true;
		}
		return false;	
	}//end equals
	
	public static <T extends Comparable <? super T>> boolean compruebaAVL(T[] valores,AVL<T> avl) {
		return equals(valores,avl.inOrden());
	}//end compruebaAVL
}