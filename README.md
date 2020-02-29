# 自动重名命名Shape文件

> 只解析简单的shape文件(根节点为shape)，支持单文件、多文件、文件夹重命名


* [自动重名命名Shape文件](#自动重名命名shape文件)
  * [一.下载安装](#一下载安装)
  * [二.使用](#二使用)
  * [三.样式格式](#三样式格式)
    * [1.$shape](#1shape)
    * [2.$solid](#2solid)
    * [3.$stroke](#3stroke)
    * [4.$corners](#4corners)
    * [5.$padding](#5padding)
    * [6.$size](#6size)
  * [四.属性格式](#四属性格式)
    * [1.color](#1color)
    * [2.dimens](#2dimens)
  * [五.示例](#五示例)
  * [六.更新日志](#六更新日志)
    * [0.0.3](#003)
    * [0.0.2](#002)
    * [0.0.1](#001)


## 一.下载安装

1. 下载[插件Shaper.jar](http://gitlab.ekwing.com/mird/Android/library/shaper/blob/master/jar)

2. File -> Settings -> Plugins -> Install Plugin from Disk... -> 重启IDEA

## 二.使用

右击 ->  Refactor -> Shaper -> 输入前缀名称，默认为shape -> OK

> 前缀名要符合Android命名规范，当前没有对输入内容做正则检查

## 三.样式格式

> $shape_$solid_$stroke_$corners_$padding_$size，多个标签和标签内属性使用'_'连接。

### 1.$shape 

shape标签下的属性，目前只支持以下属性

* rectangle : 简写为 'rec'，命名格式为：'shape_rec'
* oval : 命名格式为：shape_oval
* line : 命名格式为：shape_line
* oval : 命名格式为：shape_ring

### 2.$solid

> 'solid' 简写为'sol'

* color : 简写为 'clr'，命名格式为：'clrffffff'，'clr'后跟着颜色名或者颜色值

> 完整格式 ： sol_clrffffff

### 3.$stroke

> 'stroke'简写为'str'

* width : 简写为```w``` ，命名格式 : 'w2'，表示宽为2

* color同上

> 完整格式 ： str_w1_clr000000

### 4.$corners

> 'corners'简写为'crn'

* radius : 简写为```rds``` ，命名格式 : 'rds5'，表示半径为5

* topLeftRadius : 简写为```tl``` ，命名格式 : 'tl5'，表示半径为5

* topRightRadius : 简写为```tr``` ，命名格式 : 'tr5'，表示半径为5

* bottomLeftRadius : 简写为```bl``` ，命名格式 : 'bl5'，表示半径为5

* bottomRightRadius : 简写为```br``` ，命名格式 : 'br5'，表示半径为5

> 完整格式 ： crn_rds5 / crn_tl5_tr5_bl5_br5，优先判断```radius```

### 5.$padding

> 'padding'简写为'pad'

* top : 简写为```t``` ，命名格式 : 't5'，表示paddingTop为5

* bottom : 简写为```b``` ，命名格式 : 'b5'，表示paddingBottom为5

* left : 简写为```l``` ，命名格式 : 'l5'，表示paddingLeft为5

* right : 简写为```r``` ，命名格式 : 'r5'，表示paddingRight为5

> 完整格式 ：pad_t5_b5_l5_r5

### 6.$size

* width : 简写为```w``` ，命名格式 : 'w2'，表示width为2

* height : 简写为```h``` ，命名格式 : 'h2'，表示height为2

> 完整格式 ：size_w2_h2



## 四.属性格式

### 1.color

> color属性目前支持以下几种格式

1. #ffffff : 解析为ffffff

2. @color/color_ffffff : 解析为ffffff

3. @color/common_white : 解析为common_white

### 2.dimens

> dimens属性目前支持以下几种格式

1. 5dp / 5sp / 5dip : 解析为5

2. @dimen/dp_5 / @dimen/sp_5 : 解析为5

3.  @dimen/common_margin_top : 解析为common_margin_top

## 五.示例

shape.xml内容如下，文件命名：**shape_rec_sol_clrffffff_str_w1_clr000000_crn_tl5_tr5_bl5_br5_pad_t2_b2_l2_r2_size_w2_h2.xml**

```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">

    <solid android:color="#ffffff" />

    <stroke
        android:width="1dp"
        android:color="#000000" />

    <size
        android:width="20dp"
        android:height="20dp" />

    <corners
        android:topLeftRadius="5dp"
        android:topRightRadius="5dp"
        android:bottomLeftRadius="5dp"
        android:bottomRightRadius="5dp" />


    <padding
        android:top="2dp"
        android:bottom="2dp"
        android:left="2dp"
        android:right="2dp" />

</shape>
```

## 六.更新日志

### [0.0.3](/jar/Shaper_0.0.3.jar)
1. 支持自定义前缀

### [0.0.2](/jar/Shaper_0.0.2.jar)
1. 支持多文件、文件夹
2. 过滤根节点非shape的资源

### [0.0.1](/jar/Shaper_0.0.1.jar)
1. 重命名shape文件，支持单文件
