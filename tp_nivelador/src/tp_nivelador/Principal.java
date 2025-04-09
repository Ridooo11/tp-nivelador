package tp_nivelador;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		final int CANT_PRODUCTOS_MAX = 50;
		final int CANT_ATRIBUTOS = 4;
		final int COD_MAX = 500;
		final int COD_MIN = 0;
		final int CANT_PRODUCTOS_MIN = 0;
		
		final String[][] PRODUCTOS = new String[CANT_PRODUCTOS_MAX][CANT_ATRIBUTOS];
		Scanner scan = new Scanner(System.in);
		
		int opc = 0;
		int cantProductos = 0;
		
		do {
			opc = mostrarMenuYElegirOpcion(scan);
			cantProductos = generarAccion(scan, PRODUCTOS, opc, cantProductos, COD_MIN, COD_MAX, CANT_PRODUCTOS_MIN, CANT_PRODUCTOS_MAX, CANT_ATRIBUTOS);
			
		} while(opc!=11);
		
		scan.close();
		
		

	}
	
	private static int mostrarMenuYElegirOpcion(Scanner s) {
		System.out.println("--------------------------------------");
		System.out.println("MENU");
		System.out.println("1) Consultar un producto");
		System.out.println("2) Dar de alta un producto");
		System.out.println("3) Dar de baja un producto");
		System.out.println("4) Modificar un producto");
		System.out.println("5) Listar todos los productos");
		System.out.println("6) Buscar producto mas caro");
		System.out.println("7) Buscar producto mas barato");
		System.out.println("8) Buscar producto con mayor stock");	
		System.out.println("9) Buscar producto con menor stock");
		System.out.println("10) Calcular valor total del inventario");
		System.out.println("11) Salir");
		System.out.println("--------------------------------------");
		return ingresarEntero(s,1,11);
	}
	
	private static int generarAccion(Scanner s, final String[][] PRODUCTOS , final int OPC, int cantProductos, final int COD_MIN, final int COD_MAX, final int CANT_PRODUCTOS_MIN, final int CANT_PRODUCTOS_MAX, final int CANT_ATRIBUTOS) {
		boolean superaCantidadMinimaDeProductos = cantProductos > CANT_PRODUCTOS_MIN;
		boolean noSuperaCantidadMaximaDeProductos = cantProductos < CANT_PRODUCTOS_MAX;
		switch(OPC) {
		case 1:
				if(superaCantidadMinimaDeProductos) {		
					consultarProducto(s, PRODUCTOS, cantProductos, COD_MIN, COD_MAX);
				} else {
					System.out.println("No hay productos ingresados para consultar.");
				}
			break;
		case 2:
			if (noSuperaCantidadMaximaDeProductos) {
				cantProductos = darDeAltaUnProducto(s, PRODUCTOS, cantProductos, COD_MIN, COD_MAX, CANT_PRODUCTOS_MIN, CANT_PRODUCTOS_MAX);
			} else {
				System.out.println("No se pueden ingresar mas productos.");
			}
			break;
		case 3:
			
			if(superaCantidadMinimaDeProductos) {
				cantProductos = darDeBajaUnProducto(s, PRODUCTOS, cantProductos, COD_MIN, COD_MAX,CANT_ATRIBUTOS);
				
			}else {
				System.out.println("No hay productos para dar de baja.");
			}
			break;
		case 4:
			if(superaCantidadMinimaDeProductos) {
				
				modificarProducto(s, PRODUCTOS, cantProductos, COD_MIN, COD_MAX);
				
			}else {
				System.out.println("No hay productos para dar modificar.");
			}
			break;
		case 5:
			if(superaCantidadMinimaDeProductos) {
			
				listarProductos(PRODUCTOS, cantProductos);
				
			} else {
				System.out.println("No hay productos para listar.");
			}
			break;
		case 6:
			if (superaCantidadMinimaDeProductos) {
			    int indiceProductoMasCaro = buscarProductoMasCaro(PRODUCTOS, cantProductos);		
				mostrarProducto(PRODUCTOS, indiceProductoMasCaro, 1);	
			} else {
				System.out.println("No hay productos para buscar el más caro.");
			}
			break;
		case 7:
			if (superaCantidadMinimaDeProductos) {
				int indiceProductoMasBarato = buscarProductoMasBarato(PRODUCTOS, cantProductos);
				mostrarProducto(PRODUCTOS, indiceProductoMasBarato, 1);
			} else {
				System.out.println("No hay productos para buscar el más barato.");
			}
			break;
		case 11:
			System.out.println("Adiós.");
			break;
		default:
			System.out.println("Opcion incorrecta.");
			break;
		}
		
		return cantProductos;
		
	}
	
		private static void consultarProducto(Scanner s, final String[][] PRODUCTOS, final int CANT_PRODUCTOS, final int COD_MIN, final int COD_MAX) {
		
			final int IND_PRODUCTO = buscarProductoPorCodigo(s, PRODUCTOS, CANT_PRODUCTOS, COD_MIN, COD_MAX);
		
			if(IND_PRODUCTO == -1) {
				System.out.println("No se encontró el código en la lista de productos.");
			} else {
				mostrarProducto(PRODUCTOS, IND_PRODUCTO, 1);
            }
	     }
		
		
		private static void mostrarProducto(final String[][] PRODUCTOS, final int IND_PRODUCTO, final int NUMERACION) {
			System.out.println(
				NUMERACION + 
				". CODIGO: " + PRODUCTOS[IND_PRODUCTO][0] + 
				" NOMBRE: " + PRODUCTOS[IND_PRODUCTO][1] +
				" PRECIO: $" + PRODUCTOS[IND_PRODUCTO][2] +
				" CANTIDAD: " + PRODUCTOS[IND_PRODUCTO][3]);
		}
		
		
		
		private static int darDeAltaUnProducto(Scanner s, final String[][] PRODUCTOS, int cantProductos, final int COD_MIN, final int COD_MAX, final int CANT_PRODUCTOS_MIN, final int CANT_PRODUCTOS_MAX) {
			
			int codigoProducto = 0;
			int indiceCodigoBuscado = -1;
			
			do {
				System.out.println("Ingrese el codigo del producto:");
				codigoProducto = ingresarEntero(s, COD_MIN, COD_MAX);
				indiceCodigoBuscado = buscarNroEnMatriz(PRODUCTOS, 0, codigoProducto, cantProductos);
				
				if(indiceCodigoBuscado >= 0) {
					System.out.println("El código ingresado ya existe en el sistema");
					System.out.println("Vuelva a ingresar.");
				}
			} while(indiceCodigoBuscado >= 0);
			
			int indiceNombreBuscado = -1;
			String nombreProducto;
			
			do {
				System.out.println("Ingrese el nombre del producto:");
				nombreProducto = s.nextLine();
				indiceNombreBuscado = buscarCadenaEnMatriz(PRODUCTOS , 1 , nombreProducto, cantProductos);
				
				if(indiceNombreBuscado >= 0) {
					System.out.println("El nombre ingresado ya existe en el sistema");
					System.out.println("Vuelva a ingresar.");
				}
				
			} while(indiceNombreBuscado >= 0);
			
			System.out.println("Ingrese el precio del producto:");
			final int PRECIO_PRODUCTO = ingresarEntero(s, 0, Integer.MAX_VALUE);
			
			System.out.println("Ingrese la cantidad del producto:");
			final int CANTIDAD_PRODUCTO = ingresarEntero(s, 0, Integer.MAX_VALUE);
			
			PRODUCTOS[cantProductos][0] = String.valueOf(codigoProducto);
			PRODUCTOS[cantProductos][1] = nombreProducto;
			PRODUCTOS[cantProductos][2] = String.valueOf(PRECIO_PRODUCTO);
			PRODUCTOS[cantProductos][3] = String.valueOf(CANTIDAD_PRODUCTO);
		
		return ++cantProductos;
	}
		
		
		private static int buscarCadenaEnMatriz(final String[][] MATRIZ, final int IND_COL, final String CADENA_BUSCADA, final int LONGITUD) {
			int i = 0;		
			while(i<LONGITUD) {
				if(MATRIZ[i][IND_COL].toLowerCase().equals(CADENA_BUSCADA.toLowerCase())) {
					return i;
				}			
				i++;
			}		
			return -1;
		}
		
		
		private static int darDeBajaUnProducto(Scanner s, final String[][] PRODUCTOS, int cantProductos, final int COD_MIN, final int COD_MAX,final int CANT_ATRIBUTOS ) {
			
			int codigoProducto = 0;
			int indiceCodigoBuscado = -1;
			
				System.out.println("Ingrese el código del producto a eliminar:");
				codigoProducto = ingresarEntero(s, COD_MIN, COD_MAX);
				indiceCodigoBuscado = buscarNroEnMatriz(PRODUCTOS, 0, codigoProducto, cantProductos);
				
				if(indiceCodigoBuscado >= 0) {
					System.out.println("Se ha encontrado el producto");
					for(int i = indiceCodigoBuscado; i < cantProductos-1 ; i++ ) {
						PRODUCTOS[i] = PRODUCTOS[i+1];
					}
					cantProductos--;

					PRODUCTOS[cantProductos] = new String[CANT_ATRIBUTOS];
					System.out.println("El producto se ha dado de baja correctamente.");
				} else {
					System.out.println("No se ha encontrado un producto con ese codigo.");
				}
			
			
			return cantProductos;
			
		}
	

	private static void modificarProducto(Scanner s, String[][] PRODUCTOS, int cantProductos, int COD_MIN, int COD_MAX) {
	
		int codigoProducto = 0;
		int indiceBuscado = -1;
		
			System.out.println("Ingrese el código del producto a modificar:");
			codigoProducto = ingresarEntero(s, COD_MIN, COD_MAX);
			indiceBuscado = buscarNroEnMatriz(PRODUCTOS, 0, codigoProducto, cantProductos);
			
			if(indiceBuscado >= 0) {
				System.out.println("Se ha encontrado el producto");
				int opcion = 0;
				
				do {
				System.out.println("Que campo desea modificar ");
				System.out.println("1) CODIGO ");
				System.out.println("2) NOMBRE ");
				System.out.println("3) PRECIO ");
				System.out.println("4) CANTIDAD ");
				opcion = s.nextInt();
				
				}while(opcion < 0 && opcion >= 4);
				
				switch(opcion) {
				case 1:
					System.out.println("Ingrese el nuevo código:");
					int codigoNuevo = s.nextInt();
					PRODUCTOS[indiceBuscado][0] = String.valueOf(codigoNuevo);
					break;
				case 2:
					System.out.println("Ingrese el nuevo nombre:");
					s.nextLine();
					String nombreNuevo = s.nextLine();
					PRODUCTOS[indiceBuscado][1] = nombreNuevo;
					break;
				case 3:
					System.out.println("Ingrese el nuevo precio:");
					int precioNuevo = s.nextInt();
					PRODUCTOS[indiceBuscado][2] = String.valueOf(precioNuevo);
					break;
				case 4:
					System.out.println("Ingrese la nueva cantidad:");
					int cantidadNueva = s.nextInt();
					PRODUCTOS[indiceBuscado][3] = String.valueOf(cantidadNueva);
					break;
				}
				
			}else {
				System.out.println("No se ha encontrado un producto con ese código.");
			}	
	}
	
	private static void listarProductos(final String[][] PRODUCTOS, int cantProductos) {
		for (int i = 0; i < cantProductos; i++) {
			System.out.println((i+1) + " CODIGO: "+ PRODUCTOS[i][0] +" NOMBRE: " + PRODUCTOS[i][1] + " PRECIO $" + PRODUCTOS[i][2]+ " CANTIDAD: "+PRODUCTOS[i][3]);
		}
		
	}

	
	private static int buscarProductoMasCaro(final String[][] PRODUCTOS, int cantProductos) {
		int precioMasAlto = Integer.parseInt(PRODUCTOS[0][2]);
		int indiceDePrecioMasAlto = 0;
		
		for (int i = 1; i < cantProductos; i++) {
			int precioActual = Integer.parseInt(PRODUCTOS[i][2]);
			
			if (precioActual > precioMasAlto) {
				precioMasAlto = precioActual;
				indiceDePrecioMasAlto = i;
			}
		}
		
		return indiceDePrecioMasAlto;
	}
	
	private static int buscarProductoMasBarato(final String[][] PRODUCTOS, int cantProductos) {
		int precioMasBajo = Integer.parseInt(PRODUCTOS[0][2]);
		int indiceDePrecioMasBajo = 0;
		
		for (int i = 1; i < cantProductos; i++) {
			int precioActual = Integer.parseInt(PRODUCTOS[i][2]);
			
			if (precioActual < precioMasBajo) {
				precioMasBajo = precioActual;
				indiceDePrecioMasBajo = i;
			}
		}
		
		return indiceDePrecioMasBajo;
	}
	
	
	private static int buscarProductoPorCodigo(Scanner s, final String[][] PRODUCTOS, int cantProductos, final int COD_MIN, final int COD_MAX) {
		System.out.println("Ingrese el codigo del producto:");
		final int ID_PRODUCTO_BUSCADO = ingresarEntero(s, COD_MIN, COD_MAX);
		final int IND_PRODUCTO = buscarNroEnMatriz(PRODUCTOS, 0, ID_PRODUCTO_BUSCADO, cantProductos);
		return IND_PRODUCTO;
	}
	
	private static int buscarNroEnMatriz(final String[][] MATRIZ, final int IND_COL, final int NRO_BUSCADO, final int LONGITUD) {
		int i = 0;		
		while(i<LONGITUD) {
			if(Integer.parseInt(MATRIZ[i][IND_COL]) == NRO_BUSCADO) {
				return i;
			}			
			i++;
		}		
		return -1;
	}
	
	
	private static int ingresarEntero(Scanner s, final int MIN, final int MAX) {
		int nro = 0;	

		boolean error = false;
		do {
			error = false;
			
			try {
				nro = s.nextInt();
				if(nro<MIN || nro>MAX) {
					error = true;
					System.out.println("Error. El número ingresado debe estar entre " + MIN + " y " + MAX);
					System.out.println("Vuelva a ingresar.");
				}
			} catch(InputMismatchException e) {
				System.out.println("Error. Tipo de dato mal ingresado");
				System.out.println("Vuelva a ingresar.");
				error = true;
			} catch(Exception e) {
				System.out.println("Error inesperado.");
			} finally {
				s.nextLine();
			}
		} while(error);
		
		return nro;
	}

}
