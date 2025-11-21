package multifont_connection_JSON_XML;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import multifont_connection_JSON_XML.connection.MyConnection;
import multifont_connection_JSON_XML.dao.UserDAO;
import multifont_connection_JSON_XML.vo.UserVO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    public static void createDatabase(){

    }

    // We will not be using this tbh.
    private static void writeXml(Document doc,
                                 OutputStream output)
            throws TransformerException, UnsupportedEncodingException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        // The default add many empty new line, not sure why?
        // https://mkyong.com/java/pretty-print-xml-with-java-dom-and-xslt/
        // Transformer transformer = transformerFactory.newTransformer();

        // add a xslt to remove the extra newlines
        Transformer transformer = transformerFactory.newTransformer();
        //new StreamSource(new File(FORMAT_XSLT))

        // pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }

public static void scanXMLfile(Connection conn) {
    File directorio = new File("D:\\CODING v2\\Acceso a Datos\\src\\multifont_connection_JSON_XML");
    if (!directorio.exists()) {
        directorio.mkdirs();
    }
    // We create the two user classes
    UserVO user = new UserVO();
    UserDAO userMethods = new UserDAO();

    File archivoXML = new File(directorio, "usuarios_Mercadona.xml");

    if (!archivoXML.exists()) {
        System.out.println("File not found");
    }

    try {
        System.out.println("Parseando: " + archivoXML.getAbsolutePath());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(archivoXML); // Parseamos el fichero


        XPathFactory factory1 = XPathFactory.newInstance();
        XPath xpath = factory1.newXPath();

        // It looks like I can do different queries here, like: "/book_catalog/book/author[text() = \"Brandon Sanderson\"]"
        // or "/book_catalog/book[series_book_number>=2]"
        // This would only return the books that have an author with that name
        // However, when using this, I should be careful and control the possible XPathExpressionException

        try{
            XPathExpression expr = xpath.compile("//Usuario");
            NodeList resultList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
            NodeList nodes =  (NodeList) resultList;
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                String nameXML = element.getElementsByTagName("Nombre").item(0).getTextContent();
                String surNameXML = element.getElementsByTagName("Apellido").item(0).getTextContent();
                String DniXML = element.getElementsByTagName("DNI").item(0).getTextContent();
                String mailsXML = element.getElementsByTagName("Correos").item(0).getTextContent();
                String phonesXML = element.getElementsByTagName("Telefonos").item(0).getTextContent();


                // We transform the data in the elements that have more than one child into an arrayList.
                // First transforming it into an array, but observing that that array had an extra empty position (index 0),
                // So I decided to iterate through the array, and copy all positions to an arrayList, for more flexibility
                // in the future. And in that process, I ignored the first position of the array.
                String [] mailList = mailsXML.replaceAll(" ", "").split("\n");
                ArrayList <String> mailListMercadona = new ArrayList<>();
                for (int j = 1; j < mailList.length; j++) {
                    mailListMercadona.add(mailList[j]);
                }

                // I do the same for the phone numbers, but casting the numbers from String to Integer
                String [] phoneList = phonesXML.replaceAll(" ", "").split("\n");
                ArrayList <Long> phoneListMercadona = new ArrayList<>();
                for (int j = 1; j < phoneList.length; j++) {
                    phoneListMercadona.add(Long.valueOf(phoneList[j]));
                }

                user.setNombre(nameXML);
                user.setApellido(surNameXML);
                user.setDni(DniXML);
                user.setCorreos(mailListMercadona);
                user.setTelefonos(phoneListMercadona);

                userMethods.insertUser(conn, user, "Mercadona");




            }
        }catch (XPathExpressionException e){
            e.printStackTrace();
        }

    } catch (Exception e) { // Capturamos excepciones generales de parsing (ParserConfiguration, SAX, IO, etc.)
        System.err.println("Error al parsear el XML: " + e.getMessage());
        e.printStackTrace();
    }
}



public static void scanJSONfile(Connection conn) {
            //JSON:
    File directorio = new File("D:\\CODING v2\\Acceso a Datos\\src\\multifont_connection_JSON_XML");
    if (!directorio.exists()) {
        directorio.mkdirs();
    }
            UserVO user = new UserVO();
            UserDAO userMethods = new UserDAO();
            File archivoJSON = new File(directorio, "usuarios_Pascual.json");


            try {

                ObjectMapper mapper = new ObjectMapper(); // El objeto principal de Jackson
                System.out.println("Parseando: " + archivoJSON.getAbsolutePath());
                // Leer el JSON y convertirlo a una estructura de nodos genérica (JsonNode)
                JsonNode rootNode = mapper.readTree(archivoJSON);


                // Acceder a los valores por clave
                if (rootNode.isArray()) {
                    for(JsonNode userNode : rootNode){
                        String nameJSON = userNode.get("nombre").asText();
                        String surNameJSON = userNode.get("apellido").asText();
                        String dniJSON = userNode.get("dni").asText();


                        // We access the arrays inside the json array
                        JsonNode phonesNode = userNode.get("telefonos");
                        ArrayList <Long> phoneList = new ArrayList<>();
                        if (phonesNode != null && phonesNode.isArray()) {
                            for (JsonNode phone : phonesNode) {
                                phoneList.add(phone.asLong());
//                        System.out.print(phone.asText() + ", ");
                            }
                            System.out.println(phoneList);
                            System.out.println();

                        }
                        JsonNode emailsNode = userNode.get("correos");
                        ArrayList <String> mailList = new ArrayList<>();
                        if (emailsNode != null && emailsNode.isArray()) {
                            for (JsonNode email : emailsNode) {
                                mailList.add(email.asText());
//                        System.out.print("Emails:" + email.asText() + ", ");
                            }
                            System.out.println(mailList);
                            System.out.println();
                        }
                        user.setNombre(nameJSON);
                        user.setApellido(surNameJSON);
                        user.setDni(dniJSON);
                        user.setCorreos(mailList);
                        user.setTelefonos(phoneList);
//                        System.out.println(user);
                        userMethods.insertUser(conn, user, "Pascual");

                    }
                }


            } catch (IOException e) { // Puede ser JsonProcessingException también
                System.err.println("An error has occurred while parsing the JSON file: " + e.getMessage());
                e.printStackTrace();
            }

    }
}
