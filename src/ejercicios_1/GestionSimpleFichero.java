package ejercicios_1;

import java.io.File;
import java.io.IOException;

public class GestionSimpleFichero {
    // Little method to check for folders already created, and to create a new folder if needed
    static File folderNameChanger (File directorio) {
        int folderNum = 1;
        String baseName = "";
        File[] listDir = directorio.listFiles();
        // We go through a loop when the directory exists, to also check if the next directory exists as well, and so on.
        // Until we get a directory name that hasn't been created yet.
        // We need to do this here so the files will be created in the newest directory.
        while (directorio.exists() && listDir != null && listDir.length == 5) {
            // However, we only want to create a new directory if the old one already has 5 files, for this, we create a list of its files:
            listDir = directorio.listFiles();
            // Thus, when the list has 5 files the creation of a new directory begins. (The check for the null value is because I have had some problems with .length with null lists)
            // This next if is necessary because if it isn't here, when the directory exists but is not 'full', it creates the next directory
            if (listDir != null && listDir.length == 5) {
                // We make the folder number go up for each time we find the directory, so the new name to find will have the next number
                folderNum++;
                System.out.println("Checking for the while");
                // We get the 'basic' name by replacing the number that is preceded by a dash (to not replace the other number as well)
                baseName = directorio.getName().replaceAll("-[0-9]", "");
                // Then we create the file with the new name, using our base name and the counter/folder number
                directorio = new File(baseName + "-" + folderNum);
                // This new name will be then checked in the loop condition



            }

        }
        // When the program gets here, its either because there is a new folder to be created,
        // or because the last folder checked has less than 5 files.
        // So we check if it's the first case, and in if it is, we actually create the directory:
        if(!directorio.exists()){
            directorio.mkdirs();
        }
        return directorio;
    }


        public static void main (String[] args) {
            int numberFile = 0;

            // Usamos una subcarpeta para mantener organizados los ejemplos
            File directorioEjemplo = new File("unidad1_ejemplos-1");
            if (!directorioEjemplo.exists()) {
                directorioEjemplo.mkdirs(); // Crear directorio si no existe
            }else{
                // This method handles checking if the directory is 'full' or already has 5 files,
                // If it doesn't, nothing happens, and the next file is created (below)
                // If it has, it checks if the next directory (+1 in the number) exists,
                // and if it exists, it checks the above-mentioned condition,
                // it will go on until it creates a new folder, or the current folder is not full yet.
                directorioEjemplo = folderNameChanger(directorioEjemplo);
            }

            // Referencia al fichero dentro de la subcarpeta
            // We create files in the newest directory, thanks to the method above
            File fichero = new File(directorioEjemplo, "n1_fichero_prueba.txt");

            System.out.println("Operando con: " + fichero.getAbsolutePath());

            try {

                // Crear fichero si no existe
                if (fichero.createNewFile()) {
                    System.out.println("Fichero creado: " + fichero.getName());
                } else {

                    System.out.println("El fichero ya existe: " + fichero.getName());
                    //fichero = new File(directorioEjemplo, "n1_fichero_prueba" + numberFile + ".txt");
                    // We use a while, and the fact that this method returns false if the file already exists to change the name of the file to be created until a file with that name doesn't exist
                    while(!fichero.createNewFile()) {
                        numberFile+=1;
                        // To get the new file name we replace the last number with the dash (if there was one)
                        // And then replace the extension with nothing, we add the corresponding number, and we add the extension again.
                        // (This only works with .txt files of course, I would consider using a switch for every type of file maybe? Because I cannot think of a better way to do it right now)
                        fichero = (new File(directorioEjemplo, fichero.getName().replaceAll("-[0-9]", "").replace(".txt", "") + "-" + Integer.toString(numberFile) + ".txt"));
                        // After the file has a new name, this name will be checked in the while, and if it already exists, the loop happens again.
                        //System.out.println("Second loop"); // Checking for entry on this loop.


                    }
                    /*System.out.println("Name of the file: ");
                    System.out.println(fichero.getName().replace(".txt", "") + "-" + Integer.toString(numberFile) + ".txt");*/
                    // I can do this instead of the manual counting that I had before.


                }



                // Mostrar propiedades
                if (fichero.exists()) {
                    System.out.println("Propiedades de '" + fichero.getName() + "':");
                    System.out.println(" - Es un fichero: " + fichero.isFile());
                    System.out.println(" - Es un directorio: " + fichero.isDirectory());
                    System.out.println(" - Se puede leer: " + fichero.canRead());
                    System.out.println(" - Se puede escribir: " + fichero.canWrite());
                    System.out.println(" - Tamano (bytes): " + fichero.length());
                }

            } catch (IOException e) {
                System.err.println("Error de E/S al operar con el fichero: " + e.getMessage());
                System.err.println("Verifica los permisos en: " + directorioEjemplo.getAbsolutePath());
            }

            // Opcional: limpieza (descomentar si quieres eliminar el fichero y directorio)
        /*
        if (fichero.exists()) {
            if (fichero.delete()) {
                System.out.println("Fichero de prueba eliminado: " + fichero.getName());
            } else {
                System.err.println("Error al eliminar el fichero de prueba.");
            }
        }
        if (directorioEjemplo.delete()) { // Solo si está vacío
            System.out.println("Directorio de prueba eliminado: " + directorioEjemplo.getName());
        }
        */
        }
    }
