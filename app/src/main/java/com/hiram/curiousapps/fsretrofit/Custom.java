package com.hiram.curiousapps.fsretrofit;

import java.util.List;

/**
 * Created by Hiram on 16/04/2017.
 */

public class Custom {

    String name;
    int edad;
    String profesion;
    List<CustomLenguaje> lenguajes;

    public Custom() {
    }

    public String getName() {
        return name;
    }

    public int getEdad() {
        return edad;
    }

    public String getProfesion() {
        return profesion;
    }

    public List<CustomLenguaje> getLenguajes() {
        return lenguajes;
    }
}
