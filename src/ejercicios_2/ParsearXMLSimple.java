package ejercicios_2;

import org.w3c.dom.Document;
import org.w3c.dom.Element; // Para el elemento raíz
import org.w3c.dom.NodeList; // Para acceder a otros elementos
import org.w3c.dom.Node; // Para nodos individuales
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter; // Para escribir el XML
import java.io.File; // Para escribir el XML
import java.io.FileWriter; // Para escribir el XML
import java.io.IOException; // Para escribir el XML

public class ParsearXMLSimple {

    public static void main(String[] args) {
        File directorioEjemplo = new File("D:\\CODING v2\\Acceso a Datos\\src\\ejercicios_2\\unidad1_ejemplos");
        if (!directorioEjemplo.exists()) {
            directorioEjemplo.mkdirs();
        }
        File archivoXML = new File(directorioEjemplo, "config_simple.xml");
        // 1. Crear un fichero XML de ejemplo si no existe
        if (!archivoXML.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoXML))) {
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                writer.write("<config>\n");
                writer.write("  <parametro id=\"nombreApp\">Mi Aplicación Genial</parametro>\n");
                writer.write("  <parametro id=\"version\">1.0.2</parametro>\n");
                writer.write("  <debug activado=\"true\"/>\n");
                writer.write("</config>\n");
                System.out.println("Fichero XML de ejemplo creado: " + archivoXML.getName());
            } catch (IOException e) {
                System.err.println("Error al crear el fichero XML de ejemplo: " + e.getMessage());
                return; // Salimos si no se puede crear el XML
            }
        }
        // 2. Parsear el fichero XML con DOM
        try {
            System.out.println("Parseando: " + archivoXML.getAbsolutePath());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(archivoXML); // Parseamos el fichero
// Obtenemos el elemento raíz
            Element elementoRaiz = document.getDocumentElement();
            System.out.println("Elemento raíz del XML: " + elementoRaiz.getNodeName());

// Recorrer nodos hijos (ejemplo: los <parametro> y <debug>)
            NodeList nodosParametro = elementoRaiz.getElementsByTagName("parametro");
            for (int i = 0; i < nodosParametro.getLength(); i++) {
                Node nodo = nodosParametro.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;
                    String id = elemento.getAttribute("id");
                    String valor = elemento.getTextContent();
                    System.out.println("  Parámetro encontrado -> ID: " + id + ", Valor: " + valor);
                }
            }
            NodeList nodosDebug = elementoRaiz.getElementsByTagName("debug");
            if (nodosDebug.getLength() > 0 && nodosDebug.item(0).getNodeType() == Node.ELEMENT_NODE) {
                Element elemDebug = (Element) nodosDebug.item(0);
                System.out.println("  Debug activado: " + elemDebug.getAttribute("activado"));
            }
        } catch (Exception e) { // Capturamos excepciones generales de parsing (ParserConfiguration, SAX, IO, etc.)
            System.err.println("Error al parsear el XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
