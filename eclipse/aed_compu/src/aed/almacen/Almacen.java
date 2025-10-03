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
  
  @Override
 public Producto getProducto(String productoId) {
	 int esta=busquedaBinariaEnProductos(productoId);
	 if(esta<productos.size()&&this.productos.get(esta).getProductoId().compareTo(productoId)==0) {
		 return this.productos.get(esta);
	 }else return null;
 }

  @Override
 public Compra getCompra(Integer compraId) {
	 Compra compra=null;
	 for(int i=0;i<this.compras.size();i++) {
		 if(compraId.equals(compras.get(i).getCompraId())) {
			 compra=compras.get(i);
			 break;
		 }
	 }
	 return compra;
 }

 @Override
 public IndexedList<Producto> getProductos(){
	return new ArrayIndexedList<Producto>(this.productos); //La nueva lista con los productos

 }

 @Override
 public IndexedList<Compra> getCompras(){
	 return new ArrayIndexedList<Compra>(this.compras);//la nueva lista con las compras contenidas
 }

 
 @Override
 public IndexedList<Compra> comprasCliente(String clienteId){
	 IndexedList<Compra> compra=new ArrayIndexedList<Compra>();
	 for(int i=0;i<this.compras.size();i++) {
		 if(compras.get(i).getClienteId().equals(clienteId)) {
			 compra.add(compra.size(), compras.get(i));
		 }
	 }
	 return compra;
 }

 @Override
 public IndexedList<Compra> comprasProducto(String productoId){
	 IndexedList<Compra> compra=new ArrayIndexedList<Compra>();
	 for(int i=0;i<this.compras.size();i++) {
		 if(compras.get(i).getProductoId().equals(productoId)) {
			 compra.add(compra.size(), compras.get(i));
		 }
	 }
	 return compra;
 }
 /**
  * Un cliente identificado por clienteId realiza una compra
  * de cantidad productos identificados por el productoId, si hay suficientes
  * articulos disponibles.
  * Devuelve la compraId de la compra (o null si no hay suficientes articulos disponibles).
  * Debe cambiar (reducir) el numero de productos disponibles.
  */
 public Integer pedir(String clienteId, String productoId, int cantidad) {
	 
 }
 
 @Override
 public void reabastecerProducto(String productoId, int cantidad) {
	 int donde_esta=busquedaBinariaEnProductos(productoId);
	 if(donde_esta<this.productos.size()&&this.productos.get(donde_esta).getProductoId().compareTo(productoId)==0) {
		 this.productos.get(donde_esta).setCantidadDisponible(cantidad+this.productos.get(donde_esta).getCantidadDisponible());
	 }else {
		 Producto p=new Producto(productoId,cantidad);
		 this.productos.add(donde_esta, p);
	 }
 }

}
