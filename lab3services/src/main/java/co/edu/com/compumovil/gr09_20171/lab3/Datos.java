package co.edu.com.compumovil.gr09_20171.lab3;

/**
 * Created by leonardo on 17/04/17.
 */

public class Datos {

    // Aquí seteas los datos que vas a ingresar.

    private int idPhoto;
    private String nombre, edad;

    public Datos(int idPhoto, String nombre, String edad) {
        this.idPhoto = idPhoto;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

}
