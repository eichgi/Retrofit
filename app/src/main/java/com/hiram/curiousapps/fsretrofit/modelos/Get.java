package com.hiram.curiousapps.fsretrofit.modelos;

/**
 * Created by Hiram on 18/04/2017.
 */

public class Get {

    String nombre;
    int edad;
    String profesion;

    public Get(String nombre, int edad, String profesion) {
        this.nombre = nombre;
        this.edad = edad;
        this.profesion = profesion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getProfesion() {
        return profesion;
    }
}
