package multifont_connection_JSON_XML.vo;

import java.util.ArrayList;

public class UserVO {
    private String nombre;
    private String apellido;
    private String dni;
    private ArrayList <Long>  telefonos;
    private ArrayList <String> correos;

    public UserVO(ArrayList<String> correos, ArrayList<Long> telefonos, String dni, String apellido, String nombre) {
        this.correos = correos;
        this.telefonos = telefonos;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
    }
    public UserVO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public ArrayList<Long> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<Long> telefonos) {
        this.telefonos = telefonos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public ArrayList<String> getCorreos() {
        return correos;
    }

    public void setCorreos(ArrayList<String> correos) {
        this.correos = correos;
    }

    @Override
    public String toString() {
        return "User{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", telefonos=" + telefonos +
                ", correos=" + correos +
                '}';

        /*
        StringBuilder sb = new StringBuilder();
		sb.append("***** Employee Details *****\n");
		sb.append("ID="+getId()+"\n");
		sb.append("Name="+getName()+"\n");
		sb.append("Permanent="+isPermanent()+"\n");
		sb.append("Role="+getRole()+"\n");
		sb.append("Phone Numbers="+Arrays.toString(getPhoneNumbers())+"\n");
		sb.append("Address="+getAddress()+"\n");
		sb.append("Cities="+Arrays.toString(getCities().toArray())+"\n");
		sb.append("Properties="+getProperties()+"\n");
		sb.append("*****************************");

		return sb.toString();*/
    }
}
