package ejercicios_2;

import java.io.BufferedReader; // Para leer el fichero
import java.io.File;
import java.io.FileNotFoundException; // Para leer el fichero si existe
import java.io.FileReader;
import java.io.IOException;

public class ManejoErrorSimple {

    public static void main(String[] args) {

        File directorioEjemplo = new File("unidadi_ejemplos");
        // NO creamos el directorio para este ejemplo, para forzar el error si no existe

        File ficheroAProcesar = new File(directorioEjemplo, "fichero_que_no_existe.txt");
        System.out.println("Intentando leer: " + ficheroAProcesar.getAbsolutePath());

        // Usamos try-with-resources para el BufferedReader y FileReader
        // Si FileReader falla al abrir (porque no existe), se lanzará FileNotFoundException
        // y el recurso no llegará a asignarse, por lo que no habrá nada que cerrar.
        try (BufferedReader br = new BufferedReader(new FileReader(ficheroAProcesar))) {

            System.out.println("Fichero encontrado. Leyendo contenido...");
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            // El BufferedReader y FileReader se cerrarán automáticamente aquí

        } catch (FileNotFoundException e) {
            // Este es un catch específico para cuando el fichero no se encuentra.
            // Es una subclase de IOException.
            System.err.println("¡ERROR ESPECÍFICO! El fichero no pudo ser encontrado.");
            System.err.println("Ruta intentada: " + e.getMessage()); // e.getMessage()
            System.err.println("Por favor, asegúrate de que el fichero exista en esa ubicación.");

        } catch (IOException e) {
            // Este catch es más genérico, para otros problemas de E/S
            // (ej. si el fichero existe pero no tenemos permisos de lectura).
            System.err.println("¡ERROR GENÉRICO DE E/S! Ocurrió un problema al leer el fichero.");
            System.err.println("Mensaje: " + e.getMessage());
            e.printStackTrace(); // Muestra la traza completa del error, útil para depurar
        } finally {
            // Bloque 'finally': Este bloque se ejecuta siempre, haya o no error.
            System.out.println("Es útil para limpieza final, aunque try-with-resources ya la hace.");
        }

        System.out.println("Final del programa.");
    }
}
