package com.ekwing.shaper.bean;

import com.ekwing.shaper.Constants;
import com.ekwing.shaper.interfaces.SimpleName;
import com.ekwing.shaper.utils.Utils;

public class PaddingBean implements SimpleName {

    public static final String NAME = Constants.PADDING;

    public String top;

    public String bottom;

    public String left;

    public String right;

    @Override
    public String toString() {
        return "PaddingBean{" +
                "top=" + top +
                ", bottom=" + bottom +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public String getSimpleName() {
        StringBuilder builder = new StringBuilder();
        builder.append("pad");
        if (top != null) {
            builder.append(Constants.SEPARATOR + Utils.getDimensValue("t", top));
        }
        if (bottom != null) {
            builder.append(Constants.SEPARATOR + Utils.getDimensValue("b", bottom));
        }
        if (left != null) {
            builder.append(Constants.SEPARATOR + Utils.getDimensValue("l", left));
        }
        if (right != null) {
            builder.append(Constants.SEPARATOR + Utils.getDimensValue("r", right));
        }
        return builder.toString();
    }

}