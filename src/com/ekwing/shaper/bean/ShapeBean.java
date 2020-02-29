package com.ekwing.shaper.bean;

import com.ekwing.shaper.Constants;
import com.ekwing.shaper.interfaces.SimpleName;

public class ShapeBean implements SimpleName {

    public static final String NAME = Constants.SHAPE;

    public String shape = Constants.DEFAULT_SHAPE;

    public SolidBean solid;

    public StrokeBean stroke;

    public SizeBean size;

    public CornersBean corners;

    public PaddingBean padding;

    @Override
    public String getSimpleName() {
        StringBuilder builder = new StringBuilder();
        if (shape.equals(Constants.DEFAULT_SHAPE)) {
            builder.append("rec");
        } else {
            builder.append(shape);
        }
        if (solid != null) {
            builder.append(Constants.SEPARATOR);
            builder.append(solid.getSimpleName());
        }
        if (stroke != null) {
            builder.append(Constants.SEPARATOR);
            builder.append(stroke.getSimpleName());
        }
        if (corners != null) {
            builder.append(Constants.SEPARATOR);
            builder.append(corners.getSimpleName());
        }
        if (padding != null) {
            builder.append(Constants.SEPARATOR);
            builder.append(padding.getSimpleName());
        }
        if (size != null) {
            builder.append(Constants.SEPARATOR);
            builder.append(size.getSimpleName());
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return "ShapeBean{" +
                shape == null ? ", shape= null" : "shape=" + shape +
                (solid == null ? ", solid= null" : ", solid=" + solid.toString()) +
                (stroke == null ? ", stroke= null" : ", stroke=" + stroke.toString()) +
                (size == null ? ", size= null" : ", size=" + size.toString()) +
                (corners == null ? ", corners= null" : ", corners=" + corners.toString()) +
                (padding == null ? ", padding= null" : ", padding=" + padding.toString()) +
                '}';
    }
}
