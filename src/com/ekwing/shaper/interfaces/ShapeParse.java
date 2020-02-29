package com.ekwing.shaper.interfaces;

import com.ekwing.shaper.bean.ShapeBean;

import java.io.InputStream;

public interface ShapeParse {

    ShapeBean parse(InputStream inputStream);

}
