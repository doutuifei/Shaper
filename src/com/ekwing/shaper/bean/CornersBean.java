package com.ekwing.shaper.bean;

import com.ekwing.shaper.Constants;
import com.ekwing.shaper.interfaces.SimpleName;
import com.ekwing.shaper.utils.Utils;

public class CornersBean implements SimpleName {

    public static final String NAME = Constants.CORNERS;

    public String radius;

    public String topLeftRadius;

    public String topRightRadius;

    public String bottomLeftRadius;

    public String bottomRightRadius;

    @Override
    public String toString() {
        return "CornersBean{" +
                "radius=" + radius +
                ", topLeftRadius=" + topLeftRadius +
                ", topRightRadius=" + topRightRadius +
                ", bottomLeftRadius=" + bottomLeftRadius +
                ", bottomRightRadius=" + bottomRightRadius +
                '}';
    }

    @Override
    public String getSimpleName() {
        if (radius != null) {
            return "crn" + Constants.SEPARATOR + Utils.getDimensValue("rds", radius);
        }
        StringBuilder builder = new StringBuilder();
        builder.append("crn");
        if (topLeftRadius != null) {
            builder.append(Constants.SEPARATOR + Utils.getDimensValue("tl", topLeftRadius));
        }
        if (topRightRadius != null) {
            builder.append(Constants.SEPARATOR + Utils.getDimensValue("tr", topRightRadius));
        }
        if (bottomLeftRadius != null) {
            builder.append(Constants.SEPARATOR + Utils.getDimensValue("bl", bottomLeftRadius));
        }
        if (bottomRightRadius != null) {
            builder.append(Constants.SEPARATOR + Utils.getDimensValue("br", bottomRightRadius));
        }
        return builder.toString();
    }

}
