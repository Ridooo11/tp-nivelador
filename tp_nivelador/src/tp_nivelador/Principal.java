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
			opc = mostrarMenuElegirOpc(scan);
			cantProductos = generarAccion(scan, PRODUCTOS, opc, cantProductos, COD_MIN, COD_MAX, CANT_PRODUCTOS_MIN, CANT_PRODUCTOS_MAX);
			
		} while(opc!=11);
		
		scan.close();
		
		

	}
	
	private static int mostrarMenuElegirOpc(Scanner s) {
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
	
	private static int generarAccion(Scanner s, final String[][] PRODUCTOS , final int OPC, int cantProductos, final int COD_MIN, final int COD_MAX, final int CANT_PRODUCTOS_MIN, final int CANT_PRODUCTOS_MAX) {
		switch(OPC) {
		case 1:
				if(cantProductos > CANT_PRODUCTOS_MIN) {		
					consultarAccion(s, PRODUCTOS, cantProductos, COD_MIN, COD_MAX);
				} else {
					System.out.println("No hay productos ingresados para consultar");
				}
			break;
		case 2:
			if (cantProductos < CANT_PRODUCTOS_MAX) {
				cantProductos = ingresarProducto(s, PRODUCTOS, cantProductos, COD_MIN, COD_MAX, CANT_PRODUCTOS_MIN, CANT_PRODUCTOS_MAX);
			} else {
				System.out.println("No se pueden ingresar mas productos");
			}
			
			
			break;

		case 11:
			System.out.println("Adios");
			break;
		default:
			System.out.println("Opción incorrecta");
			break;
		}
		
		return cantProductos;
		
	}
	
	
	
	private static int ingresarProducto(Scanner s, final String[][] PRODUCTOS, int cantProductos, final int COD_MIN, final int COD_MAX, final int CANT_PRODUCTOS_MIN, final int CANT_PRODUCTOS_MAX) {
				
			int codigoEmpresa = 0;
			int indiceCodigoBuscado = -1;
			
			do {
				System.out.println("Ingrese el código del producto");
				codigoEmpresa = ingresarEntero(s, COD_MIN, COD_MAX);
				indiceCodigoBuscado = buscarNroEnMatriz(PRODUCTOS, 0, codigoEmpresa, cantProductos);
				
				if(indiceCodigoBuscado >= 0) {
					System.out.println("El código ingresado ya existe en el sistema");
					System.out.println("Vuelva a ingresar");
				}
			} while(indiceCodigoBuscado >= 0);
			
			int indiceNombreBuscado = -1;
			String nombreProducto;
			
			do {
				System.out.println("Ingrese el nombre del producto");
				nombreProducto = s.nextLine();
				indiceNombreBuscado = buscarCadenaEnMatriz(PRODUCTOS ,1 , nombreProducto, cantProductos);
				
				if(indiceNombreBuscado >= 0) {
					System.out.println("El nombre ingresado ya existe en el sistema");
					System.out.println("Vuelva a ingresar");
				}
				
			} while(indiceNombreBuscado >= 0);
			
			System.out.println("Ingrese el precio del producto");
			final int PRECIO_PRODUCTO = ingresarEntero(s, 0, Integer.MAX_VALUE);
			
			System.out.println("Ingrese la cantidad del producto");
			final int CANTIDAD_PRODUCTO = ingresarEntero(s, 0, Integer.MAX_VALUE);
			
			PRODUCTOS[cantProductos][0] = String.valueOf(codigoEmpresa);
			PRODUCTOS[cantProductos][1] = nombreProducto;
			PRODUCTOS[cantProductos][2] = String.valueOf(PRECIO_PRODUCTO);
			PRODUCTOS[cantProductos][3] = String.valueOf(CANTIDAD_PRODUCTO);
		
		return ++cantProductos;
	}
	
	private static void consultarAccion(Scanner s, final String[][] PRODUCTOS, final int CANT_PRODUCTOS, final int COD_MIN, final int COD_MAX) {
		
		final int IND_PRODUCTO = buscarProductoPorCodigo(s, PRODUCTOS, CANT_PRODUCTOS, COD_MIN, COD_MAX);
		
		if(IND_PRODUCTO == -1) {
			System.out.println("No se encontró el codigo en la lista de productos");
		} else {
			mostrarAccion(PRODUCTOS, IND_PRODUCTO, 1);
		}
	}

	private static void mostrarAccion(final String[][] PRODUCTOS, final int IND_PRODUCTO, final int NUMERACION) {
		System.out.println(NUMERACION + ". Codigo: " + PRODUCTOS[IND_PRODUCTO][0] + 
				" Nombre: " + PRODUCTOS[IND_PRODUCTO][1] +
				" Precio: " + PRODUCTOS[IND_PRODUCTO][2] +
				" Cantidad: " + PRODUCTOS[IND_PRODUCTO][3]);
	}
	
	private static int buscarProductoPorCodigo(Scanner s, final String[][] PRODUCTOS, int cantProductos, final int COD_MIN, final int COD_MAX) {
		System.out.println("Ingrese el codigo del producto");
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
					System.out.println("Vuelva a ingresar");
				}
			} catch(InputMismatchException e) {
				System.out.println("Error. Tipo de dato mal ingresado");
				System.out.println("Vuelva a ingresar");
				error = true;
			} catch(Exception e) {
				System.out.println("Error inesperado");
			} finally {
				s.nextLine();
			}
		} while(error);
		
		return nro;
	}

}
