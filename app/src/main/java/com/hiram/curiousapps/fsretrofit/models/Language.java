package com.hiram.curiousapps.fsretrofit.models;

import java.util.Arrays;

/**
 * Created by Hiram on 16/04/2017.
 */

public class Language {

    String name;
    String creator;
    int released;
    String[] frameworks;

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

    public String[] getFrameworks() {
        return frameworks;
    }

    public String allData() {
        return "Name: " + name + ", Creator: " + creator + ", Realeased: " + released + ", Frameworks: " + Arrays.toString(frameworks);
    }
}
