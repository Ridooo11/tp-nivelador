## Trabajo Practico Nivelador

  Se desea crear un programa para administrar el stock de productos de una tienda de comestibles la cual tiene la capacidad de administrar HASTA 50 variedades de los mismos.
  Para cada producto se debe ingresar:
  · Código: Valor único, de tipo entero entre 0 y 500.
  · Nombre: Cadena de texto única que identifica al producto
  · Precio: Valor entero positivo.
  · Cantidad: Valor entero positivo.

## Se debe mostrar un menú con las siguientes opciones:
  1. Consultar un producto
  2. Dar de alta un producto
  3. Dar de baja un producto
  4. Modificar un producto
  5. Listar todos los productos
  6. Buscar producto más caro
  7. Buscar producto más barato
  8. Buscar producto con mayor stock
  9. Buscar producto con menor stock
  10. Calcular valor total del inventario
  11. Salir

## Métodos a utilizar: 
1. <b>ingresarProducto:</b> Mediante este método se debe poder ingresar un producto por consola.
2. <b>mostrarMenuYElegirOpcion:</b> Muestra el menú y permite que el usuario elija una opción. Devuelve la opción elegida.
3. <b>generarAccion:</b> Según la opción elegida en el menú deberá generar la acción correspondiente.
4. <b>consultarProducto:</b> Se debe llevar a cabo las operaciones correspondientes para consultar un producto y mostrar todos los datos del mismo.
5. <b>darDeAltaUnProducto:</b> Se debe llevar a cabo las operaciones correspondientes para dar de alta un producto.
6. <b>darDeBajaUnProducto:</b> Se debe llevar a cabo las operaciones correspondientes para dar de baja un producto.
7. <b>modificarProducto:</b> Permite elegir que propiedad modificar y efectuar dicha modificación
8. <b>listarProductos:</b> Muestra todos los productos activos con sus respectivos datos.
9. <b>buscarProductoMasCaro:</b> Busca y muestra el producto con el precio más alto.
10. <b>buscarProductoMasBarato:</b> Busca y muestra el producto con el precio más bajo.
11. <b>buscarProductoMayorStock:</b> Busca y muestra el producto con la mayor cantidad en stock.
12. <b>buscarProductoMenorStock:</b> Busca y muestra el producto con la menor cantidad en stock.
13. <b>calcularValorInventario:</b> Calcula y muestra el valor total del inventario (suma de precio * cantidad para todos los productos).
14. <b>ingresarEntero:</b> Valida que el dato ingresado sea un entero y esté dentro del rango especificado.
15. <b>buscarProducto:</b> Busca un producto por su código o por nombre y devuelve su posición en la estructura utilizada o -1 si no existe.
