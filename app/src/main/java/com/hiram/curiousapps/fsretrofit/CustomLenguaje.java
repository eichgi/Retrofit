package com.hiram.curiousapps.fsretrofit;

/**
 * Created by Hiram on 16/04/2017.
 */

public class CustomLenguaje {

    String nombre;
    String framework;

    public CustomLenguaje() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getFramework() {
        return framework;
    }

    public String getInfo() {
        return "Nombre: " + nombre + " - Framework: " + framework;
    }
}
