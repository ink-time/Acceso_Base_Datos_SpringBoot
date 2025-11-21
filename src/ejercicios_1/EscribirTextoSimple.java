package ejercicios_1;

import java.io.*;

public class EscribirTextoSimple {
        public static void main(String[] args) {

            File directorioEjemplo = new File("unidad1_ejemplos");
            if (!directorioEjemplo.exists()) {
                directorioEjemplo.mkdirs();
            }

            File ficheroSalida = new File(directorioEjemplo, "saludo_simple.txt");
            System.out.println("Escribiendo en: " + ficheroSalida.getAbsolutePath());

            // Usamos try-with-resources para BufferedWriter, que a su vez gestionará FileWri
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida))) {

                bw.write("¡Hola, mundo desde Acceso a Datos!"); // Escribe una cadena
                bw.newLine(); // Escribe un separador de línea dependiente del sistema
                bw.write("Esta es la segunda línea del fichero de texto.");
                bw.newLine();
                bw.write("Utilizando BufferedWriter para una escritura eficiente.");


                System.out.println("Texto escrito correctamente en " + ficheroSalida.getName());

            } catch (IOException e) {
                System.err.println("Error al escribir texto: " + e.getMessage());
            }

            // So if I want to read the contnt of the file, we have to use a BufferedReader

            try (BufferedReader bR = new BufferedReader(new FileReader(ficheroSalida))){
                String text = bR.readAllAsString();
                System.out.println();
                System.out.println("So here we have the content of the file: ");
                System.out.println(text);

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    // Parsing se encarga de reestructurar un texto para que tenga un formato diferente

// Dependiendo de la empresa o usuario que introduzcamos,
// una de ellas tendrá una versión de XML 12 y la otra 13.
// La app y el archivo también tienen que cambiar de nombre.
// Qué pasaría, cómo se leería el archivo XML si no se parsease.