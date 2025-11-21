public class TestingStuff {
    public static void main(String[] args) {
        String name = "unidad1_ejemplos-1";
        System.out.print(name.replaceAll("-[0-9]", ""));

/*
File[] listaDir = directorioEjemplo.listFiles();
                if (listaDir != null) {
                    System.out.println(listaDir.length);

                    if (listaDir.length == 5){
                        System.out.println("Checking for the 5 if");
                        int folderNum = 1;
                        String oldName = "";
                        do{
                            folderNum++;
                            System.out.println("Checking for the while");
                            oldName = directorioEjemplo.getName().replaceAll("-[0-9]", "");
                            directorioEjemplo = new File (oldName + "-" + folderNum);
                            String name = "";
                            // I have to be able to search and see which names of folders have already been created,  to know
                            //File newDirectorio = new File(oldName + "-" + folderNum);
                            //directorioEjemplo.renameTo(newDirectorio);
                            //directorioEjemplo.mkdirs();

                        }while(!directorioEjemplo.mkdirs());

                            //directorioEjemplo = new File("unidad1_ejemplos");
                            //if (!directorioEjemplo.exists()) {
                                directorioEjemplo.mkdirs(); // Crear directorio si no existe
                            //}
    }
} */
    }
}
