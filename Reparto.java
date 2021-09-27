package reparto;

import java.io.IOException;
import java.util.Set;

/**
 * Clase que calcula el reparto equitativo de activos de una
 * empresa entre 2 socios
 * 
 *
 */
public class Reparto 
{
	/**
	 * Método principal
	 * 
	 * @param args Vector de cadenas de caracteres que indican las opciones
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException 
	{
		System.out.println("Programa Reparto\n");
		
		// Obtenemos los datos de la entrada de comandos
		int [] entrada = EntradaSalida.DatosInicio(args);
		
		if (entrada != null)
		{
			// Fijamos el modo de trazado del algoritmo
			Algoritmos.setTraza(EntradaSalida.getTraza());
			
			// Aplicamos el algoritmo con los datos
			Set <String> resultado = Algoritmos.ResolverSeparacionSocios(entrada);
			
			// Escribimos la salida
			EntradaSalida.Salida(resultado);
		}	
	}
}