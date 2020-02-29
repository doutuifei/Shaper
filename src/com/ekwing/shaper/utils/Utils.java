package com.ekwing.shaper.utils;

import com.ekwing.shaper.Constants;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static Dimension getDimension() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static int getDialogWidth() {
        Dimension dimension = getDimension();
        if (dimension == null) {
            return 400;
        }
        return dimension.width / 4;
    }

    public static int getDialogHeight() {
        Dimension dimension = getDimension();
        if (dimension == null) {
            return 450;
        }
        return dimension.height / 2;
    }

    /**
     * 设置对话框的位置(设置居中)
     *
     * @return
     */
    public static Point getDialogCenterLocation() {
        Dimension dimension = getDimension();
        Point point = new Point(0, 0);
        if (dimension == null) {
            return point;
        }
        /**
         * dimension.width / 4 即对话框的宽度
         * dimension.height / 3 i对话框的高度
         */
        point.x = (dimension.width - getDialogWidth()) >> 1;
        point.y = (dimension.height - getDialogHeight()) >> 1;
        return point;
    }

    /**
     * 提取数字
     *
     * @param prefix
     * @param s
     * @return
     */
    public static String getDimensValue(String prefix, String s) {
        String value = null;
        if (s.startsWith(Constants.PREFIX_DIMENS_DP)) {
            value = s.substring(Constants.PREFIX_DIMENS_DP.length());
        } else if (s.startsWith(Constants.PREFIX_DIMENS_SP)) {
            value = s.substring(Constants.PREFIX_DIMENS_SP.length());
        } else if (s.endsWith(Constants.SUFFIX_DP) ||
                s.endsWith(Constants.SUFFIX_DIP) ||
                s.endsWith(Constants.SUFFIX_SP)) {
            Pattern pattern = Pattern.compile("[0-9|.]");
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                value = matcher.group();
            }
        }
        if (value != null) {
            return prefix + value;
        }
        return null;
    }

    /**
     * 提取Color
     *
     * @param prefix
     * @param s      支持 #ffffff、@color/color_ffffff、@color/white
     * @return ffffff、ffffff、white
     */
    public static String getColor(String prefix, String s) {
        String value = null;
        if (s.startsWith(Constants.PREFIX_COLOR_STRING)) {
            //#ffffff
            value = s.substring(Constants.PREFIX_COLOR_STRING.length());
        } else if (s.startsWith(Constants.PREFIX_COLOR_EXT)) {
            //@color/color_ffffff
            value = s.substring(Constants.PREFIX_COLOR_EXT.length());
        } else if (s.startsWith(Constants.PREFIX_COLOR)) {
            //@color/white
            value = s.substring(Constants.PREFIX_COLOR.length());
        }
        if (value != null) {
            return prefix + value;
        }
        return null;
    }

    public static boolean isEmpty(String text) {
        if (text == null || text.length() < 1) {
            return true;
        }
        return false;
    }

}