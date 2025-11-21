package ejercicios_2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.*;
import java.io.*;
import java.util.Scanner;


public class AccesoXML {
//    private static final String FORMAT_XSLT ="D:\\CODING v2\\First_project_Acceso a Datos\\src\\ejercicios_2\\unidad1_ejemplos\\xslt\\book-xml-format.xslt";

    // write doc to output stream
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


        public static void main(String[] args) {
            Scanner scan = new Scanner (System.in);
            File directorio = new File("D:\\CODING v2\\First_project_Acceso a Datos\\src\\ejercicios_2\\unidad1_ejemplos");
            File archivoXML = new File(directorio, "books.xml");

            // 2. Parsear el fichero XML con DOM
            try {
                System.out.println("Parseando: " + archivoXML.getAbsolutePath());
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(archivoXML); // Parseamos el fichero
// Obtenemos el elemento raíz
                XPathFactory factory1 = XPathFactory.newInstance();
                XPath xpath = factory1.newXPath();

                // It looks like I can do different queries here, like: "/book_catalog/book/author[text() = \"Brandon Sanderson\"]"
                // or "/book_catalog/book[series_book_number>=2]"
                // This would only return the books that have an author with that name
                // However, when using this, I should be careful and control the possible XPathExpressionException
                try{
                    XPathExpression expr = xpath.compile("/book_catalog/book/author[text() = \"Brandon Sanderson\"]/..");
                    NodeList resultList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
                    NodeList nodes =  (NodeList) resultList;
                    for (int i = 0; i < nodes.getLength(); i++) {
                        Element element = (Element) nodes.item(i);
                        String id = element.getAttribute("id");
                        String title = element.getElementsByTagName("title").item(0).getTextContent();
                        String author = element.getElementsByTagName("author").item(0).getTextContent();
                        String genre = element.getElementsByTagName("genre").item(0).getTextContent();
                        String series = element.getElementsByTagName("series").item(0).getTextContent();
                        String seriesBookNum = element.getElementsByTagName("series_book_number").item(0).getTextContent();
                        String publicationYear = element.getElementsByTagName("publication_year").item(0).getTextContent();
                        String setting = element.getElementsByTagName("setting").item(0).getTextContent();


                        System.out.println("Book id = " + id);
                        System.out.println("Title = " + title);
                        System.out.println("Author = " + author);
                        System.out.println("Genre = " + genre);
                        System.out.println("Series = " + series);
                        System.out.println("Series book number = " + seriesBookNum);
                        System.out.println("Publication year = " + publicationYear);
                        System.out.println("Setting = " + setting);
                        System.out.println();
                        System.out.println("Do you wanna change the title?");

                        String option = scan.nextLine().toLowerCase().trim();
                        if(option.equals("yes")){
                            System.out.println("Type the new title: ");
                            String newText = scan.nextLine();
                            // However, this is not enough, I have to parse the file to make this information actually get written down
                            element.getElementsByTagName("title").item(0).setTextContent(newText);
                            title = element.getElementsByTagName("title").item(0).getTextContent();
                            System.out.println("Title = " + title);
                            System.out.println();
                            try (FileOutputStream output =
                                         new FileOutputStream("D:\\CODING v2\\Acceso a Datos\\src\\ejercicios_2\\unidad1_ejemplos\\books.xml")) {
                                writeXml(document, output);
                            } catch (IOException | TransformerException e) {
                                e.printStackTrace();
                        }

                            scan.nextLine();
                        }


                    }
                }catch (XPathExpressionException e){
                    e.printStackTrace();
                }

            } catch (Exception e) { // Capturamos excepciones generales de parsing (ParserConfiguration, SAX, IO, etc.)
                System.err.println("Error al parsear el XML: " + e.getMessage());
                e.printStackTrace();
            }
        }


}
