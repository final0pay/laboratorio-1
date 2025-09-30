package aed.almacen;

import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.indexedlist.ArrayIndexedList;


/**
 * Implementa la logica del almacen.
 */
public class Almacen implements ClienteAPI, AlmacenAPI, ProductorAPI {

  // Compras (sin ningun orden especial)
  private ArrayIndexedList<Compra> compras;
  // Productos ordenados ascendamente usando el productoId de un Product.
  private ArrayIndexedList<Producto> productos;

  // No es necesario cambiar el constructor
  /**
   * Crea un almacen.
   */
  public Almacen() {
    this.compras = new ArrayIndexedList<>();
    this.productos = new ArrayIndexedList<>();
  }

  public int busquedaBinariaEnProductos(String productosid) {
	  int izquierda=0;
	  int derecha = productos.size()-1;
	  while(izquierda<=derecha) {
		  int medio = (izquierda+derecha)/2;
		  int comparacion = productos.get(medio).getProductoId().compareTo(productosid);
		  if(comparacion==0) {
			  return medio;
		  }else if(comparacion>0) {
			  derecha=medio-1;
		  }else {
			  izquierda=medio+1;
		  }
	  }
	   
	  return izquierda;
  }

  // Implementa los métodos necesarios aqui ...
}
