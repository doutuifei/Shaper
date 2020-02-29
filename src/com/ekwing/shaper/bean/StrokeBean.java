package com.ekwing.shaper.bean;

import com.ekwing.shaper.Constants;
import com.ekwing.shaper.interfaces.SimpleName;
import com.ekwing.shaper.utils.Utils;

public class StrokeBean implements SimpleName {

    public static final String NAME = Constants.STROKE;

    public String width;

    public String color;

    @Override
    public String toString() {
        return "StrokeBean{" +
                "width=" + width +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public String getSimpleName() {
        return "str" + Constants.SEPARATOR + Utils.getDimensValue("w", width) + Constants.SEPARATOR + Utils.getColor("clr", color);
    }
}
