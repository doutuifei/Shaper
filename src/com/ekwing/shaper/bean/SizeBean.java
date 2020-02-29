package com.ekwing.shaper.bean;

import com.ekwing.shaper.Constants;
import com.ekwing.shaper.interfaces.SimpleName;
import com.ekwing.shaper.utils.Utils;

public class SizeBean implements SimpleName {

    public static final String NAME = Constants.SIZE;

    public String width;

    public String height;

    @Override
    public String toString() {
        return "SizeBean{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public String getSimpleName() {
        StringBuilder builder = new StringBuilder();
        builder.append("size");
        if (width != null) {
            builder.append(Constants.SEPARATOR + Utils.getDimensValue("w", width));
        }
        if (height != null) {
            builder.append(Constants.SEPARATOR + Utils.getDimensValue("h", height));
        }
        return builder.toString();
    }
}
