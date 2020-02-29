package com.ekwing.shaper;

import com.ekwing.shaper.bean.ShapeBean;
import com.ekwing.shaper.parse.ShapeDomParse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        String xmlPath = "E:\\GradleProjects\\Shaper\\shape.xml";
        FileInputStream inputStream = new FileInputStream(xmlPath);
        ShapeBean shapeBean = new ShapeDomParse().parse(inputStream);
        System.out.println(shapeBean.toString());
        System.out.println(shapeBean.getSimpleName());

        System.out.println(shapeBean.corners.getSimpleName());
        System.out.println(shapeBean.padding.getSimpleName());
        System.out.println(shapeBean.size.getSimpleName());
        System.out.println(shapeBean.solid.getSimpleName());
        System.out.println(shapeBean.stroke.getSimpleName());
    }

}