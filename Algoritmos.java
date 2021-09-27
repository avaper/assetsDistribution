package reparto;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase que realiza el algoritmo Vuelta atrás
 * 
 *
 */
public class Algoritmos 
{
	public static Set <String> soluciones = new HashSet <String>();
	
	// Para activar la traza del algoritmo
	private static boolean traza;

	/**
	 * Método que aplica el algoritmo Vuelta Atrás. Recorreremos el grafo que representa el problema
	 * 
	 * @param activos El vector de activos que se va a procesar
	 * @param suma Array para las sumas de los dos socios
	 * @param sumaTotal La suma total acumulada
	 * @param k Nivel de exploración actual del grafo
	 * @param solucion Vector de solucion. Contiene el valor del socio al que pertenecen cada objeto
	 */
	public static void Vuelta_Atras(int [] activos, int [] suma, int sumaTotal, int k, int [] solucion) 
	{ 
		if (traza) System.out.println("\tNivel actual: " + k + "\n");
		
		// Comprobamos si estamos en el último nivel
		if (k == activos.length) 
		{										
			// Comprobamos si es solución
			if ((suma[0] == suma[1]) && (suma[0] == sumaTotal/2)) 
			{	
				if (traza) System.out.println("\t\tSolución encontrada: " + imprimirArray(solucion)+ "\n");
				
				Procesar(activos, solucion);
			}
		} 
		else 
		{
			// Expandimos nuevos niveles
			for (int socio = 0; socio < 2; socio++) 
			{								
				// Comprobamos si es completable
				if ((suma[socio] + activos[k]) <= (sumaTotal / 2)) 
				{			
					// Asignamos nivel
					solucion[k] = socio + 1;
					
					// Actualizo su suma
					suma[socio] = suma[socio] + activos[k];
					
					if (traza) System.out.println("\t\tAsignando activo " + activos[k] + " al socio " + (socio + 1) + "\n");
					
					// Vuelta atrás
					Vuelta_Atras(activos, suma, sumaTotal, (k + 1), solucion);
					
					if (traza) System.out.println("\t\t\tDeshaciendo decisión asignar activo " + activos[k] + " al socio " + (socio + 1) + "\n");
					
					// Deshacemos la ultima decisión
					suma[socio] = suma[socio] - activos[k];
				}
			}
		}
	}

	/**
	 * Método que efectúa la preparación del algoritmo de Vuelta Atrás
	 * 
	 * @param x El vector de activos que se va a procesar
	 * @return
	 */
	public static Set<String> ResolverSeparacionSocios (int [] x) 
	{
		int [] v = new int[x.length];
		int [] suma = new int[2];
		int sumaTotal = 0;
		
		for (int i = 0; i < x.length; i++) 
		{
			sumaTotal = sumaTotal + x[i];
		}
		
		if (sumaTotal % 2 == 0) 
		{
			Vuelta_Atras(x, suma, sumaTotal, 0, v);	
		}
		
		return soluciones;
	}
	
	/**
	 * Método que procesa un vector solución
	 * 
	 * @param activos El vector de activos que se va a procesar
	 * @param solucion Vector que contiene 1s (si el elemento pertenece al socio 1) o 2s (socio 2)
	 */	
	public static void Procesar(int [] activos, int [] solucion) 
	{	
		String [] cadena = new String [2];
		
		for (int i = 0; i < cadena.length; i++) 
		{
			cadena[i]="";
		}
		
		// Recorremos la solución
		for (int i = 0; i < solucion.length; i++) 
		{
			if (solucion[i]==1) 
			{
				// Asignamos el elemento a la cadena del socio i
				cadena[0] = cadena[0] + activos[i] + " ";
			} 
			else 
			{
				// Asignamos el elemento a la cadena del socio i
				cadena[1] = cadena[1] + activos[i] + " ";
			}
		}
		
		int numeroSoluciones = soluciones.size();
		
		// Se almacena la solución en un HashSet (no añaden elementos repetidos)
		soluciones.add(cadena[0] + "\n" + cadena[1]);	
		
		if (traza) 
		{
			if (numeroSoluciones == soluciones.size()) 
			{
				System.out.println("\t\t\tSolución repetida\n");
			}
		}
	}

	/**
	 * Setter del atributo traza
	 * 
	 * @param Traza Traza del algoritmo
	 */
	public static void setTraza(boolean Traza) 
	{
		traza = Traza;
	}

	/**
	 * Método que crea una cadena de caracteres para una solución
	 * 
	 * @param v Vector que estamos procesando
	 * @return Versión horizontal del vector que estamos procesando
	 */
	public static String imprimirArray(int[] v) 
	{
		String array = "{";
		
		for (int i = 0; i < v.length; i++) 
		{		
			if (i<v.length-1) array = array + v[i] + ", ";			
			else array = array + v[i] + "}";	
		}
		return array;
	}
}
