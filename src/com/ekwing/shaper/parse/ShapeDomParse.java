package com.ekwing.shaper.parse;

import com.ekwing.shaper.Constants;
import com.ekwing.shaper.bean.*;
import com.ekwing.shaper.interfaces.ShapeParse;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.lang.reflect.Field;

public class ShapeDomParse implements ShapeParse {

    @Override
    public ShapeBean parse(InputStream inputStream) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            return create(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ShapeBean create(Document document) {
        //如果根节点不是shape就过滤
        Element documentElement = document.getDocumentElement();
        String rootTagName = documentElement.getTagName();
        if (!ShapeBean.NAME.equals(rootTagName)) {
            return null;
        }

        //开始解析shape
        NodeList shapeNodeList = document.getElementsByTagName(ShapeBean.NAME);
        if (shapeNodeList == null || shapeNodeList.getLength() < 1) {
            return null;
        }
        ShapeBean shapeBean = new ShapeBean();
        for (int i = 0; i < shapeNodeList.getLength(); i++) {
            Node shapeNode = shapeNodeList.item(i);

            //解析shape属性
            if (shapeNode.getNodeName().equals(ShapeBean.NAME)) {
                forFiled(shapeBean, shapeNode.getAttributes());
            }

            //遍历ChildNode
            NodeList childNodeList = shapeNode.getChildNodes();
            for (int j = 0; j < childNodeList.getLength(); j++) {
                Node childNode = childNodeList.item(j);
                NamedNodeMap childNodeAttributes = childNode.getAttributes();
                String childNoteName = childNode.getNodeName();
                Class<?> aClass = Constants.MAP.get(childNoteName);
                if (aClass == null) {
                    continue;
                }
                Object o = null;
                try {
                    o = aClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        o = aClass.getDeclaredConstructor().newInstance();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if (o == null) {
                    continue;
                }
                try {
                    Field field = ShapeBean.class.getDeclaredField(childNoteName);
                    setValue(shapeBean, field, o);
                    forFiled(o, childNodeAttributes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return shapeBean;
    }

    private Object forFiled(Object o, NamedNodeMap map) {
        Field[] fields = o.getClass().getFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (fieldName.equals(Constants.FIELD_NAME)) {
                continue;
            }
            String attributeName = Constants.ANDROID_PREFIX + fieldName;
            Node node = map.getNamedItem(attributeName);
            if (node == null) {
                continue;
            }
            String value = node.getNodeValue();
            setValue(o, field, value);
        }
        return o;
    }

    private void setValue(Object o, Field field, Object value) {
        try {
            field.set(o, value);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                field.setAccessible(true);
                field.set(o, value);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}