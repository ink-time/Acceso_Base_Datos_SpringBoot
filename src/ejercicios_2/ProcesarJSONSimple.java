package ejercicios_2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper; // El parser principal
import java.io.File;
import java.io.BufferedWriter; // Para escribir el JSON
import java.io.FileWriter; // Para escribir el JSON
import java.io.IOException;

public class ProcesarJSONSimple {

    public static void main(String[] args) {

        File directorioEjemplo = new File("D:\\CODING v2\\First_project_Acceso a Datos\\src\\ejercicios_2\\unidad1_ejemplos");
        if (!directorioEjemplo.exists()) {
            directorioEjemplo.mkdirs();
        }

        File archivoJSON = new File(directorioEjemplo, "usuario_simple.json");

        // 1. Crear un fichero JSON de ejemplo si no existe
        if (!archivoJSON.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoJSON))) {
                writer.write("{\n");
                writer.write("\t\"nombre\": \"Elena\",\n");
                writer.write("\t\"email\": \"elena@ejemplo.com\",\n");
                writer.write("\t\"edad\": 28,\n");
                writer.write("\t\"activo\": false,\n");
                writer.write("\t\"hobbies\": [\"leer\", \"senderismo\"]\n");
                writer.write("}");
                System.out.println("Fichero JSON de ejemplo creado: " + archivoJSON.getName());
            } catch (IOException e) {
                System.err.println("Error al crear el fichero JSON de ejemplo: " + e.getMessage());
                return; // Salimos si no se puede crear
            }
        }

        // 2. Leer y parsear el fichero JSON usando Jackson
        try {
            ObjectMapper mapper = new ObjectMapper(); // El objeto principal de Jackson
            System.out.println("Parseando: " + archivoJSON.getAbsolutePath());
            // Leer el JSON y convertirlo a una estructura de nodos genérica (JsonNode)
            JsonNode rootNode = mapper.readTree(archivoJSON);

            // Acceder a los valores por clave
            String nombre = rootNode.get("nombre").asText();
            int edad = rootNode.get("edad").asInt();
            boolean activo = rootNode.get("activo").asBoolean();

            System.out.println("Datos del usuario:");
            System.out.println("Nombre: " + nombre);
            System.out.println("Edad: " + edad);
            System.out.println("Activo: " + activo);

            // Acceder a un array
            JsonNode hobbiesNode = rootNode.get("hobbies");
            if (hobbiesNode != null && hobbiesNode.isArray()) {
                System.out.print("Hobbies: ");
                for (JsonNode hobby : hobbiesNode) {
                    System.out.print(hobby.asText() + " ");
                }
                System.out.println();
            }

        } catch (IOException e) { // Puede ser JsonProcessingException también
            System.err.println("Error al leer o parsear el JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
