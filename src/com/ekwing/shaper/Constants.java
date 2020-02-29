package com.ekwing.shaper;

import com.ekwing.shaper.bean.*;
import com.ekwing.shaper.enums.Shape;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    /**
     * 默认前缀名称
     */
    public static final String PREFIX = "shape";

    public static final String XML = "xml";

    public static final String POINT = ".";

    public static final String SEPARATOR = "_";

    /**
     * 默认shape属性
     */
    public static final String DEFAULT_SHAPE = Shape.rectangle.toString();

    public static final String FIELD_NAME = "NAME";

    public static final String ANDROID_PREFIX = "android:";

    public static final String SHAPE = "shape";

    public static final String CORNERS = "corners";

    public static final String PADDING = "padding";

    public static final String SIZE = "size";

    public static final String SOLID = "solid";

    public static final String STROKE = "stroke";

    public static final String PREFIX_DIMENS_DP = "@dimen/dp_";

    public static final String PREFIX_DIMENS_SP = "@dimen/sp_";

    public static final String SUFFIX_DP = "dp";

    public static final String SUFFIX_DIP = "dip";

    public static final String SUFFIX_SP = "sp";

    public static final String PREFIX_COLOR_STRING = "#";

    public static final String PREFIX_COLOR = "@color/";

    public static final String PREFIX_COLOR_EXT = PREFIX_COLOR + "color_";

    public static final Map<String, Class<?>> MAP = new HashMap<>();

    static {
        MAP.put(SolidBean.NAME, SolidBean.class);
        MAP.put(StrokeBean.NAME, StrokeBean.class);
        MAP.put(SizeBean.NAME, SizeBean.class);
        MAP.put(CornersBean.NAME, CornersBean.class);
        MAP.put(PaddingBean.NAME, PaddingBean.class);
    }

}
