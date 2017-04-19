package com.hiram.curiousapps.fsretrofit.modelos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hiram on 18/04/2017.
 */

public class Post {

    @SerializedName("nombre")
    String nombre;
    @SerializedName("edad")
    int edad;
    @SerializedName("profesion")
    String profesion;

    public Post(String nombre, int edad, String profesion) {
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
