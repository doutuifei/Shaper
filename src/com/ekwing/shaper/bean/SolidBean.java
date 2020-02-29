package com.ekwing.shaper.bean;

import com.ekwing.shaper.Constants;
import com.ekwing.shaper.interfaces.SimpleName;
import com.ekwing.shaper.utils.Utils;

public class SolidBean implements SimpleName {

    public static final String NAME = Constants.SOLID;

    public String color;

    @Override
    public String toString() {
        return "SolidBean{" +
                "color='" + color + '\'' +
                '}';
    }

    @Override
    public String getSimpleName() {
        return "sol" + Constants.SEPARATOR + Utils.getColor("clr", color);
    }

}