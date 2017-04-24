package com.hiram.curiousapps.fsretrofit.modelos;

import java.util.List;

/**
 * Created by Hiram on 16/04/2017.
 */

public class Language {

    String name;
    String creator;
    int released;
    List<Framework> frameworks;

    public Language() {
    }

    public String getName() {
        return name;
    }

    public String getCreator() {
        return creator;
    }

    public int getReleased() {
        return released;
    }

    public List<Framework> getFrameworks() {
        return frameworks;
    }
}
