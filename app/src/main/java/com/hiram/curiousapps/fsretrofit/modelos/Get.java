package com.hiram.curiousapps.fsretrofit.modelos;

/**
 * Created by Hiram on 18/04/2017.
 */

public class Get {

    String nombre;
    int edad;
    String email;

    public Get(String nombre, int edad, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getEmail() {
        return email;
    }
}
