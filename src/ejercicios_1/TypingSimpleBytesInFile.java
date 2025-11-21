package ejercicios_1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TypingSimpleBytesInFile
{

        public static void main(String[] args) {

            File directorioEjemplo = new File("unidad1_ejemplos_txt");
            if (!directorioEjemplo.exists()) {
                directorioEjemplo.mkdirs();
            }

            File ficheroSalida = new File(directorioEjemplo, "datos_simples.bin");
            System.out.println("Escribiendo en: " + ficheroSalida.getAbsolutePath());

            // try-with-resources asegura que el flujo 'fos' se cierre automáticamente
            // al finalizar el bloque try, incluso si ocurren excepciones.
            try (FileOutputStream fos = new FileOutputStream(ficheroSalida)) {

                // Escribimos unos pocos bytes
                fos.write(72); // 'H' en ASCII
                fos.write(101); // 'e' en ASCII
                fos.write(108); // 'l' en ASCII
                fos.write(108); // 'l' en ASCII
                fos.write(111); // 'o' en ASCII

                byte[] masDatos = {32, 87, 111, 114, 108, 100, 33}; // " World!" en ASCII
                fos.write(masDatos); // Escribe un array de bytes

                System.out.println("Bytes escritos correctamente en " + ficheroSalida.getName());


                Scanner scan = new Scanner (System.in);
                // I think this part is pretty self-explanatory, with the sysout here haha
                System.out.print("Character to change: ");
                char oldChar = scan.next().charAt(0);
                System.out.print("New Character:  ");
                char newChar = scan.next().charAt(0);

                // So this is the text:
                String text = ("""
                        Pink ponies and purple giraffes roamed the field. Cotton candy grew from\s
                        the ground as a chocolate river meandered off to the side. What looked like stones \s
                        in the pasture were actually rock candy. Everything in her dream seemed to be perfect \s
                        except for the fact that she had no mouth.
                        Indescribable oppression, which seemed to generate in some unfamiliar part of her \s
                        consciousness, filled her whole being with a vague anguish. It was like a shadow, \s
                        like a mist passing across her soul's summer day. It was strange and unfamiliar; \s
                        it was a mood. She did not sit there inwardly upbraiding her husband, lamenting at Fate, \s
                        which had directed her footsteps to the path which they had taken. She was just having \s
                        a good cry all to herself. The mosquitoes made merry over her, biting her firm, \s
                        round arms and nipping at her bare insteps.""");


                // We create a new file to do this, since the last file was .bin file, and I want a .txt
                File ficheroSalida2 = new File(directorioEjemplo, "datos_simples2.txt");
                // We create a new FileOutputStream to write the text into the file.
                try (FileOutputStream fos2 = new FileOutputStream(ficheroSalida2)) {
                    // I use the combination of an FileOutputStrem and an OutputStreamWriter because
                    // I wanna write the text directly, not having to do it with bytes myself.
                    // And from what I've gathered investigating around, this is better than using BufferedWriter
                    OutputStreamWriter writer = new OutputStreamWriter(fos2, StandardCharsets.UTF_8);
                    writer.write(text);
                    writer.close();
                }

                // We have a similar situation to read the text on the file
                try(FileInputStream fis = new FileInputStream(ficheroSalida2)){
                    InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
                    // We create a String that contains the original text, so we can search the specified character
                    String oldText = reader.readAllAsString();
                    // After that, we replace all the occurrences of the old character by the new one
                    String newText = oldText.replace(oldChar, newChar);
                    // And then we write this changed text to the file
                    try (FileOutputStream fos2 = new FileOutputStream(ficheroSalida2)) {
                        OutputStreamWriter writer = new OutputStreamWriter(fos2, StandardCharsets.UTF_8);
                        writer.write(newText);
                        writer.close();
                    }
                    reader.close();
                }

            } catch (IOException e) {
                System.err.println("Error al escribir bytes: " + e.getMessage());
            }
        }
    }

